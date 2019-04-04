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
public class Ant extends Organism{

    
    public Ant(PredatorPreySimulator sim, int posX, int posY, int lastTimeStep){
        super(sim, posX, posY, lastTimeStep);
        
    }
    
    public void breed(){
        if ((sim.timeStep - lastTimeStep) % 3 == 0){
            Point breedPoint = getNextPoint();
            if (breedPoint.getX() != posX && breedPoint.getY() != posY){
                Ant newBug = new Ant(sim, breedPoint.getX(), breedPoint.getY(), -1);
            }
        }        
    }
    
}
