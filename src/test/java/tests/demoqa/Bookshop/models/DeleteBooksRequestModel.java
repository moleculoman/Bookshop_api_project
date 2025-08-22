package tests.demoqa.Bookshop.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteBooksRequestModel {
    String userId, isbn;
}
