package com.yuri.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.pontointeligente.api.entities.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
	/*
	 * utilizada para demarcar transações com o banco de dados.
	 * é mais comum colocá-las no service do que no DAO ( um service pode consumir varios DAO's)
	 * o readonly indica que apenas consultas poderão ser executadas, impede updates, inserts, etc
	 */
	@Transactional(readOnly = true)
	Empresa findByCnpj(String cnpj);

}
