package com.pdffiller.autotests.utils;

import java.util.Random;

final public class DataGenerator {

    public static String generateEmailAddress() {
        StringBuilder stringBuilder = new StringBuilder();
        String name = getRandomString();
        stringBuilder.append(name).append("@mail.com");
        return stringBuilder.toString();
    }

    public static String getRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        StringBuilder stringBuilder = new StringBuilder();
        Random rand = new Random();
        stringBuilder.append("TEST");
        while (stringBuilder.length() < 10) { // length of the random string.
            int index = (int) (rand.nextFloat() * chars.length());
            stringBuilder.append(chars.charAt(index));
        }
        return stringBuilder.toString();
    }
}
