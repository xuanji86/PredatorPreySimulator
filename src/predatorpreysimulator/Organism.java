/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

/**
 *
 * @author Honigmaster
 */
import java.awt.Point;

public class Organism {

    int posX, posY;
    int lastTimeStep;
    PredatorPreySimulator sim;
    int lastBreed;

    public Organism(PredatorPreySimulator sim, Point position, int lastTimeStep) {
        this.sim = sim;
        this.posX = position.x;
        this.posY = position.y;
        this.lastTimeStep = lastTimeStep;
        sim.organismTable[this.posX][this.posY] = this;
        sim.grid[this.posX][this.posY].setIcon(this);
        this.lastBreed = sim.timeStep;
    }

    void move(Point newPos) {
        if (newPos != null) {
            this.sim.organismTable[this.posX][this.posY] = null;
            this.sim.grid[this.posX][this.posY].setIcon(null);
            this.posX = newPos.x;
            this.posY = newPos.y;
            this.sim.organismTable[this.posX][this.posY] = this;
            this.sim.grid[this.posX][this.posY].setIcon(this);

        }
    }

    void breed(Point newPos, Organism newOrganism) {
        this.sim.organismTable[newPos.x][newPos.y] = newOrganism;
        this.sim.grid[newPos.x][newPos.y].setIcon(this);
        this.lastBreed = this.sim.timeStep;
    }

    protected Point getEmptyCellToMove() {
        int rN = (int) (Math.random() * 4.0D);
        int[][] shift = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        Point newPos = new Point(this.posX + shift[rN][0], this.posY + shift[rN][1]);
        if ((!isInGrid(newPos)) || (this.sim.organismTable[newPos.x][newPos.y] != null)) {
            return null;
        }
        return newPos;
    }

    protected Point findACell(String toBreedOrEat) {
        int[][] shift = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        int[] array = {0, 1, 2, 3};
        for (int i = 0; i < 4; i++) {
            int ran = i + (int) (Math.random() * (4 - i));

            int temp = array[i];
            array[i] = array[ran];
            array[ran] = temp;

            Point newPos = new Point(this.posX + shift[array[i]][0], this.posY + shift[array[i]][1]);
            if (isInGrid(newPos)) {
                if ((toBreedOrEat.equalsIgnoreCase("toBreed")) && (this.sim.organismTable[newPos.x][newPos.y] == null)) {
                    return newPos;
                }
                if ((toBreedOrEat.equalsIgnoreCase("toEat")) && ((this.sim.organismTable[newPos.x][newPos.y] instanceof Ant))) {
                    return newPos;
                }
            }
        }
        return null;
    }

    private boolean isInGrid(Point newPos) {
        if ((newPos.x < 0) || (newPos.x >= this.sim.numOfRows) || (newPos.y < 0) || (newPos.y >= this.sim.numOfCols)) {
            return false;
        }
        return true;
    }

    public void setTimeStep() {
        this.lastTimeStep = this.sim.timeStep;
    }
}
