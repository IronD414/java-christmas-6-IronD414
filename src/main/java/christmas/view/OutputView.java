package christmas.view;

import christmas.constants.Standards;
import christmas.domain.model.Customer;
import christmas.domain.model.Menu;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

public class OutputView {
    private NumberFormat numberFormat = NumberFormat.getInstance();
    public void printWelcome(){
        System.out.printf("안녕하세요! 우테코 식당 %d월 이벤트 플래너입니다.\n", Standards.EVENT_MONTH.getValue());
    }
    public void printMenu(Map<Menu, Integer> order) {
        System.out.println("\n<주문 메뉴>");
        for (Map.Entry<Menu, Integer> eachOrder : order.entrySet()){
            System.out.printf("%s %d개\n", eachOrder.getKey().getName(), eachOrder.getValue());
        }
    }
    public void printTotalPriceBeforeDiscount(Customer customer){
        System.out.println("\n<할인 전 총주문 금액>");
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
        if (benefitPrice.get(0) == 0 && benefitPrice.get(1) == 0 && benefitPrice.get(2) == 0
                && benefitPrice.get(3) == 0 && giveAway == null){
            System.out.println("없음");
        }
    }
    public void printTotalBenefitPrice(int benefitPrice, Menu giveAway){
        System.out.println("\n<총혜택 금액>");
        int totalPrice = benefitPrice;
        if (giveAway != null) totalPrice += giveAway.getPrice();
        System.out.printf("-%s원\n", numberFormat.format(totalPrice));
    }
    public void printTotalPriceAfterDiscount(Customer customer){
        System.out.println("\n<할인 후 예상 결제 금액>");
        System.out.printf("%s원\n", numberFormat.format(customer.getTotalPrice()));
    }
    public void printEventBadge(String badge){
        System.out.println("\n<12월 이벤트 배지>");
        System.out.println(badge);
    }
}
