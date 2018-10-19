package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	Optional<PegawaiModel> getPegawaiDetailByNip(String nip);
	
	List<PegawaiModel> getPegawaiMudaTuaInstansi(InstansiModel instansi);
	
	void addPegawai(PegawaiModel pegawai);
}
