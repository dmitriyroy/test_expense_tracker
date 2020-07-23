package dmroy.expensetracker.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptedPasswordUtils {

    // Encrypte Password with BCryptPasswordEncoder
    public static String encryptedPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = "123";

        System.out.println("------------------------------");

        String encryptedPassword1 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword1);
        System.out.println("match = " + encoder.matches(password, "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword1));

        System.out.println("--------//////////////--------");

        String encryptedPassword2 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword2);
        System.out.println("match = " + encoder.matches(password, "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword2));

        System.out.println("--------////////////// --------");

        String encryptedPassword3 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword3);
        System.out.println("match = " + encoder.matches(password, "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword3));

        System.out.println("--------////////////// --------");

        String encryptedPassword4 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword4);
        System.out.println("match = " + encoder.matches(password, "$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword4));

        System.out.println("------------------------------");

        password = "password";
        System.out.println("------------------------------");

        encryptedPassword1 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword1);
        System.out.println("match = " + encoder.matches(password, "$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword1));

        System.out.println("--------//////////////--------");

        encryptedPassword2 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword2);
        System.out.println("match = " + encoder.matches(password, "$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword2));

        System.out.println("--------////////////// --------");

        encryptedPassword3 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword3);
        System.out.println("match = " + encoder.matches(password, "$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword3));

        System.out.println("--------////////////// --------");

        encryptedPassword4 = encryptedPassword(password);
        System.out.println("Encrypted password: " + password + " = " + encryptedPassword4);
        System.out.println("match = " + encoder.matches(password, "$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri"));
        System.out.println("match = " + encoder.matches(password, encryptedPassword4));

        System.out.println("------------------------------");
    }

}