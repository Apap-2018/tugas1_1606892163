package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.repository.JabatanDb;

@Service
public class JabatanServiceImpl implements JabatanService {
	@Autowired
	private JabatanDb jabatanDb;
	
	@Override
	public Optional<JabatanModel> getDetailJabatanById(long id) {
		return jabatanDb.findById(id);
	}
	
	@Override 
	public List<JabatanModel> getAllJabatan() {
		return jabatanDb.findAll();
	}
	
	@Override
	public void addJabatan(JabatanModel jabatan) {
		jabatanDb.save(jabatan);
	}
	
	@Override
	public void updateJabatan(Long idJabatan, JabatanModel jabatanNew) {
		JabatanModel jabatanOld = jabatanDb.findById(idJabatan).get();
		jabatanOld.setNama(jabatanNew.getNama());
		jabatanOld.setDeskripsi(jabatanNew.getDeskripsi());
		jabatanOld.setGajiPokok(jabatanNew.getGajiPokok());
		jabatanDb.save(jabatanOld);
	}
	
	@Override
	public void deleteJabatanById(Long idJabatan) {
		JabatanModel jabatan = jabatanDb.findById(idJabatan).get();
		
		jabatanDb.delete(jabatan);
	}
}
