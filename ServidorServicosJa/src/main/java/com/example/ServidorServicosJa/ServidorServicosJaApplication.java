package com.example.ServidorServicosJa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class ServidorServicosJaApplication {

	public static String PORTA_PADRAO = "6001";

	public static void main(String[] args) {
		// Inicia o Spring Boot
		ConfigurableApplicationContext context = SpringApplication.run(ServidorServicosJaApplication.class, args);

		// Argumentos da linha de comando
		if (args.length > 1) {
			System.err.println("Uso esperado: java Servidor [PORTA]\n");
			return;
		}

		String porta = ServidorServicosJaApplication.PORTA_PADRAO;

		if (args.length == 1) {
			porta = args[0];
		}

		// Lista de parceiros (usuários)
		ArrayList<Parceiro> usuarios = new ArrayList<Parceiro>();

		// Iniciar a aceitadora de conexões em uma thread separada
		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();
		} catch (Exception erro) {
			System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
			return;
		}

		// Exibir a mensagem uma vez
		System.out.println("O servidor está ativo! Aguardando requisições...\n");

		// A partir daqui, o Spring Boot fica no controle do servidor e ele não vai mais pedir comandos.

		// Não há necessidade do loop de comandos
		// O servidor agora ficará aguardando as requisições e mantendo os logs do Spring Boot, MongoDB, etc.

		// Caso precise interromper o servidor manualmente, o Spring Boot pode ser fechado automaticamente:
		// context.close(); // Isso pode ser chamado de outro lugar para parar o servidor programaticamente, se necessário.
	}
}
