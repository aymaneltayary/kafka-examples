
package guru.learningjournal.kafka.examples.model;

import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total_salary",
    "employee_count",
    "avg_salary"
})
@Generated("jsonschema2pojo")
public class DepartmentAggregate {

    @JsonProperty("total_salary")
    private Integer totalSalary;
    @JsonProperty("employee_count")
    private Integer employeeCount;
    @JsonProperty("avg_salary")
    private Double avgSalary;

    @JsonProperty("total_salary")
    public Integer getTotalSalary() {
        return totalSalary;
    }

    @JsonProperty("total_salary")
    public void setTotalSalary(Integer totalSalary) {
        this.totalSalary = totalSalary;
    }

    public DepartmentAggregate withTotalSalary(Integer totalSalary) {
        this.totalSalary = totalSalary;
        return this;
    }

    @JsonProperty("employee_count")
    public Integer getEmployeeCount() {
        return employeeCount;
    }

    @JsonProperty("employee_count")
    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    public DepartmentAggregate withEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
        return this;
    }

    @JsonProperty("avg_salary")
    public Double getAvgSalary() {
        return avgSalary;
    }

    @JsonProperty("avg_salary")
    public void setAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
    }

    public DepartmentAggregate withAvgSalary(Double avgSalary) {
        this.avgSalary = avgSalary;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DepartmentAggregate.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("totalSalary");
        sb.append('=');
        sb.append(((this.totalSalary == null)?"<null>":this.totalSalary));
        sb.append(',');
        sb.append("employeeCount");
        sb.append('=');
        sb.append(((this.employeeCount == null)?"<null>":this.employeeCount));
        sb.append(',');
        sb.append("avgSalary");
        sb.append('=');
        sb.append(((this.avgSalary == null)?"<null>":this.avgSalary));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
