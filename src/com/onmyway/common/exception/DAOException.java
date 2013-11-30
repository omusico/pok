 
package com.onmyway.common.exception;

 
public class DAOException extends Exception {
    private String message="";
    public DAOException(){
        super();
    }
    public DAOException(String message){
        super(message);
        this.message=message;
    }
    public DAOException(Exception e){
        super(e);
        this.message=e.getMessage();
    }
    public String toString(){
        return this.getClass().getName()+":"+message;
    }
}
