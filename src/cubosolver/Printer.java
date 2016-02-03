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
public class Printer {
    public static void printCantidades (Cubo[] cubos, int nPasos){
        for(int i=0;i<61;i++){
            System.out.printf("%s","*");
            if(i==30)
                System.out.println("");
            System.out.printf("%s","*");
        }
        System.out.println("");
        String cantidadString=" ";
        for(int i=0; i<cubos.length;i++){
                cantidadString += String.format("%d/%d",cubos[i].getCantidad(),cubos[i].getCapacidad());
                cantidadString += " ";
        }
        System.out.printf("Las cantidades: %s, la iteracion: %d%n",cantidadString,nPasos);
        
    }
    public static void printArray (int[] array){
        for (int element:array)
            System.out.printf(" %d ",element);
        System.out.println("");
    }
    public static void printSolutions (ArrayList[] array){
        for (int i=0;i<array.length;i++){
            for(int j=0;j<array[i].size();j++)
                System.out.printf("%d ",array[i].get(j));
            System.out.println("");
        }
    }
    public static void main (String args[]){

    }
}
