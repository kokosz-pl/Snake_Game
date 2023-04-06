import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Snake {

    private int length;
    private List<List<Integer>> coordinates = new ArrayList<>();

    public Snake() {

        length = 3;

        this.coordinates.add(Arrays.asList(30, 20));

        for (int i = 1; i <= length; i++) {
            this.coordinates
                    .add(Arrays.asList(this.coordinates.get(0).get(0) - i * 10, this.coordinates.get(0).get(1)));
        }

    }

    public int getLength() {
        return this.length;
    }

    public void setHead_x(int newHead_x) {
        this.coordinates.get(0).set(0, newHead_x);
    }

    public int getHead_x() {
        return this.coordinates.get(0).get(0);
    }

    public void setHead_y(int newHead_y) {
        this.coordinates.get(0).set(1, newHead_y);
    }

    public int getHead_y() {
        return this.coordinates.get(0).get(1);
    }

    public List<List<Integer>> getCoordinates() {
        return new ArrayList<>(this.coordinates);
    }

    public void updateCoordinates() {

        for (int i = this.length; i > 0; i--) {
            this.coordinates.set(i,
                    Arrays.asList(this.coordinates.get(i - 1).get(0), this.coordinates.get(i - 1).get(1)));
        }
    }

    public void updateLenght(int x, int y) {


        this.coordinates.add(0, Arrays.asList(getHead_x() + x, getHead_y() + y));
        this.length += 1;
    }

    public boolean checkNewApple(int x, int y) {

        for (int i = 0; i < this.length; i++) {
            if ((this.coordinates.get(i)).equals(Arrays.asList(x, y))) {
                return false;
            }
        }
        return true;
    }

    public boolean selfCollision() {

        for (int i = 3; i <= this.length; i++) {
            if ((this.coordinates.get(0)).equals(this.coordinates.get(i))) {
                return true;
            }
        }
        return false;
    }

}
