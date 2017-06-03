/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.utilitarios;

import java.io.IOException;

/**
 *
 * @author Andrews-PC
 */
public class EscudeiroException extends Exception {

    public EscudeiroException() {
        super("Ocorreu um erro desconhecido!");
    }
    
    public EscudeiroException(Exception x) throws EscudeiroException{
        super(x.getMessage());
        try {
            ECLogger.insereLogException(x);
        } catch (IOException ex) {
            throw new EscudeiroException(ex);
        }
    }
    
}
