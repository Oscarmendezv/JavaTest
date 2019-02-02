package model;

public class Tondeuse {

    private static int count = 0;
    private int id;
    private int position_x;
    private int position_y;
    private String notation_cardinale;

    public Tondeuse(){
        this.id = count;
        count++;
    }
    public int getPosition_x() {
        return position_x;
    }

    public void setPosition_x(int position_x) {
        this.position_x = position_x;
    }

    public int getPosition_y() {
        return position_y;
    }

    public void setPosition_y(int position_y) {
        this.position_y = position_y;
    }

    public String getNotation_cardinale() {
        return notation_cardinale;
    }

    public void setNotation_cardinale(String notation_cardinale) {
        this.notation_cardinale = notation_cardinale;
    }

    public int getId() {
        return id;
    }
}
