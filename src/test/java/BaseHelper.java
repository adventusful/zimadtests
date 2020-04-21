
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.input.KeyCodeCombinationBuilder;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertNotNull;


public class BaseHelper {
    private OkHttpClient client = new OkHttpClient();

    org.apache.log4j.Logger logger = Logger.getLogger(BaseHelper.class);

    String BASE_URL = "https://api.todoist.com/rest/v1";

    public BaseHelper() {
    }

    ObjectMapper mapper = new ObjectMapper();

    public RequestBody getCommonBody(String name) {
        RequestBody body = null;
        InputStream in = this.getClass().getClassLoader()
                .getResourceAsStream(name);
        try {
            body = RequestBody.create(MediaType.parse("application/json"),
                            mapper.writeValueAsBytes(mapper.readTree(in)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    private Request baseRequest(RequestBody body) {
        return new Request.Builder()
                .url(BASE_URL + "/tasks")
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization","Bearer 2274cf876f5979b27e2ad6030336d14cb2ed1ac7")
                .post(body)
                .build();
    }

    public Response createCommonPostRequest() {
        Request request = baseRequest(getCommonBody("common.json"));
        Call call = client.newCall(request);
        logger.info(String.format("Sending request %s on %s",
                request.url(), request.headers()));
        return getResponse(executeCall(call));
    }

    private Response getResponse(Response executeCall) {
        logger.info(String.format("Received response for %s with code %s and %s",
                executeCall.request().url(), executeCall.code(), executeCall.headers()));
        return executeCall;
    }

    public void verifyCommonPostRequest(Response commonPostRequest) {
        try {
            String body = commonPostRequest.body().string();
            assertThat(commonPostRequest.code(), equalTo(200));
            TaskBodyPojo entity = mapper.readValue(body, TaskBodyPojo.class);
            assertThat(entity,is(notNullValue()));
            assertThat(getDate(((LinkedHashMap) entity.getDue()).get("datetime").toString()), is(notNullValue()));
            logger.info(String.format("Received body %s", body));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Date getDate (String date) {
        Date result = null;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            result =  df.parse(date);
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public Response createRequestWithEmptyContent() {
        Request request = baseRequest(getCommonBody("emptycontent.json"));
        Call call = client.newCall(request);
        logger.info(String.format("Sending request %s on %s",
                request.url(), request.headers()));
        return getResponse(executeCall(call));
    }

    public Response createRequestWithEmptyBody() {
        Request request = baseRequest(getCommonBody("emptybody.json"));
        Call call = client.newCall(request);
        logger.info(String.format("Sending request %s on %s",
                request.url(), request.headers()));
        return getResponse(executeCall(call));
    }


    private Response executeCall(Call call) {
        Response response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public Response createRequestWithBigPriority() {
        Request request = baseRequest(getCommonBody("bigpriority.json"));
        Call call = client.newCall(request);
        logger.info(String.format("Sending request %s on %s",
                request.url(), request.headers()));
        return getResponse(executeCall(call));
    }

    public void verifyHumanTimeToDate(Response commonPostRequest) {
        try {
            TaskBodyPojo entity = mapper.readValue(commonPostRequest.body().string(), TaskBodyPojo.class);
            assertThat("Time not set correct", getDate(((LinkedHashMap) entity.getDue()).get("datetime").toString()).after(Calendar.getInstance().getTime()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
