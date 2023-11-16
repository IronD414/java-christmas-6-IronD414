package christmas.domain.model;

import christmas.constants.DrinkConstants;

public class DrinkMenu extends Menu implements Drink{
    public DrinkMenu(String name){
        for (DrinkConstants drinkEnum : DrinkConstants.values()){
            if (name.equals(drinkEnum.getName())){
                this.name = drinkEnum.getName();
                this.price = drinkEnum.getPrice();
                break;
            }
        }
    }
}
