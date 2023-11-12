package christmas;

import christmas.domain.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
    PrintStream originalOut = System.out;
    InputStream originalIn = System.in;
    ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
    InputStream inputStream;
    Customer customer = new Customer();
    Map<Menu, Integer> order = new HashMap<>();

    @DisplayName("구매자_할인적용_테스트")
    @Test
    void checkCustomerDiscounted(){
        order.put(new AppetizerMenu("양송이수프"), 1);   //  6000
        order.put(new AppetizerMenu("타파스"), 2); //  11000
        order.put(new DrinkMenu("레드와인"), 1);    //  60000
        customer.setCart(order);    //  total: 77000
        customer.discounted(10000); //  67000
        assertEquals(67000, customer.getTotalPrice());
    }
    @DisplayName("컨트롤러_할인기능_테스트")
    @Test
    void checkControllerDiscountFunction(){
        try {
            System.setOut(new PrintStream(outputStreamCaptor));

            String input = "25\n" +
                    "크리스마스파스타-2,아이스크림-2,시저샐러드-1,제로콜라-2\n";
            /*
            25000 * 2 +
            5000 * 2 +
            8000 * 1 +
            3000 * 2
            = 74000
            74000
            - 3400 (DDay)
            - 2023 * 2 (Weekday)
            - 1000 (Star)
            = 65554, 별
             */
            inputStream = new ByteArrayInputStream(input.getBytes());
            System.setIn(inputStream);

            Application.main(new String[]{});

            assertThat(outputStreamCaptor.toString()).contains("74,000원",
                    "크리스마스 디데이 할인: -3,400원",
                    "평일 할인: -4,046원",
                    "특별 할인: -1,000원",
                    "-8,446원",
                    "65,554원",
                    "별");
        } finally {
            System.setOut(originalOut);
            System.setIn(originalIn);
        }
    }
}
