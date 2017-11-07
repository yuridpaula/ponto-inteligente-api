package com.yuri.pontointeligente.api.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.yuri.pontointeligente.api.enums.TipoEnum;

@Entity // aponta que o objeto se trata de uma tabela no BD
@Table(name = "lancamento") // aposta qual tabela
public class Lancamento implements Serializable {
	/*
	 * Define a versão da classe a ser utilizada. mais utilizado com aplicações
	 * distribuidas para diversos clientes, onde cda cliente pode ter uma versão da
	 * classe diferente em produção. o numero gerado não é aleatorio, é um hash dos
	 * atributos e métodos da classe.
	 */
	private static final long serialVersionUID = 6524560251526772839L;

	private Long id;
	private Date data;
	private String descricao;
	private String localizacao;
	private Date dataCriacao;
	private Date dataAtualizacao;
	private TipoEnum tipo;
	private Funcionario funcionario;

	public Lancamento() {
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
	 * utilizado para forçar o hibernate a trabalhar com data e hora. utilizado o
	 * name quando não podemos deixar o nome do campo devido a caracteres especiais
	 * a instrução nullable define se o campo pode ser nulo ou não, nesse caso, não
	 * pode
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false)
	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, pode
	 */
	@Column(name = "descricao", nullable = true)
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/*
	 * utilizado o name quando não podemos deixar o nome do campo devido a
	 * caracteres especiais a instrução nullable define se o campo pode ser nulo ou
	 * não, nesse caso, pode
	 */
	@Column(name = "localizacao", nullable = true)
	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
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
	 * anotação para gravar valores do enum, que pode ser a posição ordinal,
	 * começando por 0, ou a descrição do mesmo. no exemplo abaixo, o type.STRING
	 * indica que será persistido a descrição do enum. utilizado o name quando não
	 * podemos deixar o nome do campo devido a caracteres especiais a instrução
	 * nullable define se o campo pode ser nulo ou não, nesse caso, não pode
	 */
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo", nullable = false)
	public TipoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoEnum tipo) {
		this.tipo = tipo;
	}

	/*
	 * Mapeamento de muitos para um. o proprio hibernate consegue mapear os
	 * relacionamentos do banco atravez de anotações. Nesse caso, um relacionamento
	 * de muitos para um. O fetch EAGER, indica que ao carregar o objeto principal,
	 * ira carregar o relacionamento.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	// executa o método automaticamente antes de atualizar
	@PreUpdate
	public void preUpdate() {
		dataAtualizacao = new Date();
	}

	// executa o metodo automaticamente antes de salvar (persistir)
	@PrePersist
	public void prePersist() {
		final Date atual = new Date();
		dataCriacao = atual;
		dataAtualizacao = atual;
	}

	@Override
	public String toString() {
		return "Lancamento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", localizacao=" + localizacao
				+ ", dataCriacao=" + dataCriacao + ", dataAtualizacao=" + dataAtualizacao + ", tipo=" + tipo
				+ ", funcionario=" + funcionario + "]";
	}

}
