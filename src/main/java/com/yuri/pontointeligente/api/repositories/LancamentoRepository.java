package com.yuri.pontointeligente.api.repositories;


import java.util.List;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.pontointeligente.api.entities.Lancamento;
/*
 * utilizada para demarcar transações com o banco de dados.
 * é mais comum colocá-las no service do que no DAO ( um service pode consumir varios DAO's)
 * o readonly indica que apenas consultas poderão ser executadas, impede updates, inserts, etc
 */
@Transactional(readOnly = true)
/*
 * é uma consulta JPQL (Java Persistence Query Language) estaticamente definida com seus parâmetros já pré-definidos.
 * utiliza-se o nome da classe.nome do método, por convenção 
 */
@NamedQueries({
		@NamedQuery(name = "LancamentoRepository.findByFuncionarioId", 
				query = "SELECT lanc FROM Lancamento lanc WHERE lanc.funcionario.id = :funcionarioId") })
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	List<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId);

	Page<Lancamento> findByFuncionarioId(@Param("funcionarioId") Long funcionarioId, Pageable pageable);
}
