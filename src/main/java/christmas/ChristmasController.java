package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.Customer;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.*;

public class ChristmasController {
    InputView inputView;
    OutputView outputView;
    Customer customer;

    public ChristmasController(){
        inputView = new InputView();
        outputView = new OutputView();
        customer = new Customer();
    }
    public void run(){
        outputView.printWelcome();
        customer.setVisitingDate(inputView.readDate());
        customer.setCart(inputView.readOrder());

        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", customer.getVisitingDate());
        outputView.printMenu(customer.getCart());
        outputView.printTotalPrice(customer.getCart());
    }
}
