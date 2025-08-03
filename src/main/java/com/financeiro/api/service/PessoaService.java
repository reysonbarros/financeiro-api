package com.financeiro.api.service;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.financeiro.api.model.Pessoa;
import com.financeiro.api.repository.PessoaRepository;


@Service
public class PessoaService
 {
	
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		if (ObjectUtils.allNotNull(pessoa.getContatos())) {
			pessoa.getContatos().forEach(c -> c.setPessoa(pessoa));
		}
		return pessoaRepository.save(pessoa);
	}
	
	public Pessoa atualizar(Long codigo, Pessoa pessoa) {
		
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		
		pessoaSalva.getContatos().clear();
		pessoaSalva.getContatos().addAll(pessoa.getContatos());		
		pessoaSalva.getContatos().forEach(c -> c.setPessoa(pessoaSalva));
		
		BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo", "contatos");
		
		return pessoaRepository.save(pessoaSalva);
		
	}	

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Pessoa pessoaSalva = buscarPessoaPeloCodigo(codigo);
		pessoaSalva.setAtivo(ativo);
		pessoaRepository.save(pessoaSalva);
		
	}
	
	public Pessoa buscarPessoaPeloCodigo(Long codigo) {
		
		Pessoa pessoaSalva = pessoaRepository.getReferenceById(codigo);
		
		if(pessoaSalva==null) {			
			throw new EmptyResultDataAccessException(1);
		}
		
		return pessoaSalva;
	}

}
