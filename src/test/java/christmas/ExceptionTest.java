package christmas;

import christmas.constants.ErrorMessages;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionTest {
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    InputStream inputStream;

    @DisplayName("날짜_예외_테스트")
    @Test
    void checkInvalidDate(){
        try {
            System.setOut(new PrintStream(outputStreamCaptor));

            String[] input = {"abc\n", "0\n", "32\n", "030\n"};

            for (String eachInput : input){
                inputStream = new ByteArrayInputStream(eachInput.getBytes());
                System.setIn(inputStream);

                Application.main(new String[]{});

                assertThat(outputStreamCaptor.toString()).contains(ErrorMessages.INVALID_DATE.getMessage());
            }
        }catch (final NoSuchElementException ignore) {
        }finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
    @DisplayName("음료만_주문_예외_테스트")
    @Test
    void checkDrinkOrderOnly(){
        try {
            System.setOut(new PrintStream(outputStreamCaptor));

            String input = "1\n" + "레드와인-3,샴페인-2\n";

            inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            Application.main(new String[]{});

            assertThat(outputStreamCaptor.toString()).contains(ErrorMessages.INVALID_ORDER.getMessage());
        }catch (final NoSuchElementException ignore) {
        }finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
    @DisplayName("메뉴개수_예외_테스트")
    @Test
    void checkInvalidMenuQuantity(){
        try {
            System.setOut(new PrintStream(outputStreamCaptor));

            String[] input = {"1\n시저샐러드-a\n", "1\n초코케이크-1,해산물파스타--1\n", "1\n크리스마스파스타-0\n", "1\n제로콜라-11,해산물파스타-11\n"};

            for (String eachInput : input){
                inputStream = new ByteArrayInputStream(eachInput.getBytes());
                System.setIn(inputStream);

                Application.main(new String[]{});

                assertThat(outputStreamCaptor.toString()).contains(ErrorMessages.INVALID_ORDER.getMessage());
            }
        }catch (final NoSuchElementException ignore) {
        }finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
    @DisplayName("중복_메뉴_예외_테스트")
    @Test
    void checkDupeOrder(){
        try {
            System.setOut(new PrintStream(outputStreamCaptor));

            String input = "1\n타파스-1,바비큐립-5,타파스-3\n";

            inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            Application.main(new String[]{});

            assertThat(outputStreamCaptor.toString()).contains(ErrorMessages.INVALID_ORDER.getMessage());
        }catch (final NoSuchElementException ignore) {
        }finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}
