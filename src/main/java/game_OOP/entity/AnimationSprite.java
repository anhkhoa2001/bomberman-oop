package game_OOP.entity;

public class AnimationSprite extends Sprite {
    public final int countDirection = 5;

    public double[][] coordinatesX = new double[countDirection][];
    public double[][] coordinatesY = new double[countDirection][];

    protected int countSprite;
    protected int countChange;


    public AnimationSprite(double width, double height, double widthX, double heightY) {
        super(width, height, widthX, heightY);
        speed = 4;
        countSprite = 0;
        countChange = 0;
    }

    public void animation(int direction) {
        switch (direction) {
            case 0:
                changeSprite(directionLEFT);
                break;
            case 1:
                changeSprite(directionRIGHT);
                break;
            case 2:
                changeSprite(directionBOTTOM);
                break;
            case 3:
                changeSprite(directionTOP);
                break;
        }
        updateSprite(direction);
    }

    public void changeSprite(int direction) {
        countChange ++;
        if(countChange >= speed*3) {
            countChange = 0;
            countSprite = (countSprite + 1)%coordinatesX[direction].length;
        }
    }

    public void updateSprite(int direction) {
        spriteX = coordinatesX[direction][countSprite];
        spriteY = coordinatesY[direction][countSprite];
    }
}
