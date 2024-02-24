import java.awt.event.*;
public class MouseMove implements MouseMotionListener{
    private Player pc;
    private GameArena arena;
    private Ball b;

    public MouseMove(Player p, GameArena arena, Ball b){
        this.pc = p;
        this.arena = arena;
        this.b = b;
        (arena.getPanel()).addMouseMotionListener(this);
    }
    @Override
    public void mouseMoved(MouseEvent e){
        pc.move(b,arena);
    }
    @Override
    public void mouseDragged(MouseEvent e){
        return;
    }

}