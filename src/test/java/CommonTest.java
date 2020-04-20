import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CommonTest extends BaseTest{

    @Test(description = "Check status of request to /v1/tasks")
    public void testCommonCreateTasks() {
        baseHelper.verifyCommonPostRequest(baseHelper.createCommonPostRequest());
    }

    @Test(description = "Check error state and status of /tasks request")
    public void testErrorStateCreateTasks() {
        assertThat(baseHelper.createRequestWithEmptyContent().code(), equalTo(400));
        assertThat(baseHelper.createRequestWithEmptyBody().code(), equalTo(400));
        assertThat(baseHelper.createRequestWithBigPriority().code(), equalTo(400));
    }

    @Test(description = "Check ability to convert human string to date")
    public void testCheckHumanTimeToDate() {
        baseHelper.verifyHumanTimeToDate(baseHelper.createCommonPostRequest());
    }


}
