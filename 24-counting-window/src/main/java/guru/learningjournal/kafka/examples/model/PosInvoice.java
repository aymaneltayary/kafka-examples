
package guru.learningjournal.kafka.examples.model;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "invoiceNumber",
    "createdTime",
    "storeId",
    "posId",
    "cashierId",
    "customerType",
    "customerCardNo",
    "totalAmount",
    "numberOfItems",
    "paymentMethod",
    "taxableAmount",
    "cgst",
    "sgst",
    "cess",
    "deliveryType",
    "deliveryAddress",
    "invoiceLineItems"
})
@Generated("jsonschema2pojo")
public class PosInvoice {

    @JsonProperty("invoiceNumber")
    private String invoiceNumber;
    @JsonProperty("createdTime")
    private String createdTime;
    @JsonProperty("storeId")
    private String storeId;
    @JsonProperty("posId")
    private String posId;
    @JsonProperty("cashierId")
    private String cashierId;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("customerCardNo")
    private String customerCardNo;
    @JsonProperty("totalAmount")
    private Double totalAmount;
    @JsonProperty("numberOfItems")
    private Integer numberOfItems;
    @JsonProperty("paymentMethod")
    private String paymentMethod;
    @JsonProperty("taxableAmount")
    private Double taxableAmount;
    @JsonProperty("cgst")
    private Double cgst;
    @JsonProperty("sgst")
    private Double sgst;
    @JsonProperty("cess")
    private Double cess;
    @JsonProperty("deliveryType")
    private String deliveryType;
    @JsonProperty("deliveryAddress")
    private DeliveryAddress deliveryAddress;
    @JsonProperty("invoiceLineItems")
    private List<LineItem> invoiceLineItems = new ArrayList<LineItem>();

    @JsonProperty("invoiceNumber")
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @JsonProperty("invoiceNumber")
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public PosInvoice withInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public PosInvoice withCreatedTime(String createdTime) {
        this.createdTime = createdTime;
        return this;
    }

    @JsonProperty("storeId")
    public String getStoreId() {
        return storeId;
    }

    @JsonProperty("storeId")
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public PosInvoice withStoreId(String storeId) {
        this.storeId = storeId;
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

    public PosInvoice withPosId(String posId) {
        this.posId = posId;
        return this;
    }

    @JsonProperty("cashierId")
    public String getCashierId() {
        return cashierId;
    }

    @JsonProperty("cashierId")
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public PosInvoice withCashierId(String cashierId) {
        this.cashierId = cashierId;
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

    public PosInvoice withCustomerType(String customerType) {
        this.customerType = customerType;
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

    public PosInvoice withCustomerCardNo(String customerCardNo) {
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

    public PosInvoice withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    @JsonProperty("numberOfItems")
    public Integer getNumberOfItems() {
        return numberOfItems;
    }

    @JsonProperty("numberOfItems")
    public void setNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public PosInvoice withNumberOfItems(Integer numberOfItems) {
        this.numberOfItems = numberOfItems;
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

    public PosInvoice withPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @JsonProperty("taxableAmount")
    public Double getTaxableAmount() {
        return taxableAmount;
    }

    @JsonProperty("taxableAmount")
    public void setTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
    }

    public PosInvoice withTaxableAmount(Double taxableAmount) {
        this.taxableAmount = taxableAmount;
        return this;
    }

    @JsonProperty("cgst")
    public Double getCgst() {
        return cgst;
    }

    @JsonProperty("cgst")
    public void setCgst(Double cgst) {
        this.cgst = cgst;
    }

    public PosInvoice withCgst(Double cgst) {
        this.cgst = cgst;
        return this;
    }

    @JsonProperty("sgst")
    public Double getSgst() {
        return sgst;
    }

    @JsonProperty("sgst")
    public void setSgst(Double sgst) {
        this.sgst = sgst;
    }

    public PosInvoice withSgst(Double sgst) {
        this.sgst = sgst;
        return this;
    }

    @JsonProperty("cess")
    public Double getCess() {
        return cess;
    }

    @JsonProperty("cess")
    public void setCess(Double cess) {
        this.cess = cess;
    }

    public PosInvoice withCess(Double cess) {
        this.cess = cess;
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

    public PosInvoice withDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
        return this;
    }

    @JsonProperty("deliveryAddress")
    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    @JsonProperty("deliveryAddress")
    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public PosInvoice withDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
        return this;
    }

    @JsonProperty("invoiceLineItems")
    public List<LineItem> getInvoiceLineItems() {
        return invoiceLineItems;
    }

    @JsonProperty("invoiceLineItems")
    public void setInvoiceLineItems(List<LineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
    }

    public PosInvoice withInvoiceLineItems(List<LineItem> invoiceLineItems) {
        this.invoiceLineItems = invoiceLineItems;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(PosInvoice.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("invoiceNumber");
        sb.append('=');
        sb.append(((this.invoiceNumber == null)?"<null>":this.invoiceNumber));
        sb.append(',');
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("storeId");
        sb.append('=');
        sb.append(((this.storeId == null)?"<null>":this.storeId));
        sb.append(',');
        sb.append("posId");
        sb.append('=');
        sb.append(((this.posId == null)?"<null>":this.posId));
        sb.append(',');
        sb.append("cashierId");
        sb.append('=');
        sb.append(((this.cashierId == null)?"<null>":this.cashierId));
        sb.append(',');
        sb.append("customerType");
        sb.append('=');
        sb.append(((this.customerType == null)?"<null>":this.customerType));
        sb.append(',');
        sb.append("customerCardNo");
        sb.append('=');
        sb.append(((this.customerCardNo == null)?"<null>":this.customerCardNo));
        sb.append(',');
        sb.append("totalAmount");
        sb.append('=');
        sb.append(((this.totalAmount == null)?"<null>":this.totalAmount));
        sb.append(',');
        sb.append("numberOfItems");
        sb.append('=');
        sb.append(((this.numberOfItems == null)?"<null>":this.numberOfItems));
        sb.append(',');
        sb.append("paymentMethod");
        sb.append('=');
        sb.append(((this.paymentMethod == null)?"<null>":this.paymentMethod));
        sb.append(',');
        sb.append("taxableAmount");
        sb.append('=');
        sb.append(((this.taxableAmount == null)?"<null>":this.taxableAmount));
        sb.append(',');
        sb.append("cgst");
        sb.append('=');
        sb.append(((this.cgst == null)?"<null>":this.cgst));
        sb.append(',');
        sb.append("sgst");
        sb.append('=');
        sb.append(((this.sgst == null)?"<null>":this.sgst));
        sb.append(',');
        sb.append("cess");
        sb.append('=');
        sb.append(((this.cess == null)?"<null>":this.cess));
        sb.append(',');
        sb.append("deliveryType");
        sb.append('=');
        sb.append(((this.deliveryType == null)?"<null>":this.deliveryType));
        sb.append(',');
        sb.append("deliveryAddress");
        sb.append('=');
        sb.append(((this.deliveryAddress == null)?"<null>":this.deliveryAddress));
        sb.append(',');
        sb.append("invoiceLineItems");
        sb.append('=');
        sb.append(((this.invoiceLineItems == null)?"<null>":this.invoiceLineItems));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
