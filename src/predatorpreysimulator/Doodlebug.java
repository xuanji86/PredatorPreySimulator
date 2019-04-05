/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

import java.awt.Point;

public class Doodlebug
        extends Organism {

    int lastAte = -1;

    public Doodlebug(PredatorPreySimulator sim, Point position, int lastTimeStep) {
        super(sim, position, lastTimeStep);
        this.lastAte = (sim.timeStep - 1);
    }

    void move() {
        Point newPos = findACell("toEat");
        if (newPos == null) {
            newPos = getEmptyCellToMove();
        } else {
            this.lastAte = this.sim.timeStep;
            this.sim.numOfAnts -= 1;
            this.sim.grid[newPos.x][newPos.y].setBackground(InitPanel.antEatenColor);
        }
        super.move(newPos);
    }

    void breed() {
        Point newPos = findACell("toBreed");
        if ((this.sim.timeStep - this.lastBreed == 8) && (newPos != null)) {
            super.breed(newPos, new Doodlebug(this.sim, newPos, this.sim.timeStep));
            this.sim.numOfBugs += 1;
            this.sim.grid[newPos.x][newPos.y].setBackground(InitPanel.doodleBugBreedsColor);
        }
    }

    void starve() {
        if (this.sim.timeStep - this.lastAte == 3) {
            this.sim.organismTable[this.posX][this.posY] = null;
            this.sim.grid[this.posX][this.posY].setIcon(null);
            this.sim.numOfBugs -= 1;
            this.sim.grid[this.posX][this.posY].setBackground(InitPanel.doodleBugDiesColor);
        }
    }
}
