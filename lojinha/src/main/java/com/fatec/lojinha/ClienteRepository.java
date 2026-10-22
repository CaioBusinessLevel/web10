package com.fatec.lojinha;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

    // Login: busca o cliente que tenha exatamente esse e-mail e essa senha
    @Query(value = "SELECT * FROM cliente WHERE email = ?1 AND senha = ?2", nativeQuery = true)
    Cliente fazerLogin(String email, String senha);

    // Reenvio de senha: busca a senha cadastrada para o e-mail informado
    @Query(value = "SELECT senha FROM cliente WHERE email = ?1", nativeQuery = true)
    String buscarSenhaPorEmail(String email);

}
