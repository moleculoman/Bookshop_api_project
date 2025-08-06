package tests.demoqa.Bookshop.tests;

import helpers.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.demoqa.Bookshop.models.AddBooksRequestModel;
import tests.demoqa.Bookshop.models.DeleteBooksRequestModel;
import tests.demoqa.Bookshop.models.IsbnModel;
import tests.demoqa.Bookshop.pages.ProfilePage;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static tests.demoqa.Bookshop.api.BooksApi.*;
import static tests.demoqa.Bookshop.tests.TestData.userId;

@Tag("Bookshop_test")
@DisplayName("Тесты на взаимодействие с коллекцией книг")
public class CollectionBookTests extends TestBase {

    @Test
    @DisplayName("Успешное добавление книги в корзину")
    @WithLogin
    public void successfulAddBookTest() {
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(bookISBN);
        List<IsbnModel> isbns = List.of(isbnModel);

        AddBooksRequestModel addBookData = new AddBooksRequestModel();
        addBookData.setUserId(userId);
        addBookData.setCollectionOfIsbns(isbns);

        step("Очистить корзину", () -> deleteAllBooks());
        step("Добавить книгу", () -> addBook(addBookData));
        step("Проверить, что книга отображается в профиле", () ->
                new ProfilePage().openPage().checkBookIsPresent(bookISBN)
        );
    }

    @Test
    @DisplayName("Успешное удаление книги из корзины")
    @WithLogin
    public void successfulRemoveBookTest() {
        IsbnModel isbn = new IsbnModel();
        isbn.setIsbn(bookISBN);
        List<IsbnModel> isbns = List.of(isbn);

        AddBooksRequestModel addBookData = new AddBooksRequestModel();
        addBookData.setUserId(userId);
        addBookData.setCollectionOfIsbns(isbns);

        DeleteBooksRequestModel deleteBookData = new DeleteBooksRequestModel();
        deleteBookData.setUserId(userId);
        deleteBookData.setIsbn(bookISBN);

        step("Очистить корзину", () -> deleteAllBooks());
        step("Добавить книгу", () -> addBook(addBookData));
        step("Проверить, что книга отображается в профиле", () ->
                new ProfilePage().openPage().checkBookIsPresent(bookISBN)
        );
        step("Удалить книгу из корзины", () -> deleteBook(deleteBookData));
        step("Проверить, что корзина пуста после удаления", () -> {
            new ProfilePage().openPage().checkEmptyTableWithoutBooks();
        });
    }

    @Test
    @DisplayName("Очистка корзины удаляет все книги")
    @WithLogin
    public void clearCartTest() {
        IsbnModel isbn = new IsbnModel();
        isbn.setIsbn(bookISBN);
        List<IsbnModel> isbns = List.of(isbn);

        AddBooksRequestModel addBookData = new AddBooksRequestModel();
        addBookData.setUserId(userId);
        addBookData.setCollectionOfIsbns(isbns);

        step("Очистить корзину", () -> deleteAllBooks());
        step("Добавить книгу", () -> addBook(addBookData));
        step("Проверить, что книга отображается в профиле", () ->
                new ProfilePage().openPage().checkBookIsPresent(bookISBN)
        );
        step("Очистить корзину", () -> deleteAllBooks());
        step("Проверить, что корзина пуста", () -> {
            new ProfilePage().openPage().checkEmptyTableWithoutBooks();
        });
    }
}