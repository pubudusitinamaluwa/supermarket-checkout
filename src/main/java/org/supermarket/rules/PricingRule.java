package org.supermarket.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Pricing rules reference
 */
@AllArgsConstructor
@Getter
public class PricingRule {
    private final String item;
    private final double unitPrice;
    private final SpecialPrice specialPrice;
}
