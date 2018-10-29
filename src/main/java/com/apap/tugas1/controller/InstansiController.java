package com.apap.tugas1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class InstansiController {
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private InstansiService instansiService;
	
	/**
	 * Method untuk mengambil daftar instansi berdasarkan provinsinya sebagai bentuk web response
	 * @param idProvinsi : no id provinsi yang dipilih pada form
	 * @return daftar instansi sesuai provinsinya dalam bentuk web response
	 */
	@RequestMapping(value = "/instansi/getByProvinsi", method = RequestMethod.GET)
	@ResponseBody
	private List<InstansiModel> getInstansiByProvinsi(@RequestParam(value = "idProvinsi", required = true) long idProvinsi, Model model) {
		ProvinsiModel provinsi = provinsiService.getById(idProvinsi).get();
		List<InstansiModel> listInstansiByProv = instansiService.getListInstansiByProvinsi(provinsi);
		model.addAttribute("listInstansiByProv", listInstansiByProv);
		
		return listInstansiByProv;
	}
}
