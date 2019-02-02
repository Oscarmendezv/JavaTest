package service;

import model.Surface;
import model.Tondeuse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TondeuseSrv implements ITondeuseSrv {

    private int key;
    private Map<Integer, String> cardinalitymap = new HashMap<Integer, String>()
    {
        {
            put(1, "N");
            put(2, "E");
            put(3, "S");
            put(4, "W");
        }
    };

    /**
     * This method sets the position of a Tondeuse on a surface.
     * @param tondeuse
     * @param position_x
     * @param position_y
     * @param cardinality
     */
    @Override
    public void setPosition(Tondeuse tondeuse, int position_x, int position_y, String cardinality) throws Exception {
        tondeuse.setPosition_x(position_x);
        tondeuse.setPosition_y(position_y);
        if (cardinalitymap.containsValue(cardinality)) {
            tondeuse.setNotation_cardinale(cardinality);
        } else {
            throw new Exception("Cardinality not valid. Stopping the program...");
        }
    }

    /**
     * Method to change the orientation of the tondeuse to the right or the left.
     * @param order --> Command to follow
     * @param tondeuse
     */
    @Override
    public void rotate(String order, Tondeuse tondeuse) {
        for (Map.Entry<Integer, String> entry : cardinalitymap.entrySet()) {
            if (Objects.equals(tondeuse.getNotation_cardinale(), entry.getValue())) {
                key = entry.getKey();
            }
        }
        if (order.equals("D")) {
            if (key == 4) key = 1;
            else{
                key++;
            }
        } else {
            if (key == 1) key = 4;
            else {
                key--;
            }
        }
        tondeuse.setNotation_cardinale(cardinalitymap.get(key));
    }

    /**
     * Method to advance the tondeuse in the position that it's facing. Will not do anything if the tondeuse wants to move out of the limits.
     * @param tondeuse
     * @param surfaceSrv --> surface given for the tondeuse to move
     */
    @Override
    public void advance(Tondeuse tondeuse, SurfaceSrv surfaceSrv) {
        int position_x = tondeuse.getPosition_x();
        int position_y = tondeuse.getPosition_y();
        String orientation = tondeuse.getNotation_cardinale();

        // For every case, we check if the tondeuse wants to move either out of the limits of the surface, or ontop of another tondeuse.
        if (orientation.equals("N") && position_y != surfaceSrv.surface.getSurface_y()){
            position_y += 1;
            if(!surfaceSrv.checkAvailability(position_x, position_y)) {
                tondeuse.setPosition_y(position_y);
                surfaceSrv.changeAvailability(position_x, position_y);
                surfaceSrv.changeAvailability(position_x, position_y-1);
            }
        } else if (orientation.equals("S") && position_y != surfaceSrv.surface.getSurface_y_inf()) {
            position_y -= 1;
            if(!surfaceSrv.checkAvailability(position_x, position_y)) {
                tondeuse.setPosition_y(position_y);
                surfaceSrv.changeAvailability(position_x, position_y);
                surfaceSrv.changeAvailability(position_x, position_y+1);
            }
        } else if (orientation.equals("E") && position_x != surfaceSrv.surface.getSurface_x()) {
            position_x += 1;
            if(!surfaceSrv.checkAvailability(position_x, position_y)) {
                tondeuse.setPosition_x(position_x);
                surfaceSrv.changeAvailability(position_x, position_y);
                surfaceSrv.changeAvailability(position_x-1, position_y);
            }
        } else if (orientation.equals("W") && position_x != surfaceSrv.surface.getSurface_x_inf()){
            position_x -= 1;
            if(!surfaceSrv.checkAvailability(position_x, position_y)) {
                tondeuse.setPosition_x(position_x);
                surfaceSrv.changeAvailability(position_x, position_y);
                surfaceSrv.changeAvailability(position_x+1, position_y);
            }
        }
    }
}
