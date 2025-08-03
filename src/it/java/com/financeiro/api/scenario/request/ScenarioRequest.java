package com.financeiro.api.scenario.request;

import com.financeiro.api.model.*;
import com.financeiro.api.repository.PessoaRepository;
import com.financeiro.api.service.PessoaService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class ScenarioRequest {

    private static final String NOME_CONTATO = "Contato1";
    private static final String EMAIL_CONTATO = "teste@email.com";
    private static final String TELEFONE_CONTATO = "(98) 99999999";
    private static final String ENDERECO = "Rua X";
    private static final String NUMERO_ENDERECO = "100";
    private static final String COMPLEMENTO_ENDERECO = "Teste";
    public static final String BAIRRO_ENDERECO = "ABC";
    public static final String CEP_ENDERECO = "70.112-889";
    public static final String CPF_CNPJ_PESSOA = "99967890888";
    public static final String NOME_PESSOA = "Carlos Pessoa";
    
    public static final String NOME_CATEGORIA = "Categoria";
    
    public static final String ANEXO = "Anexo";
    public static final LocalDate DT_PAGAMENTO = LocalDate.of(2024, 07, 10);
    public static final LocalDate DT_VENCIMENTO = LocalDate.of(2024, 07, 20);
    public static final String DESCRICAO = "Descrição do lançamento";
    public static final String OBSERVACAO = "Observação do lancamento";
    public static final String URL_ANEXO = "URL do anexo";
    public static final BigDecimal VALOR = BigDecimal.valueOf(100);
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    
	
    public static class pessoa {

        public static Pessoa requiredFields(){

            Contato co = new Contato();
            co.setNome(NOME_CONTATO);
            co.setEmail(EMAIL_CONTATO);
            co.setTelefone(TELEFONE_CONTATO);

            Cidade c = new Cidade();
            c.setCodigo(10L);

            Endereco e = new Endereco();
            e.setLogradouro(ENDERECO);
            e.setNumero(NUMERO_ENDERECO);
            e.setComplemento(COMPLEMENTO_ENDERECO);
            e.setBairro(BAIRRO_ENDERECO);
            e.setCep(CEP_ENDERECO);
            e.setCidade(c);

            Pessoa pessoa = new Pessoa();
            pessoa.setCpfCnpj(CPF_CNPJ_PESSOA);
            pessoa.setNome(NOME_PESSOA);
            pessoa.setEndereco(e);
            pessoa.setAtivo(true);
            pessoa.setTipo(TipoPessoa.CLIENTE);
            pessoa.setPerfil(PerfilPessoa.PF);
            pessoa.setContatos(List.of(co));

            return pessoa;

        }
        public static Pessoa withPerfilPJ(){

            Pessoa pessoa = requiredFields();
            pessoa.setPerfil(PerfilPessoa.PJ);

            return pessoa;
        }
        public static Pessoa withTipoFornecedor(){

            Pessoa pessoa = requiredFields();
            pessoa.setTipo(TipoPessoa.FORNECEDOR);

            return pessoa;
        }

        public static Pessoa withStatusNaoAtivo(){

            Pessoa pessoa = requiredFields();
            pessoa.setAtivo(false);

            return pessoa;
        }

        public static Pessoa withoutEndereco(){
            Pessoa pessoa = requiredFields();
            pessoa.setEndereco(null);

            return pessoa;
        }

        public static Pessoa withoutContatos(){
            Pessoa pessoa = requiredFields();
            pessoa.setContatos(null);

            return pessoa;
        }

        public static Pessoa withoutCpfCnpj(){
            Pessoa pessoa = requiredFields();
            pessoa.setCpfCnpj(null);

            return pessoa;
        }

        public static Pessoa withoutNome(){
            Pessoa pessoa = requiredFields();
            pessoa.setNome(null);

            return pessoa;
        }

        public static Pessoa withoutPerfil(){

            Pessoa pessoa = requiredFields();
            pessoa.setPerfil(null);

            return pessoa;
        }

        public static Pessoa withoutTipo(){

            Pessoa pessoa = requiredFields();
            pessoa.setTipo(null);

            return pessoa;
        }

        public static Pessoa withoutStatus(){
            Pessoa pessoa = requiredFields();
            pessoa.setAtivo(null);

            return pessoa;
        }

    }
    
    public static class categoria {
    	public static Categoria requiredFields() {
			Categoria categoria = new Categoria();
			categoria.setCodigo(1L);
			categoria.setNome(NOME_CATEGORIA);
			return categoria;
    	}

    	public static Categoria withoutName() {
    		Categoria categoria = new Categoria();
    		categoria.setNome(null);
    		return categoria;
    	}
    }
    
    public static class lancamento {
    	
    	public static Lancamento requiredFields() {
    		Lancamento lancamento = new Lancamento();
    		Pessoa p = pessoa.requiredFields();
            p.setCodigo(1L);
    		Categoria c  = categoria.requiredFields();
            c.setCodigo(1L);
    		lancamento.setPessoa(p);
    		lancamento.setCategoria(c);
    		lancamento.setDataPagamento(DT_PAGAMENTO);
    		lancamento.setDataVencimento(DT_VENCIMENTO);
    		lancamento.setDescricao(DESCRICAO);
    		lancamento.setObservacao(OBSERVACAO);
    		lancamento.setTipo(TipoLancamento.DESPESA);
    		lancamento.setUrlAnexo(URL_ANEXO);
    		lancamento.setValor(VALOR);
    		
    		return lancamento;	
    	}
    	
    	public static Lancamento withTipoReceita() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setTipo(TipoLancamento.RECEITA);
    		
    		return lancamento;
    	}
    	
    	public static Lancamento withoutDescricao() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setDescricao(null);
    		
    		return lancamento;
    	}
    	
    	public static Lancamento withoutValor() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setValor(null);
    		
    		return lancamento;
    	}
    	public static Lancamento withoutDataPagamento() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setDataPagamento(null);
    		
    		return lancamento;
    	}
    	public static Lancamento withoutDataVencimento() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setDataVencimento(null);
    		
    		return lancamento;
    	}
    	public static Lancamento withoutCategoria() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setCategoria(null);
    		
    		return lancamento;
    	}
    	public static Lancamento withoutTipoLancamento() {
    		Lancamento lancamento = requiredFields();
    		lancamento.setTipo(null);
    		
    		return lancamento;
    	}
        public static Lancamento withoutObservacao() {
            Lancamento lancamento = requiredFields();
            lancamento.setObservacao(null);
            
            return lancamento;
        }
        
        public static Lancamento withoutAnexo() {
            Lancamento lancamento = requiredFields();
            lancamento.setAnexo(null);
            
            return lancamento;
        }
        
        public static Lancamento withoutUrlAnexo() {
            Lancamento lancamento = requiredFields();
            lancamento.setUrlAnexo(null);
            
            return lancamento;
        }
        
        public static Lancamento withoutPessoa() {
            Lancamento lancamento = requiredFields();
            lancamento.setPessoa(null);
            
            return lancamento;
        }  
    
    }

}
