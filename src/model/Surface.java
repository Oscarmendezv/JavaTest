package model;

import java.util.Arrays;

public class Surface {
    private int surface_x = 0;
    private int surface_y = 0;
    private final int surface_x_inf = 0;
    private final int surface_y_inf = 0;
    //defining 2D array to hold position data
    boolean[][] matrix;

    public int getSurface_x() {
        return surface_x;
    }

    public void setSurface_x(int surface_x) {
        this.surface_x = surface_x;
    }

    public int getSurface_y() {
        return surface_y;
    }

    public void setSurface_y(int surface_y) {
        this.surface_y = surface_y;
    }

    public int getSurface_x_inf() {
        return surface_x_inf;
    }

    public int getSurface_y_inf() {
        return surface_y_inf;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }
}
