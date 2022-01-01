package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.*;

import java.io.*;
import java.time.*;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
class Snippets {

    void browser_command_examples() {
        // -Dselenide.baseUrl=http://github.com - основная часть хоста, это можно сделать динамическим и менять при запуске теста

        //Команды для браузера:
        open("https://google.com"); //открыть страницу браузера
        open("/customer/orders");  //частичный путь, основная часть хоста -Dselenide.baseUrl= указывается выше
        open("/", AuthenticationType.BASIC, "user", "password"); //используется для запуска сайта с защитой

        //Сначала браузер открывается без куки и данных, но после операций, данные сохраняются. например, при заходе на сайт сохраняется пароль.
        // И тест на проверку пароля не выполнится, для это можно добавить перед open очистить кукки и LocalStorage

        Selenide.back(); // команда назад на сайте
        Selenide.refresh(); //обновить страницу

        Selenide.clearBrowserCookies(); // очистка кукки
        Selenide.clearBrowserLocalStorage(); //очистка настроек приложения, (режим инкогнито)

        Selenide.confirm(); // OK in alert dialogs //ответы на всплывашках ок
        Selenide.dismiss(); // Cancel in alert dialogs  //ответы на всплывашках отменить

        Selenide.closeWindow(); // close active tab //закрыть активное окно
        Selenide.closeWebDriver(); // close browser completely //закрыть все активные окна

        Selenide.switchTo().frame("new"); //если поиск нужно осуществить внутри frame (области)
        Selenide.switchTo().defaultContent(); //выход обратно в стандартный документ из фрейма
        Selenide.switchTo().window("The Internet"); //переход на окнам
    }

    //локаторы

    void selectors_examples() {
        $("div").click(); //синоним $ слово element //ищет первый див
        element("div").click(); //используется в котлине

        $("div", 2).click(); // the third div //ищет 2 див (начинается с 0).

        $x("//h1/div").click(); //запить xpath
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click(); //поиск по тексту, ищет по полному совпадению тескта
        $(withText("ull tex")).click(); //поиск по тексту, ищет по частичному совпадению тескта

        //для навигации по дереву

        $("").parent(); //ищет родителя
        $("").sibling(1); //ищет вниз второго соседа по дереву
        $("").preceding(1); // ищет вверх второго соседа по дереву
        $("").closest("div"); //ищет вверх по дереву первый элемент
        $("").ancestor("div"); // синоним closest
        $("div:last-child"); //последнего ребенка

// их можно комбинировать: сначала ищем div. внутри него h1. внутри него текст abc
        $("div").$("h1").find(byText("abc")).click();

        // very optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click(); //[] для атрибута

        $(byId("mytext")).click();
        $("#mytext").click(); //# для id

        $(byClassName("red")).click();
        $(".red").click(); // , для класса
    }

    //действия

    void actions_examples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick(); //правый клик мышкой

        $("").hover(); //поднести мышку

        $("").setValue("text"); //очищает поле и вписывает туда текст
        $("").append("text"); //не очищает поле, добавляет тест
        $("").clear(); //очищает поле
        $("").setValue(""); // синоним clear


        $("div").sendKeys("c"); // hotkey c on element
        actions().sendKeys("c").perform(); // нажать с на сайте
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F, иногда не работает
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f")); //отправить к верхнему элементу и нажать f

        $("").pressEnter(); //нажать энтер
        $("").pressEscape(); //нажать Escape
        $("").pressTab(); //нажать Tab


        // complex actions with keybord and mouse, example
        //подвинуть мышку к элементу (div). нажимаем клавишу мыши и не отпускаем и перемещаем мыim на 300 символов направо и 200 вниз и выполнить
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option"); //для старых версий dropdown
        $("").selectRadio("radio_options"); //чекбокс

    }
//Проверка

    void assertions_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc")); //тоже самое, что shouldBe, просто должен быть text ()
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);


        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30)); //новый, ждет указанное время ожидания
        $("").waitUntil(visible, 30000); //тайм аут, ждет, но и проверяет, не используется
    }

    void conditions_examples() {
        $("").shouldBe(visible); //должен быть видимым
        $("").shouldBe(hidden); //должен быть невидимый

        $("").shouldHave(text("abc")); //должен иметь abc (часть)
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$")); //например, 7 буква в слове х, а всего 14 букв

        $("").shouldHave(cssClass("red")); //проверяет классы, каждый класс отдельно, через запятую cssClass(""), cssClass("")
        $("").shouldHave(cssValue("font-size", "12")); //строгие физические параметры элемента, например, размер фронта (параметр находится справа в консоли)

        $("").shouldHave(value("25")); //в селектах проверяется вариант ответа, частичное
        $("").shouldHave(exactValue("25"));  //в селектах проверяется вариант ответа, полное совпадение
        $("").shouldBe(empty); //проверяет, что поле пустое

        $("").shouldHave(attribute("disabled")); //атрибуты проверяются на наличие
        $("").shouldHave(attribute("name", "example")); //атрибуты проверяются на значение
        $("").shouldHave(attributeMatching("name", "[0-9]abc$")); ////атрибуты проверяются на особенные параметры

        $("").shouldBe(checked); //включен/выключен чекбокс

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        // не путать exist с visible! exist проверяет наличие в доме, а не на видимость
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled); //элемент заблокирован, проверяется наличие элемента "disabled"
        $("").shouldBe(enabled); //элемент доступен для выбора, активен
    }

    void collections_examples() {
        // ищет набор элементов

        $$("div"); // does nothing!

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1)); //находит все элементы с 123
        $$("div").excludeWith(text("123")).shouldHave(size(1)); //обратно фильтрованию, находит все элементы, в которых нет 123

        $$("div").first().click();
        elements("div").first().click();

        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0) //ищет 2 элемент по списку
        $("div", 1).click(); // same as previous  //ищет 2 элемент по списку, быстрее
        $$("div").findBy(text("123")).click(); //  finds first

        // assertions
        //проверка в коллекции (массив)
        $$("").shouldHave(size(0)); //размер равен 0, пустой
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma")); //сравнивается порядок и количество текста
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));

        $$("").shouldHave(itemWithText("Gamma")); // из всего списка нам нужна только "Gamma"

        $$("").shouldHave(sizeGreaterThan(0)); //в результате 0 результатов
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2)); //в результате должно быть максимум 2 результата


    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links //найти элемент и скачать
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt"); //сохранить файл, который нужно загрузить, не забыть нажать на кнопку загрузки
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')"); //java скрипт
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }
}


    //$("h1").$("div") - внутри "h1" ищут элементы div
    //$("h1 div") -