package br.edu.ufage.topicos.catalogo.cadastro;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufage.topicos.catalogo.basica.Categoria;
import br.edu.ufage.topicos.catalogo.repositorio.RepositorioCategoria;

@Service
public class CadastroCategoria implements InterfaceCadastroCategoria {
	@Autowired
	private RepositorioCategoria repositorioCategoria ;

	@Override
	public Categoria salvarCategoria(Categoria entity) {
		if(repositorioCategoria.findByNomeIgnoreCase(entity.getNome()) == null) {
			return repositorioCategoria.save(entity);	
		} else {
			throw new RegistroDuplicadoException("A categoria ["+ entity.getNome() + "] já se encontra cadastrada no sistema.");
		}
	}

	@Override
	public List<Categoria> listarCategorias() {
		return repositorioCategoria.findAll();
	}

	@Override
	public void apagarCategoria(Long id) {
		repositorioCategoria.deleteById(id);
	}

	@Override
	public void apagarCategoria(Categoria entity) {
		repositorioCategoria.delete(entity);
	}

	@Override
	public Categoria encontrarCategoria(Long id) {
		Optional<Categoria> optional = repositorioCategoria.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		} else {
			throw new ObjetoNaoEncontradoException("Não existe categoria com o id: " + id);
		}
	}
	
}
