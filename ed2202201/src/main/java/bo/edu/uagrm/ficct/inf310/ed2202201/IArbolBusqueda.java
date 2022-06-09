/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310.ed2202201;

import java.util.List;

/**
 *
 * @author USUARIO
 */
public interface IArbolBusqueda <K extends Comparable<K>,V>{
    
    void insertar(K claveAInsertar, V valorAInsertar) throws NullPointerException;
    V eliminar(K claveAEliminar)throws NullPointerException, ExeptionClaveNoExiste;
    V buscar(K claveABuscar) throws NullPointerException;
    boolean contiene(K claveABuscar) throws NullPointerException;
    int size();
    int altura();
    void vaciarArbol();
    boolean esArbolVacio();
    
    List<K> recorridoPorNiveles();
    List<K> recorridoEnPreOrden();
    List<K> recorridoEnInOrden();
    List<K> recorridoEnPostOrden();
}
