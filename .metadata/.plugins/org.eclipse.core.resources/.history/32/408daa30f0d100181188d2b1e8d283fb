package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
		
		if (pegawaiService.getPegawaiDetailByNip(nip).isPresent()) {
			pegawai = pegawaiService.getPegawaiDetailByNip(nip).get();
		}
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("pageTitle", "Detail Pegawai");
		return "view-pegawai";
	}
}
