package christmas.domain.model;

import christmas.constants.DessertConstants;

public class DessertMenu extends Menu implements Food{
    public DessertMenu(String name){
        for (DessertConstants dessertEnum : DessertConstants.values()){
            if (name.equals(dessertEnum.getName())){
                this.name = dessertEnum.getName();
                this.price = dessertEnum.getPrice();
                break;
            }
        }
    }
}
