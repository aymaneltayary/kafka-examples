
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "newsType",
    "count"
})
@Generated("jsonschema2pojo")
public class TypeCount {

    @JsonProperty("newsType")
    private String newsType;
    @JsonProperty("count")
    private Double count;

    @JsonProperty("newsType")
    public String getNewsType() {
        return newsType;
    }

    @JsonProperty("newsType")
    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public TypeCount withNewsType(String newsType) {
        this.newsType = newsType;
        return this;
    }

    @JsonProperty("count")
    public Double getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Double count) {
        this.count = count;
    }

    public TypeCount withCount(Double count) {
        this.count = count;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        sb.append("newsType");
        sb.append('=');
        sb.append(((this.newsType == null)?"<null>":this.newsType));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null)?"<null>":this.count));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
