package org.example;

public class Verifiers {
    public static boolean verifyUserId(String userId) {
        return Variables.USER_ID.equals(userId);
    }
}
