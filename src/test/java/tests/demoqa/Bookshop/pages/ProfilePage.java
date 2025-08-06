package tests.demoqa.Bookshop.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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
}
