package org.supermarket.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Special item price reference
 */
@AllArgsConstructor
@Getter
public class SpecialPrice {
    private final int itemCount;
    private final double price;
}
