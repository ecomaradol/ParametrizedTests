package demo.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class ParametrizedEmailFieldTest {

    @BeforeAll
    static void browserSize() {
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void preCondition() {
        open("https://demoqa.com/text-box");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @ValueSource(strings = {"test@test.com", "test", "test@", "test@test"})
    @ParameterizedTest(name = "Fill in email field for {0}")
    void emailField(String testData) {
        $("#userName").setValue("Mara");
        $("#userEmail").setValue(testData);
        $("#currentAddress").setValue("current address");
        $("#permanentAddress").setValue("permanent address");
        $("#submit").click();

        $("#output").shouldHave(text("Mara"))
                .shouldHave(text(testData))
                .shouldHave(text("current address"))
                .shouldHave(text("permanent address"));

    }

    @CsvSource(value = {"Mara, test@test.com", "Mar, test@test", "Dol, test"})
    @ParameterizedTest(name = "Fill in name field for {0}")
    void nameField(String testData, String email) {
        $("#userName").setValue(testData);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue("current address");
        $("#permanentAddress").setValue("permanent address");
        $("#submit").click();

        $("#output").shouldHave(text(testData))
                .shouldHave(text(email))
                .shouldHave(text("current address"))
                .shouldHave(text("permanent address"));
    }


    static Stream<org.junit.jupiter.params.provider.Arguments> differentArgumentsTest() {
        return Stream.of(
                Arguments.of("Mara", "test@test.com"),
                Arguments.of("Mar", "test@test"),
                Arguments.of("Dol", "test")
        );
    }

    @MethodSource(value = "differentArgumentsTest")
    @ParameterizedTest
    void differentArguments(String firstArg, String secondArg) {
        $("#userName").setValue(firstArg);
        $("#userEmail").setValue(secondArg);
        $("#currentAddress").setValue("current address");
        $("#permanentAddress").setValue("permanent address");
        $("#submit").click();

        $("#output").shouldHave(text(firstArg))
                .shouldHave(text(secondArg))
                .shouldHave(text("current address"))
                .shouldHave(text("permanent address"));
    }
}