package com.fatec.lojinha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class PedidoController {
    @Autowired
    PedidoRepository bd ;

     
    @GetMapping("/api/pedido/{codigo}")
    public Pedido retorna(@PathVariable("codigo") int id){
        if(bd.existsById(id)){
            return bd.findById(id).get();
        } else {
            return new Pedido();
        }
    }

    // Lista de pedidos de um determinado cliente
    @GetMapping("/api/pedido/cliente/{codigoCliente}")
    public List<Pedido> listarPorCliente(@PathVariable("codigoCliente") int codigoCliente){
        return bd.listarPedidosDoCliente(codigoCliente);
    }

    @PostMapping("/api/pedido")
    public String gravar(@RequestBody Pedido obj){
        // liga cada item ao pedido (senão a coluna codigoPedido do item fica nula)
        for(ItemPedido item : obj.getItens()){
            item.setPedido(obj);
        }
        bd.save(obj);
        return "Cliente gravado com sucesso! "+ obj.getCodigoPedido();
    }
   
    @PutMapping("/api/pedido")
    public String alterar(@RequestBody Pedido obj){
        if(bd.existsById(obj.getCodigoPedido())){
            bd.save(obj);
            return "PEdido alterado com sucesso! "+ obj.getCodigoPedido();
        } else {
            return "Pedido não encontrado!";
        }
    }

    @DeleteMapping("/api/pedido/{codigo}")
    public String apagar(@PathVariable("codigo") int id){
        bd.deleteById(id);
        return "Pedido removido com sucesso!";
    }
}
