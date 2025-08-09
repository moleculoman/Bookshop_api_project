package tests.demoqa.Bookshop.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class ProfilePage {
    private final SelenideElement tableNoData = $(".rt-noData");
    private final SelenideElement booksTable = $(".rt-table");

    @Step("Открываем страницу профиля")
    public ProfilePage openPage() {
        open("/profile");
        return this;
    }

    @Step("Проверка, что таблица с книгами пуста")
    public ProfilePage checkEmptyTableWithoutBooks() {
        tableNoData.shouldBe(visible);
        return this;
    }

    @Step("Проверка, что таблица с книгами содержит добавленную книгу")
    public ProfilePage checkBookIsPresent(String isbn) {
        booksTable.$("a").shouldHave(href("/profile?book="+isbn));
        return this;
    }

    @Step("Проверка, что книга заменена")
    public ProfilePage checkBookReplaced(String oldIsbn, String newIsbn) {
        checkBookIsNotPresent(oldIsbn);
        checkBookIsPresent(newIsbn);
        return this;
    }

    @Step("Проверка, что книга со старым ISBN не отображается")
    private void checkBookIsNotPresent(String isbn) {
        assertFalse($$("div.book-list").texts().contains(isbn),
                "Книга с ISBN " + isbn + " не должна отображаться");
    }
}
