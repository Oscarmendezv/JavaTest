package service;

import model.Surface;

public class SurfaceSrv implements ISurfaceSrv {
    Surface surface = new Surface();

    /**
     * This method creates the Position Matrix of a Surface.
     * @param x_dimension
     * @param y_dimension
     * @return
     */
    @Override
    public Surface createPositionMatrix(int x_dimension, int y_dimension) {
        boolean[][] matrix = new boolean[x_dimension+1][y_dimension+1];
        surface.setMatrix(matrix);
        surface.setSurface_x(x_dimension);
        surface.setSurface_y(y_dimension);
        return surface;
    }

    /**
     * This method checks if a certain position in the Surface is available.
     * @param x_position
     * @param y_position
     * @return
     */
    @Override
    public boolean checkAvailability(int x_position, int y_position) {
        return surface.getMatrix()[x_position][y_position];
    }

    /**
     * This method changes the availability of a point in the Surface. This means a Tondeuse has been positioned there.
     * @param x_position
     * @param y_position
     */
    @Override
    public void changeAvailability(int x_position, int y_position) {
        boolean[][] matrix = surface.getMatrix();
        if(!matrix[x_position][y_position]) {
            matrix[x_position][y_position] = true;
        } else {
            matrix[x_position][y_position] = false;
        }
        surface.setMatrix(matrix);
    }


}
