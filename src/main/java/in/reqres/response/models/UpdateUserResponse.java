package in.reqres.response.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties
@Data
public class UpdateUserResponse {
    private String name;
    private String job;
    @JsonProperty("updatedAt")
    private String updatedAt;
}

