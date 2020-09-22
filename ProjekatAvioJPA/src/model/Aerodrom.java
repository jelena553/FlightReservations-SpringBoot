package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the aerodrom database table.
 * 
 */
@Entity
@NamedQuery(name="Aerodrom.findAll", query="SELECT a FROM Aerodrom a")
public class Aerodrom implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAerodrom;

	private String kod;

	private String mjesto;

	private String naziv;

	//bi-directional many-to-one association to Linija
	@OneToMany(mappedBy="aerodrom1")
	private List<Linija> linijas1;

	//bi-directional many-to-one association to Linija
	@OneToMany(mappedBy="aerodrom2")
	private List<Linija> linijas2;

	public Aerodrom() {
	}

	public int getIdAerodrom() {
		return this.idAerodrom;
	}

	public void setIdAerodrom(int idAerodrom) {
		this.idAerodrom = idAerodrom;
	}

	public String getKod() {
		return this.kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getMjesto() {
		return this.mjesto;
	}

	public void setMjesto(String mjesto) {
		this.mjesto = mjesto;
	}

	public String getNaziv() {
		return this.naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Linija> getLinijas1() {
		return this.linijas1;
	}

	public void setLinijas1(List<Linija> linijas1) {
		this.linijas1 = linijas1;
	}

	public Linija addLinijas1(Linija linijas1) {
		getLinijas1().add(linijas1);
		linijas1.setAerodrom1(this);

		return linijas1;
	}

	public Linija removeLinijas1(Linija linijas1) {
		getLinijas1().remove(linijas1);
		linijas1.setAerodrom1(null);

		return linijas1;
	}

	public List<Linija> getLinijas2() {
		return this.linijas2;
	}

	public void setLinijas2(List<Linija> linijas2) {
		this.linijas2 = linijas2;
	}

	public Linija addLinijas2(Linija linijas2) {
		getLinijas2().add(linijas2);
		linijas2.setAerodrom2(this);

		return linijas2;
	}

	public Linija removeLinijas2(Linija linijas2) {
		getLinijas2().remove(linijas2);
		linijas2.setAerodrom2(null);

		return linijas2;
	}

}