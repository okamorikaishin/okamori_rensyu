package janken;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;
    private boolean CPU;
    
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public Player(String name) {
        this(name, false);
    }
    
    public Player(String name, boolean CPU) {
    	this.name = name;
    	this.CPU = CPU;
    }
    
    public Hand chooseHand() {
        if (CPU) {
            return chooseHandCPU();
        } else {
            return chooseHandHuman();
        }
    }
    
    private Hand chooseHandHuman() {
    		while (true) { System.out.print(name + "さん、手を選んでください (1:グー, 2:チョキ, 3:パー): ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    return Hand.ROCK;
                case "2":
                    return Hand.SCISSORS;
                case "3":
                    return Hand.PAPER;
                default:
                    System.out.println("入力が不正です。1〜3の数字を入力してください。");
            }
    			
    		}
        
    }

    private Hand chooseHandCPU() {
        int r = random.nextInt(3);
        switch (r) {
            case 0:
                return Hand.ROCK;
            case 1:
                return Hand.SCISSORS;
            default:
                return Hand.PAPER;
        }
    }
    
    public int judge(Hand myHand, Hand opponentHand) {
        if (myHand == opponentHand) {
            return 0;
        }

        if (myHand == Hand.ROCK && opponentHand == Hand.SCISSORS) {
            return 1;
        }
        if (myHand == Hand.SCISSORS && opponentHand == Hand.PAPER) {
            return 1;
        }
        if (myHand == Hand.PAPER && opponentHand == Hand.ROCK) {
            return 1;
        }

        return 2;
    }
    
    // 自分の手(myHand)と相手の手(opponentHand)を比較して勝敗を判定
    // 戻り値: 1=自分の勝ち, 2=相手の勝ち, 0=あいこ

    public String getName() {
        return name;
    }
}