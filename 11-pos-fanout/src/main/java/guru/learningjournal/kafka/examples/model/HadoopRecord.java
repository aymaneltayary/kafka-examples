
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "createdTime",
    "storeID",
    "posId",
    "customerType",
    "paymentMethod",
    "deliveryType",
    "city",
    "state",
    "pinCode",
    "itemCode",
    "itemDescription",
    "itemPrice",
    "itemQty",
    "totalValue"
})
@Generated("jsonschema2pojo")
public class HadoopRecord {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("createdTime")
    private Object createdTime;
    @JsonProperty("storeID")
    private String storeID;
    @JsonProperty("posId")
    private String posId;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("city")
    private String city;
    @JsonProperty("state")
    private String state;
    @JsonProperty("pinCode")
    private String pinCode;
    @JsonProperty("itemCode")
    private String itemCode;
    @JsonProperty("itemDescription")
    private String itemDescription;
    @JsonProperty("itemPrice")
    private Double itemPrice;
    @JsonProperty("itemQty")
    private Integer itemQty;
    @JsonProperty("totalValue")
    private Double totalValue;

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public HadoopRecord withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
        return this;
    }

    @JsonProperty("createdTime")
    public Object getCreatedTime() {
        return createdTime;
    }

    @JsonProperty("createdTime")
    public void setCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
    }

    public HadoopRecord withCreatedTime(Object createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("storeID")
    public String getStoreID() {
        return storeID;
    }

    @JsonProperty("storeID")
    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public HadoopRecord withStoreID(String storeID) {
        this.storeID = storeID;
        return this;
    }

    @JsonProperty("posId")
    public String getPosId() {
        return posId;
    }

    @JsonProperty("posId")
    public void setPosId(String posId) {
        this.posId = posId;
    }

    public HadoopRecord withPosId(String posId) {
        this.posId = posId;
        return this;
    }

    @JsonProperty("customerType")
    public String getCustomerType() {
        return customerType;
    }

    @JsonProperty("customerType")
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public HadoopRecord withCustomerType(String customerType) {
        this.customerType = customerType;
        return this;
    }

    @JsonProperty("paymentMethod")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    @JsonProperty("paymentMethod")
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public HadoopRecord withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @JsonProperty("deliveryType")
    public String getDeliveryType() {
        return deliveryType;
    }

    @JsonProperty("deliveryType")
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public HadoopRecord withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    @JsonProperty("city")
    public String getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(String city) {
        this.city = city;
    }

    public HadoopRecord withCity(String city) {
        this.city = city;
        return this;
    }

    @JsonProperty("state")
    public String getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    public HadoopRecord withState(String state) {
        this.state = state;
        return this;
    }

    @JsonProperty("pinCode")
    public String getPinCode() {
        return pinCode;
    }

    @JsonProperty("pinCode")
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public HadoopRecord withPinCode(String pinCode) {
        this.pinCode = pinCode;
        return this;
    }

    @JsonProperty("itemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("itemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public HadoopRecord withItemCode(String itemCode) {
        this.itemCode = itemCode;
        return this;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public HadoopRecord withItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
        return this;
    }

    @JsonProperty("itemPrice")
    public Double getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("itemPrice")
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public HadoopRecord withItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
        return this;
    }

    @JsonProperty("itemQty")
    public Integer getItemQty() {
        return itemQty;
    }

    @JsonProperty("itemQty")
    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    public HadoopRecord withItemQty(Integer itemQty) {
        this.itemQty = itemQty;
        return this;
    }

    @JsonProperty("totalValue")
    public Double getTotalValue() {
        return totalValue;
    }

    @JsonProperty("totalValue")
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public HadoopRecord withTotalValue(Double totalValue) {
        this.totalValue = totalValue;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(HadoopRecord.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("storeID");
        sb.append('=');
        sb.append(((this.storeID == null)?"<null>":this.storeID));
        sb.append(',');
        sb.append("posId");
        sb.append('=');
        sb.append(((this.posId == null)?"<null>":this.posId));
        sb.append(',');
        sb.append("customerType");
        sb.append('=');
        sb.append(((this.customerType == null)?"<null>":this.customerType));
        sb.append(',');
        sb.append("paymentMethod");
        sb.append('=');
        sb.append(((this.paymentMethod == null)?"<null>":this.paymentMethod));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("city");
        sb.append('=');
        sb.append(((this.city == null)?"<null>":this.city));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("pinCode");
        sb.append('=');
        sb.append(((this.pinCode == null)?"<null>":this.pinCode));
        sb.append(',');
        sb.append("itemCode");
        sb.append('=');
        sb.append(((this.itemCode == null)?"<null>":this.itemCode));
        sb.append(',');
        sb.append("itemDescription");
        sb.append('=');
        sb.append(((this.itemDescription == null)?"<null>":this.itemDescription));
        sb.append(',');
        sb.append("itemPrice");
        sb.append('=');
        sb.append(((this.itemPrice == null)?"<null>":this.itemPrice));
        sb.append(',');
        sb.append("itemQty");
        sb.append('=');
        sb.append(((this.itemQty == null)?"<null>":this.itemQty));
        sb.append(',');
        sb.append("totalValue");
        sb.append('=');
        sb.append(((this.totalValue == null)?"<null>":this.totalValue));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
