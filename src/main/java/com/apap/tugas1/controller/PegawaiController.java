package com.apap.tugas1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

/**
 * PegawaiController
 * @author rico.putra
 * @version 17/10/18
 */
@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	
	@Autowired
	private InstansiService instansiService;
	
	
	@Autowired
	private ProvinsiService provinsiService;
	
	
	
	@RequestMapping("/")
	private String home(Model model) {
		List<JabatanModel> listJabatanAll = jabatanService.getAllJabatan(); 
		List<InstansiModel> listInstansiAll = instansiService.getAllInstansi();
		model.addAttribute("pageTitle", "Home");
		model.addAttribute("listJabatanAll", listJabatanAll);
		model.addAttribute("listInstansiAll", listInstansiAll);
		return "home";
	}
	
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawaiByNip(@RequestParam(value = "nip", required = true) String nip, Model model) {
		PegawaiModel pegawai = null;
		List<JabatanModel> listJabatan = null;
		
		if (pegawaiService.getPegawaiDetailByNip(nip).isPresent()) {
			pegawai = pegawaiService.getPegawaiDetailByNip(nip).get();
			
			if (pegawai.getJabatan() != null) {
				listJabatan = pegawai.getJabatan();
			}
		}
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listJabatan", listJabatan);
		model.addAttribute("gaji", pegawai.getGaji());
		model.addAttribute("pageTitle", "Detail Pegawai");
		return "view-pegawai";
	}
	
	
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewPegawaiTermudaTertua(@RequestParam(value = "idInstansi", required = true) long idInstansi, Model model) {
		
		InstansiModel instansi = instansiService.getInstansiById(idInstansi).get();
		model.addAttribute("instansi", instansi);
		
		List<PegawaiModel> listPegawaiTermudaTertua = pegawaiService.getPegawaiMudaTuaInstansi(instansi);
		PegawaiModel pegawaiTermuda = listPegawaiTermudaTertua.get(0);
		PegawaiModel pegawaiTertua = listPegawaiTermudaTertua.get(listPegawaiTermudaTertua.size() - 1);
		model.addAttribute("pegawaiTermuda", pegawaiTermuda);
		model.addAttribute("pegawaiTertua", pegawaiTertua);
		
		List<JabatanModel> jabatanPegawaiTermuda = pegawaiTermuda.getJabatan();
		List<JabatanModel> jabatanPegawaiTertua = pegawaiTertua.getJabatan();
		model.addAttribute("jabatanPegawaiTermuda", jabatanPegawaiTermuda);
		model.addAttribute("jabatanPegawaiTertua", jabatanPegawaiTertua);
		
		model.addAttribute("pageTitle", "Detail Pegawai");
		
		return "view-pegawai-mudatua";
	}
	
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String addPegawai(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		pegawai.setJabatan(new ArrayList<JabatanModel>());
		model.addAttribute("pegawai", pegawai);
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		model.addAttribute("listProvinsi", listProvinsi);
		
		List<InstansiModel> listInstansiAll = instansiService.getAllInstansi();
		model.addAttribute("listInstansiAll", listInstansiAll);
		
		List<JabatanModel> listJabatanAll = jabatanService.getAllJabatan();
		model.addAttribute("listJabatanAll", listJabatanAll);
		
		model.addAttribute("pageTitle", "Tambah Pegawai");
		return "add-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST, params={"save"})
	private String addPegawaiSubmit(@ModelAttribute PegawaiModel pegawai, Model model) {
		
		pegawaiService.addPegawai(pegawai);
		model.addAttribute("nip", pegawai.getNip());
		return "pegawai-added";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
	private String cariPegawai(	@RequestParam(value = "idProvinsi", required = false) Optional<Long> idProvinsi,
								@RequestParam(value = "idInstansi", required = false) Optional<Long> idInstansi,
								@RequestParam(value = "idJabatan", required = false) Optional<Long> idJabatan,
								@RequestParam(value = "thnMasuk", required = false) Optional<String> thnMasuk,
								Model model) {
		
		ProvinsiModel provinsi = null;
		InstansiModel instansi = null;
		JabatanModel jabatan = null;
		PegawaiModel pegawai = new PegawaiModel();
		
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		
		List<JabatanModel> listJabatanAll = jabatanService.getAllJabatan();
		
		List<String> listThnMasuk = pegawaiService.getAllThnMasuk();
		
		List<PegawaiModel> hasilPencarian = new ArrayList<PegawaiModel>();
		model.addAttribute("listThnMasuk", listThnMasuk);
		
		if (idProvinsi.isPresent()) {
			provinsi = provinsiService.getById(idProvinsi.get()).get();
			
			if (idInstansi.isPresent()) {
				instansi = instansiService.getInstansiById(idInstansi.get()).get();
				
				if (idJabatan.isPresent()) {
					jabatan = jabatanService.getDetailJabatanById(idJabatan.get()).get();
					
					if (thnMasuk.isPresent()) {
						hasilPencarian = pegawaiService.getPegawaiByInstansiAndJabatanAndThnMasuk(instansi, jabatan, thnMasuk.get());
					}
					else {
						hasilPencarian = pegawaiService.getPegawaiByInstansiAndJabatan(instansi, jabatan);
					}
				}
				else {
					if (thnMasuk.isPresent()) {
						hasilPencarian = pegawaiService.getPegawaiByInstansiAndThnMasuk(instansi, thnMasuk.get());
					}
					else {
						hasilPencarian = pegawaiService.getPegawaiByInstansi(instansi);

					}
				}
			}
			else {
				if (idJabatan.isPresent()) {
					jabatan = jabatanService.getDetailJabatanById(idJabatan.get()).get();
					
					if (thnMasuk.isPresent()) {
						hasilPencarian = pegawaiService.getPegawaiByProvinsiAndJabatanAndThnMasuk(provinsi, jabatan, thnMasuk.get());
					}
					else {
						hasilPencarian = pegawaiService.getPegawaiByProvinsiAndJabatan(provinsi, jabatan);
					}
				}
				else {
					if (thnMasuk.isPresent()) {
						hasilPencarian = pegawaiService.getPegawaiByProvinsiAndThnMasuk(provinsi, thnMasuk.get());
					}
					else {
						hasilPencarian = pegawaiService.getPegawaiByProvinsi(provinsi);

					}
				}
			}
		}
		else {
			if (idJabatan.isPresent()) {
				jabatan = jabatanService.getDetailJabatanById(idJabatan.get()).get();
				
				if (thnMasuk.isPresent()) {
					hasilPencarian = pegawaiService.getPegawaiByJabatanAndThnMasuk(jabatan, thnMasuk.get());
				}
				else {
					hasilPencarian = pegawaiService.getPegawaiByJabatan(jabatan);
				}
			}
			else {
				if (thnMasuk.isPresent()) {
					hasilPencarian = pegawaiService.getPegawaiByThnMasuk(thnMasuk.get());
				}
			}
		}
		
		model.addAttribute("provinsi", provinsi);
		model.addAttribute("instansi", instansi);
		model.addAttribute("jabatan", jabatan);
		
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listJabatanAll", listJabatanAll);
		
		model.addAttribute("hasilPencarian", hasilPencarian);
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("pageTitle", "Cari Pegawai");
		
		return "cari-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/jumlah", method = RequestMethod.GET)
	private String getJumlahPegawaiByJabatan(Model model) {
		List<JabatanModel> listJabatanAll = jabatanService.getAllJabatan();
		
		model.addAttribute("listJabatanAll", listJabatanAll);
		return "view-pegawai-jabatan";
	}
}
