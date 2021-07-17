package Classes;

import javafx.scene.image.Image;

/**
 * The type Troop.
 */
public class Troop extends FightCard{

    /**
     * The Speed.
     */
    protected String Speed;
    /**
     * The Is melee.
     */
    protected boolean IsMelee;
    /**
     * The Area splash.
     */
    protected boolean AreaSplash;
    /**
     * The Count.
     */
    protected int Count;


    public Troop(Image cardPic, Image gamePic) {
        super(cardPic, gamePic);
    }
}
