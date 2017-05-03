/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sipcentric.simwood.api.exception;

import java.io.IOException;
import java.net.URL;

/**
 *
 * @author charles
 */
final public class HttpException extends IOException {
    private static final long serialVersionUID = -8301101841509056974L;
    private final int httpStatus;
    private final URL url;

    public int getHttpStatus() {
        return this.httpStatus;
    }

    public URL getUrl() {
        return this.url;
    }
    
    public HttpException(String message, int httpStatus, URL url) {
        super(message);
        this.httpStatus = httpStatus;
        this.url = url;
    }

    public HttpException(String message, int httpStatus, URL url,
            Throwable cause) {
        super(message, cause);
        this.httpStatus = httpStatus;
        this.url = url;
    }

}