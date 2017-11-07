package com.yuri.pontointeligente.api.services;

import java.util.Optional;

import com.yuri.pontointeligente.api.entities.Empresa;

public interface EmpresaService {
	/*
	 * o /** aponta um comentário extraivel para documentação na interface, por
	 * convenção, não declaramos o tipo de método (public. private), porque será
	 * declarado na implementação, para não ficar redundante
	 */

	/**
	 * Retorna uma empresa dado um CNPJ.
	 * 
	 * @param cnpj
	 * @return Optional<Empresa>
	 */
	Optional<Empresa> buscarPorCnpj(String cnpj);

	/**
	 * Cadastra uma nova empresa na base de dados.
	 * 
	 * @param empresa
	 * @return Empresa
	 */
	Empresa persistir(Empresa empresa);

}