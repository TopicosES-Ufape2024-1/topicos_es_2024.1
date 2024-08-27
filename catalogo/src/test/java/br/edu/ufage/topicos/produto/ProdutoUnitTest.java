package br.edu.ufage.topicos.produto;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import br.edu.ufage.topicos.catalogo.cadastro.mensagem.Event;
import br.edu.ufage.topicos.catalogo.cadastro.mensagem.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.basica.Produto;
import br.edu.ufage.topicos.catalogo.cadastro.CadastroProduto;
import br.edu.ufage.topicos.catalogo.repositorio.RepositorioProduto;

class ProdutoUnitTest {

    @InjectMocks
    private CadastroProduto cadastroProduto;

    @Mock
    private RepositorioProduto repositorioProduto;

    @Mock
    private Publisher publisher;

    private boolean result;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarProdutosPorCategoria() {
        System.out.println("Iniciando teste: testListarProdutosPorCategoria");

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        System.out.println("Categoria criada: " + categoria.getNome());

        Produto produto1 = new Produto();
        produto1.setNome("Celular");
        produto1.setCategoria(categoria);
        System.out.println("Produto 1 criado: " + produto1.getNome());

        Produto produto2 = new Produto();
        produto2.setNome("Televisão");
        produto2.setCategoria(categoria);
        System.out.println("Produto 2 criado: " + produto2.getNome());

        List<Produto> produtos = Arrays.asList(produto1, produto2);

        when(repositorioProduto.findByCategoria_nome("Eletrônicos")).thenReturn(produtos);

        List<Produto> resultado = cadastroProduto.listarProdutosPorCategoria("Eletrônicos");

        System.out.println("Resultado do teste:");
        resultado.forEach(produto -> System.out.println("Produto listado: " + produto.getNome()));

        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Celular", resultado.get(0).getNome());
        assertEquals("Televisão", resultado.get(1).getNome());
        verify(repositorioProduto, times(1)).findByCategoria_nome("Eletrônicos");

        System.out.println("Teste testListarProdutosPorCategoria concluído com sucesso.");
    }

    @Test
    void testSalvarProduto() {
        System.out.println("Iniciando teste: testSalvarProduto");

        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");
        System.out.println("Categoria criada: " + categoria.getNome());

        Produto produto = new Produto();
        produto.setNome("Notebook");
        produto.setCategoria(categoria);
        System.out.println("Produto criado: " + produto.getNome());

        when(repositorioProduto.save(produto)).thenReturn(produto);

        Produto resultado = cadastroProduto.salvarProduto(produto);

        System.out.println("Resultado do teste:");
        System.out.println("Produto salvo: " + resultado.getNome());
        System.out.println("Categoria do produto salvo: " + resultado.getCategoria().getNome());

        assertNotNull(resultado);
        assertEquals("Notebook", resultado.getNome());
        assertEquals("Eletrônicos", resultado.getCategoria().getNome());
        verify(repositorioProduto, times(1)).save(produto);

        System.out.println("Teste testSalvarProduto concluído com sucesso.");

    }
}
