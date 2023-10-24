package seminars.third.hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainHWTest {
    MainHW mainHW;

    @BeforeEach
    void setup() {
        mainHW = new MainHW();
    }

    /*
        HW 3.1. Нужно покрыть тестами метод на 100%
        Метод проверяет, является ли целое число записанное в переменную n, чётным (true)
        либо нечётным (false).
     */

    @ParameterizedTest
    @ValueSource(ints = {-999, -101, -9, -3, 1, 7, 29, 1001})
    void testOddNumber(int number) {
        boolean isNumber = mainHW.evenOddNumber(number);
        assertFalse(isNumber);
    }

    @ParameterizedTest
    @ValueSource(ints = {-100, -50, -10, 0, 2, 4, 20, 100})
    void testEvenNumber(int number) {
        boolean isNumber = mainHW.evenOddNumber(number);
        assertTrue(isNumber);
    }

    /*
        HW 3.2. Нужно написать метод, который проверяет, попадает ли переданное число в интервал (25;100)
        и возвращает true, если попадает и false - если нет, покрыть тестами метод на 100%
     */

    @ParameterizedTest
    @ValueSource(ints = {26, 30, 50, 99})
    void testIsNumberInRange(int number){
        boolean isInRange = mainHW.numberInInterval(number);

        assertTrue(isInRange);
    }

   @ParameterizedTest
    @ValueSource(ints = {-100, 0, 25, 100, 1000})
    void testIsNumberOutOfRange(int number){
        boolean isInRange = mainHW.numberInInterval(number);

        assertFalse(isInRange);
    }
}
