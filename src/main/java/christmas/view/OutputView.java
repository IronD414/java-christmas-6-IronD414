package christmas.view;

import christmas.domain.model.Customer;
import christmas.domain.model.Menu;
import java.text.NumberFormat;
import java.util.List;
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
    public void printBenefit(List<Integer> benefitPrice, Menu giveAway){
        System.out.println("\n<혜택 내역>");
        NumberFormat numberFormat = NumberFormat.getInstance();
        if (benefitPrice.get(0) > 0){
            System.out.printf("크리스마스 디데이 할인: -%s원\n", numberFormat.format(benefitPrice.get(0)));
        }if (benefitPrice.get(1) > 0){
            System.out.printf("평일 할인: -%s원\n", numberFormat.format(benefitPrice.get(1)));
        }if (benefitPrice.get(2) > 0){
            System.out.printf("주말 할인: -%s원\n", numberFormat.format(benefitPrice.get(2)));
        }if (benefitPrice.get(3) > 0){
            System.out.printf("특별 할인: -%s원\n", numberFormat.format(benefitPrice.get(3)));
        }if (giveAway != null){
            System.out.printf("증정 이벤트: -%s원\n", numberFormat.format(giveAway.getPrice()));
        }
    }
}
