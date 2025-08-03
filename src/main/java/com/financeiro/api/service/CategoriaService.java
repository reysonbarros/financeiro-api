package com.financeiro.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.financeiro.api.model.Categoria;
import com.financeiro.api.repository.CategoriaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public Categoria atualizar(Long codigo, Categoria categoria) {

		Categoria categoriaSalva = categoriaRepository.getReferenceById(codigo);

		if (categoriaSalva == null) {
			throw new EntityNotFoundException("Codigo da categoria nao encontrado!");
		}

		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");

		return categoriaRepository.save(categoriaSalva);

	}
	
	public List<Categoria> listar(){		
		return  categoriaRepository.findAll(); 
	}
	
	public Categoria criar(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	public Optional<Categoria> buscarPeloCodigo(Long codigo) {
		return categoriaRepository.findById(codigo);
	}
	
	public void remover(Long codigo) {
		categoriaRepository.deleteById(codigo);
	}


}
