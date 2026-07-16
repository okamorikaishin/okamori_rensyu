package janken;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    		Scanner sc = new Scanner(System.in);//スキャナーのインスタンス生成
        Player player1 = new Player("Player1");//player1(プレイヤー)のインスタンス生成
        Player player2 = new Player("Player2");//player2(CPU)のインスタンス生成
        Game game = new Game();//Gameクラスのインスタンス生成
        ScoreBoard scoreBoard = new ScoreBoard();//ScoreBoardクラスのインスタンス生成
        
        int con = 1;//継続判定変数conを定義
        int i =1;//対戦回数カウント用の変数iを定義
        
        /*========================================
         * 
         * ゲーム画面
         * 
         *========================================*/
        
        System.out.println("==========================================");

        //ゲームの繰り返し処理(プレイヤーが0を選んだら終了)
        while (con==1) {
        		System.out.println("~"+i+"回戦~");
        		i+=1;//対戦回数の追加
        		System.out.println();
        		System.out.println("1:グー, 2:チョキ, 3:パー");
        		System.out.print("どの手を出す?：");
        		int number = sc.nextInt();//スキャナーの呼び出し(プレイヤー出したい手を受け取る)
        		System.out.println();

            String hand1 = player1.chooseHandHuman(number);
            String hand2 = player2.chooseHandCPU();

            int result = game.judge(hand1, hand2);
            scoreBoard.record(result);//スコアに結果を記載
            
            /*
             *じゃんけん結果の表示 
             */
            
            try {
            	Thread.sleep(2000);//2秒のディレイがかかる
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            
            System.out.println(player1.getName() + ": " + hand1 + " vs " + player2.getName() + ": " + hand2);
            
            System.out.println();
            
            try {
            	Thread.sleep(500);//0.5秒のディレイがかかる
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            
            System.out.println("　　　　　　 "+game.judgeResultString(hand1, hand2));//勝敗判定の呼び出し(文字列)
            System.out.println();
            
            /*
             *コンティニュー画面の表示 
             */
            
            try {
            	Thread.sleep(1500);//1.5秒のディレイ
            }catch(InterruptedException e){
            		e.printStackTrace();
            }
            if (scoreBoard.getCurrentStreakPlayer() == 1 ) {
                System.out.println("現在 " + scoreBoard.getCurrentStreakCount() + "連勝中！");
            }
            
            System.out.println("　　　　　　　　　　　　　続けますか?");
            System.out.print("　　　　　　　　　　　　　　やめる 0,続ける 1：");
            con = sc.nextInt();//継続判定の受け取り
            
            System.out.println("==========================================");
            
        }
        
        	/*
        	 * 試合結果の表示
        	 */
        scoreBoard.display();
        
        sc.close();//スキャナーの終了

     
    }
}
