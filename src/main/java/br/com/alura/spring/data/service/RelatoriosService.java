package br.com.alura.spring.data.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.alura.spring.data.orm.Funcionario;
import br.com.alura.spring.data.orm.FuncionarioProjecao;
import br.com.alura.spring.data.repository.FuncionarioRepository;
import br.com.alura.spring.data.service.specification.SpecificationFuncionario;

@Service
public class RelatoriosService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	//-------------------------------------------------------------------
	/** Seleciona a acao a ser executada */
	//-------------------------------------------------------------------
	public void selecionaAcao(Scanner scanner) {
		while(true) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Qual relatorio deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Pesquisa funcionario por nome");
			System.out.println("2 - Pesquisa funcionario por nome, data contratacao e salario maior");
			System.out.println("3 - Pesquisa funcionario por data de contratacao");
			System.out.println("4 - Pesquisa funcionario e seu salario");
			System.out.println("5 - Pesquisa dinamica com specification");
			System.out.print("Informe uma opcao: ");
			
			int action = scanner.nextInt();
			
			switch (action) {
			case 1:
				pesquisaFuncionarioPorNome(scanner);
				break;
			case 2:
				pesquisaFuncionarioPorNomeSalarioMaiorData(scanner);
				break;
			case 3:
				pesquisaFuncionarioPorDataContratacao(scanner);
				break;
			case 4:
				pesquisafuncionarioSalario();
				break;
			case 5:
				pesquisaDinamicaComSpecification(scanner) ;
				break;
			default:
				return;
			}
			
		}
	}
	
	//-------------------------------------------------------------------
	/** Pesquisa funcionario por nome */
	//-------------------------------------------------------------------
	private void pesquisaFuncionarioPorNome(Scanner scanner) {
		System.out.print("Qual o nome do funcionario? ");
		String nome = scanner.next();
		
		List<Funcionario> list = funcionarioRepository.findByNome(nome);
		list.forEach(System.out::println);
	}
	
	//---------------------------------------------------------------------
	/** Pesquisa funcionario por nome, data contratacao e salario maior */
	//---------------------------------------------------------------------
	private void pesquisaFuncionarioPorNomeSalarioMaiorData(Scanner scanner) {
		System.out.print("Qual o nome do funcionario? ");
		String nome = scanner.next();
		
		System.out.print("Qual a data de contratacao? ");
		String data = scanner.next();
		LocalDate localDate = Util.toLocalDate(data);
		
		System.out.print("Qual o salario? ");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> list = funcionarioRepository
				.findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
		list.forEach(System.out::println);
	}
	
	//-------------------------------------------------------------------
	/** Pesquisa funcionario por data de contratacao */
	//-------------------------------------------------------------------
	private void pesquisaFuncionarioPorDataContratacao(Scanner scanner) {
		System.out.print("Qual a data de contratacao? ");
		String data = scanner.next();
		LocalDate localDate = Util.toLocalDate(data);
		
		List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
		list.forEach(System.out::println);
	}
	
	//-------------------------------------------------------------------
	/** Pesquisa funcionario e seu salario */
	//-------------------------------------------------------------------
	private void pesquisafuncionarioSalario() {
		List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
		list.forEach(f -> System.out.println("Funcionario: id: " + f.getId() 
		+ " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
	}
	
	//-------------------------------------------------------------------
	/** Pesquisa dinamica com specification */
	//-------------------------------------------------------------------
	public void pesquisaDinamicaComSpecification(Scanner scanner) {		
		// Pede o nome do funcionario
		System.out.print("Informe o nome: ");
		String nome = scanner.next();
		
		if(nome.equalsIgnoreCase("NULL")) {
			nome = null;
		}
		
		// Pede o cpf do funcionario
		System.out.print("Informe o cpf: ");
		String cpf = scanner.next();
		
		if(cpf.equalsIgnoreCase("NULL")) {
			cpf = null;
		}
		
		// Pede o salario do funcionario
		System.out.print("Informe o salario: ");
		Double salario = scanner.nextDouble();
		
		if(salario == 0) {
			salario = null;
		}
		
		// Pede a data de contratacao
		System.out.print("Informe a data de contratacao: ");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("NULL")) {
			dataContratacao = null;
		} else {
			dataContratacao = Util.toLocalDate(data);
		}
		
		// Usa os dados informados para fazer a consulta por specification (antigo criteria)
		List<Funcionario> funcionarios = funcionarioRepository.findAll(
				Specification.where(
						SpecificationFuncionario.nome(nome))
						.or(SpecificationFuncionario.cpf(cpf))
						.or(SpecificationFuncionario.salario(salario))
						.or(SpecificationFuncionario.dataContratacao(dataContratacao))
				);
		funcionarios.forEach(System.out::println);
	}
	
}
