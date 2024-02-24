import java.util.ArrayList;
public class Enemy{

    double coords[] = {-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10,-10};

    public Enemy(int number, GameArena arena, ArrayList<Ball> ballArray){
        // create the snake (the enemies that follow the main enemy)
        int x = -10;
        int y = -10;
        for(int i = 0; i<number;i++){
            ballArray.add(new Ball(x,y,50,"RED",10));
            arena.addBall(ballArray.get(i));
            x -= 10;
            y -= 10;
        }
    }

    public void move(Ball e, GameArena arena, Ball b, ArrayList<Ball> ballArray, int number){
        arena.pause();
        double bx = b.getXPosition();
        double by = b.getYPosition();
        double ex = e.getXPosition();
        double ey = e.getYPosition();
        double angle = 0;
        double x = 0;
        double y = 0;
        Ball temp = new Ball(-200,-200,50,"Red");
        Ball temp2 = new Ball(-200,-200,50,"Red");
        if(ey==by){
            if(ex<bx){
                e.move(10,0);
                return;
            }else{
                e.move(-10,0);
                return;
            }
        }
        //used sine rule to find the angle of the enemy to the player then substituted 10 as the hypotenuse to find x and y
        if(ey<by)
            if(ex==bx){
                x = 0;
                y = 10;
            }else if(ex<bx){
                angle = Math.atan((by-ey)/(bx-ex));
                x = Math.cos(angle)*10;
                y = Math.sin(angle)*10;
            }else if(ex>bx){
                angle = Math.atan((by-ey)/(ex-bx));
                x = -(Math.cos(angle)*10);
                y = Math.sin(angle)*10;
            }
        if(ey>by)
            if(ex==bx){
                x = 0;
                y = -10;
            }else if(ex<bx){
                angle = Math.atan((bx-ex)/(ey-by));
                x = Math.sin(angle)*10;
                y = -(Math.cos(angle)*10);
            }else if(ex>bx){
                angle = Math.atan((ex-bx)/(ey-by));
                x = -(Math.sin(angle)*10);
                y = -(Math.cos(angle)*10);
            } 
        if(ey==by) 
            if(ex<bx){
                x = 10;
                y = 0;
            }else{
                x = -10;
                y = 0;
            }
        e.move(x,y);
        //starting from the back of the snake set the coords of the ball infront of it
        for(int i = (ballArray.size()) - 1; i>0;i--){
            temp = ballArray.get(i);
            temp2 = ballArray.get(i-1);
            temp.setXPosition(temp2.getXPosition());
            temp.setYPosition(temp2.getYPosition());
        }
        temp = ballArray.get(0);
        temp.setXPosition(e.getXPosition());
        temp.setYPosition(e.getYPosition());


    }
}
