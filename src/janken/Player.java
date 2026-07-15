package janken;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    // 手を選ぶロジック(人間ならコンソール入力、CPUならランダム)
    public Hand chooseHand(int number) {
        // 実装はAさんにお任せ
        return Hand.ROCK; // 仮
    }
<<<<<<< HEAD
    
   
=======

>>>>>>> 80559d3e7771225bf1cee7844e5190b8f2016d2d
    public String getName() {
        return name;
    }
}