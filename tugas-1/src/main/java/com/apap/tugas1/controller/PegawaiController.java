package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.service.PegawaiService;

/**
 * PegawaiController
 * @author rico.putra
 * @version 17/10/18
 */
@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@RequestMapping("/")
	private String home(Model model) {
		model.addAttribute("pageTitle", "Home");
		return "home";
	}
	
	@RequestMapping(value = "/pegawai/view", method = RequestMethod.GET)
	private String viewPegawaiByNip(@RequestParam(value = "pegawaiNip", required = true) String nip, Model model) {
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
}
