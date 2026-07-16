package janken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Game game = new Game();
        ScoreBoard scoreBoard = new ScoreBoard();
        
        System.out.println("==========================================");

        // 例: 5回勝負
        for (int i = 1; i < 6; i++) {
        		System.out.println("~"+i+"回戦~");
        		System.out.println();
        		System.out.println("1:グー, 2:チョキ, 3:パー");
        		System.out.print("どの手を出す?：");
        		int number = sc.nextInt();
        		System.out.println();
            Hand hand1 = player1.chooseHandHuman(number);
            Hand hand2 = player2.chooseHandCPU();

            int result = game.judge(hand1, hand2);
            scoreBoard.record(result);

            System.out.println(player1.getName() + ": " + hand1 + " vs " + player2.getName() + ": " + hand2);
            
            System.out.println();
            
            System.out.println("　　　　　　 "+game.judgeResultString(hand1, hand2));
            
            System.out.println();
            
            System.out.println("==========================================");
            
        }

        scoreBoard.display();
        sc.close();

     
    }
}