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
public class Produto {

    private String nome;
    private int id;
    private Setor setor;

    public Produto() {
    }

    public Produto(String nome, int id, Setor setor) {
        this.nome = nome;
        this.id = id;
        this.setor = setor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Setor getSetor() {
        return setor;
    }

    public void setSetor(Setor setor) {
        this.setor = setor;
    }

    @Override
    public String toString() {
        return "Produto " + nome + ", com código " + id + ", cadastrado no setor " + setor;
    }

}
