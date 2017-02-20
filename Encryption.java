/*
 * Bailey Thompson
 * Encryption (1.2.3)
 * 20 February 2017
 */

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * Takes in text to be encrypted, returns the encrypted message to the user, then decrypts the message and shows the
 * message to the user. The encrypted text has four parts. The first part is the main encryption characters. The second
 * part is the encryption code. The encryption code is randomly generated, but it is the same amount of characters as
 * the plain text that the user inputs. When these numbers are generated, they are then added to the plain text to form
 * the main encryption characters. The third part is the buffer, it is a set of random characters, and the buffer is of
 * a random size. However, the buffer is determined by the fourth part: the buffer number. The buffer number is one
 * character long, and it determines the amount of buffer characters.
 */
class Encryption {

    private static final String PROGRAM_NAME = "Encryption";

    public static void main(String[] args) {
        Encryption Encryption = new Encryption();
        Encryption.encryption();
    }

    private void encryption() {
        final String ENCRYPTED_MESSAGE = encrypt();
        decrypt(ENCRYPTED_MESSAGE);
    }

    private String encrypt() {
        String word = userInput("Write in text to be encrypted.");
        while ("".equals(word)) {
            word = userInput("I didn't quite get that!\nWrite in text to be encrypted.");
        }
        if (word == null) {
            System.exit(0);
        }
        final int LENGTH = word.length();
        final int BUFFER = (int) (Math.random() * LENGTH);
        final char[] letter = new char[((LENGTH * 2) + BUFFER + 1)];
        letter[((LENGTH * 2) + BUFFER)] = (char) BUFFER;
        for (int i = 0; i < LENGTH; i++) {
            final int random = (int) (Math.random() * 500) + 20;
            letter[(i + LENGTH)] = (char) random;
            letter[i] = (char) (word.charAt(i) + letter[(i + LENGTH)]);
        }
        for (int i = 0; i < BUFFER; i++) {
            final int random = (int) (Math.random() * 500) + 20;
            letter[(i + (LENGTH * 2))] = (char) random;
        }
        word = String.valueOf(letter);
        if (LENGTH > 40) {
            userDialog("This is the encrypted message:\n" + "<html><body width='1000'>" + word + "\nTo decrypt the "
                    + "message, press ok.");
        } else {
            userDialog("This is the encrypted message:\n" + word + "\nTo decrypt the message, press ok.");
        }
        return word;
    }

    private void decrypt(String word) {
        final int LENGTH = word.length() - word.charAt(word.length() - 1) - 1;
        char[] letter = new char[LENGTH];
        for (int i = 0; i < (LENGTH); i++) {
            letter[i] = word.charAt(i);
        }
        for (int i = 0; i < (LENGTH / 2); i++) {
            letter[i] -= letter[(i + (LENGTH / 2))];
        }
        letter = Arrays.copyOf(letter, (LENGTH / 2));
        word = String.valueOf(letter);
        if (LENGTH > 400) {
            userDialog("This is the decrypted message:\n" + "<html><body width='1000'>" + word);
        } else {
            userDialog("This is the decrypted message:\n" + word);
        }
    }

    private void userDialog(String message) {
        final int CHECK = JOptionPane.showConfirmDialog(null, message, PROGRAM_NAME, JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
        if (CHECK != 0) {
            System.exit(0);
        }
    }

    private String userInput(String message) {
        return JOptionPane.showInputDialog(null, message, PROGRAM_NAME, JOptionPane.PLAIN_MESSAGE);
    }
}
