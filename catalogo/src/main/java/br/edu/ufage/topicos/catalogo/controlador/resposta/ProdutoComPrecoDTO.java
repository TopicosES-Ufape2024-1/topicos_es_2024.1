package br.edu.ufage.topicos.catalogo.controlador.resposta;

import br.edu.ufage.topicos.catalogo.basica.Produto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoComPrecoDTO {

    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    public ProdutoComPrecoDTO(Produto produto, PrecoDTO preco) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.preco = preco.getValue();
    }

}
