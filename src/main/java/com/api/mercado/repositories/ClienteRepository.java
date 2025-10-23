package com.api.mercado.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;

import com.api.mercado.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

    // @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    Page<Cliente> findAllByAtivoTrue(Pageable pageable);
    
}
