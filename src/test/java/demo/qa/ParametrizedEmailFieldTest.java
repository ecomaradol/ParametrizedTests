package demo.qa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

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
    void closeBrowser(){
        closeWebDriver();
    }

    @ValueSource (strings = {"test@test.com", "test", "test@", "test@test"})
    @ParameterizedTest (name= "Fill in email field for {0}")
    void emailField(String testData) {
        $("#userName").setValue("Mara");
        $("#userEmail").setValue(testData);
        $("#currentAddress").setValue("current address");
        $("#permanentAddress").setValue("permanent address");
        $("#submit").click();
    }

    @CsvSource(value = {"Mara, test@test.com", "Mar, test@test", "Dol, test"})
    @ParameterizedTest (name= "Fill in name field for {0}")
    void nameField(String testData, String email) {
        $("#userName").setValue(testData);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue("current address");
        $("#permanentAddress").setValue("permanent address");
        $("#submit").click();
    }
}
