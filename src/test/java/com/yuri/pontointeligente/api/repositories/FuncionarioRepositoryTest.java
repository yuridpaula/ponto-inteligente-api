package com.yuri.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.security.NoSuchAlgorithmException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.pontointeligente.api.entities.Empresa;
import com.yuri.pontointeligente.api.entities.Funcionario;
import com.yuri.pontointeligente.api.enums.PerfilEnum;
import com.yuri.pontointeligente.api.utils.PasswordUtils;

/*
 * indica ao jUnit qual a classe deve ser invocada para executar os testes.
 * aponta que a classe é teste, e por isso, fornece recursos extras do spring, especificos para teste.
 * aponta qual o profile em que será executado
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class FuncionarioRepositoryTest {
	/*
	 * utilizado para injeção de dependencias, sem precisar utilizar o import do
	 * package
	 */
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	/*
	 * utilizado para injeção de dependencias, sem precisar utilizar o import do
	 * package
	 */
	@Autowired
	private EmpresaRepository empresaRepository;

	private static final String EMAIL = "email@email.com";
	private static final String CPF = "24291173474";

	// aponta o que será executado antes do teste
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());
		this.funcionarioRepository.save(obterDadosFuncionario(empresa));
	}

	// aponta o que será executado após o teste
	@After
	public final void tearDown() {
		this.empresaRepository.deleteAll();
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarFuncionarioPorEmail() {
		Funcionario funcionario = this.funcionarioRepository.findByEmail(EMAIL);
		// resultado do teste
		assertEquals(EMAIL, funcionario.getEmail());
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarFuncionarioPorCpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpf(CPF);
		// resultado do teste
		assertEquals(CPF, funcionario.getCpf());
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarFuncionarioPorEmailECpf() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, EMAIL);
		// resultado do teste
		assertNotNull(funcionario);
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarFuncionarioPorEmailOuCpfParaEmailInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail(CPF, "email@invalido.com");
		// resultado do teste
		assertNotNull(funcionario);
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarFuncionarioPorEmailECpfParaCpfInvalido() {
		Funcionario funcionario = this.funcionarioRepository.findByCpfOrEmail("12345678901", EMAIL);
		// resultado do teste
		assertNotNull(funcionario);
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano de Tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf(CPF);
		funcionario.setEmail(EMAIL);
		funcionario.setEmpresa(empresa);
		return funcionario;
	}

	private Empresa obterDadosEmpresa() {
		Empresa empresa = new Empresa();
		empresa.setRazaoSocial("Empresa de exemplo");
		empresa.setCnpj("51463645000100");
		return empresa;
	}

}
