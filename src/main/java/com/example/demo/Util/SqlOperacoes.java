package com.example.demo.Util;

import java.sql.ResultSet;

import com.example.demo.Db.Conexao;
import java.sql.Statement;

public class SqlOperacoes {

    public static ResultSet executarSql(String sql) {
        Conexao conexao = new Conexao();
        	
		try {
            Statement ps = conexao.getConexao().createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                int idade = rs.getInt("idade");
                
                // print the results
                System.out.format("%s, %s, %s\n", id, nome, idade);
            }
			return rs;
		}
		catch (Exception e ) {
			e.getStackTrace();
		}
        return null;
    }
}
