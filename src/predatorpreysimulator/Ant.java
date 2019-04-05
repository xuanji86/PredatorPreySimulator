/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

import java.awt.Point;
import predatorpreysimulator.CellPanel;
import predatorpreysimulator.InitPanel;

public class Ant
        extends Organism {

    public Ant(PredatorPreySimulator sim, Point position, int lastTimeStep) {
        super(sim, position, lastTimeStep);
    }

    void move() {
        super.move(getEmptyCellToMove());
    }

    void breed() {
        Point newPos = findACell("toBreed");
        if ((this.sim.timeStep - this.lastBreed >= 3) && (newPos != null)) {
            super.breed(newPos, new Ant(this.sim, newPos, this.sim.timeStep));
            this.sim.numOfAnts += 1;
            this.lastBreed = this.sim.timeStep;
            this.sim.grid[newPos.x][newPos.y].setBackground(InitPanel.antBreedsColor);
        }
    }
}
