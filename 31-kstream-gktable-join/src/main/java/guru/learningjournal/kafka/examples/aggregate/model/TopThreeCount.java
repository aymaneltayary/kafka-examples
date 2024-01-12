package guru.learningjournal.kafka.examples.aggregate.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.learningjournal.kafka.examples.model.Inventory;

import java.io.Serial;
import java.io.Serializable;
import java.util.StringJoiner;
import java.util.TreeSet;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import guru.learningjournal.kafka.examples.model.TypeCount;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "topSet"
})
public class TopThreeCount implements Serializable {

    private final ObjectMapper mapper = new ObjectMapper();

    @JsonProperty("topSet")
    private TreeSet<TypeCount> topSet= new TreeSet<>((count1, count2)-> {
        int order=count2.getCount().compareTo(count1.getCount());
        if(order==0) return count2.getNewsType().compareTo(count1.getNewsType());
        return order;
    });

    @JsonProperty("topSet")
    public String getTopSet() throws JsonProcessingException {

        return mapper.writeValueAsString(topSet);
    }

    @JsonProperty("topSet")
    public void setTopSet(String topSet) throws JsonProcessingException {
        TypeCount[] typeCountArray = mapper.readValue(topSet, TypeCount[].class);
        for (TypeCount i:typeCountArray){
            addElement(i);
        }
    }

    public void addElement(TypeCount typeCount ){
        topSet.add(typeCount);
        if(topSet.size()>3){
            topSet.remove(topSet.last());

        }

    }
    public void removeElement(TypeCount typeCount ){
        topSet.remove(typeCount);

    }

    @Override
    public String toString() {

       StringJoiner stringJoiner= new StringJoiner(",");
        for(TypeCount typeCount:topSet){
            stringJoiner.add(typeCount.toString());
       }
        return "TopThreeCount{" +
                "topSet=" + stringJoiner.toString() +
                '}';
    }
}
