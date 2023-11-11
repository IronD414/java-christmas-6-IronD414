package christmas.constants;

import java.util.ArrayList;
import java.util.List;

public enum MainConstants {
    T_BONE_STEAK("티본스테이크", 55000),
    BARBEQUE_RIBS("바비큐립", 54000),
    SEAFOOD_PASTA("해산물파스타", 35000),
    CHRISTMAS_PASTA("크리스마스파스타", 25000);

    private final String name;
    private final int price;

    MainConstants(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    public static List<String> getNameAll() {
        List<String> names = new ArrayList<>();
        for (MainConstants mainEnum : MainConstants.values()) {
            names.add(mainEnum.getName());
        }
        return names;
    }
}
