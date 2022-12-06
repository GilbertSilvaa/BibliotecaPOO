package com.example.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.Util.SqlOperacoes;


@SpringBootApplication
public class BibliotecaPooApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaPooApplication.class, args);

		System.out.println(SqlOperacoes.executarSql("insert into testes (id, nome, idade) values (4, 'testando', 73)"));
	}

}
