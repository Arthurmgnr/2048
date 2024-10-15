package core;

import java.awt.*;

public abstract class Case {
    protected int x, y;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void deplacement();

    public abstract void afficher(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
