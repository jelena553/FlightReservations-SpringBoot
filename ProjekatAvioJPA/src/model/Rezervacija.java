package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the rezervacija database table.
 * 
 */
@Entity
@NamedQuery(name="Rezervacija.findAll", query="SELECT r FROM Rezervacija r")
public class Rezervacija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRezervacija;

	private int cijena;

	@Temporal(TemporalType.DATE)
	private Date datumPlacanja;

	//bi-directional many-to-one association to Korisnik
	@ManyToOne
	private Korisnik korisnik;

	//bi-directional many-to-one association to Let
	@ManyToOne
	private Let let;

	//bi-directional many-to-one association to Sjediste
	@ManyToOne
	private Sjediste sjediste;

	public Rezervacija() {
	}

	public int getIdRezervacija() {
		return this.idRezervacija;
	}

	public void setIdRezervacija(int idRezervacija) {
		this.idRezervacija = idRezervacija;
	}

	public int getCijena() {
		return this.cijena;
	}

	public void setCijena(int cijena) {
		this.cijena = cijena;
	}

	public Date getDatumPlacanja() {
		return this.datumPlacanja;
	}

	public void setDatumPlacanja(Date datumPlacanja) {
		this.datumPlacanja = datumPlacanja;
	}

	public Korisnik getKorisnik() {
		return this.korisnik;
	}

	public void setKorisnik(Korisnik korisnik) {
		this.korisnik = korisnik;
	}

	public Let getLet() {
		return this.let;
	}

	public void setLet(Let let) {
		this.let = let;
	}

	public Sjediste getSjediste() {
		return this.sjediste;
	}

	public void setSjediste(Sjediste sjediste) {
		this.sjediste = sjediste;
	}

}