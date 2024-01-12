
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "inventoryId"
})
@Generated("jsonschema2pojo")
public class UserClick {

    @JsonProperty("inventoryId")
    private String inventoryId;

    @JsonProperty("inventoryId")
    public String getInventoryId() {
        return inventoryId;
    }

    @JsonProperty("inventoryId")
    public void setInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
    }

    public UserClick withInventoryId(String inventoryId) {
        this.inventoryId = inventoryId;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UserClick.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("inventoryId");
        sb.append('=');
        sb.append(((this.inventoryId == null)?"<null>":this.inventoryId));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
