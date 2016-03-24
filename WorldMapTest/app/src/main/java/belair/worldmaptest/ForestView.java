package belair.worldmaptest;
//////////////////////////////////////////////////////////
//                   COMMENTS  March 17th, 2016         //
//       Updates Implemented:                           //
//    -  Tile based overworld.                          //
//    -  World imported from text file                  //
//    -  Player + Entity hierarchy                      //
//    -                                                 //
//                                                      //
//      Updates Currently Implementing                  //
//    - Collision with Entities                         //
//    - Collision with Tiles                            //
//    - Code needs MASSIVE overhaul on organization     //
//    -                                                 //
//                                                      //
//      Future Updates                                  //
//    - Skill tree system                               //
//    - Damage and Health systems for Entities          //
//    - Active skills for skill trees                   //
//    - Particle system                                 //
//                                                      //
//////////////////////////////////////////////////////////
import android.content.Context;
import android.graphics.*;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import belair.worldmaptest.Maps.Map;
import belair.worldmaptest.ParticleEngine.ParticleEngine;
import belair.worldmaptest.Tile.Tile;

public class ForestView extends SurfaceView {
    Player player;
    Map map;
    SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    float xFinger = 0;
    float yFinger = 0;
    //ParticleEngine PE = new ParticleEngine();

    Boolean isFingerDown = false;
    Paint paint = new Paint();



    public ForestView(Context context) {
        super(context);
        DisplayMetrics dm = new DisplayMetrics();
        int height = dm.heightPixels;
        int width = dm.widthPixels;
        gameLoopThread = new GameLoopThread(this);
        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopThread.setRunning(false);
                while (retry) {
                    try {
                        gameLoopThread.join();
                        retry = false;
                    } catch (InterruptedException e) {
                        System.out.print("Exception");
                    }
                }
            }
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });


        map = new Map(context, "map.txt");
        player = new Player(0, 0, map);
        player.bmp = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        //PARTICLE TEST
       // Bitmap tempParticleBMP = BitmapFactory.decodeResource(getResources(), R.raw.particletest);
      //  PE.generateNewParticle(tempParticleBMP, 2, 2, 2, 2, 2, 2, 2, 1000);


        Tile.grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        Tile.forest = BitmapFactory.decodeResource(getResources(), R.drawable.forest);
        Tile.river = BitmapFactory.decodeResource(getResources(), R.drawable.river);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        canvas.translate(-player.x + this.getWidth() / 2 - (player.bmp.getWidth() / 2), -player.y + this.getHeight() / 2 - (player.bmp.getHeight() / 2));

        //Tile.tileArray[0].Draw(canvas, 0, 0);
        //Tile.tileArray[1].Draw(canvas, 192,50);

        // player collision with tiles //
        if(map.GetTile(4, 4).IsSolid()){
            player.isMoving = false;
        }

        //PE.Update();

        map.Update();
        player.Update();
        map.Draw(canvas);
        //PE.Draw(canvas);
        if(isFingerDown) {

            canvas.drawLine(player.x + player.bmp.getWidth() / 2, player.y + player.bmp.getHeight() / 2, xFinger, yFinger, paint);
        }

        player.Render(canvas);
    }

    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                if (player.isMoving){
                    player.isMoving = false;
                    player.endX = player.x;
                    player.endY = player.y;

                }
                xFinger = event.getX() + player.x - 1080 / 2 + (player.bmp.getWidth() / 2);
                yFinger = event.getY()  + player.y - 1920 / 2 + (player.bmp.getHeight() / 2);
                isFingerDown = true;
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                xFinger = event.getX() + player.x - 1080 / 2 + (player.bmp.getWidth() / 2);
                yFinger = event.getY() + player.y - 1920 / 2 + (player.bmp.getHeight() / 2);
                isFingerDown = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                player.endX = xFinger = event.getX() + player.x - 1080 / 2 + (player.bmp.getWidth() / 2);
                player.endY = yFinger = event.getY() + player.y - 1920 / 2 + (player.bmp.getHeight() / 2);
                isFingerDown = false;

                player.FingerLift();
                invalidate();
                break;
        }
        return true;
    }
}