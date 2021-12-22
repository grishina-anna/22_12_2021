package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class HomeWork_21_12_2021 {

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
        $(".react-datepicker__month-select").selectOption("March");
        $(".react-datepicker__year-select").selectOption("1988");
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
//        $(byText("29")).click();


        $("[aria-label$='March 29th, 1988']").click();

        //$ внутри кавычек означает, что значение параметра должно заканчиваться на "март"
        // $$ означает коллекцию элементов (массив)
        // $ возвращает первый, который найдёт, $$ вернет все, кто будет подходить

        $("#subjectsInput").setValue("Math").pressEnter();

        $(byText("Sports")).click();
        $("#uploadPicture").uploadFromClasspath("simple.txt");
       $("#currentAddress").setValue("Krasnaya, 1-1-11");
        $("#state").setValue("NCR").pressEnter();
        $("#city").setValue("Delhi").pressEnter();
        $("#submit").click();
    }
    }

