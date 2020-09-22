package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the let database table.
 * 
 */
@Entity
@NamedQuery(name="Let.findAll", query="SELECT l FROM Let l")
public class Let implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLet;

	@Temporal(TemporalType.DATE)
	private Date datumPolaska;

	private int popunjenost;

	private int popunjenostBusiness;

	private int popunjenostEconomy;


	//bi-directional many-to-one association to Avion
	@ManyToOne
	private Avion avion;

	//bi-directional many-to-one association to Linija
	@ManyToOne
	private Linija linija;

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="let")
	private List<Rezervacija> rezervacijas;

	public Let() {
	}

	public int getIdLet() {
		return this.idLet;
	}

	public void setIdLet(int idLet) {
		this.idLet = idLet;
	}

	public Date getDatumPolaska() {
		return this.datumPolaska;
	}

	public void setDatumPolaska(Date datumPolaska) {
		this.datumPolaska = datumPolaska;
	}

	public int getPopunjenost() {
		return this.popunjenost;
	}

	public void setPopunjenost(int popunjenost) {
		this.popunjenost = popunjenost;
	}

	public int getPopunjenostBusiness() {
		return this.popunjenostBusiness;
	}

	public void setPopunjenostBusiness(int popunjenostBusiness) {
		this.popunjenostBusiness = popunjenostBusiness;
	}

	public int getPopunjenostEconomy() {
		return this.popunjenostEconomy;
	}

	public void setPopunjenostEconomy(int popunjenostEconomy) {
		this.popunjenostEconomy = popunjenostEconomy;
	}

	

	public Avion getAvion() {
		return this.avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public Linija getLinija() {
		return this.linija;
	}

	public void setLinija(Linija linija) {
		this.linija = linija;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setLet(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setLet(null);

		return rezervacija;
	}

}