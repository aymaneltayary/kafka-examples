
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "transactionId",
    "createdTime",
    "sourceAccountId",
    "targetAccountId",
    "amount",
    "otp"
})
@Generated("jsonschema2pojo")
public class PaymentRequest {

    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("sourceAccountId")
    private String sourceAccountId;
    @JsonProperty("targetAccountId")
    private String targetAccountId;
    @JsonProperty("amount")
    private Double amount;
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

    public PaymentRequest withTransactionId(String transactionId) {
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

    public PaymentRequest withCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("sourceAccountId")
    public String getSourceAccountId() {
        return sourceAccountId;
    }

    @JsonProperty("sourceAccountId")
    public void setSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public PaymentRequest withSourceAccountId(String sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
        return this;
    }

    @JsonProperty("targetAccountId")
    public String getTargetAccountId() {
        return targetAccountId;
    }

    @JsonProperty("targetAccountId")
    public void setTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public PaymentRequest withTargetAccountId(String targetAccountId) {
        this.targetAccountId = targetAccountId;
        return this;
    }

    @JsonProperty("amount")
    public Double getAmount() {
        return amount;
    }

    @JsonProperty("amount")
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentRequest withAmount(Double amount) {
        this.amount = amount;
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

    public PaymentRequest withOtp(String otp) {
        this.otp = otp;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PaymentRequest.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("transactionId");
        sb.append('=');
        sb.append(((this.transactionId == null)?"<null>":this.transactionId));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("sourceAccountId");
        sb.append('=');
        sb.append(((this.sourceAccountId == null)?"<null>":this.sourceAccountId));
        sb.append(',');
        sb.append("targetAccountId");
        sb.append('=');
        sb.append(((this.targetAccountId == null)?"<null>":this.targetAccountId));
        sb.append(',');
        sb.append("amount");
        sb.append('=');
        sb.append(((this.amount == null)?"<null>":this.amount));
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
