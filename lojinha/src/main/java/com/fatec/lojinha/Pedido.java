package com.fatec.lojinha;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoPedido;
    private double valorTotal;
    private String entrega;
    private String obs;
    private Date dataPedido;
    private int codigoCliente;
   @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, 
        orphanRemoval =  true)
    private List<ItemPedido> itens = new ArrayList<>();

    public int getCodigoPedido() {
        return codigoPedido;
    }
    public void setCodigoPedido(int codigoPedido) {
        this.codigoPedido = codigoPedido;
    }
    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public String getEntrega() {
        return entrega;
    }
    public void setEntrega(String entrega) {
        this.entrega = entrega;
    }
    public String getObs() {
        return obs;
    }
    public void setObs(String obs) {
        this.obs = obs;
    }
    public Date getDataPedido() {
        return dataPedido;
    }
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }
    public int getCodigoCliente() {
        return codigoCliente;
    }
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
  
    public List<ItemPedido> getItens() {
        return itens;
    }
    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

}
