import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Counter {

    // Common stop words to ignore
    private static final Set<String> commonStopWords = new HashSet<>(Arrays.asList(
            "the", "and", "in", "to", "is", "a", "it", "of", "that", "for", "with", "on", "as", "an", "at", "from", "by", "this"
    ));

    public static int countWords(String text) {
        String[] words = text.split("\\s+");
        return words.length;
    }

    public static int countWordsIgnoringStopWords(String text) {
        String[] words = text.split("\\s+");
        int count = 0;
        for (String word : words) {
            if (!commonStopWords.contains(word.toLowerCase())) {
                count++;
            }
        }
        return count;
    }

    public static Map<String, Integer> getWordFrequency(String text) {
        String[] words = text.split("\\s+");
        Map<String, Integer> wordFrequency = new HashMap<>();
        for (String word : words) {
            String lowercaseWord = word.toLowerCase();
            if (!commonStopWords.contains(lowercaseWord)) {
                wordFrequency.put(lowercaseWord, wordFrequency.getOrDefault(lowercaseWord, 0) + 1);
            }
        }
        return wordFrequency;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the Word Counter!");

        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter a text or provide a file
        System.out.println("Enter '1' to input text, '2' to provide a file: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        String text = "";

        if (choice == 1) {
            // Read text from user input
            System.out.println("Enter a text: ");
            text = scanner.nextLine();
        } else if (choice == 2) {
            // Read text from a file
            System.out.println("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (IOException e) {
                System.out.println("Error: File not found or couldn't be read.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid choice. Exiting.");
            scanner.close();
            return;
        }

        // Count words
        int wordCount = countWords(text);
        System.out.println("Total words: " + wordCount);

        // Count words ignoring stop words
        int wordCountIgnoringStopWords = countWordsIgnoringStopWords(text);
        System.out.println("Total words (excluding stop words): " + wordCountIgnoringStopWords);

        // Word frequency statistics
        Map<String, Integer> wordFrequency = getWordFrequency(text);
        System.out.println("Word frequency:");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        scanner.close();
    }

    private static String readFile(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }
}