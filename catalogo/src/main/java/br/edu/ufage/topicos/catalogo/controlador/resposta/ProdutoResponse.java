package br.edu.ufage.topicos.catalogo.controlador.resposta;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.config.SpringApplicationContext;
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
