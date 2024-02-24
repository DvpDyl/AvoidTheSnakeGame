import java.util.Random;
public class StaticEnemy{

    private double x;
    private double y;

    public StaticEnemy(){
        Random rand = new Random();

        this.x = rand.nextDouble() * 1000;
        this.y = rand.nextDouble() * 600;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public String getColour(){
        String[] colours = {"BLUE","CYAN","GREEN","LIGHTGREY","MAGENTA","ORANGE","PINK","YELLOW"};

        Random rand = new Random();
        int i = rand.nextInt(8);

        return colours[i];
    }
    
}