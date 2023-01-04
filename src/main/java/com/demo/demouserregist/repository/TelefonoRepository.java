package com.demo.demouserregist.repository;

import com.demo.demouserregist.model.Phones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TelefonoRepository extends JpaRepository<Phones, Long>, JpaSpecificationExecutor<Phones> {
}
