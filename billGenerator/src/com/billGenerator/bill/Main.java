package com.codecool.bill;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static final double NORMAL_BEER = 1.2;
    private static final double DISCOUNT_BEER = 1;

    public static void main(String[] args) {
        List<Item> data = new ArrayList<>();
        data.add(new Item(1001, "beer", 1, 1.20));
        data.add(new Item(1001, "beer", 2, 2.0));
        data.add(new Item(1001, "beer", 3, 3.2));
        data.add(new Item(1243, "eggs", 1, 0.20));
        data.add(new Item(3401, "chocolate", 1, 3.15));
        data.add(new Item(1243, "eggs", 10, 1.90));

        List<Integer> barcodeList = getBasketCodes(data);
        calculateTotal(barcodeList);
        barcodeList.forEach(System.out::println);
    }

    public static List<Integer> getBasketCodes(List<Item> data) {
        List<Integer> barcodeList = new ArrayList<>();
        for (Item e : data) {
            if (e.getAmount() > 1) {
                for (int i = 1; i < e.getAmount(); i++) {
                    barcodeList.add(e.getBarcode());
                }
            }
            barcodeList.add(e.getBarcode());
        }

        return barcodeList;
    }


    public static BigDecimal getBarcodeFrequency(List barcodeList, int barcode) {
        return BigDecimal.valueOf(Collections.frequency(barcodeList, barcode));
    }


    public static BigDecimal calculateTotal(List<Integer> barcodeList) {
        BigDecimal price = BigDecimal.ZERO;

        List<Integer> distinctBarcodeList;

        distinctBarcodeList = barcodeList.stream()
                .distinct()
                .collect(Collectors.toList());

        for (Integer code : distinctBarcodeList) {
            BigDecimal frequency = getBarcodeFrequency(barcodeList, code);
            if (( frequency.remainder(new BigDecimal(2)).compareTo(BigDecimal.ZERO) == 0 )) {
                price = price
                        .add(frequency.add((getPriceByBarcode(code, true))));
            } else {
                price = price.add(
                        ((frequency
                                .subtract(new BigDecimal(1)))
                                .multiply(getPriceByBarcode(code, true)))
                                .add(getPriceByBarcode(code, false)));
            }
        }
        System.out.println(price);
        return price;
    }

    ;

    public static BigDecimal getPriceByBarcode(int code, boolean isPromo) {
        BigDecimal priceIfDiscount = BigDecimal.ZERO;
        return switch (code) {
            case 1001 -> (isPromo) ? Prices.BEER.getDiscount_price() : Prices.BEER.getNormal_price();
            case 1243 -> (isPromo) ? Prices.EGG.getDiscount_price() : Prices.EGG.getNormal_price();
            case 3401 -> (isPromo) ? Prices.CHOCOLATE.getDiscount_price() : Prices.CHOCOLATE.getNormal_price();
            default -> priceIfDiscount ;
        };
    }

}

