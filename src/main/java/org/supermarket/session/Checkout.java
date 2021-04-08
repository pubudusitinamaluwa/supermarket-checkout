package org.supermarket.session;

import lombok.Getter;
import org.supermarket.rules.PricingRule;
import org.supermarket.rules.SpecialPrice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Transaction reference
 */
@Getter
public class Checkout {
    private final String transactionId;
    private final List<Item> basket = new ArrayList<>();
    Map<String, PricingRule> pricingRuleMap;

    public Checkout(List<PricingRule> pricingRules) {
        this.transactionId = UUID.randomUUID().toString();
        // Generate pricing rules map
        if (pricingRules == null) {
            pricingRuleMap = new HashMap<>();
        } else {
            this.pricingRuleMap = pricingRules.stream().collect(Collectors.toMap(PricingRule::getItem, item -> item));
        }
    }

    /**
     * Add item to basket
     *
     * @param item Item
     */
    public void scan(Item item) {
        this.basket.add(item);
    }

    /**
     * Calculate total price of items in the basket including special prices
     *
     * @return Total value of the items in the basket
     */
    public double getTotalPrice() {
        double total = 0;
        // Grout items in the basket by item id and collect as a Map (ItemId -> Item List)
        Map<String, List<Item>> groupedItems = basket.stream().collect(Collectors.groupingBy(Item::getId));
        // Iterate over each grouped item set
        for (Map.Entry<String, List<Item>> entry : groupedItems.entrySet()) {
            String itemId = entry.getKey();
            int itemCount = entry.getValue().size();
            PricingRule pricingRule = pricingRuleMap.get(itemId);
            if (pricingRule != null) {
                double unitPrice = pricingRule.getUnitPrice();
                SpecialPrice specialPrice = pricingRule.getSpecialPrice();
                // Apply special pricing if applicable
                if (specialPrice != null) {
                    int specialPriceMinItemCount = specialPrice.getItemCount();
                    int specialPriceChunks = itemCount / specialPriceMinItemCount;
                    int remainder = itemCount % specialPriceMinItemCount;
                    total += (specialPriceChunks * specialPrice.getPrice() + remainder * unitPrice);
                } else {
                    total += unitPrice * itemCount;
                }
            }
        }
        return total;
    }
}
