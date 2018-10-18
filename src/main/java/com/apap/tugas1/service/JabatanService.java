package com.apap.tugas1.service;

import java.util.List;
import java.util.Optional;

import com.apap.tugas1.model.JabatanModel;

public interface JabatanService {
	Optional<JabatanModel> getDetailJabatanById(long id);
	
	List<JabatanModel> getAllJabatan();
	
	void addJabatan(JabatanModel jabatan);
	
	void updateJabatan(Long idJabatan, JabatanModel jabatanNew);
	
	void deleteJabatanById(Long idJabatan);
}
