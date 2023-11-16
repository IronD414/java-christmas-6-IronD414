package christmas;

import christmas.constants.Standards;
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

        System.out.printf("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n", Standards.EVENT_MONTH.getValue(),
                customer.getVisitingDate());
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
        outputView.printEventBadge(eventBadge);
    }
    private void presentGiveAway(Customer customer){
        if (customer.getTotalPrice() >= Standards.MIN_GIVEAWAY_PRICE.getValue())
            giveAway = new DrinkMenu("샴페인");
    }
    private int discountDDay(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        if (visitingDate > 25) return benefitPrice;
        benefitPrice = (Standards.MIN_DISCOUNT_DDAY.getValue() - 100) + 100*visitingDate;
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private int discountWeekday(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        Map<Menu, Integer> cart = customer.getCart();
        if (cart == null) return 0;
        if (visitingDate%7 == 1 || visitingDate%7 == 2) return benefitPrice;
        for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
            if (eachOrder.getKey() instanceof DessertMenu) benefitPrice
                    = Standards.DISCOUNT_WEEKDAY.getValue() * eachOrder.getValue();
        }
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private int discountWeekend(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        Map<Menu, Integer> cart = customer.getCart();
        if (cart == null) return 0;
        if (visitingDate%7 == 1 || visitingDate%7 == 2){
            for (Map.Entry<Menu, Integer> eachOrder : cart.entrySet()){
                if (eachOrder.getKey() instanceof MainMenu) benefitPrice
                        += Standards.DISCOUNT_WEEKEND.getValue() * eachOrder.getValue();
            }
            customer.discounted(benefitPrice);
            return benefitPrice;
        }
        return 0;
    }
    private int discountStar(Customer customer){
        int benefitPrice = 0;
        int visitingDate = customer.getVisitingDate();
        if (visitingDate%7 == 3 || visitingDate == 25) benefitPrice = Standards.DISCOUNT_STAR.getValue();
        customer.discounted(benefitPrice);
        return benefitPrice;
    }
    private void giveEventBadge(){
        int totalBenefitPrice = getTotalBenefitPrice();
        if (giveAway != null) totalBenefitPrice += giveAway.getPrice();
        if (totalBenefitPrice >= Standards.EVENT_BADGE_STAR.getValue()) eventBadge = "별";
        if (totalBenefitPrice >= Standards.EVENT_BADGE_TREE.getValue()) eventBadge = "트리";
        if (totalBenefitPrice >= Standards.EVENT_BADGE_SANTA.getValue()) eventBadge = "산타";
    }
    private int getTotalBenefitPrice(){
        int totalBenefitPrice = 0;
        for (int price : benefitPrice){
            totalBenefitPrice += price;
        }
        return totalBenefitPrice;
    }
}
