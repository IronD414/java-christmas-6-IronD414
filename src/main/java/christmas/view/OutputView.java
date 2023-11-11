package christmas.view;

import christmas.domain.model.Customer;
import christmas.domain.model.Menu;
import java.text.NumberFormat;
import java.util.Map;

public class OutputView {
    public void printWelcome(){
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public void printMenu(Map<Menu, Integer> order) {
        System.out.println("\n<주문 메뉴>");
        for (Map.Entry<Menu, Integer> eachOrder : order.entrySet()){
            System.out.printf("%s %d개\n", eachOrder.getKey().getName(), eachOrder.getValue());
        }
    }
    public void printTotalPriceBeforeDiscount(Customer customer){
        System.out.println("\n<할인 전 총주문 금액>");
        NumberFormat numberFormat = NumberFormat.getInstance();
        System.out.printf("%s원\n", numberFormat.format(customer.getTotalPrice()));
    }
    public void printGiveAway(Menu giveAway){
        System.out.println("\n<증정 메뉴>");
        if (giveAway == null){
            System.out.println("없음");
            return;
        }
        System.out.printf("%s %d개\n", giveAway.getName(), 1);
    }
}
