package br.edu.ufage.topicos.catalogo.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.repositorio.RepositorioProduto;

@Service
public class CadastroProduto implements InterfaceCadastroProduto {
	@Autowired
	private RepositorioProduto repositorioProduto;

	@Override
	public List<Produto> listarProdutos(String descricao) {
		return repositorioProduto.findByDescricaoContainingIgnoreCase(descricao);
	}

	@Override
	public List<Produto> listarProdutosPorCategoria(String nome) {
		return repositorioProduto.findByCategoria_nome(nome);
	}

	@Override
	public  Produto salvarProduto(Produto entity) {
		return repositorioProduto.save(entity);
	}

	@Override
	public List<Produto> listarProdutos() {
		return repositorioProduto.findAll();
	}

	@Override
	public Optional<Produto> encontrarProdutoId(Long id) {
		return repositorioProduto.findById(id);
	}

	@Override
	public void apagarProduto(Long id) {
		repositorioProduto.deleteById(id);
	}

	@Override
	public void apagarProduto(Produto entity) {
		repositorioProduto.delete(entity);
	}
	

}
