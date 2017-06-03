/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.modelo;

/**
 *
 * @author Andrews-PC
 */
public class Setor {

    private String nomeSetor;
    private int id;

    public Setor() {
    }

    public Setor(String nomeSetor, int id) {
        this.nomeSetor = nomeSetor;
        this.id = id;
    }

    public String getNomeSetor() {
        return nomeSetor;
    }

    public void setNomeSetor(String nomeSetor) {
        this.nomeSetor = nomeSetor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return nomeSetor;
    }
}
