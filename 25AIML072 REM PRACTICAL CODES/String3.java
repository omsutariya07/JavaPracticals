import java.util.Scanner;

public class String3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String sentence = sc.nextLine();
        String keyword = sc.nextLine();

        String trimmed = sentence.trim();
        String[] words = trimmed.split(" +");

        StringBuilder cleaned = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            if (i > 0) {
                cleaned.append(" ");
            }
            cleaned.append(words[i].toLowerCase());
        }

        cleaned.setCharAt(
                0,
                Character.toUpperCase(cleaned.charAt(0))
        );

        String shortestWord = words[0].toLowerCase();
        int keywordCount = 0;
        int vowelCount = 0;

        for (String word : words) {
            String lowerWord = word.toLowerCase();

            if (lowerWord.length() < shortestWord.length()) {
                shortestWord = lowerWord;
            }

            if (lowerWord.equals(keyword.toLowerCase())) {
                keywordCount++;
            }
        }

        for (int i = 0; i < sentence.length(); i++) {
            char ch = Character.toLowerCase(sentence.charAt(i));

            if (ch == 'a' || ch == 'e' || ch == 'i'|| ch == 'o' || ch == 'u') 
            {
                vowelCount++;
            }
        }

        System.out.println("Sentence: " + cleaned);
        System.out.println("Words: " + words.length);
        System.out.println("Shortest word: " + shortestWord);
        System.out.println("Keyword count: " + keywordCount);
        System.out.println("Vowels: " + vowelCount);

        sc.close();
    }
}