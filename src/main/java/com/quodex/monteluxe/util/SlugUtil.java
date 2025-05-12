package com.quodex.monteluxe.util;
public class SlugUtil {
    public static String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9\\s-]", "")   // remove non-alphanumeric
                .replaceAll("\\s+", "-")           // replace spaces with hyphen
                .replaceAll("-{2,}", "-")           // remove repeated hyphens
                .replaceAll("^-|-$", "");           // trim hyphens
    }
}
