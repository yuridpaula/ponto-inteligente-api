package com.yuri.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

@Entity // aponta que o objeto se trata de uma tabela no BD
@Table(name = "empresa") // aposta qual tabela
public class Empresa implements Serializable {
	/*
	 * Define a versão da classe a ser utilizada. mais utilizado com aplicações
	 * distribuidas para diversos clientes, onde cda cliente pode ter uma versão da
	 * classe diferente em produção. o numero gerado não é aleatorio, é um hash dos
	 * atributos e métodos da classe.
	 */
	private static final long serialVersionUID = 3960436649365666213L;

	private Long id;
	private String razaoSocial;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;
	// Lista para montar o relacionamento do banco
	private List<Funcionario> funcionarios;

	public Empresa() {
	}

	@Id // marca o identificador
	@GeneratedValue(strategy = GenerationType.AUTO) // define como auto incremento
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "razao_social", nullable = false)
	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "cnpj", nullable = false)
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "data_criacao", nullable = false)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "data_atualizacao", nullable = false)
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	/*
	 * Mapeamento de um pra muitos. o proprio hibernate consegue mapear os
	 * relacionamentos do banco atravez de anotações. Nesse caso, um relacionamento
	 * de um pra muitos. O fetch LAZY, indica que ao carregar o objeto principal,
	 * não ira carregar o relacionamento. O cascade ALL indica que ao apagar o
	 * objeto principal, irá apagar todos seus dependentes.
	 */
	@OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	// executa o método automaticamente, antes de atualizar
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	// executa o método automaticamente antes de salvar(persistir)
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", dataCriacao=" + dataCriacao
				+ ", dataAtualizacao=" + dataAtualizacao + "]";
	}
}
