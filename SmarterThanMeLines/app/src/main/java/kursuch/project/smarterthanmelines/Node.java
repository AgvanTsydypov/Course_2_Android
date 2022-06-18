package kursuch.project.smarterthanmelines;

public class Node {

    int green = 0;
    int blue = 0;
    private boolean is_wall = false;
    private boolean is_start = false;
    private boolean is_end = false;
    boolean close_wall = false;

    public boolean isIs_way() {
        return is_way;
    }

    public void setIs_way(boolean is_way) {
        this.is_way = is_way;
    }

    public boolean is_way = false;

    public Node(int green, int blue, boolean is_wall) {
        this.green = green;
        this.blue = blue;
        this.is_wall = is_wall;
    }

    public boolean isIs_wall() {
        return is_wall;
    }

    public void setIs_wall(boolean is_wall) {
        this.is_wall = is_wall;
    }

    public boolean isIs_start() {
        return is_start;
    }

    public void setIs_start(boolean is_start) {
        this.is_start = is_start;
    }

    public boolean isIs_end() {
        return is_end;
    }

    public void setIs_end(boolean is_end) {
        this.is_end = is_end;
    }
}