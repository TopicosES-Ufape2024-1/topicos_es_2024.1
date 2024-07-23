package br.edu.ufage.topicos.catalogo.controlador.requisicao;

import org.modelmapper.ModelMapper;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.config.SpringApplicationContext;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CategoriaRequest {
	@NotBlank(message ="O nome da categoria é obrigatório")
	private String nome;
	private String descricao;
	private String icone;
	
	//private long categoriaPai;
	
	public Categoria converterParaClasseBasica() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		Categoria categoria = modelMapper.map(this, Categoria.class);
		return categoria;
		
	}
	
	
}
