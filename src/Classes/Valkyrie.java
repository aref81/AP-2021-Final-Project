package Classes;

import javafx.scene.image.Image;
import sample.GameControllers.Action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * The type Valkyrie.
 */
public class Valkyrie extends Troop{
    /**
     * Instantiates a new Valkyrie.
     */
    public Valkyrie() throws FileNotFoundException {
        super(new Image(new FileInputStream("src/sample/GameMenu/Troops/ValkyrieCard.png")),new Image(new FileInputStream("src/sample/Game/Troops/Valkyrie.png")),"Valkyrire");
        HitSpeed = 1.5;
        Speed = "Medium";
        Target = "G";
        IsMelee = true;
        AreaSplash = true;
        Count = 1;
        Cost = 4;
        Level = 1;
        HP = 880;
        Damage = 120;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 968;
                Damage = 132;
                break;
            case 3:
                HP = 1064;
                Damage = 145;
                break;
            case 4:
                HP = 1170;
                Damage = 159;
                break;
            case 5:
                HP = 1284;
                Damage = 175;
                break;
        }
    }

    public synchronized void action (Action action){
        Action enemy = action.inRange(1);
        if (enemy != null){
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            enemy.getHit(Damage);
            action.updateStatus();
            enemy.areaSplash(action.getStatus(),Damage);
        }
        else {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            action.move(action.getTroop(),action.getMapContent());
        }
    }
}
