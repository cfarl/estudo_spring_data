package br.com.luizcurado.springdata.service.specification;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import br.com.luizcurado.springdata.model.Funcionario;

//------------------------------------------------------------
/** Retricoes usadas na consulta por Specification */
//------------------------------------------------------------
public class SpecificationFuncionario {

	//-------------------------------------------------------------
	/** Restricao por nome */
	//-------------------------------------------------------------
	public static Specification<Funcionario> nome(String nome) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.like(root.get("nome"), "%" + nome + "%");
	}
	
	//-------------------------------------------------------------
	/** Restricao por cpf */
	//-------------------------------------------------------------
	public static Specification<Funcionario> cpf(String cpf) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.equal(root.get("cpf"), cpf);
	}
	
	//-------------------------------------------------------------
	/** Restricao por salario */
	//-------------------------------------------------------------
	public static Specification<Funcionario> salario(Double salario) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.get("salario"), salario);
	}
	
	//-------------------------------------------------------------
	/** Restricao por data de contratacao */
	//-------------------------------------------------------------
	public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
		return (root, criteriaQuery, criteriaBuilder) -> 
			criteriaBuilder.greaterThan(root.get("dataContratacao"), dataContratacao);
	}
	
}
