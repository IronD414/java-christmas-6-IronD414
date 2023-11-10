package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.*;

public class Application {
    public static void main(String[] args) {
        Map<String, Integer> menuAppetizer = Map.of(
                "양송이수프", 6000,
                "타파스", 5500,
                "시저샐러드", 8000
        );
        Map<String, Integer> menuMain = Map.of(
                "티본스테이크", 55000,
                "바비큐립", 54000,
                "해산물파스타", 35000,
                "크리스마스파스타", 25000
        );
        Map<String, Integer> menuDessert = Map.of(
                "초코케이크", 15000,
                "아이스크림", 5000
        );
        Map<String, Integer> menuDrink = Map.of(
                "제로콜라", 3000,
                "레드와인", 60000,
                "샴페인", 25000
        );
        List<Map<String, Integer>> menus = List.of(menuAppetizer, menuMain, menuDessert, menuDrink);

        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        String userInput;

        int visitingDay;
        while (true){
            userInput = Console.readLine();
            try{
                visitingDay = Integer.parseInt(userInput);  //  NumberFormatException -> IllegalArgumentException 변환을 위해 Integer.parseInt()를 메서드로 뺄 필요 있음
                if (visitingDay < 1 || visitingDay > 31) throw new IllegalArgumentException();
                break;
            }catch (IllegalArgumentException e){
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        Map<String, Integer> order = new HashMap<>();
        while (true){
            try{
                userInput = Console.readLine();
                List<String> dupeChecker = new ArrayList<>();
                boolean thereIsFoodMenu = false;

                String[] splitUserInput = userInput.split(",");
                for (String eachUserInput : splitUserInput){
                    if (!eachUserInput.contains("-")) throw new IllegalArgumentException();

                    String[] splitEachUserInput = eachUserInput.split("-");
                    String userInputMenuName = splitEachUserInput[0];

                    dupeChecker.add(userInputMenuName);

                    int userInputMenuQuantity = Integer.parseInt(splitEachUserInput[1]);

                    boolean inMenus = false;
                    for (Map<String, Integer> specificMenus : menus){
                        if (specificMenus.containsKey(userInputMenuName)) {
                            inMenus = true;
                            break;
                        }
                    }
                    if (!inMenus) throw new IllegalArgumentException();
                    if (userInputMenuQuantity < 1) throw new IllegalArgumentException();

                    order.put(userInputMenuName, userInputMenuQuantity);
                }
                if (dupeChecker.size() != new HashSet<>(dupeChecker).size()) throw new IllegalArgumentException();
                for (String orderedMenuName : order.keySet()){
                    if (!menus.get(3).containsKey(orderedMenuName)) thereIsFoodMenu = true;
                }
                if (!thereIsFoodMenu) throw new IllegalArgumentException();
                break;
            }catch (IllegalArgumentException e){
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }
}
