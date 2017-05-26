/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrews-PC
 */
public class ECLogger {

    private final static String arquivo = "escudeiro.log";

    public static void insereLogException(Exception ex) throws IOException {
        StringWriter errors = new StringWriter();
        ex.printStackTrace(new PrintWriter(errors));
        String x = errors.toString();
        insereLog(x);

    }

    public static void insereLog(String mensagem) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo, true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String data = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            bufferedWriter.write(data + " " + mensagem);
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }

    public static void insereLog(String mensagem, boolean b) throws IOException {
        FileWriter fileWriter = new FileWriter(arquivo, true);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            String data = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
            bufferedWriter.write(data + " " + mensagem);
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.flush();
            bufferedWriter.close();
        }
    }
}
