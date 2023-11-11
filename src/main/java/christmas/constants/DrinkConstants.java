package christmas.constants;

import java.util.ArrayList;
import java.util.List;

public enum DrinkConstants {
    ZERO_COKE("제로콜라", 3000),
    RED_WINE("레드와인", 60000),
    CHAMPAGNE("샴페인", 25000);

    private final String name;
    private final int price;

    DrinkConstants(String name, int price) {
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
        for (DrinkConstants drinkEnum : DrinkConstants.values()) {
            names.add(drinkEnum.getName());
        }
        return names;
    }
}
