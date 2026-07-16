package janken;

import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Main {
    // BGMをコントロールするためのオブジェクトを保持
    private static Clip bgmClip;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Player player1 = new Player("Player1");
        Player player2 = new Player("Player2");
        Game game = new Game();
        ScoreBoard scoreBoard = new ScoreBoard();
        
        // ----------------------------------------------------
        // ★ ゲーム開始：BGM再生スタート！
        // ----------------------------------------------------
        playBGM("bgm.wav");

        int con = 1;
        int i = 1;
        
        System.out.println("==========================================");

        while (con == 1) {
            System.out.println("~" + i + "回戦~");
            i += 1;
            System.out.println();
            System.out.println("1:グー, 2:チョキ, 3:パー");
            System.out.print("どの手を出す?：");
            int number = sc.nextInt();
            System.out.println();

            String hand1 = player1.chooseHandHuman(number);
            String hand2 = player2.chooseHandCPU();

            int result = game.judge(hand1, hand2);
            scoreBoard.record(result);
            
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
            
            System.out.println("             続けますか?");
            System.out.print("              やめる 0,続ける 1：");
            con = sc.nextInt();
            
            System.out.println("==========================================");
        }
        
        // ----------------------------------------------------
        // ★ ゲーム終了：BGMを止める！
        // ----------------------------------------------------
        stopBGM();

        scoreBoard.display();
        sc.close();
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