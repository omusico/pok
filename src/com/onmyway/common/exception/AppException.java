package com.onmyway.common.exception;

public class AppException extends Exception {
    /**
     * 
     */
    public AppException() {
        super();
    }

    /**
     * @param arg0
     */
    public AppException(String arg0) {
        super(arg0);
    }

    /**
     * @param arg0
     * @param arg1
     */
    public AppException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    /**
     * @param arg0
     */
    public AppException(Throwable arg0) {
        super(arg0);
    }


}
