public class Main {
    public static void main(String[] args) {
        generateHash hashGenerator = new generateHash();
        String textToHash = "mySecurePassword";
        String saltedHash = hashGenerator.createPasswordHashWithSalt(textToHash);

        if (saltedHash != null) {
            System.out.println("Salted Hash: " + saltedHash);
        } else {
            System.out.println("Error al generar el hash.");
        }
    }
}
