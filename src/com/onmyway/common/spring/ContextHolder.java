package com.onmyway.common.spring;

import org.springframework.context.ApplicationContext;

 
public class ContextHolder {
    private final static ContextHolder instance = new ContextHolder();

    private ApplicationContext ac;

    private ContextHolder() {
    }

    public static ContextHolder getInstance() {
        return instance;
    }

    public synchronized void setApplicationContext(ApplicationContext ac) {
        this.ac = ac;
    }

    public ApplicationContext getApplicationContext() {
        return ac;
    }
}
