package br.edu.ufage.topicos.catalogo.controlador.resposta;

import org.modelmapper.ModelMapper;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.config.SpringApplicationContext;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoriaResponse {
	private long id;
	private String nome;
	private String descricao;
	private String icone;
	private long categoriaPaiId;
	
	public CategoriaResponse(Categoria categoria) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(categoria, this);
	}

}
