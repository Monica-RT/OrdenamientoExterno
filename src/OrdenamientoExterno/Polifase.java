package OrdenamientoExterno;

import static OrdenamientoExterno.Archivo.stdin;
import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;

/**
 *
 * @author Lupita
 * /*Esta clase va implementar el algoritmo de Polifase para el ordenamiento externo de un archivo
utilizando estructuras de colas. Para este caso, se pueden ordenar bajo cualquiera de los criterios deseados
(nombre, apellido o número de cuenta)

Entradas: Una lista de listas con los datos del archivo a ordenar, obtenida desde la clase Archivo
Salidas: El archivo Ordenado con la primera separación del procedimiento
* Las iteraciones en los "archivos" son representadas como listas en la pantalla
 */
public class Polifase {
    //metodo combiar que recibe la lista de listas creada a partir de la clase archivo y un entero
    //Con el criterio de ordenamiento seleccionado por el usuario
    //Se crean cuatro listas de listas que harán el papel de archivos y se le solucita
    //al usuario que decida los elementos que tendrá cada bloque en el que se divida el archivo
    //el resultado del as divisiones se guarda en la lista F1 y el la lista F2
    //c es un contador que determina si las listas a modificar son el par F0 y F3 o el par F1 y F2
    //para combinar los elementos de las listas en cada iteracion se usa a funcion merge
    //para escribir las listas el los archivos en cada iteracion se usa el metodo escribirArchivo
    //Finalmente, ya que no fue posible acomodar los elementos conforme a las iteraciones, se requirió
    //acomodar la lista utilizando el metodo ordenarP
    static void combinar (LinkedList<LinkedList<String>> lista, int opt){
            LinkedList<LinkedList<String>> listaF1=new LinkedList();
            LinkedList<LinkedList<String>> listaF2=new LinkedList();
            LinkedList<LinkedList<String>> listaF0=new LinkedList();
            LinkedList<LinkedList<String>> listaF3=new LinkedList();
            
            System.out.println("Ingrese el numero de elementos que desea en cada bloque ");
            int r = stdin.nextInt();
            int t=lista.size();
            int c=0;
            int a=r;
            dividir(lista,opt, r, listaF1,listaF2);
            do{
                if(c%2==0){
                    merge(listaF0,listaF3,a,listaF1,listaF2);
                    a=a*2;
                }
                else{
                    merge(listaF1,listaF2,a,listaF0,listaF3);
                    a=a*2;
                }
             c++; 
             if(listaF0.isEmpty()==false){
                System.out.println("listaF0 "+listaF0);
                escribirArchivo(listaF0,0);
             }
             if(listaF3.isEmpty()==false){
                System.out.println("listaF3 "+listaF3);
                escribirArchivo(listaF3,3);
             }   
            if(listaF1.isEmpty()==false){  
                System.out.println("listaF1 "+listaF1);
                escribirArchivo(listaF1,1);
            }
            if(listaF2.isEmpty()==false){
                System.out.println("listaF2 "+listaF2);
                escribirArchivo(listaF2,2);
            }
            }
            while(a<=t);
            
        if(listaF0.isEmpty()==false){
            listaF0=ordenarP(listaF0,opt,0);
            escribirArchivo(listaF0,5);
        }
        if(listaF1.isEmpty()==false){
            listaF1=ordenarP(listaF1,opt,0);
            escribirArchivo(listaF1,5);
        } 
        if(listaF2.isEmpty()==false){
           listaF2=ordenarP(listaF2,opt,1);
           escribirArchivo(listaF2,5);
        } 
        if(listaF3.isEmpty()==false){
           listaF3=ordenarP(listaF3,opt,1);
           escribirArchivo(listaF3,5);
        } 
              
    }
    //metodo que divide la lista de listas obtenida del archivo txt en el numero de claves que el usuario
    // decida y las coloca en dos listas de listas que recibe como parámetro desde el método combinar
    //se crea un contador y un auxiliar que van en aumento para poder tomar en cuenta todos los elementos de la lista de listas sin repetirlos 
    //y se van agregando en listas de listas del tamaño que el usuario especificó, si el contador supera el tamaño de la lista, 
    //se corta el coclo para crear listas mas pequeñas y dependiento si el auxiliar es par o impar, se añaden a la listaF1 o a la listaF2
    //de manera ordenada gracias al metodo ordenarP
    public static void dividir(LinkedList<LinkedList<String>> lista, int op,int r, LinkedList<LinkedList<String>> listaF1, LinkedList<LinkedList<String>> listaF2) {
        int cont=0;
        int aux=0;
        int tamano=lista.size();
        while(cont<=tamano){
            LinkedList<LinkedList<String>> dividida=new LinkedList();
            for(int i =0;i<r;i++){
                dividida.add(lista.get(cont));
                cont++;
                if(cont>=tamano){//Condicion para ordenar listas menores a 4 elementos si se ha alcanzado el maximo en la lista original
                    if (aux%2==0){
                        listaF1.addAll(ordenarP(dividida,op,aux));
                    }
                    else{
                        listaF2.addAll(ordenarP(dividida,op,aux));
                    }
                    
                    aux++;
                return;
                }
            }
            if (aux%2==0){
                listaF1.addAll(ordenarP(dividida,op,aux));
            }
            else{
                listaF2.addAll(ordenarP(dividida,op,aux));
            } 
            aux++;
        }
    }
    //El metodo recibe una lista de listas, que es en realidad una sublista 
    //de la lista de listas del tamaño determinado por el usuario, un entero que 
    //Es el criterio de ordenamiento y un auxiliar a utilizar para la escritura de 
    //un archivo. Se utilizó el método de ordenamiento selection sort debido a 
    //su sencillez para proramar.
    //Se crean dos listas, una copia exacta de la lista del parametro (apellidos) y otra de tamaño 1 (original)
    //donde se almacenará un elemento durante la sustitucion para evitar la perdida de informacion
    //el metodo regresa una lista para poder almacenar su resultado ordenado en otras listas de listas
    public static LinkedList ordenarP(LinkedList<LinkedList<String>> lista, int op,int aux) {
        int criterio=op-1;
        LinkedList<LinkedList<String>> apellidos=new LinkedList();
        LinkedList<LinkedList<String>> original=new LinkedList();
        original.add(lista.get(0));
        for(int i=0; i<lista.size();i++){
              apellidos.add(lista.get(i));
        }
        //algoritmo de ordenamiento selecion sort
        for(int i=0;i<apellidos.size();i++){
            int minimo=i;
            int comp=0;
            for(int j=i+1;j<apellidos.size();j++){
                //para poder comparar entre Strings, se convirtieron en cadenas de caracteres
                char a[]= apellidos.get(minimo).get(criterio).toCharArray();
                char b[]= apellidos.get(j).get(criterio).toCharArray();
                while(b[comp]==a[comp]){//esta condicion permite recorrer ambos arreglos de caracteres cuando tienen elementos en principio iguales
                    comp++;
                    if(comp>=b.length||comp>=a.length){//Condicion que evita un error en caso de encontrar caracteres completamente iguales, en este caso, pone en la lista el primer elemento encontrado 
                    comp=0;
                    break;
                    }
                }
                if(b[comp]<a[comp]){
                    minimo=j;    
                    comp=0;
                }
                else{
                comp=0;
                }
            }
            original.set(0,apellidos.get(i));
            apellidos.set(i, apellidos.get(minimo));
            apellidos.set(minimo, original.get(0));
        }
        System.out.println(apellidos);
        escribir(apellidos,aux);
        return apellidos;
    }
    //Metodo que permite escribir las primeras divisiones del archivo, de acuerdo a un auxiliar, que en caso de ser par o cero, 
    //indicará que la lista pertenece al "archivo 1", en caso de ser impar, pertenecera al "archivo 2" . En realidad todo se
    //escribe en un mismo archivo, solo con las notas pertinentes de archivo F1 y archivo F2
    public static void escribir(LinkedList<LinkedList<String>> lista,int aux){
        try{
            File F1=new File("F1.txt");//Creacion de objeto File que se encarga de crear o abrir acceso a un archivo txt especificado
            FileWriter escribir1=new FileWriter(F1,true); //Creacion de objeto FileWriter que nos ayude a escribir sobre el archivo
            if (aux%2==0){                  
                escribir1.write("Archivo F1");
                escribir1.write("{");
                for(int i=0;i<lista.size();i++){
                    escribir1.write("[");
                    for(int j=0;j<3;j++){
                        escribir1.write(lista.get(i).get(j));
                        escribir1.write(",");
                    }
                    escribir1.write("]");
                }
                escribir1.write("}");
                escribir1.append("\r\n");
                escribir1.close();  
            }
            else{
               FileWriter escribir2=new FileWriter(F1,true); //Creacion de objeto FileWriter que nos ayude a escribir sobre el archivo
                escribir2.write("Archivo F2");
                escribir2.write("{");
                for(int i=0;i<lista.size();i++){
                    escribir2.write("[");
                    for(int j=0;j<3;j++){
                        escribir2.write(lista.get(i).get(j));
                        escribir2.write(",");
                    }
                    escribir2.write("]");
                    
                }
                escribir2.write("}");
                escribir2.append("\r\n");
                escribir2.close();
            }   
        }
        catch(Exception e){
        System.out.println("Error al escribir");
        }
    }
    //El metodo escribir archivo nos sirve para simular la escritura de las iteraciones del 
    //metodo merge en cuatro diferentes archivos, dependiendo del valor entero que
    //reciba como parametro. En realidad se escriben en uno solo
    //en caso de que la lista este ordenada completamente if (x==5), entonces se imprime el 
    //resultado en el archivo FinalPolifase
    public static void escribirArchivo(LinkedList<LinkedList<String>> lista,int x){
        try{
            File F1=new File("F1.txt");//Creacion de objeto File que se encarga de crear o abrir acceso a un archivo txt especificado
            File F2=new File("FinalPolifase.txt");//Creacion de objeto File que se encarga de crear o abrir acceso a un archivo txt especificado  
            FileWriter escribir1=new FileWriter(F1,true); //Creacion de objeto FileWriter que nos ayude a escribir sobre el archivo
            if(x==5){
               FileWriter escribir2=new FileWriter(F2,true); //Creacion de objeto FileWriter que nos ayude a escribir sobre el archivo
                escribir2.write("Archivo Final");
                escribir2.write("{");
                for(int i=0;i<lista.size();i++){
                    escribir2.write("[");
                    for(int j=0;j<3;j++){
                        escribir2.write(lista.get(i).get(j));
                        escribir2.write(",");
                    }
                    escribir2.write("]");
                    
                }
                escribir2.write("}");
                escribir2.append("\r\n");
                escribir2.close();
            }  
            else{
                if(x==0){                  
                    escribir1.write("Archivo F0");
                }
                if(x==1){
                    escribir1.write("Archivo F1");
                }
                 if(x==2){
                    escribir1.write("Archivo F2");
                }
                 if(x==3){
                    escribir1.write("Archivo F3");
                }
                    escribir1.write("{");
                    for(int i=0;i<lista.size();i++){
                        escribir1.write("[");
                        for(int j=0;j<3;j++){
                            escribir1.write(lista.get(i).get(j));
                            escribir1.write(",");
                        }
                        escribir1.write("]");
                    }
                    escribir1.write("}");
                    escribir1.append("\r\n");
                    escribir1.close();  
            }
             
    }
        catch(Exception e){
        System.out.println("Error al escribir");
        }
    }
    //Metodo merge, que intercala los elementos de dos listas y los coloca en las dos listas faltantes
    //Se recibe un entero a, que duplica su valor con cada iteracion del metodo combinar, para permitir
    //el doble de elementos en una lista y poder tener los elementos correspondientes en cada archivo
    //Si alguna de las listas se queda vacía, se añaden sus elementos a la primer lista
    
    static void merge (LinkedList<LinkedList<String>> lista1, LinkedList<LinkedList<String>> lista2, int a, LinkedList<LinkedList<String>> lista3, LinkedList<LinkedList<String>> lista4){
        
        for(int i=0;i<a;i++){//Ciclo para llenar la primera lista con los primeros elementos de otras dos listas
            if(lista3.isEmpty()==true){
            lista1.addAll(lista4);
            lista4.clear();
            }
            if(lista4.isEmpty()==true){
            lista1.addAll(lista3);
            lista3.clear();
            }
            else{
            lista1.add(lista3.poll());
            lista1.add(lista4.poll());  
            }
        }
         for(int i=0;i<a;i++){//Ciclo para llenar la segunda lista con los elementos siguientes de dos listas diferentes
            if(lista3.isEmpty()==true){
                lista2.addAll(lista4);
                lista4.clear();
            }
            if(lista4.isEmpty()==true){
                lista2.addAll(lista3);
                lista3.clear();
            }
            else{
            lista2.add(lista3.poll());
            lista2.add(lista4.poll());  
            }
        }
        if(lista3.isEmpty()==false || lista4.isEmpty()==false){//condicion para anadir los elementos sobrantes de las dos listas a la primera
            lista1.addAll(lista3);
            lista1.addAll(lista4);
            lista3.clear();
            lista4.clear();
        }
    }
}


