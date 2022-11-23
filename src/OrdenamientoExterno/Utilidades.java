package OrdenamientoExterno;

import static OrdenamientoExterno.Polifase.combinar;
import static OrdenamientoExterno.RadixSort.sort;
import java.util.*;
//clase Utilidades que contiene los menus y submenus con las opciones q elegir del usuario
public class Utilidades {
    static Scanner stdin = new Scanner(System.in);
    static int opc = -1;
    //Menu con las opciones de los diferentes algoritmos de ordenamiento a utilizar
    static void menu(LinkedList<LinkedList<String>> lista){
        while(opc!=0){
            try{
                System.out.println(" Seleccione la opcion deseada ");
                System.out.println(" 1. Ordenamiento por Polifase ");
                System.out.println(" 2. Ordenamiento por Mezcla Equilibrada ");
                System.out.println(" 3. Ordenamiento por Radix ");
                System.out.println(" 0. Salir ");
                int p = stdin.nextInt();
                switch(p){
                    case 1:
                        subMenu(lista,p);
                        break;
                    case 2: 
                        subMenu(lista,p);
                        break;
                    case 3:
                        System.out.println("El ordenamiento por RadixSort es solo por Núm. de cuenta");
                        sort(lista);
                        break;
                    case 0: 
                        System.out.println("Regresando al menu principal... ");
                        opc=0;
                        break;
                    default:
                         System.out.println("Ups, opción inválida, intente nuevamente ");
                         break;
                }
                
            }catch(InputMismatchException e){
                System.out.println(" Ups, opción inválida ");
                stdin.next();
                
            }
        }
    }
    //metodo submenu con las opciones de los diferentes criterios a utilizar para realizar el ordenamiento
    static int subMenu(LinkedList<LinkedList<String>> lista, int p) {
        int oc=-1;
        int opt=0;
        while(oc!=0){
            try{
                System.out.println(" Seleccione el criterio de ordenamiento ");
                System.out.println(" 1. Apellido ");
                System.out.println(" 2. Nombre ");
                System.out.println(" 3. No. cuenta ");
                System.out.println(" 0. Regresar ");
                opt = stdin.nextInt();
                switch(opt){
                    case 1://Caso para ordenar por apellido
                        if(p==1){
                             combinar(lista ,opt);
                        }
                        if(p==2){
                        }
                        break;
                    case 2: //caso para ordenar por nombre
                        if(p==1){
                             combinar(lista ,opt);
                        }
                        if(p==2){
                        }
                        break;
                    case 3://caso para ordenar por no de cuenta
                        if(p==1){
                             combinar(lista ,opt);
                        }
                        if(p==2){
                        }
                        break;
                    case 0: 
                        System.out.println(" Regresando al menú anterior... ");
                        
                        oc=0;
                        break;
                    default:
                         System.out.println("Ups, opción inválida, intente nuevamente ");
                         break;
                }
            }catch(InputMismatchException e){
                System.out.println(" Ups, opción inválida ");
                stdin.next();
            }
        }
    return opt;
    } 
}
