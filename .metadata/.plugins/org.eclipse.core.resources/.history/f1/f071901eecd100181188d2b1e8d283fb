package com.apap.tugas1.model;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Jabatan Model
 * @author rico.putra
 * @version 16/10/2018
 */
@Entity
@Table(name = "jabatan")
public class JabatanModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "deskripsi", nullable = false)
	private String deskripsi;
	
	@NotNull
	@Column(name = "gaji_pokok", nullable = false)
	private Double gajiPokok;

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

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public Double getGajiPokok() {
		return gajiPokok;
	}

	public void setGajiPokok(Double gajiPokok) {
		this.gajiPokok = gajiPokok;
	}
}
