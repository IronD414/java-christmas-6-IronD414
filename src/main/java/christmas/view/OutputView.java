package christmas.view;

import christmas.domain.model.Menu;

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
    // ...
}
