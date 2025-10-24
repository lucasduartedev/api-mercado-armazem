package com.api.mercado.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.mercado.models.Funcionario;

@Repository
public interface FuncionarioRepostiroty extends JpaRepository<Funcionario, Long> {

    Page<Funcionario> findAllByAtivoTrue(Pageable pageable);
    
}
