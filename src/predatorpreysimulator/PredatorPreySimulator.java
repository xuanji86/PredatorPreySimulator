/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

import java.awt.Point;

/**
 *
 * @author Sikder Huq
 */
public class PredatorPreySimulator {

    public int numOfRows;
    public int numOfCols;
    public int numOfAnts;
    public int numOfBugs;
    public int timeStep;
    Organism[][] organismTable;
    CellPanel[][] grid;

    public static void main(String[] args) {
        // TODO code application logic here
        new SimulationFrame().setVisible(true);

    }

    public PredatorPreySimulator(int row, int col, int ants, int bugs) {
        this.numOfRows = row;
        this.numOfCols = col;
        this.numOfAnts = ants;
        this.numOfBugs = bugs;
        this.organismTable = new Organism[this.numOfRows][this.numOfCols];
    }

    public void initWorld() {
        for (int i = 0; i < this.numOfBugs; i++) {
            new Doodlebug(this, getRandomAvailableCell(), -1);
        }
        for (int i = 0; i < this.numOfAnts; i++) {
            new Ant(this, getRandomAvailableCell(), -1);
        }
    }

    Point getRandomAvailableCell() {
        int x;
        int y;
        do {
            x = (int) (Math.random() * this.numOfRows);
            y = (int) (Math.random() * this.numOfCols);
        } while (this.organismTable[x][y] != null);
        return new Point(x, y);
    }

    public void setGrid(CellPanel[][] grid) {
        this.grid = grid;
    }

    public void goNext() {
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfCols; j++) {
                if (((this.organismTable[i][j] instanceof Doodlebug)) && (this.organismTable[i][j].lastTimeStep < this.timeStep)) {
                    Doodlebug currentBug = (Doodlebug) this.organismTable[i][j];
                    currentBug.move();
                    currentBug.breed();
                    currentBug.starve();
                    currentBug.setTimeStep();
                }
            }
        }
        for (int i = 0; i < this.numOfRows; i++) {
            for (int j = 0; j < this.numOfCols; j++) {
                if (((this.organismTable[i][j] instanceof Ant)) && (this.organismTable[i][j].lastTimeStep < this.timeStep)) {
                    Ant currentAnt =(Ant)this.organismTable[i][j];
                    currentAnt.move();
                    currentAnt.breed();
                    currentAnt.setTimeStep();
                }
            }
        }
        this.timeStep += 1;
    }

}
