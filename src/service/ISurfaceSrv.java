package service;

import model.Surface;

public interface ISurfaceSrv {

    Surface createPositionMatrix(int x_dimension, int y_dimension);

    boolean checkAvailability(int x_position, int y_position);

    void changeAvailability(int x_position, int y_position);
}
