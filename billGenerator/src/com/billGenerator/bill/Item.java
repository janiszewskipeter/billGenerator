package com.billGenerator.bill;

import java.math.BigDecimal;

public class Item {
    private final int barcode;
    private final String name;
    private final int amount;
    private final double price;

    public Item(int barcode, String name, int amount, double price) {
        this.barcode = barcode;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public int getBarcode() {
        return barcode;
    }

    public int getAmount() {
        return amount;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}
