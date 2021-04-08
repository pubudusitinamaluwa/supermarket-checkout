package org.supermarket;

import org.supermarket.rules.PricingRule;
import org.supermarket.rules.SpecialPrice;
import org.supermarket.session.Item;
import org.supermarket.session.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class application {
    public static void main(String[] args) {
        // Add pricing rules
        List<PricingRule> pricingRules = new ArrayList<>();
        pricingRules.add(new PricingRule("A", 60, new SpecialPrice(3, 140)));
        pricingRules.add(new PricingRule("B", 30, new SpecialPrice(2, 45)));
        pricingRules.add(new PricingRule("C", 20, null));
        pricingRules.add(new PricingRule("D", 10, null));

        // Generate pricing rules map
        Map<String, PricingRule> pricingRuleMap = pricingRules.stream().collect(Collectors.toMap(PricingRule::getItem, item -> item));

        // Create transaction
        Transaction transaction = new Transaction(1);
        // Add random items to the bucket
        Random r = new Random();
        String alphabet = "ABCD";
        for (int i = 0; i < r.nextInt(7) + 3; i++) {
            String randomItemId = String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));
            transaction.addItem(new Item(randomItemId));
            System.out.println(randomItemId);
        }
        // Calculate total price
        double totalPrice = transaction.getTotalPrice(pricingRuleMap);
        System.out.println("Total = $" + totalPrice);
    }
}
