package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiService {
	Optional<PegawaiModel> getPegawaiDetailByNip(String nip);
	
	void addPegawai(PegawaiModel pegawai);
	List<String> getAllThnMasuk();
	List<PegawaiModel> getPegawaiMudaTuaInstansi(InstansiModel instansi);
	List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi);
	List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan);
	List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi);
	List<PegawaiModel> getPegawaiByThnMasuk(String thnMasuk);
	List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	List<PegawaiModel> getPegawaiByProvinsiAndJabatan(ProvinsiModel provinsi, JabatanModel jabatan);
	List<PegawaiModel> getPegawaiByJabatanAndThnMasuk(JabatanModel jabatan, String thnMasuk);
	List<PegawaiModel> getPegawaiByProvinsiAndThnMasuk(ProvinsiModel provinsi, String thnMasuk);
	List<PegawaiModel> getPegawaiByProvinsiAndJabatanAndThnMasuk(ProvinsiModel provinsi, JabatanModel jabatan, String thnMasuk);
	List<PegawaiModel> getPegawaiByInstansiAndThnMasuk(InstansiModel instansi, String thnMasuk);
	List<PegawaiModel> getPegawaiByInstansiAndJabatanAndThnMasuk(InstansiModel instansi, JabatanModel jabatan, String thnMasuk);
}
