import java.io.*;
import java.util.*;

public class HuffmanEncoder {
    public static void encode(String inputFile, String outputFile) {
        try (FileInputStream fis = new FileInputStream(inputFile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr);
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            // Read the original text from the input file
            StringBuilder text = new StringBuilder();
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }

//            System.out.println(text);
            // Call the Huffman code
            Main_Build_HuffmanTree(String.valueOf(text), outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void encode_huffman(Huffman_Node root_node, String str, Map<Character, String> huffman_Code) {
        if (root_node == null) {
            return;
        }

        if (is_Leaf(root_node)) {
            huffman_Code.put(root_node.charac, str.length() > 0 ? str : "1");
        }

        encode_huffman(root_node.left, str + '0', huffman_Code);
        encode_huffman(root_node.right, str + '1', huffman_Code);
    }

    private static void Main_Build_HuffmanTree(String text, String outputFile) {
        if (text == null || text.length() == 0) {
            return;
        }
//        System.out.println(text);

        text = text.replace("\n", "").replace("\r", "~");
//        System.out.println(text);

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Huffman_Node> prio_queue;
        prio_queue = new PriorityQueue<>(Comparator.comparingInt(l -> l.frequency));

        for (var entry : frequency.entrySet()) {
            prio_queue.add(new Huffman_Node(entry.getKey(), entry.getValue()));
        }

        while (prio_queue.size() != 1) {
            Huffman_Node left = prio_queue.poll();
            Huffman_Node right = prio_queue.poll();

            int sum = left.frequency + right.frequency;
            prio_queue.add(new Huffman_Node(null, sum, left, right));
        }

        Huffman_Node root_node = prio_queue.peek();

        // Huffman tree Traversing and storing the Huffman codes in a dict or map
        Map<Character, String> huffmanCode = new HashMap<>();
        encode_huffman(root_node, "", huffmanCode);

        // Output the Huffman codes to the console
//        System.out.println("Huffman Codes:");
//        for (Map.Entry<Character, String> entry : huffmanCode.entrySet()) {
//            System.out.println(entry.getKey() + ": " + entry.getValue());
//        }

        // Записываем коды в файл в указанном формате
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile))) {
            Iterator<Map.Entry<Character, String>> iterator = huffmanCode.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Character, String> entry = iterator.next();
                writer.print(entry.getKey() + "=" + entry.getValue());
                if (iterator.hasNext()) {
                    writer.print(", ");
                }
            }

            writer.println(); // Переходим на новую строку

            // Записываем закодированный текст
            writer.print(encodeText(huffmanCode, text));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String encodeText(Map<Character, String> huffmanCode, String text) {
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCode.get(c));
        }
        return encodedText.toString();
    }

    private static boolean is_Leaf(Huffman_Node root_node) {
        return root_node.left == null && root_node.right == null;
    }
}



