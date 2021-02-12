package com.codecool.bill;

import java.math.BigDecimal;

public enum Prices {
    BEER(1.2,1.0), EGG(0.2,0.19),
    BREAD(1.5,1.2), CHOCOLATE(3.15, 2.9);
    private final double normal_price;
    private final double discount_price;
    Prices(double normal_price, double discount_price) {
        this.normal_price = normal_price;
        this.discount_price = discount_price;
    }

    public BigDecimal getNormal_price() {
        return BigDecimal.valueOf(normal_price);
    }

    public BigDecimal getDiscount_price() {
        return BigDecimal.valueOf(discount_price);
    }
}
