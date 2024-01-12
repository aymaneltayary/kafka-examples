
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userId",
    "createdTime",
    "currentLink",
    "nextLink"
})
@Generated("jsonschema2pojo")
public class UserClick {

    @JsonProperty("userId")
    private String userId;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("currentLink")
    private String currentLink;
    @JsonProperty("nextLink")
    private String nextLink;

    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }

    @JsonProperty("userId")
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserClick withUserId(String userId) {
        this.userId = userId;
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

    public UserClick withCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("currentLink")
    public String getCurrentLink() {
        return currentLink;
    }

    @JsonProperty("currentLink")
    public void setCurrentLink(String currentLink) {
        this.currentLink = currentLink;
    }

    public UserClick withCurrentLink(String currentLink) {
        this.currentLink = currentLink;
        return this;
    }

    @JsonProperty("nextLink")
    public String getNextLink() {
        return nextLink;
    }

    @JsonProperty("nextLink")
    public void setNextLink(String nextLink) {
        this.nextLink = nextLink;
    }

    public UserClick withNextLink(String nextLink) {
        this.nextLink = nextLink;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserClick.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userId");
        sb.append('=');
        sb.append(((this.userId == null)?"<null>":this.userId));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("currentLink");
        sb.append('=');
        sb.append(((this.currentLink == null)?"<null>":this.currentLink));
        sb.append(',');
        sb.append("nextLink");
        sb.append('=');
        sb.append(((this.nextLink == null)?"<null>":this.nextLink));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
