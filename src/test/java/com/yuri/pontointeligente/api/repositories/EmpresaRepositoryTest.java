package com.yuri.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.pontointeligente.api.entities.Empresa;

/*
 * indica ao jUnit qual a classe deve ser invocada para executar os testes.
 * aponta que a classe é teste, e por isso, fornece recursos extras do spring, especificos para teste.
 * aponta qual o profile em que será executado
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaRepositoryTest {
	/*
	 * utilizado para injeção de dependencias, sem precisar utilizar o import do
	 * package
	 */
	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String CNPJ = "51463645000100";

	// aponta o que será executado antes do teste
	@Before
	public void setUp() throws Exception {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj(CNPJ);
		this.empresaRepository.save(empresa);
	}

	// aponta o que será executado após o teste
	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarPorCnpj() {
		Empresa empresa = this.empresaRepository.findByCnpj(CNPJ);

		// resultado do teste
		assertEquals(CNPJ, empresa.getCnpj());
	}

}
