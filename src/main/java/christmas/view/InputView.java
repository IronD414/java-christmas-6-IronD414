package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        while (true){
            try{
                int visitingDate = validateConvertingStringToInt(input);
                if (visitingDate < 1 || visitingDate > 31) throw new IllegalArgumentException();
                return visitingDate;
            }catch (IllegalArgumentException e){
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }
    private int validateConvertingStringToInt(String s) throws IllegalArgumentException{
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new IllegalArgumentException();
        }
    }
}
