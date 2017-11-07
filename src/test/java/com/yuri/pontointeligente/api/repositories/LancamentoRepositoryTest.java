package com.yuri.pontointeligente.api.repositories;

import static org.junit.Assert.assertEquals;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.yuri.pontointeligente.api.entities.Empresa;
import com.yuri.pontointeligente.api.entities.Funcionario;
import com.yuri.pontointeligente.api.entities.Lancamento;
import com.yuri.pontointeligente.api.enums.PerfilEnum;
import com.yuri.pontointeligente.api.enums.TipoEnum;
import com.yuri.pontointeligente.api.utils.PasswordUtils;

/*
 * indica ao jUnit qual a classe deve ser invocada para executar os testes.
 * aponta que a classe é teste, e por isso, fornece recursos extras do spring, especificos para teste.
 * aponta qual o profile em que será executado
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class LancamentoRepositoryTest {
	/*
	 * utilizado para injeção de dependencias, sem precisar utilizar o import do
	 * package
	 */
	@Autowired
	private LancamentoRepository lancamentoRepository;
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

	private Long funcionarioId;

	// aponta o que será executado antes do teste
	@Before
	public void setUp() throws Exception {
		Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

		Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
		this.funcionarioId = funcionario.getId();

		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
		this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
	}

	// aponta o que será executado após o teste
	@After
	public void tearDown() throws Exception {
		this.empresaRepository.deleteAll();
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarLancamentosPorFuncionarioId() {
		List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);
		// resultado do teste
		assertEquals(2, lancamentos.size());
	}

	// marca o método como teste, para o jUnit
	@Test
	public void testBuscarLancamentosPorFuncionarioIdPaginado() {
		PageRequest page = new PageRequest(0, 10);
		Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, page);
		// resultado do teste
		assertEquals(2, lancamentos.getTotalElements());
	}

	private Lancamento obterDadosLancamentos(Funcionario funcionario) {
		Lancamento lancameto = new Lancamento();
		lancameto.setData(new Date());
		lancameto.setTipo(TipoEnum.INICIO_ALMOCO);
		lancameto.setFuncionario(funcionario);
		return lancameto;
	}

	private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Fulano de Tal");
		funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
		funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
		funcionario.setCpf("24291173474");
		funcionario.setEmail("email@email.com");
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