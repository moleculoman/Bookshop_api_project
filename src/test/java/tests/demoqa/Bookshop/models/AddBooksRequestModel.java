package tests.demoqa.Bookshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AddBooksRequestModel {
    String userId;
    List<IsbnModel> collectionOfIsbns;
}
