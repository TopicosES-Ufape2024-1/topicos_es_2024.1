package br.edu.ufape.topicos.catalogo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufape.topicos.catalogo.basica.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {
	Categoria findByNomeIgnoreCase(String nome);

}
