package com.api.mercado.repositories;

// import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.mercado.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
    
    Page<Produto> findAllByAtivoTrue(Pageable pageable);

    // List<Produto> findAllByDisponibilidadeTrueOrderByNomeAsc();
    
}
