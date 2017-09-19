/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author SnakeGnr
 */
public class ConnectionFactory {
    private String nomeUsuario = "postgres";
    private String senhaUsuario = "professor";
    private String enderecoServidor = "localhost";
    private String nomeBanco = "senai";
    
    public Connection getConnection(){
        
        try {
            return DriverManager.getConnection("jdbc:postgresql://"+ enderecoServidor
                    + "/"+ nomeBanco, nomeUsuario, senhaUsuario);
        } catch (SQLException ex) {
            System.out.println("Erro ao abrir conexão:");
            throw new RuntimeException(ex);
        }
    }
    
}
