package br.edu.ufage.topicos.catalogo.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.edu.ufage.topicos.catalogo.cadastro.ServicoDePrecoIndisponivelException;
import br.edu.ufage.topicos.catalogo.controlador.resposta.PrecoDTO;
import br.edu.ufage.topicos.catalogo.controlador.resposta.ProdutoComPrecoDTO;
import br.edu.ufage.topicos.catalogo.cadastro.PrecoCliente;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PrecoCliente precoCliente;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/produto")
    Produto cadastrarProduto(@Valid @RequestBody ProdutoRequest newObj) {
        Produto prod = newObj.converterParaClasseBasica();
        return catalogo.salvarProduto(prod);
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

    @GetMapping("/produto/{id}/preco")
    public ResponseEntity<ProdutoComPrecoDTO> carregarProdutoComPreco(@PathVariable long id) {
        try {
            Optional<Produto> produto = catalogo.encontrarProdutoId(id);
            if (produto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            PrecoDTO preco = precoCliente.getPrecoByProdutoId(id);
            if (preco == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            ProdutoComPrecoDTO produtoComPrecoDTO = new ProdutoComPrecoDTO(produto.get(), preco);
            return ResponseEntity.ok(produtoComPrecoDTO);

        } catch (FeignException e) {
            throw new ServicoDePrecoIndisponivelException("Serviço de preço indisponível");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/produto/{id}")
    void apagarProduto(@PathVariable long id) {
        catalogo.apagarProduto(id);
    }
}
