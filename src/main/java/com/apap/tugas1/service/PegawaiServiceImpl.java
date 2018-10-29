package com.apap.tugas1.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.PegawaiDb;

@Service
public class PegawaiServiceImpl implements PegawaiService {
	@Autowired
	private PegawaiDb pegawaiDb;
	
	@Override
	public Optional<PegawaiModel> getPegawaiDetailByNip(String nip) {
		return pegawaiDb.findByNip(nip);
	}
	
	@Override
	public List<PegawaiModel> getPegawaiMudaTuaInstansi(InstansiModel instansi) {
		List<PegawaiModel> listPegawaiMudaTua = pegawaiDb.findByInstansiOrderByTglLahirAsc(instansi);
		return listPegawaiMudaTua;
	}
	
	@Override
	public void addPegawai(PegawaiModel pegawai) {
		pegawaiDb.save(pegawai);
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsi(ProvinsiModel provinsi) {
		List<PegawaiModel> listPegawai = new ArrayList<PegawaiModel>();
		
		for (PegawaiModel pegawai : pegawaiDb.findAll()) {
			if (pegawai.getInstansi().getProvinsi() == provinsi) {
				listPegawai.add(pegawai);
			}
		}
		return listPegawai;
	}

	@Override
	public List<PegawaiModel> getPegawaiByJabatan(JabatanModel jabatan) {
		return pegawaiDb.findByJabatans(jabatan);
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}

	@Override
	public List<PegawaiModel> getPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		return pegawaiDb.findByInstansiAndJabatans(instansi, jabatan);
	}

	@Override
	public List<PegawaiModel> getPegawaiByProvinsiAndJabatan(ProvinsiModel provinsi, JabatanModel jabatan) {
		List<PegawaiModel> listPegawai = new ArrayList<PegawaiModel>();
		
		for (PegawaiModel pegawai : this.getPegawaiByProvinsi(provinsi)) {
			if (pegawai.getJabatan().contains(jabatan)) {
				listPegawai.add(pegawai);
			}
		}
		return listPegawai;
	}
	
	
}
