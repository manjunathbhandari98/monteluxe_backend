package com.quodex.monteluxe.util;

import java.util.UUID;

public class IdGenerator {

    // General UUID Generator
    public static UUID generateUUID() {
        return UUID.randomUUID();
    }

    public static String generateUserId(){
        return "ML-USER-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Order ID: e.g., ORD-8A12F0B3
    public static String generateOrderId() {
        return "ML-ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Product Code: e.g., PROD-F4B9C2A1
    public static String generateProductCode() {
        return "ML-PROD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    // Wishlist ID or Guest Cart ID
    public static String generateGuestCartId() {
        return "ML-CART-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateAddressId(){
        return "ML-ADDR-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public static String generateCategoryId(){
        return "ML-COL-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

