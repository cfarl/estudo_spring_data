package br.com.luizcurado.springdata.service;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.luizcurado.springdata.model.Funcionario;
import br.com.luizcurado.springdata.repository.FuncionarioRepository;

@Service
public class CrudFuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;	
	
	//------------------------------------------------
	/** Seleciona a acao a ser executada */
	//------------------------------------------------
	public void selecionaAcao(Scanner scanner) {
		while(true) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("Qual acao de funcionario deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Salvar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			System.out.print("Informe uma opcao: ");
			
			int acao = scanner.nextInt();
			
			switch (acao) {
			case 1:
				cadastrarFuncionario(scanner);
				break;
			case 2:
				atualizar(scanner);
				break;
			case 3:
				visualizar(scanner);
				break;
			case 4:
				excluirFuncionario(scanner);
				break;
			default:
				return ;
			}			
		}		
	}

	//------------------------------------------------------
	/** Cadastra um novo funcionario */
	//------------------------------------------------------
	private void cadastrarFuncionario(Scanner scanner) {
		// Pede os dados do funcionario
		System.out.print("Digite o nome: ");
        String nome = scanner.next();

        System.out.print("Digite o cpf: ");
        String cpf = scanner.next();

        System.out.print("Digite o salario: ");
        Double salario = scanner.nextDouble();

        System.out.print("Digite a data de contracao: ");
        String dataContratacao = scanner.next();

        // Carrega dados no objeto Funcionario
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(Util.toLocalDate(dataContratacao));

        // Guarda no banco de dados
        funcionarioRepository.save(funcionario);
        System.out.println("-->cadastrou");
	}
	
	//------------------------------------------------------
	/** Atualiza os dados de um funcionario */
	//------------------------------------------------------	
	private void atualizar(Scanner scanner) {
		// Pede os dados do funcionario
		System.out.print("Digite o id: ");
        Integer id = scanner.nextInt();

        System.out.print("Digite o nome: ");
        String nome = scanner.next();

        System.out.print("Digite o cpf: ");
        String cpf = scanner.next();

        System.out.print("Digite o salario: ");
        Double salario = scanner.nextDouble();

        System.out.print("Digite a data de contracao: ");
        String dataContratacao = scanner.next();

        // Carrega dados no objeto Funcionario
        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(Util.toLocalDate(dataContratacao));

        // Guarda no banco de dados
        funcionarioRepository.save(funcionario);
        System.out.println("-->alterou");
	}
	
	//------------------------------------------------------
	/** Visualiza funcionarios, usando paginacao */
	//------------------------------------------------------
	private void visualizar(Scanner scanner) {
		System.out.print("Qual pagina voce deseja visualizar?");
		Integer pagina = scanner.nextInt();
		
		// Faz consulta paginada, recuperando 5 registros
		Pageable pageable = PageRequest.of(pagina, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual: " + funcionarios.getNumber());
		System.out.println("Total de funcionarios: " + funcionarios.getTotalElements());
		funcionarios.forEach(funcionario -> System.out.println("-->" + funcionario));
	}
	
	//---------------------------------------
	/** Exclui um funcionario */
	//---------------------------------------
	private void excluirFuncionario(Scanner scanner) {
		System.out.print("Entre com o id do funcionário: ");
		int id = scanner.nextInt();
		
		funcionarioRepository.deleteById(id);
		System.out.println("-->excluido");
	}
	
}
