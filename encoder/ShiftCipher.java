import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
   * The ShiftCipher class is used to encode and decode messages based on a shift cipher around a reference table.
   * The shift offset is determined during its instantiation, such that multiple ShiftCipher objects can be used to
   * generate messages with a specific offset. Additionally, during encoding, information about the shift offset is
   * included, such that any ShiftCipher class will be able to decode any ShiftCipher encoded message.
*/

public class ShiftCipher {

    // Reference Tables

    /**
     * Reference table: The values of the cipher reference table are stored in a map, which provides
     * character values given an index, which is used for encoding purposes.
    */
    private static HashMap<Integer, Character> referenceTable = new HashMap<Integer, Character>();
    // (It is indeed possible to use an array, but this is easier for humans)

    static {
        referenceTable.put(0, 'A');
        referenceTable.put(1, 'B');
        referenceTable.put(2, 'C');
        referenceTable.put(3, 'D');
        referenceTable.put(4, 'E');
        referenceTable.put(5, 'F');
        referenceTable.put(6, 'G');
        referenceTable.put(7, 'H');
        referenceTable.put(8, 'I');
        referenceTable.put(9, 'J');
        referenceTable.put(10, 'K');
        referenceTable.put(11, 'L');
        referenceTable.put(12, 'M');
        referenceTable.put(13, 'N');
        referenceTable.put(14, 'O');
        referenceTable.put(15, 'P');
        referenceTable.put(16, 'Q');
        referenceTable.put(17, 'R');
        referenceTable.put(18, 'S');
        referenceTable.put(19, 'T');
        referenceTable.put(20, 'U');
        referenceTable.put(21, 'V');
        referenceTable.put(22, 'W');
        referenceTable.put(23, 'X');
        referenceTable.put(24, 'Y');
        referenceTable.put(25, 'Z');
        referenceTable.put(26, '0');
        referenceTable.put(27, '1');
        referenceTable.put(28, '2');
        referenceTable.put(29, '3');
        referenceTable.put(30, '4');
        referenceTable.put(31, '5');
        referenceTable.put(32, '6');
        referenceTable.put(33, '7');
        referenceTable.put(34, '8');
        referenceTable.put(35, '9');
        referenceTable.put(36, '(');
        referenceTable.put(37, ')');
        referenceTable.put(38, '*');
        referenceTable.put(39, '+');
        referenceTable.put(40, ',');
        referenceTable.put(41, '-');
        referenceTable.put(42, '.');
        referenceTable.put(43, '/');
    }

    // Length of the reference table - please ensure this value is correct as well
    private static Integer referenceTableLength = 44;

    /**
     * Lookup table: This map is used to find the index of a character. It enables quick checking of
     * whether the character is in the table as well.
    */
    private static HashMap<Character, Integer> lookupTable = new HashMap<Character, Integer>();

    static {
        for (Map.Entry<Integer, Character> entry : referenceTable.entrySet()) {
            lookupTable.put(entry.getValue(), entry.getKey());
        }
    }

    /** Offset - determines the extent of the shift when using this instance to encode a message */
    private int offsetCharacterIndex;

    /**
     * Constructs a ShiftCipher object. The offsetCharacterIndex represents the character offset
     * to be used when encoding strings.
    */
    public ShiftCipher(int offsetCharacterIndex) {

        // Ensures shift is within range
        this.offsetCharacterIndex = offsetCharacterIndex % referenceTableLength;
        
    }

    /** Constructs a ShiftCipher object with a random character offset. */
    public ShiftCipher() {

        // Extra constructor, in case shift value is not provided
        this((int)(Math.random() * referenceTableLength));

    }

    /** Encodes a given string based on the StringCipher's offset. */
    public String encode(String plainText) {

        // Excludes case where string is empty
        if (plainText.length() == 0) {
            return plainText;
        }

        // Converts string to upper case first thing
        plainText = plainText.toUpperCase(Locale.ROOT);

        StringBuilder encodedText = new StringBuilder();
        encodedText.append(referenceTable.get(offsetCharacterIndex));
        
        for (char c : plainText.toCharArray()) {

            if (lookupTable.containsKey(c)) {

                Integer oldPosition = lookupTable.get(c);
                Integer newPosition = oldPosition - offsetCharacterIndex;
                
                if (newPosition < 0) {
                    newPosition = newPosition + referenceTableLength;
                } else if (newPosition >= referenceTableLength) {
                    newPosition = newPosition - referenceTableLength;
                }
                encodedText.append(referenceTable.get(newPosition));

            } else {

                encodedText.append(c); // Keep non-letter characters unchanged

            }
        }
        
        return encodedText.toString();
    }

    /** Decodes a given string. */
    public String decode(String encodedText) {

        // Excludes case where string is empty
        if (encodedText.length() == 0) {
            return encodedText;
        }

        // Converts string to upper case first thing
        encodedText = encodedText.toUpperCase(Locale.ROOT);

        // Determines the shift using the first character
        Integer shift;
        if (lookupTable.containsKey(encodedText.charAt(0))) {
            shift = lookupTable.get(encodedText.charAt(0));
            encodedText = encodedText.substring(1);
        } else {
            return null;
        }

        StringBuilder decodedText = new StringBuilder();

        for (char c : encodedText.toCharArray()) {

            if (lookupTable.containsKey(c)) {

                Integer oldPosition = lookupTable.get(c);
                Integer newPosition = oldPosition + shift;
                
                if (newPosition < 0) {
                    newPosition = newPosition + referenceTableLength;
                } else if (newPosition >= referenceTableLength) {
                    newPosition = newPosition - referenceTableLength;
                }
                decodedText.append(referenceTable.get(newPosition));

            } else {

                decodedText.append(c); // Keep non-letter characters unchanged

            }
        }

        return decodedText.toString();
    }
}
