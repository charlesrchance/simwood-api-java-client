/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.exception;

/**
 *
 * @author charles
 */
public class NumberingException extends Exception {
    private static final long serialVersionUID = -1923104535309628719L;

    public NumberingException(String message) {
        super(message);
    }

    public NumberingException(String message, Throwable cause) {
        super(message, cause);
    }    
}
