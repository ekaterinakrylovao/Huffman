import java.util.Scanner;

public class HuffmanApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите режим работы:");
        System.out.println("1. Кодирование");
        System.out.println("2. Декодирование");
        System.out.println("3. Информирование");

        int choice = scanner.nextInt();
        scanner.nextLine(); // Очистка буфера после считывания числа

        switch (choice) {
            case 1:
                System.out.print("Введите имя входного файла: ");
                String inputEncodeFile = scanner.nextLine();
                System.out.print("Введите имя выходного файла: ");
                String outputEncodeFile = scanner.nextLine();
                HuffmanEncoder.encode(inputEncodeFile, outputEncodeFile);
                break;

            case 2:
                System.out.print("Введите имя входного закодированного файла: ");
                String inputDecodeFile = scanner.nextLine();
                System.out.print("Введите имя выходного файла: ");
                String outputDecodeFile = scanner.nextLine();
                HuffmanDecoder.decode(inputDecodeFile, outputDecodeFile);
                break;

            case 3:
                System.out.print("Введите имя закодированного файла для информации: ");
                String inputInfoFile = scanner.nextLine();
                HuffmanInformation.printInformation(inputInfoFile);
                break;

            default:
                System.out.println("Некорректный выбор. Завершение программы.");
        }
    }
}
