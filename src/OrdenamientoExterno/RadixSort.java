package OrdenamientoExterno;

import java.util.*;
import java.io.*;
/**
 *
 * @author ASPIRE 5315
 */
/*Esta clase va implementar el algoritmo de RadixSort para el ordenamiento externo de un archivo
utilizando archivos txt en lugar de las estructuras de colas. Para este caso, solo sera ordenado
bajo el criterio del número de cuenta

Entradas: Una lista de listas con los datos del archivo a ordenar, obtenida desde la clase Archivo
Salidas: El archivo Ordenado y un archivo adicional con las iteraciones del procedimiento

*/
public class RadixSort {
    /*
    El método deArchivoALista() lee todas las líneas del archivo y las va almacenando
    en la lista de listas original. Al final de este proceso elimina este archivo y 
    crea un nuevo archivo en blanco con el mismo nombre.
    Este proceso simula la extraccion de datos de las colas (segun el algoritmo original)
    para almacenar ordenadamente la lista original
    
    Entrada: Lista de listas donde se van a almacenar los datos y el archivo de donde se
             van a estraer dichos datos
    Salida: No regreza nada.
    */
    static void deArchivoALista(LinkedList<LinkedList<String>> lista, File f){
	Scanner s;
	try {
            s = new Scanner(f);
            while (s.hasNextLine()) {
                String linea = s.nextLine();
                Scanner sl = new Scanner(linea);
                sl.useDelimiter("\\s*,\\s*");
                LinkedList separado =new LinkedList(); 
                lista.add(separado);
                separado.add(sl.next());
                separado.add(sl.next());
                separado.add(sl.next());                  
            }
	    s.close();
            f.delete();
            try {
              f.createNewFile();
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }         
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
	}
    }
    /*
    El método escribirEnArchivos() recibe, desde la lista de listas, los datos que
    tienen un mismo criterio (en este caso un numero), les da formato y los escribe
    en el Archivo que se le indique
    Entradas: El archivo donde va a escribir, un número que obtiene iteraciones y
              la lista de listas donde obtiene los datos
    Salidas: No regreza nada
    */
    static void escribirEnArchivos(File f,int i, LinkedList<LinkedList<String>> lista){
        FileWriter fw= null;
        PrintWriter pw= null;
        try{
            fw= new FileWriter(f,true);
            pw = new PrintWriter(fw);
            pw.println(lista.get(i).get(0)+", "+lista.get(i).get(1)+", "+lista.get(i).get(2));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fw!=null)
                    fw.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*
    El método Iteraciones escribe, en el Archivo adicional que muestra el procedimiento,
    el número de una iteración
    Entradas: El archivo donde va a escribir y el número de la iteracion
    Salidas: No regreza nada
    */
    static void Iteraciones(File f,int j,int sz){
        FileWriter fw= null;
        PrintWriter pw= null;
        try{
            fw= new FileWriter(f,true);
            pw = new PrintWriter(fw);
            pw.println("Impersion de los Archivos en la iteracion: "+(-(-(sz)+j)));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fw!=null)
                    fw.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*
    El método estadoDeLista() escribe, en el archivo que se le indique, el estado de 
    la lista de listas en el momento en que se use este método
    Entradas: Archivo donde va a escribir, el número de la iteracion donde se encuentra
              y la lista de listas
    Salidas: No regresa nada
    */
    static void estadoDeLista(File f,int j,LinkedList<LinkedList<String>> lista){
        FileWriter fw= null;
        PrintWriter pw= null;
        int sz= lista.get(0).get(0).length();
        try{
            fw= new FileWriter(f,true);
            pw = new PrintWriter(fw);
            pw.println("La lista en la iteracion: "+(-(-(sz)+j)));
            pw.println(lista);
            pw.println("\n\n");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fw!=null)
                    fw.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*
    El método escribirArchivoIteraciones() escribe en el archivo adicional,
    que muestra el procedimiento del ordenamiento, el estado de cada archivo
    utilizado como almacenamietno externo: El nombre del archivo, y los datos 
    que hay en el.
    Entradas: El archivo donde lee los datos, el archivo donde los escribe y un
              número que representa el nombre del archivo
    Salidas: No regresa nada.
    */
    static void escribirArchivoIteracines(File f, File Iteraciones, int i){
        Scanner s;
        FileWriter fw= null;
        PrintWriter pw= null;
        try{
            s= new Scanner(f);
            fw= new FileWriter(Iteraciones,true);
            pw = new PrintWriter(fw);
            pw.println("Archivo "+i+": ");
            while(s.hasNextLine()){
                String linea = s.nextLine();
                pw.println(linea);
            }
            s.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally{
            try{
                if(fw!=null)
                    fw.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
    /*
    El método escribirArchivOrdenado() escribe en el archivo "ArchivoOrdenado.txt"
    los datos ordenados obtenidos de la ultima iteración en que la lista fue llenada.
    Les da formato a cada línea separando los datos por comas obteniendo cada dato con
    la metodologia de las colas (FIFO)
    Entradas: La lista de listas con los datos ordenados y el archivo donde los va
              a escribir
    */
    static void escribirArchivOrdenado(LinkedList<LinkedList<String>> lista, File f){
        f.delete();
            try {
              f.createNewFile();
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
        for(int i=0;i<lista.size();i++){
        FileWriter fw= null;
        PrintWriter pw= null;
        try{
            fw= new FileWriter(f,true);
            pw = new PrintWriter(fw);
            pw.println(lista.get(i).get(0)+", "+lista.get(i).get(1)+", "+lista.get(i).get(2));
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(fw!=null)
                    fw.close();
            }catch(Exception e2){
                e2.printStackTrace();
            }
        }
    }
        
    }
    
    /*
    El método sort() implementa la lógica del algortmo de RadixSort utilizando
    los métodos antes explicados para poder lograrlo. 
    Entradas: La lista de listas obtenida de la clase "Archivo.java"
    Salidas: No regresa nada
    */
    public static void sort(LinkedList<LinkedList<String>> listaEnviada){
        int i,j,criterio=0;
        LinkedList<LinkedList<String>> lista= new LinkedList();
        for(i=0;i<listaEnviada.size();i++){
            LinkedList personas =new LinkedList(); 
            lista.add(personas);
            personas.add(listaEnviada.get(i).get(2));
            personas.add(listaEnviada.get(i).get(1));
            personas.add(listaEnviada.get(i).get(0));
        }
        //System.out.println(lista);
        File f0= new File("0.txt");
        File f1= new File("1.txt");
        File f2= new File("2.txt");
        File f3= new File("3.txt");
        File f4= new File("4.txt");
        File f5= new File("5.txt");
        File f6= new File("6.txt");
        File f7= new File("7.txt");
        File f8= new File("8.txt");
        File f9= new File("9.txt");
        File ArchivoOrdenado = new File("ArchivoOrdenado.txt");
        File Iteraciones = new File ("IteracionesRadix.txt");
        Iteraciones.delete();
            try {
              Iteraciones.createNewFile();
            } catch (IOException ioe) {
              ioe.printStackTrace();
            }
        int sizeNumCuenta = lista.get(0).get(criterio).length()-1;
        for(j=sizeNumCuenta;j>=0;j--){
            for(i=0;i<lista.size();i++){
                switch(lista.get(i).get(criterio).charAt(j)){
                    case '0':
                        escribirEnArchivos(f0,i,lista);
                        break;
                    case '1':
                        escribirEnArchivos(f1,i,lista);
                        break;
                    case '2':
                        escribirEnArchivos(f2,i,lista);
                        break;
                    case '3':
                        escribirEnArchivos(f3,i,lista);
                        break;
                    case '4':
                        escribirEnArchivos(f4,i,lista);
                        break;
                    case '5':
                        escribirEnArchivos(f5,i,lista);
                        break;
                    case '6':
                        escribirEnArchivos(f6,i,lista);
                        break;
                    case '7':
                        escribirEnArchivos(f7,i,lista);
                        break;
                    case '8':
                        escribirEnArchivos(f8,i,lista);
                        break;
                    case '9':
                        escribirEnArchivos(f9,i,lista);
                        break;
                    default:
                        System.out.println("Lo datos no se ingresaron correctamente");
                        break;
                        
                }
            }
            int sz= lista.get(0).get(0).length();
            Iteraciones(Iteraciones,j,sz);
            escribirArchivoIteracines(f0,Iteraciones,i=0);
            escribirArchivoIteracines(f1,Iteraciones,i=1);
            escribirArchivoIteracines(f2,Iteraciones,i=2);
            escribirArchivoIteracines(f3,Iteraciones,i=3);
            escribirArchivoIteracines(f4,Iteraciones,i=4);
            escribirArchivoIteracines(f5,Iteraciones,i=5);
            escribirArchivoIteracines(f6,Iteraciones,i=6);
            escribirArchivoIteracines(f7,Iteraciones,i=7);
            escribirArchivoIteracines(f8,Iteraciones,i=8);
            escribirArchivoIteracines(f9,Iteraciones,i=9);
            estadoDeLista(Iteraciones,j,lista);
            
            lista.clear();
            
            deArchivoALista(lista,f0);
            deArchivoALista(lista,f1);
            deArchivoALista(lista,f2);
            deArchivoALista(lista,f3);
            deArchivoALista(lista,f4);
            deArchivoALista(lista,f5);
            deArchivoALista(lista,f6);
            deArchivoALista(lista,f7);
            deArchivoALista(lista,f8);
            deArchivoALista(lista,f9);
            
            
        }
        escribirArchivOrdenado(lista,ArchivoOrdenado);
        System.out.println("Tu archivo Ordenado es: ArchivoOrdenado.txt");
        System.out.println("Las iteraciones del proceso estan en: IteracionesRadix.txt");
    
    }
}
