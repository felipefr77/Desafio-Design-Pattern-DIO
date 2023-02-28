package one.digitalinnovation.patterns.service;

import one.digitalinnovation.patterns.model.Cliente;

/**
 * Interface que define o padrão de projeto Strategy
 * 
 * @author felipefruhauf
 */

public interface ClienteService {
	
	Iterable<Cliente> buscarTodos();
	Cliente buscarPorId(Long id);
	void inserir(Cliente cliente);
	void atualizar(Long id, Cliente cliente);
	void deletar(Long id);
}
