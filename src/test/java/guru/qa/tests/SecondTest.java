package guru.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SecondTest {

    @Test
    void firstContrubuterShoundBeAndreiSolntcev() {
        //открыть страницу репозитория селенида
        open("https://github.com/selenide/selenide");
//        $("").scrollTo();
        //навести мышку к первому элементу в области Contrubuter
                //Выбрать контейнер Layout-sidebar и в нем ищем текст Contributors. closest - вверх
        $(".Layout-sidebar").$(byText("Contributors")).closest("div")
                .$$("ul li").first().hover();
        //первым должен быть Андрей Солнцев
//        $$("grid-mode-element").first().$("[data-hovercard-url=\"/users/asolntsev/hovercard\"]").click();
        //ищем массив и из них нужный элемент
        $$(".Popover-message").find(Condition.visible).shouldHave(text("Andrei Solntsev"));
        sleep(5000);
    };
}
