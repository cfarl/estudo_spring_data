package br.com.alura.spring.data;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import br.com.alura.spring.data.service.CrudFuncionarioService;
import br.com.alura.spring.data.service.RelatoriosService;

@EnableJpaRepositories
@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	@Autowired
	private RelatoriosService relatoriosService;
	
	@Autowired
	private CrudFuncionarioService funcionarioService;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}

	//----------------------------------------------------------------------------
	/** Método de CommandLineRunner, chamado após a inicialização do Spring */
	//----------------------------------------------------------------------------
	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while (true) {			
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Qual função deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Funcionario");
			System.out.println("2 - Relatorios");
			System.out.print("Informe uma opcao: ");

			Integer funcao = scanner.nextInt();

			switch (funcao) {
				case 1:
					funcionarioService.selecionaAcao(scanner);
					break;
				case 2:
					relatoriosService.selecionaAcao(scanner);
					break;
				default:
					System.out.println("Finalizando");
					return;					
			}
		}
	}
}