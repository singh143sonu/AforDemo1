package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class LinkValidator {

    public static boolean isLinkBroken(String linkUrl) {
        try {
            URL url = new URL(linkUrl);
            
            // Skip non-HTTP protocols (like mailto:, tel:, etc.)
            String protocol = url.getProtocol().toLowerCase();
            if (!protocol.equals("http") && !protocol.equals("https")) {
                return false; // Consider non-HTTP links as not broken
            }
            
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.connect();
            int responseCode = connection.getResponseCode();

            return responseCode >= 400; // Broken if 4xx or 5xx
        } catch (IOException | ClassCastException e) {
            return true; // Treat exceptions as broken
        }
    }
}
