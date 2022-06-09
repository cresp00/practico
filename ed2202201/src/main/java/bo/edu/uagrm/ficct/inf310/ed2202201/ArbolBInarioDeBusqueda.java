/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310.ed2202201;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author USUARIO
 * @param <K>
 * @param <V>
 */
public class ArbolBInarioDeBusqueda<K extends Comparable <K>,V>implements IArbolBusqueda<K,V>{
protected NodoBinario<K,V> raiz;

    public ArbolBInarioDeBusqueda() {
    }

    public ArbolBInarioDeBusqueda(NodoBinario<K, V> raiz) {
        this.raiz = raiz;
    }

    public ArbolBInarioDeBusqueda(ArrayList<Integer> claveInOrden, ArrayList<String> valoresInOrden, ArrayList<Integer> clavesPostOrden, ArrayList<String> valoresPostOrden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @Override
   public void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException {
        if(claveAInsertar==null){
        throw new NullPointerException("Clave a insertar no puede ser nula");
        }
        if(valorAInsertar==null){
        throw new NullPointerException("Valor a insertar no puede ser nula");
        }
        if(this.esArbolVacio()){
        this.raiz= new NodoBinario<>(claveAInsertar, valorAInsertar);
        return;
        }
        NodoBinario<K,V>nodoAnterior=new NodoBinario<>(claveAInsertar, valorAInsertar);
        NodoBinario<K,V>nodoActual=this.raiz;
        
        while(!NodoBinario.esNodoVacio(nodoActual)){
        K claveDeNodoActual= nodoActual.getClave();
            if(claveAInsertar.compareTo(claveDeNodoActual)<0){
            nodoActual=nodoActual.getHijoIzquierdo();            
            }
            else if(claveAInsertar.compareTo(claveDeNodoActual)>0){
            nodoActual=nodoActual.getHijoDerecho();
            }
            else{
            nodoActual.setValor(valorAInsertar);
            return;
            }
        
        }
        NodoBinario<K,V> nuevoNodo= new NodoBinario<>(claveAInsertar, valorAInsertar);
        K claveDeNodoAnterior=nodoAnterior.getClave();
            if(claveAInsertar.compareTo(claveDeNodoAnterior)<0){
            nodoAnterior.setHijoIzquierdo(nuevoNodo);
            }
            else nodoAnterior.setHijoDerecho(nuevoNodo);
            return;
    }

    @Override
    public V eliminar(K claveAEliminar) {
        V valorRetorno=this.buscar(claveAEliminar);
        if(valorRetorno==null){
          return null;
        }
        this.raiz=eliminar(raiz,claveAEliminar);
        return valorRetorno;
    }
    private NodoBinario<K,V>eliminar(NodoBinario<K,V>nodoActual,K claveAEliminar){
        if(NodoBinario.esNodoVacio(nodoActual)){
            return (NodoBinario<K,V>)NodoBinario.nodoVacio();
        }
            K claveActual=nodoActual.getClave();
                if(claveAEliminar.compareTo(claveActual)<0){
                    NodoBinario<K,V>izquierdo=eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
                    nodoActual.setHijoIzquierdo(izquierdo);
                    return nodoActual;
                }
                if(claveAEliminar.compareTo(claveActual)>0){
                   NodoBinario<K,V>derecho=eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
                   nodoActual.setHijoDerecho(derecho);
                   return nodoActual;
                }
             
            if(nodoActual.esHoja()){
                return (NodoBinario<K,V>)NodoBinario.nodoVacio();
            }
            
            if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
                return nodoActual.getHijoIzquierdo();
            }
            if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
                return nodoActual.getHijoDerecho();
            }
            
            NodoBinario<K,V>nodoSucesor=cambiar(nodoActual.getHijoDerecho());
            NodoBinario<K,V>posibleNuevo=eliminar(nodoActual.getHijoDerecho(),nodoSucesor.getClave());
            nodoActual.setHijoDerecho(posibleNuevo);
            nodoSucesor.setHijoDerecho(nodoActual.getHijoDerecho());
            nodoSucesor.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
            nodoActual.setHijoDerecho((NodoBinario<K,V>)NodoBinario.nodoVacio());
            nodoActual.setHijoIzquierdo((NodoBinario<K,V>)NodoBinario.nodoVacio());
        
        return nodoSucesor;
    
    }
    public NodoBinario<K,V> cambiar(NodoBinario<K,V>nodoActual){ 
         NodoBinario<K,V>anterior=(NodoBinario<K,V>)NodoBinario.nodoVacio();
         if(NodoBinario.esNodoVacio(nodoActual)){
             return (NodoBinario<K,V>)NodoBinario.nodoVacio();
         }
         while(!NodoBinario.esNodoVacio(nodoActual)){
             anterior=nodoActual;
             nodoActual=nodoActual.getHijoIzquierdo();
         }
         return anterior;
    }

    @Override
    public V buscar(K claveBuscar) throws NullPointerException {
        if(claveBuscar==null){
            throw new NullPointerException("Clave buscar no puede ser nula");
        }
        NodoBinario<K,V> nodoActual=this.raiz;
            while(!NodoBinario.esNodoVacio(nodoActual)){
                K claveActual=nodoActual.getClave();
                if(claveBuscar.compareTo(claveActual)<0){
                    nodoActual=nodoActual.getHijoIzquierdo();
                }
                if(claveBuscar.compareTo(claveActual)>0){
                    nodoActual=nodoActual.getHijoDerecho();
                }
                else{
                    return nodoActual.getValor();
                }
            }
            return null;
    }
   
    @Override
   public boolean contiene(K claveBuscar) throws NullPointerException {
        return this.buscar(claveBuscar)!=null;
    }

    @Override
    public int size() {
        Stack<NodoBinario<K,V>>pilaNodos=new Stack<>();
       int cantidad=0;
       if(this.esArbolVacio()){
           return cantidad;
       }
       pilaNodos.push(this.raiz);
       while(!pilaNodos.empty()){
           NodoBinario<K,V>nodo=pilaNodos.pop();
           cantidad++;
            if(!nodo.esVacioHijoDerecho()){
                pilaNodos.push(nodo.getHijoDerecho());
            }
            if(!nodo.esVacioHijoIzquierdo()){
                pilaNodos.push(nodo.getHijoIzquierdo());
            }
       }
       return cantidad;
    }
    @Override
    public int altura() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void vaciarArbol() {
         this.raiz=(NodoBinario<K, V>) NodoBinario.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
        
        return NodoBinario.esNodoVacio(this.raiz); 
    }

    @Override
    public List<K> recorridoPorNiveles() {
      List<K>claves=new ArrayList<>();
     
      if(esArbolVacio()){
          return claves;
      }
      Queue<NodoBinario<K,V>>colaNodos=new LinkedList<>();
      colaNodos.offer(this.raiz);  
        while(!colaNodos.isEmpty()){
           NodoBinario<K,V> actual=colaNodos.poll();
           claves.add(actual.getClave());
           if(!actual.esVacioHijoIzquierdo()){
               colaNodos.offer(actual.getHijoIzquierdo());
           }
           if(!actual.esVacioHijoDerecho()){
               colaNodos.offer(actual.getHijoDerecho());
           }
        }  
      return claves;         
    }

    @Override
    public List<K> recorridoEnPreOrden() {
       Stack<NodoBinario<K,V>>pilaNodos=new Stack<>();
       List<K>lista=new ArrayList();
       if(this.esArbolVacio()){
           return lista;
       }
       pilaNodos.push(this.raiz);
       while(!pilaNodos.empty()){
           NodoBinario<K,V>nodo=pilaNodos.pop();
           lista.add(nodo.getClave());
            if(!nodo.esVacioHijoDerecho()){
                pilaNodos.push(nodo.getHijoDerecho());
            }
            if(!nodo.esVacioHijoIzquierdo()){
                pilaNodos.push(nodo.getHijoIzquierdo());
            }
       }
       return lista;
    }

    @Override
    public List<K> recorridoEnInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
   public List<K> recorridoEnPostOrden() {
        List<K>lista=new ArrayList<>();
        if(esArbolVacio()){
            return lista;
        }
        Stack<NodoBinario<K,V>>pilaNodos=new Stack<>();
        NodoBinario<K,V>actual=this.raiz;
        //el procesos inicial antes de iterar en la pila
        meterPilaParaPostOrden(pilaNodos,actual);
        //empezamos a iterar sobre la pila
            while(!pilaNodos.isEmpty()){
                actual=pilaNodos.pop();
                lista.add(actual.getClave());
                    if(!pilaNodos.isEmpty()){
                        NodoBinario<K,V>tope=pilaNodos.peek();
                            if(!tope.esVacioHijoDerecho() && (tope.getHijoDerecho() != actual)){
                                meterPilaParaPostOrden(pilaNodos,tope.getHijoDerecho());
                            }
                    }
            }
            return lista;
    }
    public void meterPilaParaPostOrden(Stack<NodoBinario<K,V>>pila,NodoBinario<K,V>nodo){
        while(!NodoBinario.esNodoVacio(nodo)){
            pila.push(nodo);
                if(!nodo.esVacioHijoIzquierdo()){
                    nodo=nodo.getHijoIzquierdo();
                }else{
                    nodo=nodo.getHijoDerecho();
                }
        }
    }

    
    
    
    
    //PREGUNTA 2
    public List<K> recorridoEnPostOrdenIterativo() {
        List<K>lista=new ArrayList<>();
        if(esArbolVacio()){
            return lista;
        }
        Stack<NodoBinario<K,V>>pilaNodos=new Stack<>();
        NodoBinario<K,V>actual=this.raiz;
        //el procesos inicial antes de iterar en la pila
        meterPilaParaPostOrden(pilaNodos,actual);
        //empezamos a iterar sobre la pila
            while(!pilaNodos.isEmpty()){
                actual=pilaNodos.pop();
                lista.add(actual.getClave());
                    if(!pilaNodos.isEmpty()){
                        NodoBinario<K,V>tope=pilaNodos.peek();
                            if(!tope.esVacioHijoDerecho() && (tope.getHijoDerecho() != actual)){
                                meterPilaParaPostOrden(pilaNodos,tope.getHijoDerecho());
                            }
                    }
            }
            return lista;
    }
    
    
  public int cantidadDeHijosConInOrden(){
    if(esArbolVacio()){
        return 0;
    }
    int size=0;
    Stack<NodoBinario<K,V>>pila=new Stack<>();
    meterPilaEnInParaSize(pila,this.raiz);
        while(!pila.isEmpty()){
            NodoBinario<K,V>nodoActual=pila.pop();
            size++;
                if(!nodoActual.esVacioHijoDerecho()){
                    meterPilaEnInParaSize(pila,nodoActual.getHijoDerecho());
                }
        }
    
    return size;
    
}

private void meterPilaEnInParaSize(Stack<NodoBinario<K,V>>pila,NodoBinario<K,V>nodoActual){
    while(!NodoBinario.esNodoVacio(nodoActual)){
        pila.push(nodoActual);
        if(!nodoActual.esVacioHijoIzquierdo()){
            nodoActual=nodoActual.getHijoIzquierdo();
        }else{
            nodoActual=(NodoBinario<K,V>)NodoBinario.nodoVacio();
        }
    }

}

/**14**********/
public K predecesorInOrden(K clave){
     if(!this.contiene(clave)){
       throw new IllegalArgumentException("El nodo no existe en el arbol");
   }
   NodoBinario<K,V>nodo=precesorInOrdenAux(clave);
   if(NodoBinario.esNodoVacio(nodo)){
       return null;
   }
   return nodo.getClave();

}
private NodoBinario<K,V>precesorInOrdenAux(K clave){
    NodoBinario<K,V>auxiliar=this.raiz;
    
    boolean sw=false;    
        while(sw==false){
             K claveAuxiliar=auxiliar.getClave();
            if(clave.compareTo(claveAuxiliar)<0){
                auxiliar=auxiliar.getHijoIzquierdo();
            }else if(clave.compareTo(claveAuxiliar)>0){
                auxiliar=auxiliar.getHijoDerecho();
            }else{
                sw=true;
            }
        }
        NodoBinario<K,V>nodoAnterior=auxiliar;
        if(sw==true){
                if(!NodoBinario.esNodoVacio(auxiliar.getHijoIzquierdo())){
                    nodoAnterior=auxiliar;
                    auxiliar=auxiliar.getHijoIzquierdo();
                        while(!NodoBinario.esNodoVacio(auxiliar)){
                            nodoAnterior=auxiliar;
                            auxiliar=auxiliar.getHijoDerecho();
                        }
                    
                }else{
                    nodoAnterior=(NodoBinario<K,V>)NodoBinario.nodoVacio();
                }
        }
       return nodoAnterior;
}

public boolean verificarSiSonArbolesSimilares(ArbolBInarioDeBusqueda<K,V> arbol){
    return verificarSiSonArbolesSimilares(this.raiz,arbol.raiz);
 }
 private boolean verificarSiSonArbolesSimilares(NodoBinario<K,V>nodoActual1,NodoBinario<K,V>nodoActual2){
     
        if(NodoBinario.esNodoVacio(nodoActual1) && NodoBinario.esNodoVacio(nodoActual2)){
            return true;
        }
        boolean respuestaPorIzquierda=verificarSiSonArbolesSimilares(nodoActual1.getHijoIzquierdo(),nodoActual2.getHijoIzquierdo());
        boolean respuestaPorDerecha=verificarSiSonArbolesSimilares(nodoActual1.getHijoDerecho(),nodoActual2.getHijoDerecho());
        if(NodoBinario.esNodoVacio(nodoActual1) && !NodoBinario.esNodoVacio(nodoActual2)){
            return false;
        }
        if(!NodoBinario.esNodoVacio(nodoActual1) && NodoBinario.esNodoVacio(nodoActual2)){
         return false;
        }
        return respuestaPorIzquierda && respuestaPorDerecha;
 }
 public String mostrarNiveles(){
        String cadena="";
        if(esArbolVacio()){
            return cadena;
        }
        Queue<NodoBinario<K,V>>colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(raiz);
            while(!colaDeNodos.isEmpty()){
                int cantidadDeNodos=colaDeNodos.size();
                int i=0;
                    while(i<cantidadDeNodos){
                        NodoBinario<K,V>nodoActual=colaDeNodos.poll();
                        cadena=cadena+nodoActual.getClave().toString()+"    ";
                        if(!nodoActual.esVacioHijoIzquierdo()){
                            colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                        }if(!nodoActual.esVacioHijoDerecho()){
                            colaDeNodos.offer(nodoActual.getHijoDerecho());
                        }
                        i++;
                    }
                  cadena=cadena+"\n";  
            }
            return cadena;
    }
 public List<K> recorridoIn(){
       List<K>recorrido=new ArrayList<>();
       recorridoIn(recorrido,this.raiz);
       return recorrido;
    }
    private void recorridoIn(List<K> lista,NodoBinario<K,V>nodo){
        if(NodoBinario.esNodoVacio(nodo)){
        }else{
         recorridoIn(lista,nodo.getHijoIzquierdo());
         lista.add(nodo.getClave());
         recorridoIn(lista,nodo.getHijoDerecho());
        }
    }
}

