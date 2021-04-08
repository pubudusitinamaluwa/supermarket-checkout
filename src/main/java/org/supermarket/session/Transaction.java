package org.supermarket.session;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.supermarket.rules.PricingRule;
import org.supermarket.rules.SpecialPrice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Transaction reference
 */
@Getter
@RequiredArgsConstructor
public class Transaction {
    private final long transactionId;
    private final List<Item> basket = new ArrayList<>();

    /**
     * Add item to basket
     *
     * @param item Item
     */
    public void addItem(Item item) {
        this.basket.add(item);
    }

    /**
     * Calculate total price of items in the basket including special prices
     * @param pricingRuleMap Pricing rules as a map
     * @return Total value of the items in the basket
     */
    public double getTotalPrice(Map<String, PricingRule> pricingRuleMap) {
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
