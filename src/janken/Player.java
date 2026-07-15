package janken;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    // 手を選ぶロジック(人間ならコンソール入力、CPUならランダム)
    public Hand chooseHand() {
        // 実装はAさんにお任せ
        return Hand.ROCK; // 仮
    }

    public String getName() {
        return name;
    }
}