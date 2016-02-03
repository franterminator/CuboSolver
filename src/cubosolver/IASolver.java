/*
 * Cubosolver.java
 */
package cubosolver;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Fran
 */
public class IASolver {
    Cubo cubos[]; //the diferente containers for the liquid
    int initialCondictions[]; //how much liquid there are in each cube?
    int target[]; //how much liquid we want?
    
    //constructors
    IASolver(int capacities[], int initialCondictions[], int target[]){
        if(capacities.length == initialCondictions.length
                && initialCondictions.length == target.length){
            cubos = new Cubo[capacities.length];
            for(int i=0; i<capacities.length; i++)
                cubos[i] = new Cubo(capacities[i],initialCondictions[i]);
            
            this.target = target;
        }
        else 
            System.err.println("That option doesn't exist.");
    }
    
    IASolver(int capacities[],int target[]){
        if(capacities.length == target.length){
            cubos = new Cubo[capacities.length];
            for(int i=0; i<capacities.length; i++)
                cubos[i] = new Cubo(capacities[i]);
            
            this.target = target;
        }
        else 
            System.err.println("That option doesn't exist.");
    }
    
    IASolver(Cubo[] cubos,int[] target){
        this.cubos = cubos;
        this.target = target;
    }
    
    //get and set
    public int[] getCantidades (){
        int cantidades[] = new int[cubos.length];
        for(int i=0; i<cubos.length;i++)
            cantidades[i] = cubos[i].getCantidad();
        return cantidades;
    }
    
    //check meta
    public boolean checkMeta () {
        boolean meta=true;
        for(int i=0; i<cubos.length; i++)
            if(target[i] != cubos[i].getCantidad())
                meta = false;
        
        return meta;
    }
    //check solutions made
    public boolean checkSolutions (ArrayList[] solutions){
        boolean maded = false;

        for(int i=0; i<solutions[0].size();i++){
            if(cubos[0].getCantidad() == (int)solutions[0].get(i)){
                int counter = 1;
                for(int j=1; j<cubos.length;j++){
                    if(cubos[j].getCantidad() == (int)solutions[j].get(i)){
                        ++counter;
                    }
                }
                if(counter==cubos.length){
                    maded = true;
                    return maded;
                }
            }
        }
        return maded;
    }

    //strategy to get the target INCOMPLETE
    public boolean startSolve() {
        int nPasos = 0;
        boolean maded;
        ArrayList[] solutions = new ArrayList[cubos.length];
        for(int i=0; i<cubos.length;i++)
            solutions[i] = new ArrayList();
        //1: check meta of the initial condictions
        boolean meta = checkMeta();          
        
        while (!meta) {
            int[] solution = getCantidades();
            for(int i=0;i<solutions.length;i++)
                solutions[i].add(solution[i]); 
            Printer.printCantidades(cubos, nPasos);
            //2: operators avaliable
            Operators op = new Operators(cubos);
            ArrayList avaliable = op.avaliable;

            //3: remove operator which will produce last solutions
            for(int i=0; i<avaliable.size();i++){   
                op.execute((int)avaliable.get(i));
                maded = checkSolutions(solutions);
                System.out.printf("* Avaliable: %s, check: %d, maded?: %b%n",
                        avaliable,+(int)avaliable.get(i),maded);
                if(maded){
                    if(avaliable.size()>1){
                        avaliable.remove(i);
                        --i;
                    }
                    else{
                        System.out.println("Bucle");
                        break;
                    }
                }
                op.restoreBackup();
            }
            //4: choose one operator
            int chosen = random(avaliable.size());
            System.out.printf("*** Will execute: %d,was chosen: %d%n",
                    (int)avaliable.get(chosen),chosen);
            op.execute((int)avaliable.get(chosen));                    
            //5: check meta
            meta = checkMeta();
            //6: new iteraction
            nPasos++;
            
            if(nPasos > 100)
                break;
        }
        Printer.printCantidades(cubos, nPasos);
        return meta;
    }
    //random chooser
    public int random (int length) {
        Random r = new Random();
        int randomInt = r.nextInt(length);
        return randomInt;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // create IA object
        int capacities[] = {8,6};
        int initialCondictions[] = {0,0};
        int target[] = {4,0};
        IASolver ia = new IASolver(capacities,initialCondictions,target);
        boolean solved = ia.startSolve();
        System.out.println("Esta resuelto? "+solved);
    }
    
}
