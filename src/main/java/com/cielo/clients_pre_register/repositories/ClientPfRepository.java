package com.cielo.clients_pre_register.repositories;

import com.cielo.clients_pre_register.models.ClientPfModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClientPfRepository extends JpaRepository <ClientPfModel, UUID> {



}
