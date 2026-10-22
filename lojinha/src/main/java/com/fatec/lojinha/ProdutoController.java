package com.fatec.lojinha;

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
public class ProdutoController {
    @Autowired
    private ProdutoRepository bd;

    @PostMapping("/api/produto")
    public String gravar(@RequestBody Produto obj){
        bd.save(obj);
        return "Produto gravado com sucesso "+ obj.getCodigo();
    }
    @PutMapping("/api/produto")
    public String alterar(@RequestBody Produto obj){
        if(bd.existsById(obj.getCodigo())){
            bd.save(obj);
            return "Produto alterado com sucesso";
        }  else {
            return "Produto não existe!";
        }
    }
    @GetMapping("/api/produto/{codigo}")
    public Produto carregar(@PathVariable("codigo") int codigo){
        if(bd.existsById(codigo)){
            return bd.findById(codigo).get();
        } else {
            return new Produto();
        }
    }
     @DeleteMapping("/api/produto/{codigo}")
    public String remover(@PathVariable("codigo") int codigo){
        if(bd.existsById(codigo)){
            bd.deleteById(codigo);
            return "Registro removido com sucesso!";
        } else {
            return "registro não existe!";
        }
    }

}
