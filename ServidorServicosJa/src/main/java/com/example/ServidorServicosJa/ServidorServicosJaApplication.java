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

		// Criar uma thread separada para o loop de comandos
		Thread comandoThread = new Thread(() -> {
			// Loop para aguardar comandos
			for (;;) {
				System.out.println("O servidor está ativo! Para desativá-lo,");
				System.out.println("use o comando \"desativar\"\n");
				System.out.print("> ");

				String comando = null;
				try {
					comando = Teclado.getUmString().trim();  // Remover espaços em branco extras
				} catch (Exception erro) {
					System.err.println("Erro ao ler o comando!");
				}

				if (comando != null && comando.equalsIgnoreCase("desativar")) {
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
					context.close(); // Fechar a aplicação Spring Boot
					break; // Interromper o loop de comandos
				} else {
					System.err.println("Comando inválido! Digite 'desativar' para desligar o servidor.\n");
				}
			}
		});

		// Iniciar a thread do comando
		comandoThread.start();

		try {
			// Esperar a thread de comandos terminar para finalizar o servidor
			comandoThread.join();
		} catch (InterruptedException e) {
			System.err.println("Erro ao aguardar a thread de comandos.");
		}
	}
}
