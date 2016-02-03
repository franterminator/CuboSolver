/*
 * Cubo.java
 */
package cubosolver;

/**
 *
 * @author Fran
 */
public class Cubo {
    private int capacidad; // how much can hold the cube?
    private int cantidad; //how much there are inside?
    
    //constructors
    Cubo(int capacity, int quantity){
        this.capacidad = capacity;
        this.cantidad = quantity;
    }
    
    Cubo(int capacity){
        this(capacity,0);
    }
    Cubo(){
        this(0,0);
    }
    
    //get and set methods
    public void setCapacidad (int capacity) {
        this.capacidad = capacity;
    }

    public void setCantidad(int cantidadLiquido) {
        if(cantidadLiquido >= 0 && cantidadLiquido <= capacidad)
            this.cantidad = cantidadLiquido;
        else
            fill();
    }
    
    public int getCapacidad (){
        return this.capacidad;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    //operators of the cube
    public void fill () {
        cantidad = capacidad;
    }
    public void drop () {
        cantidad = 0;
    }
    public void add(int quantity) {
        int adition = cantidad + quantity;
        cantidad = adition<capacidad? adition:capacidad;
    }
    public void reduce(int quantity) {
        int reduction = cantidad - quantity;
        cantidad = reduction>0? reduction:0;
    }
    //toString
    @Override
    public String toString (){
        String texto = String.format("Datos: cantidad -> %d; capacidad -> %d.",
                this.getCantidad(),this.getCapacidad());
        return texto;
    }
    //check
    public static void main(String args[]){
        Cubo cubo1 = new Cubo(8);
        Cubo cubo2 = new Cubo(6);
        //checking in the cmd if they are full or not
        System.out.println("Initial condictions ************");
        System.out.println("El cubo 1");
        System.out.println(cubo1.toString());
        System.out.println("El cubo 2");
        System.out.println(cubo2.toString());
        //checking filling the cubes
        cubo1.fill(); cubo2.fill();
        System.out.println("Filling *************");
        System.out.println("El cubo 1");
        System.out.println(cubo1.toString());
        System.out.println("El cubo 2");
        System.out.println(cubo2.toString());
        //checking droping the cubes
        cubo2.drop();
        System.out.println("Droping second cube *************");
        System.out.println("El cubo 1");
        System.out.println(cubo1.toString());
        System.out.println("El cubo 2");
        System.out.println(cubo2.toString());
        //checking add 2 litres to the cubes
        cubo1.add(2); cubo2.add(2);
        System.out.println("Adding 2 litros *************");
        System.out.println("El cubo 1");
        System.out.println(cubo1.toString());
        System.out.println("El cubo 2");
        System.out.println(cubo2.toString());
        //checking reducing 2 litres to the cubes
        cubo1.reduce(2); cubo2.reduce(2);
        System.out.println("Adding 2 litros *************");
        System.out.println("El cubo 1");
        System.out.println(cubo1.toString());
        System.out.println("El cubo 2");
        System.out.println(cubo2.toString());
    }
}//end of class Cubo
