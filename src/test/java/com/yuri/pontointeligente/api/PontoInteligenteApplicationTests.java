package com.yuri.pontointeligente.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/*
 * indica ao jUnit qual a classe deve ser invocada para executar os testes.
 * aponta que a classe é teste, e por isso, fornece recursos extras do spring, especificos para teste.
 */
@RunWith(SpringRunner.class)
@SpringBootTest

/* é possivel definir o profile de execução na chamada do método */
@ActiveProfiles("test")
public class PontoInteligenteApplicationTests {
	/*
	 * marca o método como teste, para o jUnit. executa todos os testes
	 */

	@Test
	public void contextLoads() {
	}

}
