package com.apap.tugas1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.PegawaiModel;

/**
 * PegawaiDb
 * @author rico.putra
 * @version 17/10/2018
 */
@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	Optional<PegawaiModel> findByNip(String nip);
}
