package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.service.JabatanService;

/**
 * JabatanController
 * @author rico.putra
 * @version 17/10/2018
 */
@Controller
public class JabatanController {
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/jabatan/view", method = RequestMethod.GET)
	private String viewJabatanById(@RequestParam(value = "idJabatan", required=true) long id, Model model) {
		JabatanModel jabatan = null;
		
		if (jabatanService.getDetailJabatanById(id).isPresent()) {
			jabatan = jabatanService.getDetailJabatanById(id).get();
		}
		
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("pageTitle", "Detail Jabatan");
		return "view-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.GET)
	private String addJabatan(Model model) {
		model.addAttribute("pageTitle", "Tambah Jabatan");
		return "add-jabatan";
	}
	
	@RequestMapping(value = "/jabatan/tambah", method = RequestMethod.POST)
	private String addJabatanSubmit(@ModelAttribute JabatanModel jabatan, Model model) {
		jabatanService.addJabatan(jabatan);
		model.addAttribute("pageTitle", "Tambah Jabatan Sukses");
		return "jabatan-added";
	}
	
	@RequestMapping(value = "/jabatan/ubah", method = RequestMethod.GET)
	private String ubahJabatan(@RequestParam(value = "idJabatan", required = true) long id, Model model) {
		JabatanModel jabatan = null;
		
		if (jabatanService.getDetailJabatanById(id).isPresent()) {
			jabatan = jabatanService.getDetailJabatanById(id).get();
		}
		model.addAttribute("jabatan", jabatan);
		model.addAttribute("pageTitle", "Ubah Jabatan");
		return "ubah-jabatan";
	}	
	
	@RequestMapping(value = "/jabatan/ubah/", method = RequestMethod.POST)
	private String ubahJabatanSubmit(@ModelAttribute JabatanModel jabatanNew, Model model) {
		
		jabatanService.updateJabatan(jabatanNew.getId(), jabatanNew);
		model.addAttribute("pageTitle", "Ubah Jabatan Sukses");
		return "jabatan-changed";
	}
	
	@RequestMapping(value = "/jabatan/hapus", method = RequestMethod.POST)
	private String hapusJabatan(@RequestParam(value = "idJabatan", required = true) long id, Model model) {
		try {
			jabatanService.deleteJabatanById(id);
			model.addAttribute("isTerhapus", "telah");
			model.addAttribute("pageTitle", "Jabatan telah terhapus");
		}
		catch (Exception e) {
			model.addAttribute("isTerhapus", "tidak dapat");
			model.addAttribute("pageTitle", "Jabatan tidak dapat dihapus");
		}
		return "jabatan-deleted";
	}
	
	@RequestMapping(value = "/jabatan/viewall", method = RequestMethod.GET)
	private String viewAllJabatan(Model model) {
		model.addAttribute("listJabatanAll", jabatanService.getAllJabatan());
		model.addAttribute("pageTitle", "Viewall Jabatan");
		return "viewall-jabatan";
	}
}
