package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.Customer;
import christmas.domain.model.DessertMenu;
import christmas.domain.model.MainMenu;
import christmas.domain.model.Menu;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.*;

public class ChristmasController {
    InputView inputView;
    OutputView outputView;
    Customer customer;
    int benefitPrice;

    public ChristmasController(){
        inputView = new InputView();
        outputView = new OutputView();
        customer = new Customer();
        benefitPrice = 0;
    }
    public void run(){
        outputView.printWelcome();
        customer.setVisitingDate(inputView.readDate());
        customer.setCart(inputView.readOrder());

        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", customer.getVisitingDate());
        outputView.printMenu(customer.getCart());
        outputView.printTotalPriceBeforeDiscount(customer);

        discountDDay(customer);
        discountDayOfWeek(customer);
    }
    private void discountDDay(Customer customer){
        int visitingDate = customer.getVisitingDate();
        if (visitingDate > 25) return;
        customer.discounted(900 + 100*visitingDate);
    }
    private void discountDayOfWeek(Customer customer){
        int visitingDate = customer.getVisitingDate();
        Map<Menu, Integer> cart = customer.getCart();
        if (visitingDate%7 == 1 || visitingDate%7 == 2){
            for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
                if (eachOrder.getKey() instanceof MainMenu) customer.discounted(2023 * eachOrder.getValue());
            }
            return;
        }
        for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
            if (eachOrder.getKey() instanceof DessertMenu) customer.discounted(2023 * eachOrder.getValue());
        }
    }
}
