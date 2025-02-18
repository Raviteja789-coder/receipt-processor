package com.assignment.fetch.service;

import com.assignment.fetch.model.Item;
import com.assignment.fetch.model.Receipt;

import java.util.List;

public class ReceiptPointCalculator {

    /** Get the total points for the receipt by applying the set of rules */
    public int getPoints(Receipt receipt) {
        int totalPoints = 0;
        totalPoints += getRetailerNamePoints(receipt.getRetailer());
        totalPoints += getTotalPriceRoundedPoints(Double.parseDouble(receipt.getTotal()));
        totalPoints += getTotalPriceMultiplyPoints(Double.parseDouble(receipt.getTotal()));
        totalPoints += getTwoItemsPoint(receipt.getItems());
        totalPoints += getItemTrimmedDescriptionPoints(receipt.getItems());
        totalPoints += getPurchaseDayOddPoints(receipt.getPurchaseDate());
        totalPoints += getPurchaseTimePoints(receipt.getPurchaseTime());
        return totalPoints;
    }

    /** One point for every alphanumeric character in the retailer name */
    private static int getRetailerNamePoints(String str) {
        int count = 0;
        for(int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);
            if((ch >= 48 && ch <= 57) || (ch >= 65 && ch <= 90) || (ch >= 97 && ch <= 122))
                count = count + 1;
        }
        return count;
    }

    /** 50 points if the total is a round dollar amount with no cents */
    private static int getTotalPriceRoundedPoints(double totalPrice) {
        if(totalPrice % 1 == 0) {
            return 50;
        }
        return 0;
    }

    /** 25 points if the total is a multiple of 0.25 */
    private static int getTotalPriceMultiplyPoints(double totalPrice) {
        if(totalPrice % 0.25 == 0) {
            return 25;
        }
        return 0;
    }

    /** 5 points for every two items on the receipt */
    private static int getTwoItemsPoint(List<Item> items) {
        int size = items.size();
        int TWO_ITEMS_POINT = 5;
        return (size / 2) * TWO_ITEMS_POINT;
    }

    /** If the trimmed length of the item description is a multiple of 3, multiply the price by 0.2
     * and round up to the nearest integer. The result is the number of points earned */
    private static int getItemTrimmedDescriptionPoints(List<Item> items) {
        int sum = 0;
        for(Item item: items) {
            int size = item.getShortDescription().trim().length();
            if(size % 3 == 0) {
                double totalPrice = Double.parseDouble(item.getPrice()) * 0.2;
                sum  = sum + (int) Math.ceil(totalPrice);
            }
        }
        return sum;
    }

    /** 6 points if the day in the purchase date is odd */
    private static int getPurchaseDayOddPoints(String purchaseDate) {
        String []dateSplit = purchaseDate.split("-");
        if(dateSplit.length >= 3) {
            int day = Integer.parseInt(dateSplit[2]);
            if(day % 2 != 0) {
                return 6;
            }
        }
        return 0;
    }

    /** 10 points if the time of purchase is after 2:00pm and before 4:00pm */
    private static int getPurchaseTimePoints(String purchaseTime) {
        String []timeSplit = purchaseTime.split(":");
        if(timeSplit.length >= 1) {
            int hour = Integer.parseInt(timeSplit[0]);
            if(hour >= 14 && hour < 16) {
                return 10;
            }
        }
        return 0;
    }
}
