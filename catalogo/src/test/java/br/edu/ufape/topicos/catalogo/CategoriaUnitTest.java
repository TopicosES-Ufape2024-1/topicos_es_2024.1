package br.edu.ufape.topicos.catalogo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.edu.ufape.topicos.catalogo.basica.Categoria;
import br.edu.ufape.topicos.catalogo.cadastro.CadastroCategoria;
import br.edu.ufape.topicos.catalogo.repositorio.RepositorioCategoria;
import br.edu.ufape.topicos.catalogo.cadastro.RegistroDuplicadoException;

class CategoriaUnitTest {

    @InjectMocks
    private CadastroCategoria cadastroCategoria;

    @Mock
    private RepositorioCategoria repositorioCategoria;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSalvarCategoriaComNomeUnico() {
        System.out.println("Iniciando teste: testSalvarCategoriaComNomeUnico");
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        when(repositorioCategoria.findByNomeIgnoreCase("Eletrônicos")).thenReturn(null);

        when(repositorioCategoria.save(categoria)).thenReturn(categoria);

        Categoria resultado = cadastroCategoria.salvarCategoria(categoria);
        System.out.println("Categoria salva: " + resultado.getNome());

        assertNotNull(resultado);
        assertEquals("Eletrônicos", resultado.getNome());
        verify(repositorioCategoria, times(1)).save(categoria);
        System.out.println("Teste para Salvar uma Categoria Com Nome Unico concluído com sucesso.");
    }

    @Test
    void testSalvarCategoriaComNomeDuplicado() {
        System.out.println("Iniciando teste: testSalvarCategoriaComNomeDuplicado");
        Categoria categoria = new Categoria();
        categoria.setNome("Eletrônicos");

        when(repositorioCategoria.findByNomeIgnoreCase("Eletrônicos")).thenReturn(categoria);

        RegistroDuplicadoException exception = assertThrows(RegistroDuplicadoException.class, () -> {
            cadastroCategoria.salvarCategoria(categoria);
        });
        System.out.println("Exceção capturada: " + exception.getMessage());

        assertEquals("A categoria [Eletrônicos] já se encontra cadastrada no sistema.", exception.getMessage());
        verify(repositorioCategoria, never()).save(categoria);
        System.out.println("Teste de Salvar uma Categoria Com Nome Duplicado concluído com sucesso.");
    }
}
