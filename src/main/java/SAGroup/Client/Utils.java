package SAGroup.Client;

import java.util.Scanner;

public class Utils {

    // En metod för att konvertera en InputStream till en String
    public static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static boolean isSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }

    public static void printAsJson(Object obj) {
        try {
            String json = new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
            System.out.println(json);
        } catch (com.fasterxml.jackson.core.JsonProcessingException e) {
            System.err.println("Error while converting object to JSON string: " + e.getMessage());
        }
    }

    public static void delay(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
