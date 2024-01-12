
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "customerCardNo",
    "totalAmount",
    "earnedLoyaltyPoints",
    "totalLoyaltyPoints"
})
@Generated("jsonschema2pojo")
public class Notification {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("customerCardNo")
    private String customerCardNo;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("earnedLoyaltyPoints")
    private Double earnedLoyaltyPoints;
    @JsonProperty("totalLoyaltyPoints")
    private Double totalLoyaltyPoints;

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Notification withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    @JsonProperty("customerCardNo")
    public String getCustomerCardNo() {
        return customerCardNo;
    }

    @JsonProperty("customerCardNo")
    public void setCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
    }

    public Notification withCustomerCardNo(String customerCardNo) {
        this.customerCardNo = customerCardNo;
        return this;
    }

    @JsonProperty("totalAmount")
    public Double getTotalAmount() {
        return totalAmount;
    }

    @JsonProperty("totalAmount")
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Notification withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    @JsonProperty("earnedLoyaltyPoints")
    public Double getEarnedLoyaltyPoints() {
        return earnedLoyaltyPoints;
    }

    @JsonProperty("earnedLoyaltyPoints")
    public void setEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyaltyPoints = earnedLoyaltyPoints;
    }

    public Notification withEarnedLoyaltyPoints(Double earnedLoyaltyPoints) {
        this.earnedLoyaltyPoints = earnedLoyaltyPoints;
        return this;
    }

    @JsonProperty("totalLoyaltyPoints")
    public Double getTotalLoyaltyPoints() {
        return totalLoyaltyPoints;
    }

    @JsonProperty("totalLoyaltyPoints")
    public void setTotalLoyaltyPoints(Double totalLoyaltyPoints) {
        this.totalLoyaltyPoints = totalLoyaltyPoints;
    }

    public Notification withTotalLoyaltyPoints(Double totalLoyaltyPoints) {
        this.totalLoyaltyPoints = totalLoyaltyPoints;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Notification.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("customerCardNo");
        sb.append('=');
        sb.append(((this.customerCardNo == null)?"<null>":this.customerCardNo));
        sb.append(',');
        sb.append("totalAmount");
        sb.append('=');
        sb.append(((this.totalAmount == null)?"<null>":this.totalAmount));
        sb.append(',');
        sb.append("earnedLoyaltyPoints");
        sb.append('=');
        sb.append(((this.earnedLoyaltyPoints == null)?"<null>":this.earnedLoyaltyPoints));
        sb.append(',');
        sb.append("totalLoyaltyPoints");
        sb.append('=');
        sb.append(((this.totalLoyaltyPoints == null)?"<null>":this.totalLoyaltyPoints));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
