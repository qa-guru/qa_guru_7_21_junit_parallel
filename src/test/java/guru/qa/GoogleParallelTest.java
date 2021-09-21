package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.domain.MenuItem;
import guru.qa.page.YandexMainPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith({SimpleCallback.class, YandexTestCondition.class})
@Tag("Google")
class GoogleParallelTest {

    private YandexMainPage ymp = new YandexMainPage();

//    static Stream<Arguments> checkSearchResultForSeveralMenuItems() {
//        return Stream.of(
//                Arguments.of(
//                       1, "String", new ArrayList<>()
//                ),
//                Arguments.of(
//                        2, "String 2", new ArrayList<>()
//                )
//        );
//    }
//    @MethodSource()

    @EnumSource(value = MenuItem.class, names = {"SEARCH"}, mode = EnumSource.Mode.EXCLUDE)
    @ParameterizedTest(name = "{1}")
    void checkSearchResultForSeveralMenuItems(MenuItem menuItem) {
        Configuration.startMaximized = true;
        Selenide.open(YandexMainPage.URL);
        ymp.doSearch("selenide")
                .switchToMenuItem(menuItem);
        System.out.println();
    }

    @EnumSource()
    @CsvSource({
            "88891, qa.guru, Very complex displayed name",
            "88892, selenide, Very complex displayed name"
    })
    @ParameterizedTest(name = "{1}")
    void testWithComplexName(int allureId, String searchQuery, String testName) {
        Configuration.startMaximized = true;
        Selenide.open(YandexMainPage.URL);
        ymp.doSearch(searchQuery)
                .checkResults(searchQuery);
    }

    @ValueSource(strings = {
            "qa.guru",
            "selenide",
            "qameta",
            "allure"
    })
    @ParameterizedTest(name = "Check search results for input string: {0}")
    void yandexSearchTest(String searchQuery, TestInfo testInfo) {
        Configuration.startMaximized = true;
        Selenide.open(YandexMainPage.URL);
        ymp.doSearch(searchQuery)
                .checkResults(searchQuery);

        System.out.println("Config for test: "
                + testInfo.getDisplayName()
                + " "
                + Configuration.startMaximized
        );
    }

    @DisplayName("JDI should be present in search results")
    @Test
    void minimizedWindowTest(TestInfo testInfo) {
        Configuration.startMaximized = false;
        Selenide.open(YandexMainPage.URL);
        ymp.doSearch("JDI")
                .checkResults("JDI");
    }
}
