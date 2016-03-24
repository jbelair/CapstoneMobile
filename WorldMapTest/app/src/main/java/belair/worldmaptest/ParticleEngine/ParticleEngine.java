package belair.worldmaptest.ParticleEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sean on 3/18/2016.
 */
public class ParticleEngine {

    public float particleOriginX;
    public float particleOriginY;
    private List<Bitmap> textures = new ArrayList<Bitmap>();
    private List<Particle> particles = new ArrayList<Particle>();

    public ParticleEngine (){
        /*particleOriginX = spawnLocationX;
        particleOriginY = spawnLocationY;
        this.textures = _textures;
        this.particles = new ArrayList<Particle>();*/
    }

    public void Update(){
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).Update();
            if (particles.get(i).life <= 0){
                particles.remove(i);
                i--;
            }
        }
    }

    public void generateNewParticle(Bitmap _bmp, float _posx, float _posy, float _velx, float _vely, float _angleOfRotation,
                                    float _angleChangeRate, float _size, int _lifetimeInMilliseconds){
        particles.add(new Particle(_bmp, _posx, _posy, _velx, _vely, _angleOfRotation, _angleChangeRate, _size, _lifetimeInMilliseconds));
    }

    public void Draw(Canvas canvas){
        for (int i = 0; i < particles.size(); i++){
            particles.get(i).Render(canvas);
        }
    }
}
