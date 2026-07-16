package janken;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Game game = new Game();
        ScoreBoard scoreBoard = new ScoreBoard();

        // 例: 5回勝負
        for (int i = 0; i < 5; i++) {
            Hand hand1 = player1.chooseHand();
            Hand hand2 = player2.chooseHand();

            int result = game.judge(hand1, hand2);
            scoreBoard.record(result);

            System.out.println(player1.getName() + ": " + hand1 + " vs " + player2.getName() + ": " + hand2);
        }

        scoreBoard.display();
    }
}
