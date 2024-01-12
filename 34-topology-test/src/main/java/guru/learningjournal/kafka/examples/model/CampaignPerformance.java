
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "Campaigner",
    "impressionCount",
    "clickCount"
})
@Generated("jsonschema2pojo")
public class CampaignPerformance {

    @JsonProperty("Campaigner")
    private String campaigner;
    @JsonProperty("impressionCount")
    private Double impressionCount;
    @JsonProperty("clickCount")
    private Double clickCount;

    @JsonProperty("Campaigner")
    public String getCampaigner() {
        return campaigner;
    }

    @JsonProperty("Campaigner")
    public void setCampaigner(String campaigner) {
        this.campaigner = campaigner;
    }

    public CampaignPerformance withCampaigner(String campaigner) {
        this.campaigner = campaigner;
        return this;
    }

    @JsonProperty("impressionCount")
    public Double getImpressionCount() {
        return impressionCount;
    }

    @JsonProperty("impressionCount")
    public void setImpressionCount(Double impressionCount) {
        this.impressionCount = impressionCount;
    }

    public CampaignPerformance withImpressionCount(Double impressionCount) {
        this.impressionCount = impressionCount;
        return this;
    }

    @JsonProperty("clickCount")
    public Double getClickCount() {
        return clickCount;
    }

    @JsonProperty("clickCount")
    public void setClickCount(Double clickCount) {
        this.clickCount = clickCount;
    }

    public CampaignPerformance withClickCount(Double clickCount) {
        this.clickCount = clickCount;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CampaignPerformance.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("campaigner");
        sb.append('=');
        sb.append(((this.campaigner == null)?"<null>":this.campaigner));
        sb.append(',');
        sb.append("impressionCount");
        sb.append('=');
        sb.append(((this.impressionCount == null)?"<null>":this.impressionCount));
        sb.append(',');
        sb.append("clickCount");
        sb.append('=');
        sb.append(((this.clickCount == null)?"<null>":this.clickCount));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
