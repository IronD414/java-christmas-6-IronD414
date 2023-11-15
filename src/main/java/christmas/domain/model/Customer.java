package christmas.domain.model;

import java.util.Map;

public class Customer {
    int visitingDate;
    Map<Menu, Integer> cart;
    int totalPrice;

    public void setVisitingDate(int visitingDate){
        this.visitingDate = visitingDate;
    }
    public int getVisitingDate(){
        return visitingDate;
    }
    public void setCart(Map<Menu, Integer> cart){
        this.cart = cart;
        calculateTotalPrice();
    }
    public Map<Menu, Integer> getCart(){
        return cart;
    }
    public int getTotalPrice(){
        return totalPrice;
    }
    public void discounted(int discountedPrice){
        totalPrice -= discountedPrice;
    }
    private void calculateTotalPrice(){
        if (cart == null) return;
        totalPrice = 0;
        for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
            totalPrice += eachOrder.getValue() * eachOrder.getKey().getPrice();
        }
    }
}
