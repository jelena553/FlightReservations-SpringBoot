package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the avion database table.
 * 
 */
@Entity
@NamedQuery(name="Avion.findAll", query="SELECT a FROM Avion a")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAvion;

	private int brRedovaBusiness;

	private int brRedovaUkupno;

	private int brSjedista;

	private int brSjedistaBusiness;

	private int brSjedistaEconomy;

	private String naziv;

	//bi-directional many-to-one association to Let
	@OneToMany(mappedBy="avion")
	private List<Let> lets;

	//bi-directional many-to-one association to Sjediste
	@OneToMany(mappedBy="avion")
	private List<Sjediste> sjedistes;

	public Avion() {
	}

	public int getIdAvion() {
		return this.idAvion;
	}

	public void setIdAvion(int idAvion) {
		this.idAvion = idAvion;
	}

	public int getBrRedovaBusiness() {
		return this.brRedovaBusiness;
	}

	public void setBrRedovaBusiness(int brRedovaBusiness) {
		this.brRedovaBusiness = brRedovaBusiness;
	}

	public int getBrRedovaUkupno() {
		return this.brRedovaUkupno;
	}

	public void setBrRedovaUkupno(int brRedovaUkupno) {
		this.brRedovaUkupno = brRedovaUkupno;
	}

	public int getBrSjedista() {
		return this.brSjedista;
	}

	public void setBrSjedista(int brSjedista) {
		this.brSjedista = brSjedista;
	}

	public int getBrSjedistaBusiness() {
		return this.brSjedistaBusiness;
	}

	public void setBrSjedistaBusiness(int brSjedistaBusiness) {
		this.brSjedistaBusiness = brSjedistaBusiness;
	}

	public int getBrSjedistaEconomy() {
		return this.brSjedistaEconomy;
	}

	public void setBrSjedistaEconomy(int brSjedistaEconomy) {
		this.brSjedistaEconomy = brSjedistaEconomy;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Let> getLets() {
		return this.lets;
	}

	public void setLets(List<Let> lets) {
		this.lets = lets;
	}

	public Let addLet(Let let) {
		getLets().add(let);
		let.setAvion(this);

		return let;
	}

	public Let removeLet(Let let) {
		getLets().remove(let);
		let.setAvion(null);

		return let;
	}

	public List<Sjediste> getSjedistes() {
		return this.sjedistes;
	}

	public void setSjedistes(List<Sjediste> sjedistes) {
		this.sjedistes = sjedistes;
	}

	public Sjediste addSjediste(Sjediste sjediste) {
		getSjedistes().add(sjediste);
		sjediste.setAvion(this);

		return sjediste;
	}

	public Sjediste removeSjediste(Sjediste sjediste) {
		getSjedistes().remove(sjediste);
		sjediste.setAvion(null);

		return sjediste;
	}

}