package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class SecurityModule {

    private static final String HASH_ALGORITHM = "SHA-256";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateHash(String data) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
        byte[] encodedhash = digest.digest(data.getBytes());
        return bytesToHex(encodedhash);
    }

    public static String generateSalt() {
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashWithSalt(String data, String salt) throws NoSuchAlgorithmException {
        return generateHash(data + salt);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean verifyUser(String username, String password, Map<String, String> userCredentials) throws NoSuchAlgorithmException {
        if (userCredentials.containsKey(username)) {
            String storedPasswordHash = userCredentials.get(username);
            String salt = storedPasswordHash.substring(0, 24); // Assuming salt is stored at the beginning of the hash
            String calculatedHash = hashWithSalt(password, salt);
            return storedPasswordHash.equals(calculatedHash);
        }
        return false;
    }

    // This method would be used to register a new user with a hashed password
    public static void registerUser(String username, String password, Map<String, String> userCredentials) throws NoSuchAlgorithmException {
        String salt = generateSalt();
        String passwordHash = hashWithSalt(password, salt);
        userCredentials.put(username, salt + passwordHash);
    }

    // Example usage
    public static void main(String[] args) {
        try {
            Map<String, String> userCredentials = new HashMap<>();
            registerUser("user1", "password123", userCredentials);

            boolean isVerified = verifyUser("user1", "password123", userCredentials);
            System.out.println("User verification status: " + isVerified);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}