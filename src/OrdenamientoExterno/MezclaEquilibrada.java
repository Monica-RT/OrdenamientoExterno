/*package OrdenamientoExterno;

//Esta clase se encuentra comentada, ya que no fue posible su implementación

import java.io.*;
import java.util.*;

public class MezclaEquilibrada {
    
    //El método particion es el que se encarga de poder leer los bloques de secuencias ordenadas
    static void particion(LinkedList<LinkedList<String>> lista, int op){
        int izq = 0, derecha = lista.size(), der = derecha;
        int criterio = op - 1;
        LinkedList<LinkedList<String>> auxiliar=new LinkedList();
        for(int i=0; i<lista.size();i++){
              auxiliar.add(lista.get(i));
        }
        for(int i=0;i<auxiliar.size();i++){
            char a[]= auxiliar.get(i).get(criterio).toCharArray();
            while(izq < derecha){
                while(a[izq] <= a[izq+1]){
                    izq++;
                }
                der = izq+1;
                while(der == derecha-1 || der<derecha && a[der]<=a[der+1]){
                   der++; 
                }
            }
        }   
    }
    
    static void acomodo(LinkedList<LinkedList<String>> lista){
        
        
    }
}*/