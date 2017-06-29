import java.awt.*;
import java.util.Random;
import java.util.Vector;

public class Food {

    private boolean Eaten;

    private Cord food;

    Food(Snake snake) {

        Eaten = true;
        food = new Cord(-1, -1);

    }


    void eaten() {
        Eaten = true;
    }


    Cord getFood() {
        return new Cord(food.x, food.y);
    }

    void update(Graphics g, Snake snake) {


        if (Eaten) {


            food = generateFood(snake);

            Eaten = false;

        }

    }


    boolean isOnSnake(Snake snake) {

        Vector<Cord> path = snake.getPath();

        for (Cord c : path) {
            if (c.x == food.x && c.y == food.y)
                return true;
        }

        return false;
    }

    Cord generateFood(Snake snake) {


        Random random = new Random();


        food.x = Math.abs(random.nextInt() % 14);
        food.y = Math.abs(random.nextInt() % 14);


        while (isOnSnake(snake)) {

            food.x = Math.abs(random.nextInt() % 14);
            food.y = Math.abs(random.nextInt() % 14);
        }

        return food;


    }


    void draw(Graphics g) {

        g.setColor(Color.blue);
        g.fillRect(Board.startingWidth + 1 + food.x * 40, Board.startingHeight + 1 + 40 * food.y, 39, 39);


    }


}
