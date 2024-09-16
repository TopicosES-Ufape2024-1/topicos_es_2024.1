package br.edu.ufape.topicos.catalogo.controlador.resposta;

import br.edu.ufape.topicos.catalogo.basica.Categoria;
import br.edu.ufape.topicos.catalogo.basica.Produto;
import br.edu.ufape.topicos.catalogo.config.SpringApplicationContext;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ProdutoResponse {
	private long id;
	private String nome;
	private String descricao;
	private String imagem;
	private long categoriaPaiId;

	public ProdutoResponse(Produto produto) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(produto, this);
	}

}
