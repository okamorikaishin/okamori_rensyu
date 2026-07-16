package janken;

import java.util.Random;

public class Player {
	
	public static final String[] HANDS = {"ROCK","SCISSORS", "PAPER"};
	
    private String name;
    
    private static final Random random = new Random();

    public Player(String name) {
        this.name=name;
    }

    public void setName(String name) {
  	  this.name = name;
    }
    
    public String chooseHandHuman(int number) {
            switch (number) {
                case 1:
                    return HANDS[0];
                case 2:
                    return HANDS[1];
                case 3:
                    return HANDS[2];
                default:
                   return null;
            }
    			
    		}
        

    public String chooseHandCPU() {
        int r = random.nextInt(3);
        switch (r) {
            case 0:
                return HANDS[0];
            case 1:
                return HANDS[1];
            default:
                return HANDS[2];
        }
    }
    
   
    public String getName() {
        return name;
    }
   
    }
