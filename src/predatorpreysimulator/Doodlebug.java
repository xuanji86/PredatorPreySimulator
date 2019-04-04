/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package predatorpreysimulator;

import java.util.Random;

/**
 *
 * @author Honigmaster
 */
public class Doodlebug extends Organism{
    int eatCount = 0;
    
    public Doodlebug(PredatorPreySimulator sim, int posX, int posY, int lastTimeStep){
        super(sim, posX, posY, lastTimeStep);
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
                if(!(sim.organismTable[posY][posX - 1] instanceof Ant) && leftSpace.inGrid() == true){
                    open = true;
                    Point nextPos = new Point(posX - 1, posY); 
                    eatCount += 1;
                    break;
                }
            }
            else if (randSpace == 1){
                if(!(sim.organismTable[posY][posX + 1] instanceof Ant) && rightSpace.inGrid() == true){
                    open = true;
                    Point nextPos = new Point(posX + 1, posY);  
                    eatCount += 1;
                    break;
                }
            }
            else if (randSpace == 2){
                if(!(sim.organismTable[posY + 1][posX] instanceof Ant) && upSpace.inGrid() == true){
                    open = true;
                    Point nextPos = new Point(posX, posY + 1);
                    eatCount += 1;
                    break;
                }
            }
            else if (randSpace == 3){
                if(!(sim.organismTable[posY - 1][posX] instanceof Ant) && downSpace.inGrid() == true){
                    open = true;
                    Point nextPos = new Point(posX, posY - 1);
                    eatCount += 1;
                    break;
                }
            }
            else{
                if (randSpace == 0){
                    if(!(sim.organismTable[posY][posX - 1] instanceof Doodlebug) && leftSpace.inGrid() == true){
                        open = true;
                        Point nextPos = new Point(posX - 1, posY); 
                        eatCount += 1;
                        break;
                    }
                }
                else if (randSpace == 1){
                    if(!(sim.organismTable[posY][posX + 1] instanceof Doodlebug) && rightSpace.inGrid() == true){
                        open = true;
                        Point nextPos = new Point(posX + 1, posY);  
                        eatCount += 1;
                        break;
                    }
                }
                else if (randSpace == 2){
                    if(!(sim.organismTable[posY + 1][posX] instanceof Doodlebug) && upSpace.inGrid() == true){
                        open = true;
                        Point nextPos = new Point(posX, posY + 1);
                        eatCount += 1;
                        break;
                    }
                }
                else if (randSpace == 3){
                    if(!(sim.organismTable[posY - 1][posX] instanceof Doodlebug) && downSpace.inGrid() == true){
                        open = true;
                        Point nextPos = new Point(posX, posY - 1);
                        eatCount += 1;
                        break;
                    }
                }else
                open = true;
                Point curPoint = new Point(posX, posY);
                return curPoint;
            }
                    
        }
        return nextPos;
    }        
    
    
    public void breed(){
        if ((sim.timeStep - lastTimeStep) % 8 && eatCount < 1){
            Point breedPoint = getNextPoint();
            if (breedPoint.getX() != posX && breedPoint.getY() != posY){
                Doodlebug newBug = new Doodlebug(sim, breedPoint.getX(), breedPoint.getY(), -1);
            }            
        }
    }
    
    public void starve(){
        if ((sim.timeStep - lastTimeStep) % 8 == 0 && eatCount > 0){
            sim.organismTable[posY][posX] = null;   
        }
    }
}
