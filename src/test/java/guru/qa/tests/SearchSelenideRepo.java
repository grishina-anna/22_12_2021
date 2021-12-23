//Поиск слово Selenide на сайте github
package guru.qa.tests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.files.DownloadActions.click;

public class SearchSelenideRepo {

    @Test
    //длинные названия - хороший тон
    void shouldFindSelenideRepositoryInGithub(){

        //сначала нужно руками пройти все шаги и записать комментариями действия

        //открываем сайт
      open("https://github.com/");
        //вводим в поле поиска "selenide" и нажать Enter
        $("[data-test-selector=nav-search-input]").setValue("selenide").pressEnter();
        //заходим в первый результат
        //$$ идем набор элементов "ul.repo-list". указывается ещё li class, которые входят в этот список
        //на списке нельзя произвести действие, его можно отфильтровать
        //без "а" не сработает, потому что селенид тыкает в центр поля
        $$("ul.repo-list li").first().$("a").click();
        //можно проверить список на размер, для этого:
//   $$("ul.repo-list li").shouldHave(CollectionCondition.size(10));
        //проверка, что в заголовке h1 встречается "selenide/selenide"
        //"selenide/selenide" там новая строка
        $("h1").shouldHave(textCaseSensitive("selenide / selenide"));
        //временно! чтобы успеть посмотреть, что получилось, 5 сек
        sleep(5000);


    };
}

