package agh.ics.oop.utils;

import java.util.Objects;
import java.util.Random;

public record Vector2d(int x, int y) {
    public String toString() {
        return "(" + this.x + "," + this.y + ")";
    }

    public boolean equals(Object other) {
        if(this == other) return true;
        if(!(other instanceof Vector2d otherVector)) return false;

        return otherVector.hashCode() == this.hashCode();
    }

    public static Vector2d random(Vector2d limits) {
        Random rand = new Random();

        return new Vector2d(rand.nextInt(limits.x), rand.nextInt(limits.y));
    }

    public boolean precedes(Vector2d other) {
        return this.x <= other.x && this.y <= other.y;
    }

    public boolean follows(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    public Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}