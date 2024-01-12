
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "impressionId",
    "campaigner"
})
@Generated("jsonschema2pojo")
public class Impression {

    @JsonProperty("impressionId")
    private String impressionId;
    @JsonProperty("campaigner")
    private String campaigner;

    @JsonProperty("impressionId")
    public String getImpressionId() {
        return impressionId;
    }

    @JsonProperty("impressionId")
    public void setImpressionId(String impressionId) {
        this.impressionId = impressionId;
    }

    public Impression withImpressionId(String impressionId) {
        this.impressionId = impressionId;
        return this;
    }

    @JsonProperty("campaigner")
    public String getCampaigner() {
        return campaigner;
    }

    @JsonProperty("campaigner")
    public void setCampaigner(String campaigner) {
        this.campaigner = campaigner;
    }

    public Impression withCampaigner(String campaigner) {
        this.campaigner = campaigner;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Impression.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("impressionId");
        sb.append('=');
        sb.append(((this.impressionId == null)?"<null>":this.impressionId));
        sb.append(',');
        sb.append("campaigner");
        sb.append('=');
        sb.append(((this.campaigner == null)?"<null>":this.campaigner));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
