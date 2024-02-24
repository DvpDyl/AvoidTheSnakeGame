import java.util.ArrayList;

public class Player{
    int x = 250;
    int y = 150;
    public void move(Ball b, GameArena arena){
        //move the ball acting as the player to mouse position
        x = arena.getMousePositionX();
        y = arena.getMousePositionY();
        b.setXPosition(x);
        b.setYPosition(y);
        //System.out.println(y);
    }
    public boolean getCollide(ArrayList<Ball> ballArray, Ball e, Ball b, ArrayList<Ball> staticEnemy){
        //check if game needs to end
        boolean collide = false;
        collide = e.collides(b);
        for(int i = 0; i<ballArray.size(); i++){
            if(collide == true)
                break;
            collide = (ballArray.get(i)).collides(b);
        }
        for(int i = 0; i<staticEnemy.size();i++){
            if(collide == true)
                break;
            collide =(staticEnemy.get(i).collides(b));
        }

        return collide;
    }
}