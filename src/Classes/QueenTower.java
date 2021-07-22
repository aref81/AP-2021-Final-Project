package Classes;

import javafx.scene.image.ImageView;
import sample.GameControllers.Action;
import sample.GameControllers.GameTime;

/**
 * The type Queen tower.
 */
public class QueenTower extends Tower{
    private int rowEnd;
    private int columnEnd;
    private KingTower king;
    /**
     * Instantiates a new Queen tower.
     */
    public QueenTower(FightCard card, ImageView[][] mapView, String[][] map, String[][] troop, Action[][] mapContent, int row, int column, GameTime gameTime, String side , ImageView imageView , String[][] mapStatus ,KingTower king, String[][] airTroop , Action[][] airFieldContent){
        super(card, mapView, map, troop, mapContent, row, column, gameTime,side , 1400, imageView,mapStatus,airTroop,airFieldContent);
        Range = 7.5;
        HitSpeed = 0.8;
        HP = 1400;
        Damage = 50;
        Level = 1;
        rowEnd = row + 2;
        columnEnd = column + 2;
        this.king = king;
    }

    @Override
    public void Upgrade() {
        super.Upgrade();
        switch (Level) {
            case 2:
                HP = 1512;
                Damage = 54;
                break;
            case 3:
                HP = 1624;
                Damage = 58;
                break;
            case 4:
                HP = 1750;
                Damage = 62;
                break;
            case 5:
                HP = 1890;
                Damage = 69;
        }
    }

    @Override
    public void run() {
        updateStatus();
        while (getHp() > 0 && !(getGameTime().isEndGame())){
            updateStatus();
            synchronized (Thread.currentThread()) {
                Action opponent = inRange((int) Math.floor(7.5));
                if (opponent != null) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    opponent.getHit(Damage);
                } else {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        die();
    }

    private void die () {
        imageView.setImage(null);
        king.setActive(true);


        int rowUp;
        int rowDown;
        int colLeft;
        int colRight;

        Action [][] mapContent = getMapContent();

        if (getRow() > rowEnd){
            rowDown = getRow();
            rowUp = rowEnd;
        }
        else {
            rowDown = rowEnd;
            rowUp = getRow();
        }

        if ( getColumn() > columnEnd){
            colRight = getColumn();
            colLeft = columnEnd;
        }
        else {
            colLeft = getColumn();
            colRight = columnEnd;
        }

        for (int i = rowUp ; i <= rowDown ; i++){
            for (int j = colLeft ; j <= colRight ; j++){
                mapContent[i][j] = null;
                if (j != colLeft + 1) {
                    map[i][j] = "Field";
                }
                else {
                    map[i][j] = "Way";
                }
            }
        }
        if (imageView.getId().equals("PrincessRedRight")) {
            for (int i = 11; i < 16; i++) {
                for (int j = 9; j < 18; j++) {
                    mapStatus[i][j] = "Free";
                }
            }
        }
        else if (imageView.getId().equals("PrincessRedLeft")){
            for (int i = 11; i < 16; i++) {
                for (int j = 0; j < 9; j++) {
                    mapStatus[i][j] = "Free";
                }
            }
        }
        setDestroyed(true);
    }

    public Action inRange (int range) {
        int rowUp;
        int rowDown;
        int colLeft;
        int colRight;

        String [][] status = getStatus();
        Action [][] mapContent = getMapContent();
        Action [][] airContent = getAirContent();

        if (getRow() > rowEnd){
            rowDown = getRow();
            rowUp = rowEnd;
        }
        else {
            rowDown = rowEnd;
            rowUp = getRow();
        }

        if ( getColumn() > columnEnd){
            colRight = getColumn();
            colLeft = columnEnd;
        }
        else {
            colLeft = getColumn();
            colRight = columnEnd;
        }

        for (int j = colLeft ; j <= colRight ; j++){
            for (int i = 1 ; i <= range && (rowDown + i < 32) ; i++) {
                if (status[rowDown + i][j].equals("Target")) {
                    if (mapContent[rowDown + i][j] != null) {
                        return mapContent[rowDown + i][j];
                    }
                    else if (airContent[rowDown + i][j] != null){
                        return airContent[rowDown + i][j];
                    }
                }
            }
        }
        for (int j = colLeft ; j <= colRight ; j++) {
            for (int i = 1; i <= range && (rowUp - i >= 0); i++) {
                if (status[rowUp - i][j].equals("Target")) {
                    if (mapContent[rowUp - i][j] != null) {
                        return mapContent[rowUp - i][j];
                    }
                    else if (airContent[rowUp - i][j] != null){
                        return airContent[rowUp - i][j];
                    }
                }
            }
        }
        for (int j = rowUp ; j <= rowDown ; j++) {
            for (int i = 1; i <= range && (colRight + i < 18); i++) {
                if (status[j][colRight + i].equals("Target")) {
                    if (mapContent[j][colRight + i] != null) {
                        return mapContent[j][colRight + i];
                    }
                    else if (airContent[j][colRight + i] != null){
                        return airContent[j][colRight + i];
                    }
                }
            }
        }
        for (int j = rowUp ; j <= rowDown ; j++) {
            for (int i = 1; i <= range && (colLeft - i >= 0); i++) {
                if (status[j][colLeft - i].equals("Target")) {
                    if (mapContent[j][colLeft - i] != null) {
                        return mapContent[j][colLeft - i];
                    }
                    else if (airContent[j][colLeft - i] != null){
                        return airContent[j][colLeft - i];
                    }
                }
            }
        }
        return null;
    }
}
