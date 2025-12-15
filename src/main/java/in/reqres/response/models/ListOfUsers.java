package in.reqres.response.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@lombok.Data
public class ListOfUsers {
    public int page;
    @JsonProperty("per_page")
    public int perPage;
    public int total;
    @JsonProperty("total_pages")
    public int totalPages;
    public List<Data> data;
    public Support support;
    @JsonProperty("_meta")
    public Meta meta;
}
