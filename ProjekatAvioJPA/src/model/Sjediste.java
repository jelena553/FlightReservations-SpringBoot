package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sjediste database table.
 * 
 */
@Entity
@NamedQuery(name="Sjediste.findAll", query="SELECT s FROM Sjediste s")
public class Sjediste implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idSjediste;

	private int broj;

	private String klasa;

	private String oznaka;

	private int red;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="sjediste")
	private List<Rezervacija> rezervacijas;

	//bi-directional many-to-one association to Avion
	@ManyToOne
	private Avion avion;

	public Sjediste() {
	}

	public int getIdSjediste() {
		return this.idSjediste;
	}

	public void setIdSjediste(int idSjediste) {
		this.idSjediste = idSjediste;
	}

	public int getBroj() {
		return this.broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getKlasa() {
		return this.klasa;
	}

	public void setKlasa(String klasa) {
		this.klasa = klasa;
	}

	public String getOznaka() {
		return this.oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public int getRed() {
		return this.red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setSjediste(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setSjediste(null);

		return rezervacija;
	}

	public Avion getAvion() {
		return this.avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

}