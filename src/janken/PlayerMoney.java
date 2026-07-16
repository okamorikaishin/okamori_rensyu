package janken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PlayerMoney {

    private int money;
    private static final String SAVE_FILE = "money.properties";

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
    
    public void reset(int amount) {
        this.money = amount;
    }
    
//    ファイルに保存する
    public void save() {
        Properties prop = new Properties();
        prop.setProperty("money", String.valueOf(money));

        try (FileOutputStream out = new FileOutputStream(SAVE_FILE)) {
            prop.store(out, "Player Money Data");
        } catch (IOException e) {
            System.out.println("所持金の保存に失敗しました: " + e.getMessage());
        }
    }
    
//    ファイルを読み込む
    public void load() {
        Properties prop = new Properties();

        try (FileInputStream in = new FileInputStream(SAVE_FILE)) {
            prop.load(in);
            String savedMoney = prop.getProperty("money");
            if (savedMoney != null) {
                money = Integer.parseInt(savedMoney);
            }
        } catch (FileNotFoundException e) {
            // ファイルがまだない場合は何もしない（初回起動時）
        } catch (IOException e) {
            System.out.println("所持金の読み込みに失敗しました: " + e.getMessage());
        }
    }
}