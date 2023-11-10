package christmas;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {


        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        String userInput;

        int visitingDay;
        while (true){
            userInput = Console.readLine();
            try{
                visitingDay = Integer.parseInt(userInput);
                if (visitingDay < 1 || visitingDay > 31) throw new NumberFormatException();
                break;
            }catch (NumberFormatException e){
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }

        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
    }
}
