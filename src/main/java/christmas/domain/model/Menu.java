package christmas.domain.model;

import java.util.Objects;

public class Menu {
    String name;
    int price;

    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return price == menu.price && Objects.equals(name, menu.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
