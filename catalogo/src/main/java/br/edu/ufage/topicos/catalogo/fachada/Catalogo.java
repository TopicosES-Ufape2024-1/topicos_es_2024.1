package br.edu.ufage.topicos.catalogo.fachada;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.cadastro.InterfaceCadastroCategoria;
import br.edu.ufage.topicos.catalogo.cadastro.InterfaceCadastroProduto;

@Service
public class Catalogo {
	@Autowired
	private InterfaceCadastroCategoria cadastroCategoria;
	@Autowired
	private InterfaceCadastroProduto cadastroProduto;
	public Categoria salvarCategoria(Categoria entity) {
		return cadastroCategoria.salvarCategoria(entity);
	}
	public List<Categoria> listarCategorias() {
		return cadastroCategoria.listarCategorias();
	}
	public void apagarCategoria(Long id) {
		cadastroCategoria.apagarCategoria(id);
	}
	public void apagarCategoria(Categoria entity) {
		cadastroCategoria.apagarCategoria(entity);
	}
	public Categoria encontrarCategoria(Long id) {
		return cadastroCategoria.encontrarCategoria(id);
	}
	public List<Produto> listarProdutos(String descricao) {
		return cadastroProduto.listarProdutos(descricao);
	}
	public List<Produto> listarProdutosPorCategoria(String nome) {
		return cadastroProduto.listarProdutosPorCategoria(nome);
	}
	public Produto salvarProduto(Produto entity) {
		return cadastroProduto.salvarProduto(entity);
	}
	public List<Produto> listarProdutos() {
		return cadastroProduto.listarProdutos();
	}
	public Optional<Produto> encontrarProdutoId(Long id) {
		return cadastroProduto.encontrarProdutoId(id);
	}
	public void apagarProduto(Long id) {
		cadastroProduto.apagarProduto(id);
	}
	public void apagarProduto(Produto entity) {
		cadastroProduto.apagarProduto(entity);
	}
	

	
}
