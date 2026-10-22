package com.fatec.lojinha;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {

    // Lista todos os pedidos de um cliente, do mais recente para o mais antigo.
    // Obs.: o Hibernate cria a coluna codigoCliente como codigo_cliente no MySQL
    @Query(value = "SELECT * FROM pedido WHERE codigo_cliente = ?1 ORDER BY data_pedido DESC", nativeQuery = true)
    List<Pedido> listarPedidosDoCliente(int codigoCliente);

}
