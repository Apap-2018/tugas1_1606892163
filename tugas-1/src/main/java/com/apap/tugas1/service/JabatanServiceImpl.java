package com.apap.tugas1.service;

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
}
