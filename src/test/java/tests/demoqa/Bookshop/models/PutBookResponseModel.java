package tests.demoqa.Bookshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PutBookResponseModel {
    @JsonProperty("userId")
    private String userId;
    private String username;
    private List<PutBookRequestModel> books;
}
