package com.projeto.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Icompra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "compra")
	private Compra compra;
	
	@ManyToOne
	@JoinColumn(name = "item")
	private Item item;
	
	@Column(name = "quantidade")
	private int quantidade;
	
	@Column(name = "valor")
	private Double valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Compra getCompra() {
		if(compra == null) {
			compra = new Compra();
		}
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

	public Item getItem() {
		if(item == null) {
			item = new Item();
		}
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
