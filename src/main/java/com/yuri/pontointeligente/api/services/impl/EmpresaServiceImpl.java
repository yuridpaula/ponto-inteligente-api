package com.yuri.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuri.pontointeligente.api.entities.Empresa;
import com.yuri.pontointeligente.api.repositories.EmpresaRepository;
import com.yuri.pontointeligente.api.services.EmpresaService;

//serve para definir uma classe como pertencente à camada de Serviço da aplicação.
@Service
public class EmpresaServiceImpl implements EmpresaService {

	// instancia do logger
	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	// injeção da dependencia, sem usar o import
	@Autowired
	private EmpresaRepository empresaRepository;

	// sobrescreve os métodos da interface, que não foram implementados
	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {
		log.info("Buscando uma empresa para o CNPJ {}", cnpj);
		return Optional.ofNullable(empresaRepository.findByCnpj(cnpj));
	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa: {}", empresa);
		return this.empresaRepository.save(empresa);
	}

}
