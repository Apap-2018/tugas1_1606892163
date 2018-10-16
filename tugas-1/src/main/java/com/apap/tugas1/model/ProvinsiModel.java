package com.apap.tugas1.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * Provinsi Model
 * @author rico.putra
 * @version 16/10/2018
 */
@Entity
@Table(name = "provinsi")
public class ProvinsiModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "presentase_tunjangan", nullable = false)
	private Double persenTunjangan;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Double getPersenTunjangan() {
		return persenTunjangan;
	}

	public void setPersenTunjangan(Double persenTunjangan) {
		this.persenTunjangan = persenTunjangan;
	}
}
