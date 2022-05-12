package guru.qa;

import com.codeborne.selenide.*;
import guru.qa.domain.MenuItem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class JU5tests {

    @BeforeAll
    static void browserConfig() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1200";
    }

    //   @Disabled
    @DisplayName("Простой тест")
    @Test
    void simpleTest() {
        Selenide.open("https://tophiend.ru/");
        $("#search").setValue("Vantage").pressEnter();
        $$(".search-result-wrapper").find(Condition.text("Vantage"))
                .shouldBe(visible);
    }

    @ValueSource(strings = {
            "Elac",
            "Vantage"
    })

    //   @Disabled
    @DisplayName("Проверка")
    @ParameterizedTest(name = "Параметризованный тест по слову {0}")
    void paramTest(String testData) {
        Selenide.open("https://tophiend.ru/");
        $("#search").setValue(testData).pressEnter();
        $$(".search-result-wrapper").find(Condition.text(testData))
                .shouldBe(visible);
    }

    @CsvSource(value = {
            "Vantage, Aкустическая система Vantage L",
            "Vantage, Aкустическая система Vantage M",
            "Vantage, Aкустическая система Vantage S",
            "Vantage, Aкустическая система Vantage B",
            "Vantage, Aкустическая система Vantage C",

    })

    //   @Disabled
    @ParameterizedTest(name = "Параметризованный тест по слову {0} с результатом {1}")
    void paramTestMulti(String testData, String expected) {
        Selenide.open("https://tophiend.ru/");
        $("#search").setValue(testData).pressEnter();
        $$(".search-result-wrapper").find(Condition.text(expected))
                .shouldBe(visible);
    }

    @EnumSource(MenuItem.class)
    @ParameterizedTest()
    void eNumTest(MenuItem testData) {
        Selenide.open("https://tophiend.ru/");
        $("#search").setValue("Elac").pressEnter();
        $$("#nav")
                .find(Condition.text(testData.rusName))
                .shouldBe(visible);

    }
}
