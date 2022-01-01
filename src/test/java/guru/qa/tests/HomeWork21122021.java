package guru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeWork21122021 {

    @BeforeAll
    static void setUp() {

//если надо несколько раз открывать один и тот же сайт, можно добавить то, что ниже:
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
    }

    //называем аннотацию
    @Test

    //называем метод
    void fillFormTest() {
        //открытие ссылки
        open("/automation-practice-form");

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Grishina");
        $("#userEmail").setValue("aaa@aa.aa");

        //выбор пола. так нельзя
//        $("#gender-radio-3").setValue("Female");

//        $("gender-radio-3").parent().click(); //работает, но нужно найти соседний

// $(byText("Female")).click(); //работает, но происходит поиск по тексу сверху вниз, и будет кликать на первый Female, который найдет

        $("#genterWrapper").$(byText("Female")).click(); //самый нормальный вариант

        $("#userNumber").setValue("8912345678");
        $("#dateOfBirthInput").click();
        //не для всех селектов подходит данный метод: "." потому что react-datepicker__month-select это класс
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1990");
        // если число встречается несколько раз в поле, то:
        //1 способ (указать, какого локатора быть не должно)
      //  $(".react-datepicker__day--030:not(.react-datepicker__day--outside-month)").click();

        //2 способ, найти массив ($$) ".react-datepicker__day--030 и отфильтровать и выбрать первый элемент

//     $$(".react-datepicker__day--030")
//              .filter(not(cssClass(".react-datepicker__day--outside-month")));
//        .first()
//        //.get(0) тоже самое, что first ()
//                .click();
//
//        не работает. есть пробелы,
//        $("[aria-label=\Choose Wednesday, July 30th, 2008\]")
//
//        3 способ. работает, но это новый элемент - среда, новые вычисления. Поэтому необходимо добавить xpath
//        $("[aria-label='Choose Wednesday, July 30th, 2008']").click();
//        Поэтому необходимо добавить xpath
//        $x("//*[contains(aria-label, \"Choose Wednesday, July 30th, 2008\"]").click();
//
        $(byText("29")).click();


//        $("[aria-label$='July, 29th, 1990']").click();

        //$ внутри кавычек означает, что значение параметра должно заканчиваться на "март"
        // $$ означает коллекцию элементов (массив)
        // $ возвращает первый, который найдёт, $$ вернет все, кто будет подходить

        $("#subjectsInput").setValue("Math").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();

//сначала нужно в папке test добавить директорию "resources", в ней папку "imd" и там файл "1.png"

        // в селениде есть 2 метода загрузки картинки:
        //1. нажать правок кнопкой на картинку, выбрать Copy Path и дальше в самом низу путь
//        $("#uploadPicture").uploadFile(new File("src/test/resources/img/1.png"));

        //ещё вариант- тоже самое, но путь выполнится выше
//        File someFile = new File("src/test/resources/img/1.png");
//        $("#uploadPicture").uploadFile(someFile);

        //2 способ: указывается пусть, относительно папки test - он лучше!
//        $("#uploadPicture").uploadFromClasspath("img/1.png");

        $("#currentAddress").setValue("Krasnaya, 1-1-11");

        // не смог нажать на эти селекты, добавили скролл

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();


        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        $("#submit").click();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        $("#currentAddress", 1).shouldHave(text("Krasnaya, 1-1-11"));

    }
    }

