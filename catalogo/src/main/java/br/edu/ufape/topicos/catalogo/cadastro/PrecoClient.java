package br.edu.ufape.topicos.catalogo.cadastro;

import br.edu.ufape.topicos.catalogo.controlador.resposta.PrecoDTO;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class PrecoClient {

    @Value("${preco.service.url}") // Injetando o valor da URL a partir das propriedades
    private String precoServiceUrl;

    private final Map<Long, PrecoDTO> CACHE = new HashMap<Long, PrecoDTO>();

    @Autowired
    private RestTemplate restTemplate;

    @CircuitBreaker(name = "produtoPreco", fallbackMethod = "fallbackGetPrecoByProdutoId")
    public PrecoDTO getPrecoByProdutoId(Long produtoId) {
        String url = precoServiceUrl + produtoId;
        var response = restTemplate.getForObject(url, PrecoDTO.class);
        System.out.println("----------------------------------------------------");
        System.out.println("Sucesso ao buscar preço do produto");
        System.out.println("Adicionando no cache");
        System.out.println("----------------------------------------------------");
        CACHE.put(produtoId, response);
        return response;
    }

    public PrecoDTO fallbackGetPrecoByProdutoId(Long produtoId, Throwable throwable) {
        System.out.println("----------------------------------------------------");
        System.out.println("FALLBACK");
        System.out.println("Buscando valor no cache");
        System.out.println("----------------------------------------------------");
        if(CACHE.containsKey(produtoId)){
            return CACHE.get(produtoId);
        }
        throw new ServicoDePrecoIndisponivelException("Serviço de preço indisponível");
    }
}
