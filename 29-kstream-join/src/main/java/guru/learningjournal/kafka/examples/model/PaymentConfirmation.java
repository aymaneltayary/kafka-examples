
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "transactionId",
    "createdTime",
    "otp"
})
@Generated("jsonschema2pojo")
public class PaymentConfirmation {

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("otp")
    private String otp;

    @JsonProperty("transactionId")
    public String getTransactionId() {
        return transactionId;
    }

    @JsonProperty("transactionId")
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentConfirmation withTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public PaymentConfirmation withCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("otp")
    public String getOtp() {
        return otp;
    }

    @JsonProperty("otp")
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public PaymentConfirmation withOtp(String otp) {
        this.otp = otp;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PaymentConfirmation.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("transactionId");
        sb.append('=');
        sb.append(((this.transactionId == null)?"<null>":this.transactionId));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("otp");
        sb.append('=');
        sb.append(((this.otp == null)?"<null>":this.otp));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
