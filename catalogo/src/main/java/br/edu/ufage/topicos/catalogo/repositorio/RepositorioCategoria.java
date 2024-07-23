package br.edu.ufage.topicos.catalogo.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufage.topicos.catalogo.basica.Categoria;

public interface RepositorioCategoria extends JpaRepository<Categoria, Long> {
	Categoria findByNomeIgnoreCase(String nome);

}
