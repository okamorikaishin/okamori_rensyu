package janken;


import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
    // BGMをコントロールするためのオブジェクトを保持
    private static Clip bgmClip;

    public static void main(String[] args) {

    		Scanner sc = new Scanner(System.in);//スキャナーのインスタンス生成
        Player player1 = new Player("Player1");//player1(プレイヤー)のインスタンス生成
        Player player2 = new Player("Player2");//player2(CPU)のインスタンス生成
        Game game = new Game();//Gameクラスのインスタンス生成
        ScoreBoard scoreBoard = new ScoreBoard();//ScoreBoardクラスのインスタンス生成
//        これを追加してほしいです
        PlayerMoney playerMoney = new PlayerMoney(1000); // 初期所持金1000
//        前回のファイルを読み込む
        scoreBoard.load();
        playerMoney.load();

        
        // ----------------------------------------------------
        // ★ ゲーム開始：BGM再生スタート！
        // ----------------------------------------------------
        playBGM("bgm.wav");

        int con = 1;
        int i = 1;
        
        System.out.println("==========================================");


        //ゲームの繰り返し処理(プレイヤーが0を選んだら終了)
        while (con==1) {
        		System.out.println("~"+i+"回戦~");
        		i+=1;//対戦回数の追加
        		System.out.println();

//        		これを追加してほしいです
        		System.out.println("所持金: " + playerMoney.getMoney() + "円");
//        		掛け金
        		int betAmount;
        		
                while (true) {
                    System.out.print("ベット額を入力してください：");
                    betAmount = sc.nextInt();
                    if (playerMoney.bet(betAmount)) {
                        break; // ベット成功
                    }
                    System.out.println("ベット額が不正です（所持金以下、かつ1円以上にしてください）");
                }

        		String hand1 = null;
        		System.out.println();


        		// 正しい手(1〜3)が選ばれるまで繰り返し聞く
        		while (hand1 == null) {
        			System.out.println("1:グー, 2:チョキ, 3:パー");
        			System.out.print("どの手を出す?：");

        			try {
        				int number = sc.nextInt();//スキャナーの呼び出し(プレイヤー出したい手を受け取る)
        				hand1 = player1.chooseHandHuman(number);

        				if (hand1 == null) {
        					System.out.println();
        					System.out.println("1〜3の数字を入力してください。");
        					System.out.println();
        				}
        			} catch (InputMismatchException e) {
        				System.out.println("数字を入力してください。");
        				System.out.println();
        				sc.nextLine(); // 不正な入力を読み捨てる
        			}
        		}

        		System.out.println();
        		
            String hand2 = player2.chooseHandCPU();

            int result = game.judge(hand1, hand2);

            scoreBoard.record(result);//スコアに結果を記載
            
//            これ追加してほしいです
            // ベット結果の反映
            if (result == 1) {
                playerMoney.win(betAmount);
            } else if (result == 0) {
                playerMoney.draw(betAmount);
            }
            
            /*
             *じゃんけん結果の表示 
             */
            
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println(player1.getName() + ": " + hand1 + " vs " + player2.getName() + ": " + hand2);
            System.out.println();
            
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            System.out.println("       " + game.judgeResultString(hand1, hand2));
            System.out.println();
            
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (scoreBoard.getCurrentStreakPlayer() == 1) {
                System.out.println("現在 " + scoreBoard.getCurrentStreakCount() + "連勝中！");
            }
            

//            これ追加してほしいです
         // 所持金が0になったら強制終了
            if (playerMoney.getMoney() <= 0) {
                System.out.println("所持金がなくなりました。ゲーム終了です。");
                break;
            }
            
            System.out.println("　　　　　　　　　　　　　続けますか?");
            System.out.print("　　　　　　　　　　　　　　やめる 0,続ける 1：");
            con = sc.nextInt();//継続判定の受け取り

            
            System.out.println("==========================================");
        }
        

        // ----------------------------------------------------
        // ★ ゲーム終了：BGMを止める！
        // ----------------------------------------------------
        stopBGM();

        	/*
        	 * 試合結果の表示
        	 */
        scoreBoard.display();
        
//        記録を保存する
        scoreBoard.save();
        playerMoney.save();
        
        sc.close();//スキャナーの終了

    }

    /**
     * 指定されたパスのWAVファイルをBGMとしてループ再生します。
     */
    private static void playBGM(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (audioFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                bgmClip = AudioSystem.getClip();
                bgmClip.open(audioStream);
                
                // 無限ループ再生の設定
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
                bgmClip.start();
            } else {
                System.out.println("BGMファイルが見つかりません: " + filePath);
            }
        } catch (Exception e) {
            System.out.println("BGMの再生中にエラーが発生しました。");
            e.printStackTrace();
        }
    }

    /**
     * BGMを停止してリソースを開放します。
     */
    private static void stopBGM() {
        if (bgmClip != null && bgmClip.isRunning()) {
            bgmClip.stop();
            bgmClip.close();
        }
    }
}