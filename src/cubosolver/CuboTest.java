/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubosolver;

import java.util.ArrayList;

/**
 *
 * @author Fran
 */
public class CuboTest {
    public static int[] getCantidades (Cubo[] cubos){
        int cantidades[] = new int[cubos.length];
        for(int i=0; i<cubos.length;i++)
            cantidades[i] = cubos[i].getCantidad();
        return cantidades;
    }
    public static boolean checkSolutions (Cubo cubos[],ArrayList[] solutions){
        boolean maded = false;

        for(int i=0; i<solutions[0].size();i++){
            System.out.printf("check: %d, array0: %d, i: %d//",cubos[0].getCantidad(),solutions[0].get(i),i);
            if(cubos[0].getCantidad() == (int)solutions[0].get(i)){
                int counter = 1;
                for(int j=1; j<cubos.length;j++){
                    System.out.printf(" check: %d, array: %d, j: %d **",cubos[j].getCantidad(),solutions[j].get(i),j);
                    if(cubos[j].getCantidad() == (int)solutions[j].get(i)){
                        ++counter;
                    }
                }
                if(counter==cubos.length){
                    System.out.println("");
                    maded = true;
                    return maded;
                }
            }
            System.out.println("");
        }
        return maded;
    }
    public static boolean checkArray (int[] check, int[][] array){
        boolean maded = false;
        for(int i=0;i<array.length;i++){
            System.out.printf("check: %d, array0: %d, i: %d//",check[0],array[0][i],i);
            if(check[0] == array[0][i]){
                int counter = 1;
                for(int j=1;j<array[i].length;j++){
                    System.out.printf(" check: %d, array: %d, j: %d **",check[j],array[j][i],j);
                    if(check[j]==array[j][i])
                        ++counter;
                }  
                if(counter==check.length){
                    maded = true;
                    return maded;
                }
            }
            System.out.println("");
            System.out.println("It is maded? "+maded);
        }
        return maded;
    }
    public static void main (String args[]) {
        //variables
        Cubo cubos[] = new Cubo[2];
        cubos[0] = new Cubo(0,8); cubos[1] = new Cubo(0,6);
        
        ArrayList[] array = new ArrayList[2];
        for(int i=0;i<array.length;i++)
            array[i] = new ArrayList();
        array[0].add(0);array[0].add(8);array[0].add(8);array[0].add(4);
        array[1].add(0);array[1].add(6);array[1].add(4);array[1].add(0);

        

        System.out.println("Imprimiendo el check...");
        Printer.printArray(getCantidades(cubos));
        
        System.out.println("Imprimiendo el array...");
        Printer.printSolutions(array);
        
        boolean maded = checkSolutions(cubos, array);
        System.out.println("Finally -> maded = "+maded);
    }
    
}
