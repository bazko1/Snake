import java.awt.*;

public class Game {

    static Food food;
    static Board b;
    static Snake snake;
    static int score=0;


    public static void main(String[] args) {


        b = new Board();

        snake = new Snake(5);

        food = new Food(snake);

        b.setBackground(Color.black);

        b.addKeyListener(snake.getListener());
        Graphics g = b.getGraphics();


        while (true) {

            food.update(g, snake);
            food.draw(g);


            if(snake.foodEaten(food) ){
               score++;
               b.updateScore(g,score);
            }

            snake.draw(g);
            snake.update();



            if (snake.isEatingHimself()) {
                snake.blink(g);
                restartGame();
                continue;

            }


        }


    }


    static void restartGame() {

        snake = new Snake(5);
        b.repaint();
        food = new Food(snake);
        b.addKeyListener(snake.getListener());
        b.setBackground(Color.black);
        score=0;

    }

}
