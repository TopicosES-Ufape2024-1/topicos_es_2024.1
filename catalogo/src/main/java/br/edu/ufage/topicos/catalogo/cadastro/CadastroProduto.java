package br.edu.ufage.topicos.catalogo.cadastro;

import java.util.List;
import java.util.Optional;

import br.edu.ufage.topicos.catalogo.cadastro.mensagem.Event;
import br.edu.ufage.topicos.catalogo.cadastro.mensagem.Publisher;
import br.edu.ufage.topicos.catalogo.controlador.requisicao.CreateProductInventoryDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.repositorio.RepositorioProduto;

@Service
public class CadastroProduto implements InterfaceCadastroProduto {
    @Autowired
    private RepositorioProduto repositorioProduto;

//	@Autowired
//	private Publisher publisher;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<Produto> listarProdutos(String descricao) {
        return repositorioProduto.findByDescricaoContainingIgnoreCase(descricao);
    }

    @Override
    public List<Produto> listarProdutosPorCategoria(String nome) {
        return repositorioProduto.findByCategoria_nome(nome);
    }

    @Override
    public Produto salvarProduto(Produto entity) {
        Produto produto = repositorioProduto.save(entity);
        //Event<Long, String> evt = new Event(Event.Type.CREATE, produto.getId(), "teste");
        //publisher.sendEvent(evt);
        //Message message = new Message(("Criar estoque para o produto com o id: " + produto.getId()).getBytes());
        CreateProductInventoryDTO createProductInventoryDTO = new CreateProductInventoryDTO(produto.getId(), 0, 1L);
        rabbitTemplate.convertAndSend("create.product.inventory", createProductInventoryDTO);
        return produto;
    }

    @Override
    public List<Produto> listarProdutos() {
        return repositorioProduto.findAll();
    }

    @Override
    public Optional<Produto> encontrarProdutoId(Long id) {
        return repositorioProduto.findById(id);
    }

    @Override
    public void apagarProduto(Long id) {
        repositorioProduto.deleteById(id);
    }

    @Override
    public void apagarProduto(Produto entity) {
        repositorioProduto.delete(entity);
    }


}
