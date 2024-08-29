package br.edu.ufage.topicos.catalogo.controlador.requisicao;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.config.SpringApplicationContext;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class ProdutoRequest {
    @NotBlank(message = "O nome do produto não pode ser vazio")
    private String nome;
    @NotBlank(message = "A descrição do produto não pode ser vazia")
    private String descricao;
    private String imagem;
    @NotNull(message = "O id da categoria não pode ser nulo")
    private long categoriaId;

    public Produto converterParaClasseBasica() {
        ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
        Produto produto = modelMapper.map(this, Produto.class);
        produto.setId(0); // ou `null`, dependendo do tipo
        return produto;
    }


}
