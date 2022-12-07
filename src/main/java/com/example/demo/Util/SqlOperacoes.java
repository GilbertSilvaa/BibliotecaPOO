package com.example.demo.Util;

import java.sql.ResultSet;

import com.example.demo.Db.Conexao;
import java.sql.Statement;

public class SqlOperacoes {

    // consultar
    public static ResultSet consulta(String sql) {
        Conexao conexao = new Conexao();
        	
		try {
            Statement ps = conexao.getConexao().createStatement();
            ResultSet rs = ps.executeQuery(sql);

            while (rs.next())
            {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                
                System.out.format("%s, %s\n", id, nome);
            }
			return rs;
		}
		catch (Exception e ) {
			e.getStackTrace();
		}
        return null;
    }

    // inserir, deletar ou atualizar
    public static String executar(String sql) {
        Conexao conexao = new Conexao();
        	
		try {
            Statement ps = conexao.getConexao().createStatement();
            ps.executeUpdate(sql);

			return "sucesso";
		}
		catch (Exception e ) {
			e.getStackTrace();

            return e.getMessage();
		}
        
    }

}
