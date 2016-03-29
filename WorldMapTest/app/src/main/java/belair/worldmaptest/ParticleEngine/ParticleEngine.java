package belair.worldmaptest.ParticleEngine;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Sean on 3/18/2016.
 */
public class ParticleEngine {
    private List<Particle> particles = new ArrayList<Particle>();
    private Random random;
    public ParticleEngine (){ }

    public void Update(){
        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).Update();
            if (particles.get(i).life <= 0){
                particles.remove(i);
                i--;
            }
        }
    }

    public void generateNewParticles(int numOfParticles, Bitmap bmp, float minXpos, float maxXpos, float minYpos, float maxYpos,
                                     float minXvel, float maxXvel, float minYvel, float maxYvel, float angleOfRotation,
                                     float angleChangeRate, float size, int lifeTime){
        for (int i = 0; i < numOfParticles; i++){
            generateNewParticle(bmp, random.nextFloat() * (maxXpos - minXpos + 1) + minXpos, random.nextFloat() * (maxYpos - minYpos + 1) + minYpos,
                    random.nextFloat() * (maxXvel - minXvel + 1) + minXvel, random.nextFloat() * (maxYvel - minYvel + 1) + minYvel,
                    angleOfRotation, angleChangeRate, size, lifeTime);
        }

    }

    private void generateNewParticle(Bitmap _bmp, float _posx, float _posy, float _velx, float _vely, float _angleOfRotation,
                                    float _angleChangeRate, float _size, int _lifetimeInMilliseconds){
        particles.add(new Particle(_bmp, _posx, _posy, _velx, _vely, _angleOfRotation, _angleChangeRate, _size, _lifetimeInMilliseconds));
    }

    public void Draw(Canvas canvas){
        for (int i = 0; i < particles.size(); i++){
            particles.get(i).Render(canvas);
        }
    }
}
