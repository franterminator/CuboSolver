/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cubosolver;

import java.util.Scanner;

/**
 *
 * @author Fran
 */
public class Console {
    //initial options
    public static void logo(){
        line();
        line("-");
        print("      *****             *****        *************");
        print("       *   *           *   *         *           *");
        print("        *   ***********   *          *           *");
        print("         *               *           *   *********");
        print("          *      *      *            *   *");
        print("           *    * *    *             *   *");
        print("            ****   ****              *****");
        System.out.printf("%59s%n","*****      *                ");
        System.out.printf("%59s%n","*          *                ");
        System.out.printf("%59s%n","***** **** * *   * **** ****");
        System.out.printf("%59s%n","    * *  * *  * *  **   *   ");
        System.out.printf("%59s%n","***** **** *   *   **** *   ");
        System.out.println("");
        line();
    }
    public static void startMenu(){
        String [] menu = {"Comenzar","Instrucciones","Salir"};
        line("-");
        printMenu(menu);
    }
    public static void instrucctions(){
        print("Este programa esta diseñado para resolver problemas ");
        print("de cubos de agua (u otros liquidos), en los que debes");
        print("lograr una cantidad de liquido determinado en uno de");
        print("los cubo. Solo pudiendo llenar, vaciar y pasar agua de");
        print("un cubo a otro. ");
        print("Este tipos de problemas generalmente se utilizan como ");
        print("puzzles para desarrollar el intelecto. Un ejemplo es el");
        print("de la pelicula \"Duro de matar\".");
    }
    //second options
    public static void secondMenu() {
        String[] menu = {"Crear y editar cubos","Resolver","Mostrar informe","Salir"};
        cls();
        printHeader("MENU");
        printMenu(menu);
    }
    //start to configure the problem
    public static int crearCubos(){
        printHeader("CREAR CUBOS");
        System.out.println("Introduzca el numero de cubos deseados");
        Scanner input = new Scanner(System.in);
        int nCubos = input.nextInt();
        return nCubos;
    }
    public static boolean editCubos(Cubo[] cubos){
        boolean creaded = true;
        String[] cubosToString = new String[4];
        cubosToString[0] = String.format("%s", "Editar cubos (numero cubos = "+cubos.length+")");
        cubosToString[1] = String.format("%s", "Mostrar informe"); 
        cubosToString[2] = String.format("%s", "Volver atras");
        cubosToString[3] = String.format("%s", "Borrar todo");
        
        //menu editar cubos
        edit: while(true){
            printHeader("EDITAR CUBOS");
            printMenu(cubosToString);        
            Scanner input = new Scanner(System.in);
            int sel = input.nextInt();
            switch(sel){
                case 1:
                    for(int i=0;i<cubos.length;i++){
                        line("-");
                        System.out.println("Cubo "+(i+1));
                        System.out.println("Capacidad?");
                        cubos[i].setCapacidad(input.nextInt());
                        System.out.println("Cantidad?");
                        cubos[i].setCantidad(input.nextInt());
                    }
            
                case 2:
                    System.out.println("Mostrar informe...");
                    informe(cubos);
                    System.out.println("-> Escriba algo y pulse ENTER para salir");
                    String wait = input.next();
                    break;
            
                case 3:
                    System.out.println("Volviendo a MENU...");
                    break edit;
                
                case 4:
                    System.out.println("Borrando todo...");
                    creaded = false;
                    break edit;
                default:
                    System.out.println("Esa opción no existe.");
                    break;
            }
        }
        return creaded;
    }
    public static void informe(Cubo[] cubos){
        line(".");
        System.out.println("Informe de las cantidad y capacidad de los cubos:");
        line();
        System.out.printf("| %15s | %8s | %9s |%n",
                "Nombre del cubo","Cantidad","Capacidad");
        for(int i=0;i<cubos.length;i++){
            System.out.printf("| %15s | %8s | %9s |%n",
                    "cubo "+(i+1),cubos[i].getCantidad(),cubos[i].getCapacidad());
        }
        line();
    }
    public static void showTip (Warning warning){
        String texto = warning.getMessage();
        if(!"".equals(texto))
            System.out.printf("Warning!!! %s%n",texto);
        warning.setMessage("");
    }
    
    
    
    //method to print
    public static void line(){
        int length = 50;
        for(int i=0; i<length;i++)
            System.out.printf("*");
        System.out.println("");
    }
    public static void line(String texto){
        int length = 50;
        for(int i=0; i<length;i++)
            System.out.printf(texto);
        System.out.println("");
    }
    public static void line(String texto, int length){
        for(int i=0; i<length;i++)
            System.out.printf(texto);
        System.out.println("");
    }
    public static void print(String texto){
        System.out.printf("%s%n",texto);
    }
    public static void printHeader (String texto){
        int media = (int)texto.length()/2;
        line("*",30);
        System.out.printf("%s %13s%-13s %s%n","|",texto.substring(0, media),texto.substring(media),"|");
        line("*",30);
    }
    public static void printMenu (String[] options){
        for(int i=0;i<options.length;i++){
            System.out.printf("%d) %s%n",i+1,options[i]);
        }
    }
    public static void cls(){
        for(int clear = 0; clear < 1000; clear++)
        {
            System.out.println("\b");
            
        }
    }
    
    /** The main of the program
    * 
    * @param args 
    */
    public static void main (String args[]){
        Scanner input = new Scanner(System.in);
        logo();
        //menu para comenzar
        start: while(true){
            startMenu();
            int sel = input.nextInt();
            switch(sel){
                case 1:
                    break start;
                case 2:
                    instrucctions();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    System.exit(0);
                default:
                    System.out.println("Esa opcion no existe.");
            }
        }
        
        //menu resolver y menu editar cubos
        Warning warning = new Warning();
        boolean creaded = false;
        int nCubos;
        Cubo[] cubos = new Cubo[1];
        menu: while(true){
            secondMenu();
            showTip(warning);
            int sel = input.nextInt();
            options: switch(sel){
                //crear cubos y abrir el menu para editar
                case 1:
                    if(!creaded){
                        nCubos = crearCubos();
                        creaded = true;
                        cubos = new Cubo[nCubos];
                        for(int i=0; i<nCubos;i++)
                            cubos[i] = new Cubo();
                    }
                    creaded = editCubos(cubos); //menu para editar los cubos
                    break;
                    
                //resolver
                case 2:
                    if(creaded){
                        //comprobar si los cubos tienen capacidad
                        for(int i=0;i<cubos.length;i++) {
                            if(cubos[i].getCapacidad()<=0) {
                                warning.setMessage("Configure bien los cubos\n"
                                        + "antes de resolver el problema");
                                break options;
                            }
                        }
                        //introducir la meta y resolver
                        System.out.println("Introduzca la meta: ");
                        int meta[] = new int[cubos.length];
                        for(int i=0;i<cubos.length;i++){
                            line("-");
                            System.out.println("Cubo "+(i+1));
                            meta[i] = input.nextInt();
                        }
                        IASolver ia = new IASolver(cubos,meta);
                        boolean maded = ia.startSolve();
                        System.out.println("El resultado es "+maded);
                        System.out.println("-> Escriba algo y pulse ENTER para salir");
                        String wait = input.next();
                    }
                    else{
                        warning.setMessage("Configure primero los cubos");
                    }
                        
                    break;
                    
                //mostrar informe
                case 3:
                    if(creaded){
                        System.out.println("Mostrar informe...");
                        informe(cubos);
                        System.out.println("-> Escriba algo y pulse ENTER para salir");
                        String wait = input.next();
                    }
                    else 
                        warning.setMessage("Configure primero los cubos");
                    break;
                    
                //salir
                case 4:
                    System.out.println("Gracias por usar el programa!");
                    break menu;
            }
        }
        
    }
}
