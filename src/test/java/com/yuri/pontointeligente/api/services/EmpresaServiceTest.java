package com.yuri.pontointeligente.api.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.pontointeligente.api.entities.Empresa;
import com.yuri.pontointeligente.api.repositories.EmpresaRepository;

/*
 * indica ao jUnit qual a classe deve ser invocada para executar os testes.
 * aponta que a classe é teste, e por isso, fornece recursos extras do spring, especificos para teste.
 * aponta qual o profile em que será executado
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class EmpresaServiceTest {
	/*
	 * indica que o objeto é falso. não faz um injeção do objeto em si.
	 */
	@MockBean
	private EmpresaRepository empresaRepository;

	// injeção de dependencia
	@Autowired
	private EmpresaService empresaService;

	private static final String CNPJ = "51463645000100";

	// executa antes do teste
	@Before
	public void setUp() throws Exception {
		/*
		 * testes com o mockito. a classe em questão executa, para cada execução do
		 * método X, retorne Y
		 */
		BDDMockito.given(this.empresaRepository.findByCnpj(Mockito.anyString())).willReturn(new Empresa());
		BDDMockito.given(this.empresaRepository.save(Mockito.any(Empresa.class))).willReturn(new Empresa());
	}

	// marca o método como test para o jUnit
	@Test
	public void testBuscarEmpresaPorCnpj() {
		Optional<Empresa> empresa = this.empresaService.buscarPorCnpj(CNPJ);

		assertTrue(empresa.isPresent());
	}

	// marca o método como test para o jUnit
	@Test
	public void testPersistirEmpresa() {
		Empresa empresa = this.empresaService.persistir(new Empresa());

		assertNotNull(empresa);
	}

}
