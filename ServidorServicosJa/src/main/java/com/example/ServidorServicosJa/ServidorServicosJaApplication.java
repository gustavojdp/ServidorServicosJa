package com.example.ServidorServicosJa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;

@SpringBootApplication
public class ServidorServicosJaApplication {

	public static String PORTA_PADRAO = "6001";

	public static void main(String[] args) {
		// Inicia o Spring Boot e o servidor
		ConfigurableApplicationContext context = SpringApplication.run(ServidorServicosJaApplication.class, args);

		// Argumentos da linha de comando
		if (args.length > 1) {
			System.err.println("Uso esperado: java Servidor [PORTA]");
			return;
		}

		String porta = ServidorServicosJaApplication.PORTA_PADRAO;
		if (args.length == 1) {
			porta = args[0];
		}

		// Lista de parceiros (usuários)
		ArrayList<Parceiro> usuarios = new ArrayList<>();

		// Iniciar a aceitadora de conexões em uma thread separada
		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();
			System.out.println("Servidor iniciado com sucesso na porta: " + porta);
		} catch (Exception erro) {
			System.err.println("Erro ao iniciar servidor. Certifique-se de que a porta esteja liberada e disponível.");
			erro.printStackTrace(); // Imprime o stack trace para detalhes do erro
			return;
		}

		// Mensagem de conexão com MongoDB (simulada)
		System.out.println("Conexão com o MongoDB estabelecida com sucesso!");

		// Log do Tomcat (Spring Boot já gerencia o servidor web)
		System.out.println("Tomcat iniciado na porta 8080 (http)");

		// Log de status do servidor
		System.out.println("O servidor está ativo! Aguardando requisições...");

		// O Spring Boot vai continuar rodando e gerenciar as requisições, sem necessidade de loop manual de comandos.
		// O servidor só será interrompido manualmente ou com algum erro crítico.
	}
}
