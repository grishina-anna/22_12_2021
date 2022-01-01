package guru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class FinishHoweWork {

        @BeforeAll
        static void setUp() {

//если надо несколько раз открывать один и тот же сайт, можно добавить то, что ниже:
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
        }

        @Test

        void fillFormTest() {
        open("/automation-practice-form");

        $("#firstName").setValue("Anna");
        $("#lastName").setValue("Grishina");
        $("#userEmail").setValue("aaa@aa.aa");

        $("#genterWrapper").$(byText("Female")).click(); //самый нормальный вариант

        $("#userNumber").setValue("8912345678");
        $("#dateOfBirthInput").click();
         $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("July");
        $(".react-datepicker__year-select").selectOption("1990");

        $(byText("29")).click();

        $("#subjectsInput").setValue("Math").pressEnter();

        $("#hobbiesWrapper").$(byText("Sports")).click();

       $("#uploadPicture").uploadFromClasspath("img/1.png");

        $("#currentAddress").setValue("Krasnaya, 1-1-11");

        // не смог нажать на эти селекты, добавили скролл

        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText("NCR")).click();


        $("#city").click();
        $("#stateCity-wrapper").$(byText("Noida")).click();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").$(byText("Student Name")).parent()
                    .shouldHave(text("Anna Grishina"));
            sleep(5000);
        }
}

