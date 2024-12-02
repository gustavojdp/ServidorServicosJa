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

		// Iniciar a aceitadora de conexões
		AceitadoraDeConexao aceitadoraDeConexao = null;
		try {
			aceitadoraDeConexao = new AceitadoraDeConexao(porta, usuarios);
			aceitadoraDeConexao.start();
		} catch (Exception erro) {
			System.err.println("Escolha uma porta apropriada e liberada para uso!\n");
			return;
		}

		// Loop para aguardar comandos
		for (;;) {
			System.out.println("O servidor está ativo! Para desativá-lo,");
			System.out.println("use o comando \"desativar\"\n");
			System.out.print("> ");

			String comando = null;
			try {
				comando = Teclado.getUmString();
			} catch (Exception erro) {
			}

			if (comando != null && comando.toLowerCase().equals("desativar")) {
				synchronized (usuarios) {
					ComunicadoDeDesligamento comunicadoDeDesligamento = new ComunicadoDeDesligamento();

					for (Parceiro usuario : usuarios) {
						try {
							usuario.receba(comunicadoDeDesligamento);
							usuario.adeus();
						} catch (Exception erro) {
						}
					}
				}

				System.out.println("O servidor foi desativado!\n");
				// Fechar o contexto da aplicação Spring Boot
				context.close(); // Fechar a aplicação Spring Boot
				return;
			} else {
				System.err.println("Comando inválido!\n");
			}
		}
	}
}
