package com.yuri.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.yuri.pontointeligente.api.entities.Funcionario;
/*
 * utilizada para demarcar transações com o banco de dados.
 * é mais comum colocá-las no service do que no DAO ( um service pode consumir varios DAO's)
 * o readonly indica que apenas consultas poderão ser executadas, impede updates, inserts, etc.
 * a anotação na interface indica que todos os métodos terão os mesmos parametros, todos dentro uma transação só
 */
@Transactional(readOnly = true)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);
	
	Funcionario findByEmail(String email);
	
	Funcionario findByCpfOrEmail(String cpf, String email);
}
