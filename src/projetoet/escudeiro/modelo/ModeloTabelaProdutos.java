/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.escudeiro.modelo;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrews-PC
 */
public class ModeloTabelaProdutos extends AbstractTableModel {

    private final String[] colunas = {"Codigo Produto", "Nome Produto"};
    private List<Produto> dados;

    public ModeloTabelaProdutos() {
        dados = new ArrayList();
    }

    public void addRow(Produto produto) {
        this.dados.add(produto);
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return this.colunas.length;
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getNome();
        }
        return null;
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public Produto get(int linha) {
        return this.dados.get(linha);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        return false;
    }

    public void limpaTabela() {
       this.dados = new ArrayList();
       this.fireTableDataChanged();
    }
}
