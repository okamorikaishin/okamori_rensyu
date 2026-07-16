package janken;

public class ScoreBoard {
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int draws = 0;

    // 連勝記録用
    private int currentStreakPlayer = 0; // 0:なし, 1:Player1, 2:Player2(CPU)
    private int currentStreakCount = 0;
    private int maxStreakPlayer1 = 0;
    
    public int getCurrentStreakPlayer() {
    	  return currentStreakPlayer;
    }
    
    public int getCurrentStreakCount() {
    	  return currentStreakCount;
    }

    public void record(int result) {
        if (result == 1) {
            player1Wins++;
        } else if (result == 2) {
            player2Wins++;
        } else {
            draws++;
        }

        updateStreak(result);
    }

    private void updateStreak(int result) {
        if (result == currentStreakPlayer) {
            currentStreakCount++;
        } else {
            currentStreakPlayer = result;
            currentStreakCount = (result == 1 || result == 2) ? 1 : 0;
        }

        if (result == 1) {
            maxStreakPlayer1 = Math.max(maxStreakPlayer1, currentStreakCount);
        }
    }

    public void display() {
        int total = player1Wins + player2Wins + draws;
        System.out.println("Player1: " + player1Wins + "勝");
        System.out.println("Player2: " + player2Wins + "勝");
        System.out.println("引き分け: " + draws + "回");

        if (total > 0) {
            double winRateP1 = (double) player1Wins / total * 100;
            System.out.println("勝率: " + String.format("%.1f", winRateP1) + "%");
        }

        System.out.println("Player1 最大連勝: " + maxStreakPlayer1 + "連勝");

        
    }
}