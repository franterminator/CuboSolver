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
public class Operators {
    Cubo cubos[];
    int [] saveCubos; //save a backup of the cubos contain
    final int nCubos; // how many cubos are
    final int nOperators; //how many operators are?
    final int FillOperations; //how many fill operations are?
    final int EmptyOperations; //how many empty operations are?
    final int DropInOperations; //how many drop in operations are?
    final int FillWithOperations; //how many fill with operations are?
    ArrayList avaliable; //which operators we are able to do actually?
    
    //constructor
    Operators(Cubo cubos[]){
        this.cubos = cubos;
        this.nCubos = cubos.length;
        saveCubos = new int[nCubos];
        for(int i=0; i<nCubos;i++)
            saveCubos[i] = cubos[i].getCantidad();
        
        FillOperations = nCubos;
        EmptyOperations = nCubos;
        int temp = nCubos;
        if((nCubos-1)>0)
            temp *= (nCubos-1);
        if((nCubos-2)>0)
            temp *= (nCubos-2);
        if((nCubos-3)>0)
            temp *= (nCubos-3);
        DropInOperations = temp;
        FillWithOperations = temp;
        nOperators = FillOperations + EmptyOperations + DropInOperations + 
                                                            FillWithOperations;
        
        avaliable = avaliableOperators();
    }
    
    /*operators
    if (random <= FillOperations) fill
    else if (random <= EmptyOperations+FillOperations) empty
    else if (random <= Fill + Empty + DropInOperations) drop in
    else if (random <= Fill+ Empty + DropIn + FillWithOperations) fill with
    */
    //fill cube
    private void fillCube (Cubo cubo) {
        cubo.fill();
    }
    private boolean checkFill (Cubo cubo) {
        boolean condiction = false;
        if(cubo.getCantidad() != cubo.getCapacidad())
            condiction = true;
        return condiction;
    }
    //empty cube
    private void emptyCube (Cubo cubo) {
        cubo.drop();
    }
    private boolean checkEmpty (Cubo cubo) {
        boolean condiction = false;
        if(cubo.getCantidad() != 0)
            condiction = true;
        return condiction;
    }
    //drop one cube with the liquid of other cube
    private void dropOneToAnother (Cubo cuboOut, Cubo cuboIn) {
        cuboIn.add(cuboOut.getCantidad());
        cuboOut.drop();
    }
    private boolean checkDropIn (Cubo cuboOut, Cubo cuboIn) {
        boolean condiction = false;
        //how many we can fill the cubo in
        int withoutFill = cuboIn.getCapacidad()-cuboIn.getCantidad();

        if(checkEmpty(cuboOut) && cuboOut.getCantidad()<=withoutFill)
            condiction = true;
        return condiction;
    }
    //fill one cube with the liquid of the other cube
    private void fillOneWithAnother (Cubo cuboOut, Cubo cuboIn) {
        cuboOut.reduce(cuboIn.getCapacidad()-cuboIn.getCantidad());
        cuboIn.fill();
    }
    private boolean checkFillWith (Cubo cuboOut, Cubo cuboIn) {
        boolean condiction = false;
        //how many we can fill the cubo in
        int withoutFill = cuboIn.getCapacidad()-cuboIn.getCantidad();

        if(checkFill(cuboIn) && cuboOut.getCantidad()>=withoutFill)
            condiction = true;
        return condiction;
    }
    //check avaliable operators
    public ArrayList avaliableOperators (){
        boolean[] check = checkOperators(cubos);
        ArrayList avaliable = new ArrayList();
        
        for(int i=0;i<check.length;i++)
            if(check[i]==true)
                avaliable.add(i);
        
        return avaliable;
    }
    private boolean[] checkOperators (Cubo cubos[]) {
        boolean [] operators = new boolean[nOperators];
        int n = 0;
        //check fill
        for(int i=0; i<nCubos; i++){
            operators[n] = checkFill(cubos[i]);
            n++;
        }
        //check empty
        for(int i=0; i<nCubos; i++){
            operators[n] = checkEmpty(cubos[i]);
            n++;
        }
        //check drop in
        for(int i=0; i<nCubos; i++){
            for(int j=0; j<nCubos;j++){
                if(i!=j){
                    operators[n] = checkDropIn(cubos[i],cubos[j]);
                    n++;
                }
            }
        }
        //check fill with
        for(int i=0; i<nCubos; i++){
            for(int j=0; j<nCubos;j++){
                if(i!=j){
                    operators[n] = checkFillWith(cubos[i],cubos[j]);
                    n++;
                }
            }
        }
        
        return operators;
    }//end method checkOperators
    //execute a specific operator
    public void execute (int operator){
        int limitFill = FillOperations; //position limit of operators fill
        int limitEmpty = limitFill + EmptyOperations; //so on...
        int limitDropIn = limitEmpty + DropInOperations;
        int limitFillWith = limitDropIn + FillWithOperations;
        
        if(operator < limitFill)
            fillCube(cubos[operator]);
        
        else if(operator < limitEmpty)
            emptyCube(cubos[operator-FillOperations]);
        
        else if (operator < limitDropIn){
            operator = operator - limitEmpty;
            int cuboOut = (int)operator/(nCubos-1);
            int temp = (int)operator/nCubos;
            int cuboIn = (temp>=cuboOut)?temp+1:temp;
            dropOneToAnother(cubos[cuboOut], cubos[cuboIn]);
        }
        
        else if (operator < limitFillWith){
            operator = operator - limitDropIn;
            int cuboOut = (int)operator/(nCubos-1);
            int temp = (int)operator/nCubos;
            int cuboIn = (temp>=cuboOut)?temp+1:temp;
            fillOneWithAnother(cubos[cuboOut], cubos[cuboIn]);
        }
        
        else {
            System.err.println("That option doesn't exist.");
        }
        
    }
    //restore backup
    public void restoreBackup (){
        for(int i=0;i<nCubos;i++){
            cubos[i].setCantidad(this.saveCubos[i]);
        }
    }
        
    //testing class
    public static void main (String args[]) {
        Cubo cubos[] = new Cubo[2];
        cubos[0] = new Cubo(4);
        cubos[1] = new Cubo(6);
        
        Operators op = new Operators (cubos);
        
        System.out.println("Los cubos:");
        System.out.printf("*Cubo0: %s *Cubo1: %s%n",cubos[0],cubos[1]);
        
        System.out.println("Los operadores disponibles son:");
        for (Object avaliable1 : op.avaliable) {
            System.out.printf(" %d", (int) avaliable1);
        }
        System.out.println("");
        
        op.execute(1);
        op = new Operators (cubos);
        
        System.out.println("Los cubos:");
        System.out.printf("*Cubo0: %s *Cubo1: %s%n",cubos[0],cubos[1]);
        
        System.out.println("Los operadores disponibles son:");
        for(int i=0; i<op.avaliable.size();i++)
            System.out.printf(" %d",(int)op.avaliable.get(i));
        System.out.println("");
        
        op.execute(7);
        System.out.println("Los cubos:");
        System.out.printf("*Cubo0: %s *Cubo1: %s%n",cubos[0],cubos[1]);
        
    }
}//end of class Operators
