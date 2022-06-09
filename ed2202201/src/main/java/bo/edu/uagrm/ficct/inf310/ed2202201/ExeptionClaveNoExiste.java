/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo.edu.uagrm.ficct.inf310.ed2202201;

/**
 *
 * @author USUARIO
 */
public class ExeptionClaveNoExiste extends Exception {

    /**
     * Creates a new instance of <code>ExeptionClaveNoExiste</code> without
     * detail message.
     */
    public ExeptionClaveNoExiste() {
        this("clave no existe en el arbol");
    }

    /**
     * Constructs an instance of <code>ExeptionClaveNoExiste</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExeptionClaveNoExiste(String msg) {
        super(msg);
    }
}
