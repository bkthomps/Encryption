/*
 * Bailey Thompson
 * Encryption (1.2.0)
 * 6 February 2017
 * This encryption program takes a user's text, tells the user the text when it is encrypted, then returns the encrypted
 * text to plain text. The plain text obviously has one part: the text. However, the encrypted text has four parts. The
 * first part is the main encryption characters. The second part is the encryption code. The encryption code is randomly
 * generated, but it is the same amount of characters as the plain text that the user inputs. When these numbers are
 * generated, they are then added to the plain text to form the main encryption characters. The third part is the
 * buffer, it is a set of random characters, and the buffer is of a random size. However, the buffer is determined by
 * the fourth part: the buffer number. The buffer number is one character long, but it determines the amount of buffer
 * characters. The whole point of the buffer is to slow down the process of determining the code, the buffer number is
 * needed in order to determine the amount of buffer characters there are. The main encryption characters and encryption
 * code work together just as the buffer and the buffer number work together. To keep adding security to the encryption
 * code, more buffers and encryption codes can be added; however, it would add too much to the length of the encrypted
 * text, and not offer as much protection as to justify the extra length.
 * The reason why the encryption and decryption are separate private voids, and the only global variable is the String
 * word is to make it more realistic, as if the encryption and decryption were separate programs operated by different
 * people sending information back and forth.
 */

import java.util.Arrays;
import javax.swing.JOptionPane;

public class Encryption {

    private String word;

    public static void main(String[] args) {
        Encryption Encryption = new Encryption();
        Encryption.encryption();
    }

    private void encryption() {
        encrypt();
        decrypt();
    }

    private void encrypt() {
        int check;
        word = JOptionPane.showInputDialog(null, "Write in text to be encrypted.", "Encryption Program",
                JOptionPane.PLAIN_MESSAGE);
        while ("".equals(word)) {
            word = JOptionPane.showInputDialog(null, "I didn't quite get that!\nWrite in text to be encrypted.",
                    "Encryption Program", JOptionPane.PLAIN_MESSAGE);
        }
        if (word == null) {
            System.exit(0);
        }
        int length = word.length(), random;
        int buffer = (int) (Math.random() * length);
        char letter[] = new char[((length * 2) + buffer + 1)];
        letter[((length * 2) + buffer)] = (char) buffer;
        for (int i = 0; i < length; i++) {
            random = ((int) (Math.random() * ((500 - 20) + 20))) + 20;
            letter[(i + length)] = (char) random;
            letter[i] = (char) (word.charAt(i) + letter[(i + length)]);
        }
        for (int i = 0; i < buffer; i++) {
            random = ((int) (Math.random() * ((500 - 20) + 20))) + 20;
            letter[(i + (length * 2))] = (char) random;
        }
        word = String.valueOf(letter);
        if (length > 40) {
            check = JOptionPane.showConfirmDialog(null, "This is the encrypted message:\n" + "<html><body width='1000'>"
                            + word + "\nTo decrypt the message, press ok.", "Encryption Program", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        } else {
            check = JOptionPane.showConfirmDialog(null, "This is the encrypted message:\n" + word
                            + "\nTo decrypt the message, press ok.", "Encryption Program", JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (check != 0) {
            System.exit(0);
        }
    }

    private void decrypt() {
        int check;
        int length = word.length();
        length = length - word.charAt((length) - 1) - 1;
        char letter[] = new char[length];
        for (int i = 0; i < (length); i++) {
            letter[i] = word.charAt(i);
        }
        for (int i = 0; i < (length / 2); i++) {
            letter[i] -= letter[(i + (length / 2))];
        }
        letter = Arrays.copyOf(letter, (length / 2));
        word = String.valueOf(letter);
        if (length > 400) {
            check = JOptionPane.showConfirmDialog(null, "This is the decrypted message:\n" + "<html><body width='1000'>"
                    + word, "Encryption Program", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        } else {
            check = JOptionPane.showConfirmDialog(null, "This is the decrypted message:\n" + word, "Encryption Program",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        }
        if (check != 0) {
            System.exit(0);
        }
    }
}
