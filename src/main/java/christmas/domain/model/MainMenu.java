package christmas.domain.model;

import christmas.constants.MainConstants;

public class MainMenu extends Menu implements Food{
    public MainMenu(String name){
        for (MainConstants mainEnum : MainConstants.values()){
            if (name.equals(mainEnum.getName())){
                this.name = mainEnum.getName();
                this.price = mainEnum.getPrice();
                break;
            }
        }
    }
}
