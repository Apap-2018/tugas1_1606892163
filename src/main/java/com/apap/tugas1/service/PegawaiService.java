package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiService {
	Optional<PegawaiModel> getPegawaiDetailByNip(String nip);
	
	List<PegawaiModel> getPegawaiMudaTuaInstansi(InstansiModel instansi);
	
	void addPegawai(PegawaiModel pegawai);
	
	List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi);
	
	List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);
	
	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
	
	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	
	List<PegawaiModel> getPegawaiByProvinsiAndJabatan(ProvinsiModel provinsi, JabatanModel jabatan);
	
	
}
