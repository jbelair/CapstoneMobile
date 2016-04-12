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
//    - Animations                                      //
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
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Button;

import java.util.Random;

import belair.worldmaptest.Maps.Map;
import belair.worldmaptest.ParticleEngine.ParticleEngine;
import belair.worldmaptest.Tile.Tile;

public class ForestView extends SurfaceView {
    Player player;
    Enemy enemy;
    Log log;
    Map map;
    SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    float xFinger = 0;
    float yFinger = 0;
    ParticleEngine PE = new ParticleEngine();
    //Button inventoryButton;

    Boolean isFingerDown = false;
    Paint paint = new Paint();
    Random random = new Random();


    public ForestView(Context context) {
        super(context);
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

        player.animWalkDown[0] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkdown1);
        player.animWalkDown[1] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkdown2);
        player.animWalkUp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkup1);
        player.animWalkUp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkup2);
        player.animWalkLeft[0] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkleft1);
        player.animWalkLeft[1] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkleft2);
        player.animWalkRight[0] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkright1);
        player.animWalkRight[1] = BitmapFactory.decodeResource(getResources(), R.drawable.playerwalkright2);
        player.animIdle[0] = BitmapFactory.decodeResource(getResources(),R.drawable.playeridle1);
        player.animIdle[1] = BitmapFactory.decodeResource(getResources(),R.drawable.playeridle2);

        enemy = new Enemy(1000, 1000);
        enemy.setBmp(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));

        log = new Log(1536,1700);
        log.logImage = BitmapFactory.decodeResource(getResources(), R.drawable.log);

        log.setRadius(log.logImage.getWidth() / 2);

        player.setRadius(player.bmp.getWidth() / 2);

        //PARTICLE TEST
        //Bitmap tempParticleBMP = BitmapFactory.decodeResource(getResources(), R.raw.particletest);
        //for(int i = 0; i < 10; i++) {
            //PE.generateNewParticle(tempParticleBMP, random.nextFloat() * (576) + 0, random.nextFloat() * (576) + 0 , random.nextFloat() * (3 - 0) + 0,random.nextFloat() * (3 - 0) + 0 , 2, 2, 2, 100000);
        //}

        Tile.grass = BitmapFactory.decodeResource(getResources(), R.drawable.grass);
        Tile.forest = BitmapFactory.decodeResource(getResources(), R.drawable.forest);
        Tile.river = BitmapFactory.decodeResource(getResources(), R.drawable.river);

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(5f);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        if(canvas != null) {
            canvas.drawColor(Color.BLACK);
            canvas.translate(-player.getX() + this.getWidth() / 2 - (player.bmp.getWidth() / 2), -player.getY() + this.getHeight() / 2 - (player.bmp.getHeight() / 2));

            //Tile.tileArray[0].Draw(canvas, 0, 0);
            //Tile.tileArray[1].Draw(canvas, 192,50);

            // player collision with tiles //
            if (map.GetTile(4, 4).IsSolid()) {
                player.setIsMoving(false);
            }

            //Updating everything
            PE.Update();
            map.Update();
            player.Update();
            if (player.getIsAlive() && player.getIsMoving() && Math.sqrt(Math.pow(getX() - enemy.getStartX(),2) + Math.pow(getY() - enemy.getStartY(),2)) <= enemy.getDistance() && !enemy.getIsMoving()){
                enemy.setIsMoving(true);
            }
            enemy.setEnd(player.getX(), player.getY());
            enemy.Update();

            player.CircleCircleCollision(log.getX() + log.logImage.getWidth() / 2, log.getY() + log.logImage.getHeight() / 2, log.getRadius());
            map.Draw(canvas);
            PE.Draw(canvas);
            if (isFingerDown) {
                canvas.drawLine(player.getX() + player.bmp.getWidth() / 2, player.getY() + player.bmp.getHeight() / 2, xFinger, yFinger, paint);
            }

            log.Render(canvas);
            player.Render(canvas);
            enemy.Render(canvas);
            if(player.getIsColliding()){
                paint.setColor(Color.GREEN);
                canvas.drawCircle(log.getX() + log.logImage.getWidth() / 2, log.getY() + log.logImage.getHeight() / 2, log.getRadius(), paint);
                canvas.drawCircle(player.getX() + player.bmp.getWidth() / 2, player.getY() + player.bmp.getHeight() / 2, player.getRadius(), paint);

            }
            else {
                paint.setColor(Color.WHITE);
                canvas.drawCircle(log.getX() + log.logImage.getWidth() / 2, log.getY() + log.logImage.getHeight() / 2, log.getRadius(), paint);
                canvas.drawCircle(player.getX() + player.bmp.getWidth() / 2, player.getY() + player.bmp.getHeight() / 2, player.getRadius(), paint);
            }

            //inventoryButton = (Button)findViewById(R.id.InventoryButton);
            paint.setColor(Color.WHITE);
            canvas.drawRect(player.getX() + 400, player.getY() - 760, player.getX() + 600, player.getY() - 560, paint);
            paint.setColor(Color.MAGENTA);
            paint.setTextSize(100);
            canvas.drawText("INV", player.getX() + 420, player.getY() - 620, paint);
        }
    }

    public boolean onTouchEvent(MotionEvent event){

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                if (player.getIsMoving()){
                    player.setIsMoving(false);
                    player.setEndX(player.getX());
                    player.setEndY(player.getY());

                }
                xFinger = event.getX() + player.getX() - 1080 / 2 + (player.bmp.getWidth() / 2);
                yFinger = event.getY()  + player.getY() - 1920 / 2 + (player.bmp.getHeight() / 2);
                isFingerDown = true;
                invalidate();
                break;

            case MotionEvent.ACTION_MOVE:
                xFinger = event.getX() + player.getX() - 1080 / 2 + (player.bmp.getWidth() / 2);
                yFinger = event.getY() + player.getY() - 1920 / 2 + (player.bmp.getHeight() / 2);
                isFingerDown = true;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                player.setEndX(event.getX() + player.getX() - 1080 / 2 + (player.bmp.getWidth() / 2));
                player.setEndY(event.getY() + player.getY() - 1920 / 2 + (player.bmp.getHeight() / 2));
                xFinger = player.getEndX();
                yFinger = player.getEndY();
                isFingerDown = false;

                player.FingerLift();
                invalidate();
                break;
        }
        return true;
    }
}