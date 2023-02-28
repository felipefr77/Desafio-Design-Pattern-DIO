package one.digitalinnovation.patterns.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.patterns.model.Cliente;
import one.digitalinnovation.patterns.model.Endereco;
import one.digitalinnovation.patterns.repository.ClienteRepository;
import one.digitalinnovation.patterns.repository.EnderecoRepository;

/**
 * Implementação concreta da interface(Strategy), a qual poderá ser injetada pelo Spring.
 * Sendo ela um {Service} passará a ser tratada como um (Singleton).
 * 
 * @author felipefruhauf
 */

@Service
public class ClienteServiceImplements implements ClienteService {
	
	//Singleton: Injeção de componentes com o @Autowired
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCep;
	
	// Strategy: Implementação dos métodos definidos na interface
	// Facade: Abstração da complexidade de integrações entre subsistemas.

	@Override
	public Iterable<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.get();
	}

	@Override
	public void inserir(Cliente cliente) {
		salvarCliente(cliente);
	}
	
	private void salvarCliente(Cliente cliente) {
		
		String cep = cliente.getEndereco().getCep();
		
		//primeiro verificar se o endereço já existe
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {

			//se não existir precisamos consultar na api ViaCep, para salvar e devolver o novo endereço.
			Endereco enderecoNovo = enderecoRepository.save(viaCep.consultarCep(cep));
			enderecoRepository.save(enderecoNovo);
			return enderecoNovo;
		});
		cliente.setEndereco(endereco);
		clienteRepository.save(cliente);
	}

	@Override
	public void atualizar(Long id, Cliente cliente) {
		
		Optional<Cliente> clienteConsulta = clienteRepository.findById(id);
		
		//verificar se o cliente existe
		if(clienteConsulta.isPresent()) {
			salvarCliente(cliente);
		}
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}
}
