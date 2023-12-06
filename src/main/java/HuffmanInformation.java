import java.io.*;

public class HuffmanInformation {

    public static void printInformation(String inputFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {

            // Read Huffman codes from the input file
            String huffmanCodeLine = reader.readLine();
            if (huffmanCodeLine != null) {
                // Display Huffman codes
                System.out.println("Huffman Codes: " + huffmanCodeLine);

                // Calculate compression ratio, original size, and compressed size
                File originalFile = new File(inputFile);
                long originalSize = originalFile.length();

                // Creating a temporary file to get the compressed size
                File compressedFile = File.createTempFile("compressed", ".tmp");
                BufferedWriter writer = new BufferedWriter(new FileWriter(compressedFile));
                writer.write(reader.readLine()); // Writing the encoded text to the temporary file
                writer.close();
                long compressedSize = compressedFile.length();
                compressedFile.delete(); // Deleting the temporary file

                double compressionRatio = (double) compressedSize / originalSize;

                // Display information
                System.out.println("Compression Ratio: " + compressionRatio);
                System.out.println("Original Size: " + originalSize + " bytes");
                System.out.println("Compressed Size: " + compressedSize + " bytes");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
