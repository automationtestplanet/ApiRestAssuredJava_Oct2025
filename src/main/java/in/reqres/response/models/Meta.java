package in.reqres.response.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Meta {
    @JsonProperty("powered_by")
    public String poweredBy;
    @JsonProperty("upgrade_url")
    public String upgradeUrl;
    @JsonProperty("docs_url")
    public String docsUrl;
    @JsonProperty("template_gallery")
    public String templateGallery;
    public String message;
    public List<String> features;
    @JsonProperty("upgrade_cta")
    public String upgradeCta;
}
