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

import java.util.Random;

public class Organism {
    int posX, posY;
    int lastTimeStep;
    PredatorPreySimulator sim;
    
    public Organism(PredatorPreySimulator sim, int posX, int posY, int lastTimeStep){
        this.sim = sim;
        this.posX = posX;
        this.posY = posY;
        this.lastTimeStep = lastTimeStep;
        
    }
    
    public class Point{
        int x;
        int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int getX(){
            return x;
        }
        
        public int getY(){
            return y;
        }
        
    public boolean inGrid(){
        boolean answer = true; 
        if (x > sim.organismTable[0].legnth() || x < 0 || y > sim.organismTable.length() || y < 0){
            answer = false;
        }
        return answer;
    }
        
    }
    
    public void move(){
        Point nextPoint = getNextPoint();
        sim.organismTable[posY][posX] = this;
        this.posX = nextPoint.getX();
        this.posY = nextPoint.getY();
    }
    
    public Point getNextPoint(){
        Point leftSpace = new Point(posX - 1, posY);
        Point rightSpace = new Point(posX + 1, posY);
        Point upSpace = new Point(posX, posY + 1);
        Point downSpace = new Point(posX, posY - 1);
        Random rand = new Random();
        
        
        boolean open = false;
        while(open == false){
            int randSpace = rand.nextInt(4);
            if (randSpace == 0){
                if(!(sim.organismTable[posY][posX - 1] instanceof Organism) || leftSpace.inGrid() == false ){
                    open = true;
                    Point nextPos = new Point(posX - 1, posY); 
                    break;
                }
            }
            if (randSpace == 1){
                if(!(sim.organismTable[posY][posX + 1] instanceof Organism) || rightSpace.inGrid() == false){
                    open = true;
                    Point nextPos = new Point(posX + 1, posY);                    
                    break;
                }
            }
            if (randSpace == 2){
                if(!(sim.organismTable[posY + 1][posX] instanceof Organism) || upSpace.inGrid() == false){
                    open = true;
                    Point nextPos = new Point(posX, posY + 1);
                    break;
                }
            }
            if (randSpace == 3){
                if(!(sim.organismTable[posY - 1][posX] instanceof Organism) || downSpace.inGrid() == false){
                    open = true;
                    Point nextPos = new Point(posX, posY - 1);
                    break;
                }
            }
            else{
                open = true;
                Point curPoint = new Point(posX, posY);
                return curPoint;
            }
                    
        }
        return nextPos;
    }
    

    
    public void breed(){
        if ((sim.timeStep - lastTimeStep) % 3 == 0){
            Point breedPoint = getNextPoint();
            if (breedPoint.getX() != posX && breedPoint.getY() != posY){
                Organism newBug = new Organism(sim, breedPoint.getX(), breedPoint.getY(), -1);
            }
        }
    }

    public int getPosX(){
        return posX;
    }
    
    public int getPosY(){
        return posY;
    }
    
    public int getLastTimeStop(){
        return lastTimeStep;
    }
}
