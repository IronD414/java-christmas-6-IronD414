package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.model.*;
import christmas.constants.*;

import java.util.*;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input;
        while (true){
            try{
                input = Console.readLine();
                int visitingDate = validateConvertingStringToInt(input);
                if (visitingDate < Standards.MONTHLY_FIRST_DATE.getValue() ||
                        visitingDate > Standards.MONTHLY_LAST_DATE.getValue()) throw new IllegalArgumentException();
                return visitingDate;
            }catch (IllegalArgumentException e){
                System.out.println(ErrorMessages.INVALID_DATE.getMessage());
            }
        }
    }
    public Map<Menu, Integer> readOrder(){
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<Menu, Integer> order;
        String userInput;
        while (true){
            try{
                userInput = Console.readLine();
                order = convertUserInputIntoOrder(userInput);

                validateOrderDupeCheck(order);
                validateOnlyDrinkCheck(order);
                validateTotalMenuQuantityCheck(order);
                break;
            }catch (IllegalArgumentException e){
                System.out.println(ErrorMessages.INVALID_ORDER.getMessage());
            }
        }
        return order;
    }
    private Map<Menu, Integer> convertUserInputIntoOrder(String userInput){
        Map<Menu, Integer> order = new HashMap<>();
        String[] splitUserInput = userInput.split(",");
        for (String eachUserInput : splitUserInput){
            if (!eachUserInput.contains("-")) throw new IllegalArgumentException();

            String[] splitEachUserInput = eachUserInput.split("-");
            if (splitEachUserInput.length != 2) throw new IllegalArgumentException();

            String userInputMenuName = splitEachUserInput[0];
            int userInputMenuQuantity = validateConvertingStringToInt(splitEachUserInput[1]);

            if (userInputMenuQuantity < 1) throw new IllegalArgumentException();

            order.put(validateInMenuCheckAndGenerateMenu(userInputMenuName), userInputMenuQuantity);
        }
        return order;
    }
    private void validateOrderDupeCheck(Map<Menu, Integer> order) throws IllegalArgumentException{
        Set<Menu> orderKeySet = order.keySet();
        if (orderKeySet.size() != new HashSet<>(orderKeySet).size()) throw new IllegalArgumentException();
    }
    private void validateOnlyDrinkCheck(Map<Menu, Integer> order) throws IllegalArgumentException{
        Set<Menu> orderKeySet = order.keySet();
        for (Menu key : orderKeySet){
            if (!(key instanceof DrinkMenu)) return;
        }
        throw new IllegalArgumentException();
    }
    private void validateTotalMenuQuantityCheck(Map<Menu, Integer> order) throws IllegalArgumentException{
        int totalMenuQuantity = 0;
        for (Menu orderKey : order.keySet()){
            totalMenuQuantity += order.get(orderKey);
        }
        if (totalMenuQuantity > Standards.MAX_ORDER_QUANTITY.getValue()) throw new IllegalArgumentException();
    }
    private Menu validateInMenuCheckAndGenerateMenu(String name) throws IllegalArgumentException{
        if (AppetizerConstants.getNameAll().contains(name)) return new AppetizerMenu(name);
        if (DessertConstants.getNameAll().contains(name)) return new DessertMenu(name);
        if (DrinkConstants.getNameAll().contains(name)) return new DrinkMenu(name);
        if (MainConstants.getNameAll().contains(name)) return new MainMenu(name);
        throw new IllegalArgumentException();
    }
    private int validateConvertingStringToInt(String s) throws IllegalArgumentException{
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }
    }
}
