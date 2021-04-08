package org.supermarket.session;

import org.junit.jupiter.api.Test;
import org.supermarket.rules.PricingRule;
import org.supermarket.rules.SpecialPrice;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CheckoutTest {

    @Test
    void addItem() {
        Checkout checkout = new Checkout(null);
        checkout.scan(new Item("A"));

        assertEquals(1, checkout.getBasket().size());
    }

    @Test
    void getTotalPrice() {
        System.out.println("This test method should be run");
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new PricingRule("A", 60, new SpecialPrice(2, 100)));
        pricingRules.add(new PricingRule("B", 20, null));

        Checkout checkout = new Checkout(pricingRules);
        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("A"));
        checkout.scan(new Item("B"));

        double total = checkout.getTotalPrice();

        assertEquals(180, total);
    }
}
