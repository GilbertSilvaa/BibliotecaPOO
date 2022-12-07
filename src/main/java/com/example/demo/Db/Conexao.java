package com.example.demo.Db;

import java.sql.DriverManager;
import java.sql.SQLException;


import java.sql.Connection;

public class Conexao {
    
    Connection connection = null;

    public Connection conectar() {
        try {
	        connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotecapoo", "root", "Ringkyu777#");
	        if (connection != null) {
	            System.out.println("Conexão realizada!");
	            System.out.println("conn:" + connection);
	            return connection;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return null;
    }

    public Connection getConexao() {
		if(connection == null) 
			connection = conectar();
		
		return connection;
	}
}
