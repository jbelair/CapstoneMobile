package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by Justin on 4/12/2016.
 */
public class Tree extends Entity {

    Random rand = new Random();
    public Bitmap logImage;
    public int amount;
    Paint paint = new Paint();

    public Tree(float x, float y){
        super(x, y);
        amount = rand.nextInt(10);
    }
    @Override
    public void Update() {
        if(getIsAlive()) {

            if (amount <= 0) {
                setIsAlive(false);
                amount = rand.nextInt(10);
            } else {

                amount -= 1;
            }
        }
    }

    @Override
    public void Render(Canvas canvas) {
        if (isAlive) {
            paint.setColor(Color.TRANSPARENT);
            canvas.drawBitmap(logImage, getX(), getY(), null);
        }
    }
}
