/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdh.apps.exception;

/**
 *
 * @author Lea
 */
public class ExceptionPollitosDeHierro extends Exception {

    /**
     * Class to control Exceptions for the API
     */
    private static final long serialVersionUID = 1L;

    public ExceptionPollitosDeHierro() {
        super();
    }

    public ExceptionPollitosDeHierro(String message) {
        super(message);
    }

    public ExceptionPollitosDeHierro(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionPollitosDeHierro(Throwable cause) {
        super(cause);
    }
}
