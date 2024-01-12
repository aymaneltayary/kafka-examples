
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "itemCode",
    "itemDescription",
    "itemPrice",
    "itemQty",
    "totalValue"
})
@Generated("jsonschema2pojo")
public class LineItem {

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

    @JsonProperty("itemCode")
    public String getItemCode() {
        return itemCode;
    }

    @JsonProperty("itemCode")
    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    @JsonProperty("itemDescription")
    public String getItemDescription() {
        return itemDescription;
    }

    @JsonProperty("itemDescription")
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    @JsonProperty("itemPrice")
    public Double getItemPrice() {
        return itemPrice;
    }

    @JsonProperty("itemPrice")
    public void setItemPrice(Double itemPrice) {
        this.itemPrice = itemPrice;
    }

    @JsonProperty("itemQty")
    public Integer getItemQty() {
        return itemQty;
    }

    @JsonProperty("itemQty")
    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }

    @JsonProperty("totalValue")
    public Double getTotalValue() {
        return totalValue;
    }

    @JsonProperty("totalValue")
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(LineItem.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
