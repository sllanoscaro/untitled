import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;

public class generateHash {
    public String createPasswordHashWithSalt(final String textToHash) {
        try {
            byte[] salt = createSalt();
            System.out.println(Arrays.toString(salt));
            return createSaltedHash(textToHash, salt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertToHex(final byte[] messageDigest) {
        BigInteger bigint = new BigInteger(1, messageDigest);
        String hexText = bigint.toString(16);
        while (hexText.length() < 32) {
            hexText = "0".concat(hexText);
        }
        return hexText;
    }

    private String createSaltedHash(String textToHash, byte[] salt)
            throws NoSuchAlgorithmException {

        String saltedHash = null;
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");

        //Add salted bytes to digest
        md.update(salt);

        //Get the hash's bytes
        byte[] bytes = md.digest(textToHash.getBytes());

        //Convert it to hexadecimal format to
        //get complete salted hash in hex format
        saltedHash = convertToHex(bytes);
        return saltedHash;
    }

    //Create salt
    private byte[] createSalt()
            throws NoSuchAlgorithmException,
            NoSuchProviderException {

        //Always use a SecureRandom generator for random salt
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}