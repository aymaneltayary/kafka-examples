
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inventoryId",
    "newsType"
})
@Generated("jsonschema2pojo")
public class Inventory {

    @JsonProperty("inventoryId")
    private String inventoryId;
    @JsonProperty("newsType")
    private String newsType;

    @JsonProperty("inventoryId")
    public String getInventoryId() {
        return inventoryId;
    }

    @JsonProperty("inventoryId")
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Inventory withInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
        return this;
    }

    @JsonProperty("newsType")
    public String getNewsType() {
        return newsType;
    }

    @JsonProperty("newsType")
    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public Inventory withNewsType(String newsType) {
        this.newsType = newsType;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Inventory.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("inventoryId");
        sb.append('=');
        sb.append(((this.inventoryId == null)?"<null>":this.inventoryId));
        sb.append(',');
        sb.append("newsType");
        sb.append('=');
        sb.append(((this.newsType == null)?"<null>":this.newsType));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
