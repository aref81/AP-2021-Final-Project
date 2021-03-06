package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Giant.
 */
public class Giant extends Troop{
    /**
     * Instantiates a new Giant.
     */
    public Giant() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/GiantCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Giant.png")),"Giant");
        HitSpeed = 1.5;
        Speed = "Slow";
        Target = "B";
        IsMelee = true;
        AreaSplash = false;
        Count = 1;
        Cost = 5;
        Level = 1;
        HP = 2000;
        Damage = 126;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 2200;
                Damage = 138;
                break;
            case 3:
                HP = 2420;
                Damage = 152;
                break;
            case 4:
                HP = 2660;
                Damage = 167;
                break;
            case 5:
                HP = 2920;
                Damage = 183;
                break;
        }
    }

    /**
     * implements the action of card
     *
     * @param action its action class
     * @param isRage the rage of card
     */
    public synchronized void action (Action action,boolean isRage){
        Action opponent = action.inRange(1);
        if (opponent != null){
            try {
                Thread.sleep((long) (1500* (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            opponent.getHit((int) (Damage * (isRage?1.4:1)));
        }
        else {
            try {
                Thread.sleep((long) (1500 * (isRage?0.6:1)));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move(action.getTroop(),action.getMapContent());
        }
    }
}