package scenarios;

import entities.MainCharacter;

import java.awt.*;

public  abstract  class Scenario {
    public abstract void render(Graphics g);
    public  abstract boolean checkCompletion(MainCharacter character);
}
