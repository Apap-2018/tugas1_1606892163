package com.apap.tugas1.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Model Pegawai
 * @author rico.putra
 * @version 16/10/2018
 */
@Entity
@Table(name = "pegawai")
public class PegawaiModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(name = "NIP", nullable = false, unique = true)
	private String nip;
	
	@NotNull
	@Column(name = "nama", nullable = false)
	private String nama;
	
	@NotNull
	@Column(name = "tempat_lahir", nullable = false)
	private String tmpLahir;
	
	@NotNull
	@Column(name = "tanggal_lahir", nullable = false)
	private Date tglLahir;
	
	@NotNull
	@Column(name = "tahun_masuk", nullable = false)
	private String thnMasuk;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_instansi", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	@JsonIgnore
	private InstansiModel instansi;
	
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
			name = "jabatan_pegawai",
			joinColumns = @JoinColumn(name = "id_pegawai"),
			inverseJoinColumns = @JoinColumn(name = "id_jabatan"))
	private List<JabatanModel> jabatans = new ArrayList<JabatanModel>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTmpLahir() {
		return tmpLahir;
	}

	public void setTmpLahir(String tmpLahir) {
		this.tmpLahir = tmpLahir;
	}

	public Date getTglLahir() {
		return tglLahir;
	}

	public void setTglLahir(Date tglLahir) {
		this.tglLahir = tglLahir;
	}

	public String getThnMasuk() {
		return thnMasuk;
	}

	public void setThnMasuk(String thnMasuk) {
		this.thnMasuk = thnMasuk;
	}

	public InstansiModel getInstansi() {
		return instansi;
	}

	public void setInstansi(InstansiModel instansi) {
		this.instansi = instansi;
	}

	public List<JabatanModel> getJabatan() {
		return jabatans;
	}

	public void setJabatan(List<JabatanModel> jabatan) {
		this.jabatans = jabatan;
	}
	
	/**
	 * Method untuk mendapatkan gaji seorang pegawai.
	 * Constraint : jika seorang pegawai memiliki lebih dari satu jabatan, 
	 * maka gaji pokok yang dihitung adalah gaji pokok yang paling besar.
	 * @return Integer gaji pegawai
	 */
	public Integer getGaji() {
		List<JabatanModel> listJabatan = this.getJabatan();
		Double gajiPokok = 0.0;
		Double gaji = 0.0;
		Double persenTunjangan = 0.0;
		Double gajiHitungTunjangan = 0.0;
		
		for (JabatanModel jabatan : listJabatan) {
			if (jabatan.getGajiPokok() > gajiPokok) {
				gajiPokok = jabatan.getGajiPokok();
				persenTunjangan = this.getInstansi().getProvinsi().getPersenTunjangan();
				gajiHitungTunjangan = (gajiPokok + (persenTunjangan * gajiPokok / 100));
				
				if (gajiHitungTunjangan > gaji) {
					gaji = gajiHitungTunjangan;
				}
			}
		}
		return gaji.intValue();
	}
}
