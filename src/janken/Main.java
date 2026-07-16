package janken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Game game = new Game();
        ScoreBoard scoreBoard = new ScoreBoard();
        int con = 1;
        int i =1;
        
        System.out.println("==========================================");

        // 例: 5回勝負
        while (con==1) {
        		System.out.println("~"+i+"回戦~");
        		i+=1;
        		System.out.println();
        		System.out.println("1:グー, 2:チョキ, 3:パー");
        		System.out.print("どの手を出す?：");
        		int number = sc.nextInt();
        		System.out.println();
            Hand hand1 = player1.chooseHandHuman(number);
            Hand hand2 = player2.chooseHandCPU();

            int result = game.judge(hand1, hand2);
            scoreBoard.record(result);
            
            try {
            	Thread.sleep(2000);
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            
            System.out.println(player1.getName() + ": " + hand1 + " vs " + player2.getName() + ": " + hand2);
            
            System.out.println();
            
            try {
            	Thread.sleep(500);
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            
            System.out.println("　　　　　　 "+game.judgeResultString(hand1, hand2));
            
            System.out.println();
            
            try {
            	Thread.sleep(1500);
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            
            System.out.println("　　　　　　　　　　　　　続けますか?");
            System.out.print("　　　　　　　　　　　　　　やめる 0,続ける 1：");
            con = sc.nextInt();
            
            System.out.println("==========================================");
            
        }

        scoreBoard.display();
        sc.close();

     
    }
}