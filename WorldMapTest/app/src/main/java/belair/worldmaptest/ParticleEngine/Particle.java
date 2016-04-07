package belair.worldmaptest.ParticleEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import belair.worldmaptest.Entity;

/**
 * Created by Sean on 3/18/2016.
 */
public class Particle extends Entity {
    public Bitmap bmp;
    public float velx;
    public float vely;
    public float angleOfRot;
    public float angleROC;
    public float size;
    public int life;

    public Particle(Bitmap _bmp, float _posx, float _posy, float _velx, float _vely, float _angleOfRotation,
                    float _angleChangeRate, float _size, int _lifetimeInMilliseconds){
        super(_posx, _posy);
        bmp = _bmp;
        velx = _velx;
        vely = _vely;
        angleOfRot = _angleOfRotation;
        angleROC = _angleChangeRate;
        size = _size;
        life = _lifetimeInMilliseconds;

    }

    @Override
    public void Update() {
        life--;
        setX(getX() + velx);
        setY(getY() + vely);
        angleOfRot += angleROC;
    }

    @Override
    public void Render(Canvas canvas) {
        canvas.drawBitmap(bmp, getX(), getY(), null);
    }
}
