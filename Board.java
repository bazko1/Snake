import javax.swing.*;
import java.awt.*;


public class Board extends JFrame {


    public static int Height = 14 * 40 + 80;

    public static int Width = 14 * 40 + 21;

    public static int startingHeight = 68;

    public static int startingWidth = 10;

    Font font = new Font(Font.MONOSPACED,Font.BOLD,20);

    private int score;

    Board() {


        super();
        score = 0;
        setFocusable(true);
        setSize(Width, Height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Snake");

    }


    @Override
    public void paint(Graphics g) {

        drawBackground(g);


    }


    public void drawBackground(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, Width, Height);

        for (int i = 0; i < 14 * 14; i++) {

            g.setColor(Color.white);
            g.drawRect(40 * (i % 14) + startingWidth ,  startingHeight  + 40 * (i / 14), 40, 40);










            g.setColor(Color.white);
            g.setFont(font);
            g.drawString("Score: 0" ,20,55 );


        }



    }

    public void updateScore(Graphics g,int score){


        g.setColor(Color.black);
        g.setFont(font);
        g.drawString("Score: " + (score-1),20,55);


        g.setColor(Color.white);
        g.setFont(font);
        g.drawString("Score: " + score,20,55);


    }


}







