package christmas;

import christmas.domain.model.AppetizerMenu;
import christmas.domain.model.Customer;
import christmas.domain.model.DrinkMenu;
import christmas.domain.model.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionTest {
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
}
