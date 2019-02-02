package service;

import model.Surface;
import model.Tondeuse;

public interface ITondeuseSrv {

    void rotate(String order, Tondeuse tondeuse);

    void advance(Tondeuse tondeuse, SurfaceSrv surfaceSrv);

    void setPosition(Tondeuse tondeuse, int position_x, int position_y, String cardinality) throws Exception;
}
