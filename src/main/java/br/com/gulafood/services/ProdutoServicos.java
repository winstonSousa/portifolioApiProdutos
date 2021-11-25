package br.com.gulafood.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gulafood.Repository.ProdutoRepository;
import br.com.gulafood.exception.ExceptionError;
import br.com.gulafood.model.Produto;

/**
 * 
 * @author winston
 *
 */

@Service
public class ProdutoServicos {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Transactional
	public List<Produto> buscarProdutoPorNomes(String nome) {

		return produtoRepository.findBynome(nome);
	}

	@Transactional
	public Produto buscarProduto(Long id) {

		return produtoRepository.findById(id).orElseThrow(() -> new ExceptionError(id));
	}

	@Transactional
	public Produto salvarProduto(Produto produto) {

		return produtoRepository.save(produto);
	}

	@Transactional
	public void deletarProduto(Long id) {

		try {
			produtoRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ExceptionError(id);
		}

	}

	@Transactional
	public void salvarFoto(Long id, String arquivo) {

		Optional<Produto> produto = produtoRepository.findById(id);
		
		produto.get().setFoto(arquivo);

	}

}














