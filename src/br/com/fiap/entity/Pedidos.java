package br.com.fiap.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pedido", schema = "vendas")
public class Pedidos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idpedido", unique = true)
	private int id;
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "idcliente") 
	private Clientes cliente;
	@Column(name="data")
	private Date data;
	@Column(name="descricao")
	private String descricao;
	@Column(name="valor")
	private double valor;

	public Pedidos() {
	}

	public Pedidos(Date data, String descricao, double valor, int id, Clientes cliente) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.id = id;
		this.cliente = cliente;
	}

	public Pedidos(Date data, String descricao, double valor, Clientes cliente) {
		super();
		this.data = data;
		this.descricao = descricao;
		this.valor = valor;
		this.cliente = cliente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
}