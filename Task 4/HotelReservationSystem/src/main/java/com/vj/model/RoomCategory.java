package com.vj.model;

import java.math.BigDecimal;

public enum RoomCategory {
    STANDARD(new BigDecimal("2500"), 2),
    DELUXE(new BigDecimal("4500"), 3),
    SUITE(new BigDecimal("8000"), 4);

    private final BigDecimal defaultPrice;
    private final int defaultCapacity;

    RoomCategory(BigDecimal defaultPrice, int defaultCapacity) {
        this.defaultPrice = defaultPrice;
        this.defaultCapacity = defaultCapacity;
    }
    public BigDecimal getDefaultPrice() { return defaultPrice; }
    public int getDefaultCapacity() { return defaultCapacity; }
}
