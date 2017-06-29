import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Snake {

    private Cord startingPoint = new Cord(0, 7);

    private Vector<Cord> path = new Vector<Cord>();

    private int size;

    private int xSpeed = 1;

    private int ySpeed = 0;

    private Cord to_Hide = new Cord(-1, -1);

    private Cord to_Draw;

    private boolean keyPressed = false;

    private KeyListener listener;


    Snake(int size) {

        this.size = size;

        to_Draw = startingPoint;

        path.add(to_Draw);

        listener = new listener();

    }


    class listener implements KeyListener {


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (!keyPressed) {
                switch (e.getKeyCode()) {

                    case KeyEvent.VK_UP:
                        if (xSpeed != 0 && ySpeed != 1)
                            setSpeed(0, -1);
                        break;

                    case KeyEvent.VK_DOWN:
                        if (xSpeed != 0 && ySpeed != -1)
                            setSpeed(0, 1);
                        break;

                    case KeyEvent.VK_LEFT:
                        if (xSpeed != 1 && ySpeed != 0)
                            setSpeed(-1, 0);
                        break;

                    case KeyEvent.VK_RIGHT:
                        if (xSpeed != -1 && ySpeed != 0)
                            setSpeed(1, 0);
                        break;

                }
                keyPressed = true;
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }


    KeyListener getListener() {
        return listener;
    }


    Vector<Cord> getPath() {
        return new Vector<>(this.path);
    }

    void setSpeed(int x, int y) {

        xSpeed = +x;
        ySpeed = +y;

    }


    public void update() {


        if (size != path.size()) {

            Cord prev = path.elementAt(path.size() - 1);

            to_Draw = new Cord(prev.x + xSpeed, prev.y + ySpeed);


            path.add(to_Draw);

            correctScene(path.size() - 1);

        } else {


            to_Hide.x = path.firstElement().x;
            to_Hide.y = path.firstElement().y;


            moveVectLeft();


            path.lastElement().x += xSpeed;

            path.lastElement().y += ySpeed;


            correctScene(path.size() - 1);


            to_Draw = path.lastElement();


        }

    }


    boolean foodEaten(Food food) {

        Cord f = food.getFood();

        if (to_Draw.x == f.x && to_Draw.y == f.y) {
            size++;

            food.eaten();

            return true;
        }

        return false;
    }


    public boolean isEatingHimself() {

        for (int i = 0; i < path.size() - 1; i++) {

            if (to_Draw.x == path.elementAt(i).x && to_Draw.y == path.elementAt(i).y) {
                return true;

            }
        }

        return false;
    }


    void moveVectLeft() {

        for (int i = 0; i < path.size() - 1; i++) {


            path.elementAt(i).x = path.elementAt(i + 1).x;
            path.elementAt(i).y = path.elementAt(i + 1).y;

        }

    }


    void correctScene(int id) {

        Cord point = path.elementAt(id);

        if (point.x == 14)
            point.x = 0;
        if (point.x == -1)
            point.x = 13;


        if (point.y == 14)
            point.y = 0;
        if (point.y == -1)
            point.y = 13;


    }


    public void draw(Graphics g) {

        try {


            TimeUnit.MILLISECONDS.sleep(250);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }


        if (to_Hide.x != -1 && to_Hide.y != -1) {

            g.setColor(Color.black);
            g.fillRect(Board.startingWidth + 1 + to_Hide.x * 40, Board.startingHeight + 1 + 40 * to_Hide.y, 39, 39);
        }


        g.setColor(Color.GREEN);
        g.fillRect(Board.startingWidth + 1 + to_Draw.x * 40, Board.startingHeight + 1 + 40 * to_Draw.y, 39, 39);


        keyPressed = false;

    }


    void blink(Graphics g) {

        for (int i = 0; i < 3; i++) {

            for (Cord c : path) {

                g.setColor(Color.black);
                g.fillRect(Board.startingWidth + 1 + c.x * 40, Board.startingHeight + 1 + 40 * c.y, 39, 39);

                g.setColor(Color.black);
                g.fillRect(Board.startingWidth + 1 + to_Hide.x * 40, Board.startingHeight + 1 + 40 * to_Hide.y, 39, 39);


            }


            try {


                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }


            for (Cord c : path) {

                g.setColor(Color.green);
                g.fillRect(Board.startingWidth + 1 + c.x * 40, Board.startingHeight + 1 + 40 * c.y, 39, 39);


                g.setColor(Color.green);
                g.fillRect(Board.startingWidth + 1 + to_Hide.x * 40, Board.startingHeight + 1 + 40 * to_Hide.y, 39, 39);


                g.setColor(Color.red);
                g.fillRect(Board.startingWidth + 1 + to_Draw.x * 40, Board.startingHeight + 1 + 40 * to_Draw.y, 39, 39);


            }


            try {

                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {

                e.printStackTrace();
            }


        }

    }


}


class Cord {

    int x;
    int y;

    Cord(int x, int y) {
        this.x = x;
        this.y = y;
    }


}