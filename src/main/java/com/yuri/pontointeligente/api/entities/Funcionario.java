package com.yuri.pontointeligente.api.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.yuri.pontointeligente.api.enums.PerfilEnum;

@Entity // aponta que o objeto se trata de uma tabela no BD
@Table(name = "funcionario") // aposta qual tabela
public class Funcionario implements Serializable {

	/*
	 * Define a versão da classe a ser utilizada. mais utilizado com aplicações
	 * distribuidas para diversos clientes, onde cda cliente pode ter uma versão da
	 * classe diferente em produção. o numero gerado não é aleatorio, é um hash dos
	 * atributos e métodos da classe.
	 */
	private static final long serialVersionUID = -5754246207015712518L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private String cpf;
	private BigDecimal valorHora;
	private Float qtdHorasTrabalhoDia;
	private Float qtdHorasAlmoco;
	private PerfilEnum perfil;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private Empresa empresa;
	// Lista para montar o relacionamento
	private List<Lancamento> lancamentos;

	public Funcionario() {
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
	@Column(name = "nome", nullable = false)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "email", nullable = false)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "cpf", nullable = false)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, pode
	 */
	@Column(name = "valor_hora", nullable = true)
	public BigDecimal getValorHora() {
		return valorHora;
	}

	/*
	 * operação utilizada apenas pela aplicação, não envolve o banco de dados. é
	 * anotado para não forçar um lock do banco, melhorando a performance.
	 */
	@Transient
	public Optional<BigDecimal> getValorHoraOpt() {
		return Optional.ofNullable(valorHora);
	}

	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, pode
	 */
	@Column(name = "qtd_horas_trabalho_dia", nullable = true)
	public Float getQtdHorasTrabalhoDia() {
		return qtdHorasTrabalhoDia;
	}

	/*
	 * operação utilizada apenas pela aplicação, não envolve o banco de dados é
	 * anotado para não forçar um lock do banco, melhorando a performance.
	 */
	@Transient
	public Optional<Float> getQtdHorasTrabalhoDiaOpt() {
		return Optional.ofNullable(qtdHorasTrabalhoDia);
	}

	public void setQtdHorasTrabalhoDia(Float qtdHorasTrabalhoDia) {
		this.qtdHorasTrabalhoDia = qtdHorasTrabalhoDia;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, pode
	 */
	@Column(name = "qtd_horas_almoco", nullable = true)
	public Float getQtdHorasAlmoco() {
		return qtdHorasAlmoco;
	}

	/*
	 * operação utilizada apenas pela aplicação, não envolve o banco de dados
	 */
	@Transient
	public Optional<Float> getQtdHorasAlmocoOpt() {
		return Optional.ofNullable(qtdHorasAlmoco);
	}

	public void setQtdHorasAlmoco(Float qtdHorasAlmoco) {
		this.qtdHorasAlmoco = qtdHorasAlmoco;
	}

	/*
	 * anotação para gravar valores do enum, que pode ser a posição ordinal,
	 * começando por 0, ou a descrição do mesmo. no exemplo abaixo, o type.STRING
	 * indica que será persistido a descrição do enum. utilizado o name quando não
	 * podemos deixar o nome do campo devido a caracteres especiais a instrução
	 * nullable define se o campo pode ser nulo ou não, nesse caso, não pode
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "perfil", nullable = false)
	public PerfilEnum getPerfil() {
		return perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
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
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, não pode
	 */
	@Column(name = "senha", nullable = false)
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	/*
	 * Mapeamento de muitos para um. o proprio hibernate consegue mapear os
	 * relacionamentos do banco atravez de anotações. Nesse caso, um relacionamento
	 * de muitos para um. O fetch EAGER, indica que ao carregar o objeto principal,
	 * ira carregar o relacionamento.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	/*
	 * Mapeamento de um pra muitos. o proprio hibernate consegue mapear os
	 * relacionamentos do banco atravez de anotações. Nesse caso, um relacionamento
	 * de um pra muitos. O fetch LAZY, indica que ao carregar o objeto principal,
	 * não ira carregar o relacionamento. O cascade ALL indica que ao apagar o
	 * objeto principal, irá apagar todos seus dependentes.
	 */
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}

	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	// executa o metodo autoamaticamente antes de atualizar
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	// executa o método automaticamente antes de salvar (persistir)
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + ", cpf=" + cpf
				+ ", valorHora=" + valorHora + ", qtdHorasTrabalhoDia=" + qtdHorasTrabalhoDia + ", qtdHorasAlmoco="
				+ qtdHorasAlmoco + ", perfil=" + perfil + ", dataCriacao=" + dataCriacao + ", dataAtualizacao="
				+ dataAtualizacao + ", empresa=" + empresa + "]";
	}

}