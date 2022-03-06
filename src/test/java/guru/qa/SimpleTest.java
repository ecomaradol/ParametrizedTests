package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {

    @Test
    @DisplayName("Green Test")
    void simpleGreenTest() {
        assertTrue(3>2);
    }
    @Test
    @DisplayName("Red Test")
    void simpleFailedTest() {
        assertTrue(1>2);
    }
    @Test
    void simpleBrokenTest() {
        throw new IllegalStateException();
    }
}
