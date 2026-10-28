import java.util.*;

public class Leaderboard {
    public static void main(String[] args) {

        Map<String, Integer> scores = new HashMap<>();

        scores.put("Om", 950);
        scores.put("Rahul", 870);
        scores.put("Nishant", 950);
        scores.put("Kushagra", 920);
        scores.put("Neha", 870);

        // Convert Map entries to List
        List<Map.Entry<String, Integer>> players =
                new ArrayList<>(scores.entrySet());

        // Sort by score descending
        // If scores are equal, sort by name
        players.sort((a, b) -> {
            int scoreCompare = b.getValue().compareTo(a.getValue());

            if (scoreCompare != 0)
                return scoreCompare;

            return a.getKey().compareTo(b.getKey());
        });

        int N = 3;

        System.out.println("Top " + N + " Players:");

        for (int i = 0; i < N && i < players.size(); i++) {
            Map.Entry<String, Integer> player = players.get(i);

            System.out.println(
                    (i + 1) + ". " +
                    player.getKey() + " - " +
                    player.getValue()
            );
        }
    }
} 

