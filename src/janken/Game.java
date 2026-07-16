package janken;

public class Game {
	
    /**
     * 【既存メソッド】プレイヤーとコンピュータの手を比較し、勝敗を数値で判定します。
     * * @param playerHand プレイヤーの手
     * @param cpuHand コンピュータの手
     * @return 1=プレイヤーの勝ち, 2=コンピュータの勝ち, 0=引き分け
     */
    public int judge(Hand playerHand, Hand cpuHand) {
        // 【防御策】どちらかの手が決定していない(null)場合は、安全のために引き分け(0)として処理する
        if (playerHand == null || cpuHand == null) {
            return 0; 
        }

        // 1. あいこ（引き分け）の判定
        if (playerHand == cpuHand) {
            return 0;
        }

        // 2. 勝敗の判定（すべての手がランダムで来ても完璧に網羅）
        switch (playerHand) {
            case ROCK: // プレイヤーが「グー」のとき
                return (cpuHand == Hand.SCISSORS) ? 1 : 2;
                
            case SCISSORS: // プレイヤーが「チョキ」のとき
                return (cpuHand == Hand.PAPER) ? 1 : 2;
                
            case PAPER: // プレイヤーが「パー」のとき
                return (cpuHand == Hand.ROCK) ? 1 : 2;
                
            default:
                throw new IllegalArgumentException("存在しない手です: " + playerHand);
        }
    }

    public String judgeResultString(Hand playerHand, Hand cpuHand) {
        // 上のjudgeメソッドを呼び出して、まずは数字の結果（1, 2, 0）をもらう
        int result = judge(playerHand, cpuHand);
        
        // 数字の結果をもとに、文字列に変換して返す
        switch (result) {
            case 1:
                return "WIN";
            case 2:
                return "LOSE";
            default:
                return "DRAW"; // 0のときはここ
        }
    }

    
    public boolean confirmExit(String input) {
        if (input == null) {
            return false;
        }
        return input.trim().equalsIgnoreCase("Y");
    }
}