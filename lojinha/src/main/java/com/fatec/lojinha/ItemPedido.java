package com.fatec.lojinha;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoItemPedido;
    private int codigoProduto;
    private int quantidade;
    private double valor;
    @JsonIgnore // evita loop infinito no JSON (pedido -> itens -> pedido -> ...)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "codigoPedido", nullable = false)
    private Pedido pedido;
   
    public int getCodigoItemPedido() {
        return codigoItemPedido;
    }
    public void setCodigoItemPedido(int codigoItemPedido) {
        this.codigoItemPedido = codigoItemPedido;
    }
    public int getCodigoProduto() {
        return codigoProduto;
    }
    public void setCodigoProduto(int codigoProduto) {
        this.codigoProduto = codigoProduto;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
   public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}

