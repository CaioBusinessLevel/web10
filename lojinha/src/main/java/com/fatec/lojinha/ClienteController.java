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
public class ClienteController {
    @Autowired
    ClienteRepository bd;

    
    @GetMapping("/api/cliente/{codigo}")
    public Cliente retorna(@PathVariable("codigo") int id){
        if(bd.existsById(id)){
            return bd.findById(id).get();
        } else {
            return new Cliente();
        }
    }

    @PostMapping("/api/cliente")
    public String gravar(@RequestBody Cliente obj){
        bd.save(obj);
        return "Cliente gravado com sucesso! "+ obj.getCodigo();
    }
   
    @PutMapping("/api/cliente")
    public String alterar(@RequestBody Cliente obj){
        if(bd.existsById(obj.getCodigo())){
            bd.save(obj);
            return "Cliente alterado com sucesso! "+ obj.getCodigo();
        } else {
            return "Cliente não encontrado!";
        }
    }

    // Login: recebe {"email": "...", "senha": "..."} no corpo da requisição
    @PostMapping("/api/cliente/login")
    public Cliente login(@RequestBody Cliente obj){
        Cliente cliente = bd.fazerLogin(obj.getEmail(), obj.getSenha());
        if(cliente != null){
            return cliente;
        } else {
            return new Cliente(); // codigo = 0 indica login inválido
        }
    }

    // Reenvio de senha: recebe {"email": "..."} no corpo da requisição
    @PostMapping("/api/cliente/reenvio")
    public String reenviarSenha(@RequestBody Cliente obj){
        String senha = bd.buscarSenhaPorEmail(obj.getEmail());
        if(senha != null){
            // Simulação do envio do e-mail (aparece no console do Spring).
            // Para enviar de verdade, dá para usar o JavaMailSender (spring-boot-starter-mail).
            System.out.println("Enviando e-mail para " + obj.getEmail() + " -> sua senha é: " + senha);
            return "Senha reenviada para o e-mail " + obj.getEmail();
        } else {
            return "E-mail não cadastrado!";
        }
    }

    @DeleteMapping("/api/cliente/{codigo}")
    public String apagar(@PathVariable("codigo") int id){
        bd.deleteById(id);
        return "Cliente removido com sucesso!";
    }


}
