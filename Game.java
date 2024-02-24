import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
public class Game{

    public static ArrayList<Ball> ballArray = new ArrayList<>();
    public static ArrayList<Ball> staticEnemy = new ArrayList<>();
    private static boolean collide = false;
    private static Text startText;
    private static Text score;
    private static Text sNum;
    private static Text topScoreText;
    private static Text hNum;
    private static Player p;
    private static Ball b;
    private static Ball e;
    private static GameArena arena;
    private static Timer timer;
    private static Enemy enemy;
    private static String scoreNum;
    private static String highScoreNum;
    public static void main(String[] args){
        arena = new GameArena(1000,600);
        startText = new Text("HOVOR OVER WHITE CIRCLE TO START",15,500,150,"WHITE");
        highScoreNum = "0";
        arena.addText(startText);
        initialiseGame();
        runGame();
    }

    private static void initialiseGame(){
        b = new Ball(-1000,-1000,2,"Green");
        p = new Player();
        arena.addBall(b);
        e = new Ball(0,0,50,"RED");
        arena.addBall(e);
        enemy = new Enemy(20, arena, ballArray);
        collide = false;
        Ball start = new Ball(500,300,100,"WHITE");
        arena.addBall(start);
        MouseMove m = new MouseMove(p,arena,b);
        topScoreText = new Text("TOP SCORE:",15,440,40,"WHITE",1);
        scoreNum = "0";
        final Integer[] number = new Integer[]{0}; //chatgpt used
        score = new Text("Score:",15,890,40, "WHITE", 1);
        sNum = new Text(scoreNum.toString(),15,950,40,"WHITE", 1);
        hNum = new Text(highScoreNum,15,550,40,"WHITE",1);
        arena.addText(hNum);
        arena.addText(topScoreText);

        while(!(start.collides(b))){
            arena.pause();
        }

        arena.removeText(startText);
        arena.removeBall(start);
        arena.addText(score);
        arena.addText(sNum);


        //used chatgpt
        //set up timer
        timer = new Timer();
        TimerTask task = new TimerTask(){
            @Override
            public void run(){
                //add enemy
                ballArray.add(new Ball(-100,-100,50,"RED"));
                arena.addBall(ballArray.get((ballArray.size())-1));
            }
        };
        TimerTask scorePlusOne = new TimerTask() {
            @Override
            public void run(){
                //add +1 to score chatgpt used
                number[0]= number[0] + 1;
                String scoreNum = number[0].toString();
                sNum.setText(scoreNum);

                int currentScore = Integer.parseInt(scoreNum);
                int currentHighScore = Integer.parseInt(highScoreNum);

                if(currentScore>currentHighScore){
                    highScoreNum = scoreNum;
                    hNum.setText(highScoreNum);
                }

            }
        };
        TimerTask addEnemy = new TimerTask() {
            @Override
            public void run(){
                StaticEnemy sEnemy = new StaticEnemy();
                String enemyColour = sEnemy.getColour();
                staticEnemy.add(new Ball(sEnemy.getX(),sEnemy.getY(), 50, enemyColour));
                arena.addBall(staticEnemy.get(staticEnemy.size()-1));
            }
        };
        

        //schedule timer task
        timer.schedule(addEnemy, 0,3000);
        timer.schedule(scorePlusOne, 0, 1000);
        timer.schedule(task, 0, 200);

    }
    
    private static void restartGame(){
        //used chatgpt
        arena.clearGameArena();

        ballArray.clear();
        staticEnemy.clear();
        collide = false;
    

        String scoreNum = "0";
        sNum.setText(scoreNum);
        arena.addText(startText);

        initialiseGame();

    }

    private static void runGame(){
        while(!collide){
            collide = p.getCollide(ballArray, e, b, staticEnemy);
            enemy.move(e, arena, b, ballArray, ballArray.size());
        }
        timer.cancel();
        restartGame();
        runGame();
    }
}

