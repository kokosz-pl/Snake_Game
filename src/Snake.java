import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Snake {

    private final static int Width = ConstValues.WINDOW_WIDTH.value;
    private final static int Height = ConstValues.WINDOW_HEIGHT.value;
    private final static int ballSize = ConstValues.BALL_SIZE.value;;
    private int length;
    private List<List<Integer>> snakeCoordinates;
    private HashSet<List<Integer>> freeCoordinates;

    public Snake() {

        this.snakeCoordinates = new ArrayList<>();
        this.freeCoordinates = new HashSet<>();

        this.length = 3;

        this.snakeCoordinates.add(Arrays.asList(3 * ballSize, 2 * ballSize));

        for (int i = 1; i <= length; i++) {
            this.snakeCoordinates
                    .add(Arrays.asList(this.snakeCoordinates.get(0).get(0) - i * ballSize,
                            this.snakeCoordinates.get(0).get(1)));
        }

        for (int i = 0; i < Height; i += 10) {
            for (int j = 0; j < Width; j += 10) {
                if (!(this.snakeCoordinates.contains(Arrays.asList(j, i)))) {
                    this.freeCoordinates.add(Arrays.asList(j, i));
                }

            }
        }

    }

    public int getLength() {
        return this.length;
    }

    public int getHead_x() {
        return this.snakeCoordinates.get(0).get(0);
    }

    public int getHead_y() {
        return this.snakeCoordinates.get(0).get(1);
    }

    public int[] getCoordinates(int index) {
        int x_corr = this.snakeCoordinates.get(index).get(0);
        int y_corr = this.snakeCoordinates.get(index).get(1);
        return new int[] { x_corr, y_corr };
    }

    public void updateCoordinates(int newHead_x, int newHead_y) {

        this.freeCoordinates.add(Arrays.asList(this.snakeCoordinates.get(this.length).get(0),
                this.snakeCoordinates.get(this.length).get(1)));

        this.snakeCoordinates.remove(this.length);
        this.snakeCoordinates.add(0, Arrays.asList(this.getHead_x() + newHead_x, this.getHead_y() + newHead_y));

        this.freeCoordinates.remove(Arrays.asList(this.getHead_x(), this.getHead_y()));

    }

    public void updateLenght(int x, int y) {

        this.snakeCoordinates.add(0, Arrays.asList(this.getHead_x() + x, this.getHead_y() + y));
        this.freeCoordinates.remove(Arrays.asList(this.getHead_x(), this.getHead_y()));
        this.length += 1;
    }

    public int[] newApplePosition() {

        Random rand = new Random();
        int index = rand.nextInt(this.freeCoordinates.size());
        Iterator<List<Integer>> iter = this.freeCoordinates.iterator();
        for (int i = 0; i < index; i++) {
            iter.next();
        }   

        List<Integer> newPosition = new ArrayList<>();
        newPosition = iter.next();

        return new int[] {newPosition.get(0), newPosition.get(1)};
    }

    public boolean selfCollision() {

        for (int i = 3; i <= this.length; i++) {
            if ((this.snakeCoordinates.get(0)).equals(this.snakeCoordinates.get(i))) {
                return true;
            }
        }
        return false;
    }

}
