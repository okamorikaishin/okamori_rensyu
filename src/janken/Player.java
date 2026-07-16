package janken;

import java.util.Random;

public class Player {
    private String name;

    private static final Random random = new Random();

    public Player(String name) {
        this.name=name;
    }


    public Hand chooseHandHuman(int number) {
            switch (number) {
                case 1:
                    return Hand.ROCK;
                case 2:
                    return Hand.SCISSORS;
                case 3:
                    return Hand.PAPER;
                default:
                   return null;
            }
    			
    		}


    public Hand chooseHandCPU() {
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


    public String getName() {
        return name;
    }
}