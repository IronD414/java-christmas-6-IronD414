package christmas.constants;

import java.util.ArrayList;
import java.util.List;

public enum AppetizerConstants {
    BUTTON_MUSHROOM_SOUP("양송이수프", 6000),
    TAPAS("타파스", 5500),
    CAESAR_SALAD("시저샐러드", 8000);

    private final String name;
    private final int price;

    AppetizerConstants(String name, int price) {
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
        for (AppetizerConstants appetizerEnum : AppetizerConstants.values()) {
            names.add(appetizerEnum.getName());
        }
        return names;
    }
}
