package christmas.domain.model;

import christmas.constants.AppetizerConstants;

public class AppetizerMenu extends Menu implements Food{
    public AppetizerMenu(String name){
        for (AppetizerConstants appetizerEnum : AppetizerConstants.values()){
            if (name.equals(appetizerEnum.getName())){
                this.name = appetizerEnum.getName();
                this.price = appetizerEnum.getPrice();
                break;
            }
        }
    }
}
