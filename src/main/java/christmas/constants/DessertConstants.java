package christmas.constants;

import java.util.ArrayList;
import java.util.List;

public enum DessertConstants {
    CHOCOLATE_CAKE("초코케이크", 15000),
    ICE_CREAM("아이스크림", 5000);

    private final String name;
    private final int price;

    DessertConstants(String name, int price) {
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
        for (DessertConstants dessertEnum : DessertConstants.values()) {
            names.add(dessertEnum.getName());
        }
        return names;
    }
}
