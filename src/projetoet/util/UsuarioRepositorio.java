/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetoet.util;

/**
 *
 * @author Andrews-PC
 */
public class UsuarioRepositorio {

    private static Usuarios usuarioConec;
    private static boolean conec = false;

    public static Usuarios getUsuarioConec() {
        return usuarioConec;
    }

    public static void setUsuarioConec(Usuarios usuarioConec) {
        UsuarioRepositorio.usuarioConec = usuarioConec;
    }

    public static boolean isConec() {
        return conec;
    }

    public static void setConec(boolean conec) {
        UsuarioRepositorio.conec = conec;
    }
}
