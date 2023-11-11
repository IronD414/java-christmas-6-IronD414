package christmas;

import christmas.domain.model.*;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.*;

public class ChristmasController {
    InputView inputView;
    OutputView outputView;
    Customer customer;
    List<Integer> benefitPrice;
    Menu giveAway;
    String eventBadge;

    public ChristmasController(){
        inputView = new InputView();
        outputView = new OutputView();
        customer = new Customer();
        benefitPrice = new ArrayList<>();
        giveAway = null;
        eventBadge = "없음";
    }
    public void run(){
        outputView.printWelcome();
        customer.setVisitingDate(inputView.readDate());
        customer.setCart(inputView.readOrder());

        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", customer.getVisitingDate());
        outputView.printMenu(customer.getCart());
        outputView.printTotalPriceBeforeDiscount(customer);

        presentGiveAway(customer);
        benefitPrice.add(discountDDay(customer));
        benefitPrice.add(discountWeekday(customer));
        benefitPrice.add(discountWeekend(customer));
        benefitPrice.add(discountStar(customer));
        giveEventBadge();

        outputView.printGiveAway(giveAway);
        outputView.printBenefit(benefitPrice, giveAway);
        outputView.printTotalBenefitPrice(getTotalBenefitPrice(), giveAway);
        outputView.printTotalPriceAfterDiscount(customer);
    }
    private void presentGiveAway(Customer customer){
        if (customer.getTotalPrice() >= 12000)
            giveAway = new DrinkMenu("샴페인");
    }
    private int discountDDay(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        if (visitingDate > 25) return benefitPrice;
        benefitPrice = 900 + 100*visitingDate;
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private int discountWeekday(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        Map<Menu, Integer> cart = customer.getCart();
        if (visitingDate%7 == 1 || visitingDate%7 == 2) return benefitPrice;
        for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
            if (eachOrder.getKey() instanceof DessertMenu) benefitPrice = 2023 * eachOrder.getValue();
        }
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private int discountWeekend(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        Map<Menu, Integer> cart = customer.getCart();
        if (visitingDate%7 == 1 || visitingDate%7 == 2){
            for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
                if (eachOrder.getKey() instanceof MainMenu) benefitPrice += 2023 * eachOrder.getValue();
            }
            customer.discounted(benefitPrice);
            return benefitPrice;
        }
        return 0;
    }
    private int discountStar(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        if (visitingDate%7 == 3 || visitingDate == 25) benefitPrice = 1000;
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private void giveEventBadge(){
        if (getTotalBenefitPrice() >= 5000) eventBadge = "별";
        if (getTotalBenefitPrice() >= 10000) eventBadge = "트리";
        if (getTotalBenefitPrice() >= 20000) eventBadge = "산타";
    }
    private int getTotalBenefitPrice(){
        int totalBenefitPrice = 0;
        for (int price : benefitPrice){
            totalBenefitPrice += price;
        }
        return totalBenefitPrice;
    }
}
