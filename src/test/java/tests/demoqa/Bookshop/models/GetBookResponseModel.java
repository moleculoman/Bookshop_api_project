package tests.demoqa.Bookshop.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBookResponseModel {
    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    @JsonProperty("publish_date")
    private LocalDateTime publishDate;
    private String publisher;
    private Integer pages;
    private String description;
    private String website;
}