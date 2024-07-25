/**
 * Tests ShiftCipher, an encoder that perform encryption and decryption of strings based on
 * a reference table and offset character. This table runs from index 0-43, which covers:
 *      0-25: A-Z
 *      26-35: 0-9
 *      36-43: ( ) * + , - . /
 * 
 * The ShiftCipher object is instantiated with an offset character, either provided or randomized.
 * 
 * Encoded output will start with the offset character. The rest of the encoded output is
 * derived from the original string, encoded based on the offset character.
 * 
 * Decoded output will return the original string, regardless of the offset character given
 * when instantiating the ShiftCipher object. (In the case of an invalid input, it will return null.)
 * 
*/


public class ShiftCipherTest {
    public static void main(String[] args) {

        ShiftCipher cipher1 = new ShiftCipher(1); // B
        ShiftCipher cipher2 = new ShiftCipher(5); // F

        // -------------- TEST 1 ----------------
        String test1 = "HELLO WORLD";
        String answer1 = "BGDKKN VNQKC";
        String result1 = cipher1.encode(test1);
        System.out.printf("\nTest Case 1 (Encoding): %s\n", answer1.equals(result1) ? "PASS" : "FAIL");
        System.out.printf("Expected Result: %s\n", answer1);
        System.out.printf("Actual Result  : %s\n", result1);

        // -------------- TEST 2 ----------------
        String test2 = "HELLO WORLD";
        String answer2 = "FC/GGJ RJMG.";
        String result2 = cipher2.encode(test2);
        System.out.printf("\nTest Case 2 (Encoding): %s\n", answer2.equals(result2) ? "PASS" : "FAIL");
        System.out.printf("Expected Result: %s\n", answer2);
        System.out.printf("Actual Result  : %s\n", result2);

        // -------------- TEST 3 ----------------
        String test3 = "BGDKKN VNQKC";
        String answer3 = "HELLO WORLD";
        String result3 = cipher1.decode(test3);
        System.out.printf("\nTest Case 3 (Decoding): %s\n", answer3.equals(result3) ? "PASS" : "FAIL");
        System.out.printf("Expected Result: %s\n", answer3);
        System.out.printf("Actual Result  : %s\n", result3);

        // -------------- TEST 4 ----------------
        String test4 = "FC/GGJ RJMG.";
        String answer4 = "HELLO WORLD";
        String result4 = cipher2.decode(test4);
        System.out.printf("\nTest Case 4 (Decoding): %s\n", answer4.equals(result4) ? "PASS" : "FAIL");
        System.out.printf("Expected Result: %s\n", answer3);
        System.out.printf("Actual Result  : %s\n", result3);

    }
}
