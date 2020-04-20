import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jdk.nashorn.internal.objects.annotations.Getter;

import java.math.BigDecimal;
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskBodyPojo {

    public TaskBodyPojo() {}

    String content;
    String due_string;
    String due_lang;
    int priority;
    BigDecimal id;
    BigDecimal project_id;
    int section_id;
    String comment_count;
    String created;
    Object due;
    Boolean recurring;
    String string;
    String date;
    String datetime;
    String timezone;
    String url;

    public String getContent() {
        return content;
    }

    public TaskBodyPojo setContent(String content) {
        this.content = content;
        return this;
    }

    public String getDue_string() {
        return due_string;
    }

    public TaskBodyPojo setDue_string(String due_string) {
        this.due_string = due_string;
        return this;
    }

    public String getDue_lang() {
        return due_lang;
    }

    public TaskBodyPojo setDue_lang(String due_lang) {
        this.due_lang = due_lang;
        return this;
    }

    public int getPriority() {
        return priority;
    }

    public TaskBodyPojo setPriority(int priority) {
        this.priority = priority;
        return this;
    }

    public Object getDue() {
        return due;
    }

    public void setDue(Object due) {
        this.due = due;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
