package br.edu.ufape.topicos.catalogo.controlador;

import java.util.*;

import br.edu.ufape.topicos.catalogo.cadastro.PrecoClient;
import br.edu.ufape.topicos.catalogo.cadastro.ServicoDePrecoIndisponivelException;
import br.edu.ufape.topicos.catalogo.controlador.resposta.PrecoDTO;
import br.edu.ufape.topicos.catalogo.controlador.resposta.ProdutoComPrecoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import br.edu.ufape.topicos.catalogo.basica.Produto;
import br.edu.ufape.topicos.catalogo.controlador.requisicao.ProdutoRequest;
import br.edu.ufape.topicos.catalogo.controlador.resposta.ProdutoResponse;
import br.edu.ufape.topicos.catalogo.fachada.Catalogo;
import jakarta.validation.Valid;

@RestController
public class ControladorProduto {

    @Autowired
    private Catalogo catalogo;

    @Autowired
    private PrecoClient precoCliente;


    @PostMapping("/produto")
    @PreAuthorize("hasRole('gerente')")
    Produto cadastrarProduto(@Valid @RequestBody ProdutoRequest newObj) {
        Produto prod = newObj.converterParaClasseBasica();
        return catalogo.salvarProduto(prod);
    }

    @GetMapping("/produto")
    @PreAuthorize("hasRole('user') or hasRole('gerente')")
    List<ProdutoResponse> listarProdutos(Authentication authentication) {
        System.out.println("Roles: " + authentication.getAuthorities());
        List<ProdutoResponse> response = new ArrayList<>();
        for (Produto p : catalogo.listarProdutos()) {
            response.add(new ProdutoResponse(p));
        }
        return response;
    }

    @GetMapping("/produto/{id}")
    @PreAuthorize("hasRole('user') or hasRole('gerente')")
    ProdutoResponse carregarProduto(@PathVariable long id) {
        Optional<Produto> produto = catalogo.encontrarProdutoId(id);
        return produto.map(ProdutoResponse::new).orElse(null);
    }

    @PreAuthorize("hasRole('user') or hasRole('gerente')")
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

        } catch (ServicoDePrecoIndisponivelException e) {
            throw e;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/produto/{id}")
    @PreAuthorize("hasRole('gerente')")
    void apagarProduto(@PathVariable long id) {
        catalogo.apagarProduto(id);
    }
}
