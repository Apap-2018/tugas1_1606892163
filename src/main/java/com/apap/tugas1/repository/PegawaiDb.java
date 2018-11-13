package com.apap.tugas1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;

/**
 * PegawaiDb
 * @author rico.putra
 * @version 17/10/2018
 */
@Repository
public interface PegawaiDb extends JpaRepository<PegawaiModel, Long>{
	
	Optional<PegawaiModel> findByNip(String nip);
	List<PegawaiModel> findByInstansiOrderByTglLahirAsc(InstansiModel instansi);
	List<PegawaiModel> findByJabatans(JabatanModel jabatan);
	List<PegawaiModel> findByInstansi(InstansiModel instansi);
	List<PegawaiModel> findByInstansiAndJabatans(InstansiModel instansi, JabatanModel jabatan);
	List<PegawaiModel> findByThnMasuk(String thnMasuk);
	List<PegawaiModel> findByJabatansAndThnMasuk(JabatanModel jabatan, String thnMasuk);
	List<PegawaiModel> findByInstansiAndThnMasuk(InstansiModel instansi, String thnMasuk);
	List<PegawaiModel> findByInstansiAndJabatansAndThnMasuk(InstansiModel instansi, JabatanModel jabatan, String thnMasuk);
}
