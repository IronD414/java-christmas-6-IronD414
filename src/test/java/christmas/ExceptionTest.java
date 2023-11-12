package christmas;

import christmas.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

                assertThat(outputStreamCaptor.toString()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }catch (final NoSuchElementException ignore) {
        }finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}
