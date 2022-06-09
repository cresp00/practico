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
public class ExceptionOrdenInvalida extends Exception {

    /**
     * Creates a new instance of <code>ExceptionOrdenInvalida</code> without
     * detail message.
     */
    public ExceptionOrdenInvalida() {
        this("Orden Invalida");
    }

    /**
     * Constructs an instance of <code>ExceptionOrdenInvalida</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ExceptionOrdenInvalida(String msg) {
        super(msg);
    }
}
