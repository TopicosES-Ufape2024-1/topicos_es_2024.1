package br.edu.ufage.topicos.catalogo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.controlador.requisicao.ProdutoRequest;
import br.edu.ufage.topicos.catalogo.controlador.resposta.ProdutoResponse;
import br.edu.ufage.topicos.catalogo.fachada.Catalogo;
import jakarta.validation.Valid;

@RestController
public class ControladorProduto {

    @Autowired
    private Catalogo catalogo;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/produto")
    Produto cadastrarProduto(@Valid @RequestBody ProdutoRequest newObj) {
        return catalogo.salvarProduto(newObj.converterParaClasseBasica());
    }

    @GetMapping("/produto")
    List<ProdutoResponse> listarProdutos() {
        List<ProdutoResponse> response = new ArrayList<>();
        for (Produto p : catalogo.listarProdutos()) {
            response.add(new ProdutoResponse(p));
        }
        return response;
    }

    @GetMapping("/produto/{id}")
    ProdutoResponse carregarProduto(@PathVariable long id) {
        Optional<Produto> produto = catalogo.encontrarProdutoId(id);
        return produto.map(ProdutoResponse::new).orElse(null);
    }

    @DeleteMapping("/produto/{id}")
    void apagarProduto(@PathVariable long id) {
        catalogo.apagarProduto(id);
    }
}
