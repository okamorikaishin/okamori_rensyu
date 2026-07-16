package janken;

public class PlayerMoney {

    private int money;

    public PlayerMoney(int initialMoney) {
        this.money = initialMoney;
    }

    public int getMoney() {
        return money;
    }

    
//     ベットする（所持金からベット額を引く）
//     ベットできたらtrue、所持金不足や不正な金額ならfalse
    
    public boolean bet(int betMoney) {
        if (betMoney <= 0 || betMoney > money) {
            return false;
        }
        money -= betMoney;
        return true;
    }

//     勝った時の処理（ベット額の倍額を獲得）
    public void win(int betMoney) {
        money += betMoney * 2;
    }

//     負けた時の処理（何もしない。betの時点で引いてるから）
    public void lose(int betMoney) {
        // 何もしない（betで既に引いてる）
    }

//     引き分けの時の処理（ベット額を返す）
    public void draw(int betMoney) {
        money += betMoney;
    }
}