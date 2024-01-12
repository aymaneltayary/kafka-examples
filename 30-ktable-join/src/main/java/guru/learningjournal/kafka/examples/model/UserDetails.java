
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "userName",
    "loginId",
    "lastLogin"
})
@Generated("jsonschema2pojo")
public class UserDetails {

    @JsonProperty("userName")
    private String userName;
    @JsonProperty("loginId")
    private String loginId;
    @JsonProperty("lastLogin")
    private String lastLogin;

    @JsonProperty("userName")
    public String getUserName() {
        return userName;
    }

    @JsonProperty("userName")
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserDetails withUserName(String userName) {
        this.userName = userName;
        return this;
    }

    @JsonProperty("loginId")
    public String getLoginId() {
        return loginId;
    }

    @JsonProperty("loginId")
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public UserDetails withLoginId(String loginId) {
        this.loginId = loginId;
        return this;
    }

    @JsonProperty("lastLogin")
    public String getLastLogin() {
        return lastLogin;
    }

    @JsonProperty("lastLogin")
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public UserDetails withLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserDetails.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("userName");
        sb.append('=');
        sb.append(((this.userName == null)?"<null>":this.userName));
        sb.append(',');
        sb.append("loginId");
        sb.append('=');
        sb.append(((this.loginId == null)?"<null>":this.loginId));
        sb.append(',');
        sb.append("lastLogin");
        sb.append('=');
        sb.append(((this.lastLogin == null)?"<null>":this.lastLogin));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
