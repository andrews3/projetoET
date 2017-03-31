/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import projetoet.JanelaPrincipal;

/**
 *
 * @author Andrews-PC
 */
public class SetorTableModel extends AbstractTableModel {

    private List<Setor> dados;
    private final String[] colunas = {"Codigo Setor", "Nome Setor"};

    public SetorTableModel() {
        dados = new ArrayList<>();

        this.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent tme) {
                int linha = tme.getFirstRow();
                try{
                Setor s = dados.get(linha);
                atualizaBanco(s);
                }catch(IndexOutOfBoundsException ie){
                    System.out.println("Não é um erro de verdade");
                }
                

            }
        });

    }

    private void atualizaBanco(Setor s) {
        try {
            Class.forName("org.hsqldb.jdbcDriver");
            Connection con;
            con = DriverManager.getConnection(JanelaPrincipal.caminhoBanco, "sa", "");
            java.sql.Statement stm = con.createStatement();
            stm.executeQuery("UPDATE SETORES SET nomeSetor = '" + s.getNomeSetor() + "' Where idsetor='" + s.getId() + "'");
            stm.execute("SHUTDOWN");

        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao carregar o driver JDBC. ");
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
        }
    }

    public void addRow(Setor s) {
        this.dados.add(s);
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int num) {
        return this.colunas[num];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getId();
            case 1:
                return dados.get(linha).getNomeSetor();
        }
        return null;
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public void removeAll() {
        
    }

    public Setor get(int linha) {
        return this.dados.get(linha);
    }

    @Override
    public boolean isCellEditable(int linha, int coluna) {
        if (coluna == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        if (valor == null) {
            return;
        }
        switch (coluna) {
            case 0:
                dados.get(linha).setId(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(linha).setNomeSetor((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

}
