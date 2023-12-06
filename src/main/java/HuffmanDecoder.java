import java.io.*;
import java.util.Map;
import java.util.HashMap;

public class HuffmanDecoder {

    public static void decode(String inputFile, String outputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {

            // Read Huffman codes from the input file
            Map<Character, String> huffmanCode = new HashMap<>();
            String line;

            // Read the Huffman codes from the file
            while ((line = reader.readLine()) != null) {
                // Assuming the codes are stored in a specific format in the file.
                // Parse the line and populate the huffmanCode map accordingly.
                // For example: "a=011, b=001, ..."
                String[] codePairs = line.split(", ");
                for (String pair : codePairs) {
                    String[] keyValue = pair.split("=");
                    huffmanCode.put(keyValue[0].charAt(0), keyValue[1]);
                }
                break;  // Assume codes are in a single line, break after processing
            }

            // Decode the Huffman codes and write the original text to the output file
            decode_huffman(huffmanCode, reader, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void decode_huffman(Map<Character, String> huffman_Code, BufferedReader reader, PrintWriter writer) {
        StringBuilder encodedText = new StringBuilder();

        try {
            // Read the encoded text from the input file
            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println("Read line: " + line); // Вывод для отладки
                encodedText.append(line);
            }
//            System.out.println("Encoded text: " + encodedText); // Вывод для отладки

            // Perform Huffman decoding
            StringBuilder decodedText = new StringBuilder();
            int index = 0;

            while (index < encodedText.length()) {
                boolean foundCode = false;

                for (Map.Entry<Character, String> entry : huffman_Code.entrySet()) {
                    String code = entry.getValue();
                    if (index + code.length() <= encodedText.length() &&
                            code.equals(encodedText.substring(index, index + code.length()))) {
                        // Если символ - перенос строки, записываем вместо него "\n"
                        char decodedChar = (entry.getKey() == '~') ? '\n' : entry.getKey();
                        decodedText.append(decodedChar);
                        index += code.length();
                        foundCode = true;
                        break;
                    }
                }

                if (!foundCode) {
                    System.out.println("Unable to find code at index " + index);
                    System.out.println("Remaining encoded text: " + encodedText.substring(index));
                    break;
                }
            }
//            System.out.println("Decoded text: " + decodedText.toString());

            // Write the decoded text to the output file
            writer.println(decodedText);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close(); // Закрыть PrintWriter даже в случае ошибки
        }
    }
}




