package janken;

public class ScoreBoard {
    private int player1Wins = 0;
    private int player2Wins = 0;
    private int draws = 0;

    public void record(int result) {



        if (result == 1) {
          	player1Wins++;
        }else if (result == 2) {
         	player2Wins++;
        }else {
         	draws++;
        }
    }

    public void display() {
        int total = player1Wins + player2Wins + draws;
        System.out.println("Player1: " + player1Wins + "勝");
        System.out.println("Player2: " + player2Wins + "勝");
        System.out.println("引き分け: " + draws + "回");
        if (total > 0) {
            double winRate = (player1Wins / total) * 100;
            System.out.println(winRate);
        }
    }
}
