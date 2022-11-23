package OrdenamientoExterno;

import static OrdenamientoExterno.Utilidades.menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
//Clase archivo que permite realizar la lectura de un archivo de tipo txt
public class Archivo {
    static Scanner stdin = new Scanner(System.in);
    int op = -1;
    //método Menu principal que le permite al usuario decidir si quiere utilizar un nuevo archivo txt o salir del programa
    void menuPrincipal(){
        while(op!=0){
            try{
                System.out.println(" Bienvenido, seleccione la opcion deseada ");
                System.out.println(" 1. Subir un nuevo archivo");
                System.out.println(" 0. Salir ");
                int o = stdin.nextInt();
                switch(o){
                    case 1:
                        Archivo archiv=new Archivo();
                        archiv.leerArchivo();//invocacion del metodo leerArchivo para leer el archivo txt
                        break;
                    case 0: 
                        System.out.println("Gracias por participar.regresa pronto ");
                        op=0;
                        break;
                    default:
                         System.out.println("Ups, opción inválida, intente nuevamente ");
                         break;
                }
            }catch(InputMismatchException e){//Metodo que manda un mensaje de alerta en caso de que se ingrese una opcion invalida
                System.out.println(" Ups, opción inválida ");
                stdin.next();
            }
        }
    }
    //metodo leerArchivo que permite crear listas de listas ligadas para acomodar la información del archivo txt
    void leerArchivo() {
        System.out.println(" Ingresa el nombre de tu archivo con la terminacion .txt ");
        Scanner tec=new Scanner(System.in);
        String archivo= tec.next();
        File f = new File(archivo);
	Scanner s;
        int count=0;
        LinkedList<LinkedList<String>> lista;
	try {   
            lista = new LinkedList();
            s = new Scanner(f);
            while (s.hasNextLine()) {//Ciclo que permite leer el archivo txt siempre que el objeto s tenga una linea siguiente
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);//creacion de un objeto sl de tipo scanner, del tamaño de un String linea
                sl.useDelimiter("\\s*,\\s*");//Comando que delimita las partes del objeto sl mediante comas
                count++;
                LinkedList separado =new LinkedList(); 
                lista.add(separado);//creacion de una lista que gusrdara los datos de un estudiante
                separado.add(sl.next());
                separado.add(sl.next());
                separado.add(sl.next());                  
            }
                menu(lista);//invocacion del metodo menu de la clase Utilidades, que recibe como parametro la lista de listas creada
		s.close();
        } 
        catch(Exception e){
            System.out.println("Error al escribir");
            
	}
    }
   
}