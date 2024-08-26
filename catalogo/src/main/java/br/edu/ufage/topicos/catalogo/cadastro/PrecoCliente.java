package br.edu.ufage.topicos.catalogo.cadastro;

import br.edu.ufage.topicos.catalogo.controlador.resposta.PrecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "precoCliente", url = "http://price:8080")
public interface PrecoCliente {

    @GetMapping("/price/product/{id}")
    PrecoDTO getPrecoByProdutoId(@PathVariable("id") Long produtoId);
}
