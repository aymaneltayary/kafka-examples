
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "appID",
    "createdTime"
})
@Generated("jsonschema2pojo")
public class HeartBeat {

    @JsonProperty("appID")
    private String appID;
    @JsonProperty("createdTime")
    private String createdTime;

    @JsonProperty("appID")
    public String getAppID() {
        return appID;
    }

    @JsonProperty("appID")
    public void setAppID(String appID) {
        this.appID = appID;
    }

    public HeartBeat withAppID(String appID) {
        this.appID = appID;
        return this;
    }

    @JsonProperty("createdTime")
    public String getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("createdTime")
    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public HeartBeat withCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HeartBeat.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("appID");
        sb.append('=');
        sb.append(((this.appID == null)?"<null>":this.appID));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
