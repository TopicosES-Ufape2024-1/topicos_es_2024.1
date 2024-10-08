package br.edu.ufape.topicos.catalogo.cadastro;

import java.util.List;
import java.util.Optional;

import br.edu.ufape.topicos.catalogo.basica.Produto;

public interface InterfaceCadastroProduto {

	List<Produto> listarProdutos(String descricao);

	List<Produto> listarProdutosPorCategoria(String nome);

	Produto salvarProduto(Produto entity);

	List<Produto> listarProdutos();

	Optional<Produto> encontrarProdutoId(Long id);

	void apagarProduto(Long id);

	void apagarProduto(Produto entity);

}