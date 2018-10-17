package com.apap.tugas1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
