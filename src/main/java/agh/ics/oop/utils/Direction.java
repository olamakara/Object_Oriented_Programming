package agh.ics.oop.utils;

public class Direction {
    private int direction = 0;

    public Direction(int direction) {
        this.direction = direction;
    }

    public int getDirection() {
        return this.direction;
    }

    public void rotate(int genome) {
        this.direction = (this.direction + genome) % 8;
    }

    public Vector2d calculateMove(Vector2d moveFrom) {
        Vector2d moveBy = switch(this.direction) {
            case 0 -> new Vector2d(0, 1);
            case 1 -> new Vector2d(1, 1);
            case 2 -> new Vector2d(1, 0);
            case 3 -> new Vector2d(1, -1);
            case 4 -> new Vector2d(0, -1);
            case 5 -> new Vector2d(-1, -1);
            case 6 -> new Vector2d(-1, 0);
            case 7 -> new Vector2d(-1, 1);
            default -> new Vector2d(0, 0);
        };

        return moveFrom.add(moveBy);
    }
}
