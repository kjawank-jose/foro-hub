package com.kjawank.forohub.infra.errores;

public class ValidationOfIntegrity extends RuntimeException {
    public ValidationOfIntegrity(String s){
        super(s);
    }
}
