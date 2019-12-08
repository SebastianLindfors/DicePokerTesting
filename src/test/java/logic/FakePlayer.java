package logic;

import java.util.ArrayList;
import java.util.List;

public class FakePlayer implements Player {

    int marker = 0;
    String name = "";

    @Override
    public void rollSomeDie(boolean die_1, boolean die_2, boolean die_3, boolean die_4, boolean die_5) {

    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void rollAllDice() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public int getMarker() {
        return this.marker;
    }

    @Override
    public void setMarker(int marker) {
        this.marker = marker;
    }

    @Override
    public boolean payMarkerToPot(int payment) {
        return (this.getMarker() >= 10);
    }

    @Override
    public List<Die> getDice() {
        Die d1 = new Die(1);
        List<Die> output = new ArrayList<>();
        output.add(d1);
        output.add(d1);
        output.add(d1);
        output.add(d1);
        output.add(d1);
        return output;
    }

    @Override
    public int[] getDieValues() {
        return new int[0];
    }

    @Override
    public void payMarkerFromPot(int pot) {

    }

    @Override
    public String toStorageString() {
        return "This is a fake storage string";
    }
}
