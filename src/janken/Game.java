package janken;

public class Game {
	
    public int judge(String playerHand, String cpuHand) {
        // 【防御策】どちらかの手が決定していない(null)場合は、安全のために引き分け(0)として処理する
       

        // 1. あいこ（引き分け）の判定
        if (playerHand == cpuHand) {
            return 0;
        }

        // 2. 勝敗の判定（すべての手がランダムで来ても完璧に網羅）
        switch (playerHand) {
            case "ROCK" : // プレイヤーが「グー」のとき
                return (cpuHand .equals("SCISSORS") ) ? 1 : 2;
                
            case "SCISSORS": // プレイヤーが「チョキ」のとき
                return (cpuHand .equals("PAPER")) ? 1 : 2;
                
            case "PAPER": // プレイヤーが「パー」のとき
                return (cpuHand .equals("ROCK")) ? 1 : 2;
                
            default:
                throw new IllegalArgumentException("存在しない手です: " + playerHand);
        }
    }
}