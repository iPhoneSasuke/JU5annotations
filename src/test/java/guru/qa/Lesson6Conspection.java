package guru.qa;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

// https://tophiend.ru/

@DisplayName("Класс теста")
public class Lesson6Conspection {
    @Disabled
    @DisplayName("Тест ассертов")
    @Test
    void assertTests() {
        Assertions.assertTrue(3 > 2);
        Assertions.assertFalse(2 > 3);
        Assertions.assertEquals("awe", "awe");
        Assertions.assertAll(
                () -> Assertions.assertTrue(2 < 3),
                () -> Assertions.assertFalse(2 > 3),
                () -> Assertions.assertEquals("solid", "solid"));
    }

    @Disabled
    @DisplayName("Отключение падающего теста")
    @Test
    void failedTest() {
        Assertions.assertTrue(2 > 3);
    }

    @BeforeAll
    static void hold() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1200";
    }

    //тест на поиск не параметризованный
    @Disabled
    @DisplayName("Поиск в/на яндексе")
    @Test
    void openYa() {
        Selenide.open("https://ya.ru/");
        $("#text").setValue("Drive2").pressEnter();
        $$(".serp-item")
                .find(Condition.text("DRIVE2.RU"))
                .shouldBe(visible);
    }

    //тест на поиск параметризованный
    @ValueSource(strings = { // вместо strings можно int и т.д.
            "VintageTech",
            "avito"
    })

    //  @Disabled
    @ParameterizedTest(name = "Параметризованный поиск по слову {0}")
    //именно {0} выводит в лог названия из ValueSource
    void ParamTest(String testData) {
        Selenide.open("https://ya.ru/");
        $("#text").setValue(testData).pressEnter();
        $$(".serp-item")
                .find(Condition.text(testData))
                .shouldBe(visible);
    }

    //тест на поиск параметризованный, индивидуальные результаты для каждых входных данных
    @CsvSource(value = {
            "VintageTech| На Авито с июня 2012", // {0}| {1} // testData| expected
            "sms| is a text messaging service component of most telephone, Internet"
    }, delimiter = '|')
    @Disabled
    @ParameterizedTest(name = "Параметризованный поиск по слову {0}, ожидаем {1}")
    //именно {0} выводит в лог названия из ValueSource
    void MultiParamTest(String testData, String expected) {
        Selenide.open("https://ya.ru/");
        $("#text").setValue(testData).pressEnter();
        $$(".serp-item")
                .find(Condition.text(expected))
                .shouldBe(visible);
    }
}
