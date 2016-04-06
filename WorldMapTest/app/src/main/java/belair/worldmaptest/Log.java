package belair.worldmaptest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Justin on 4/4/2016.
 */
public class Log extends Entity{

    public Bitmap logImage;
    Paint paint = new Paint();

    public Log(float x, float y){
        super(x, y);


    }
    @Override
    public void Update() {

    }

    @Override
    public void Render(Canvas canvas) {
        paint.setColor(Color.TRANSPARENT);
        canvas.drawBitmap(logImage, x, y, null);
    }
}
