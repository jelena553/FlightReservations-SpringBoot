package model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the linija database table.
 * 
 */
@Entity
@NamedQuery(name="Linija.findAll", query="SELECT l FROM Linija l")
public class Linija implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idLinija;

	private int cijenaBusiness;

	private int cijenaEconomy;

	private String odrediste;

	private String polaziste;

	private Time vrijemeDolaska;

	private Time vrijemePolaska;

	//bi-directional many-to-one association to Let
	@OneToMany(mappedBy="linija")
	private List<Let> lets;

	//bi-directional many-to-one association to Aerodrom
	@ManyToOne
	@JoinColumn(name="Aerodrom_idAerodrom")
	private Aerodrom aerodrom1;

	//bi-directional many-to-one association to Aerodrom
	@ManyToOne
	@JoinColumn(name="Aerodrom_idAerodrom1")
	private Aerodrom aerodrom2;

	public Linija() {
	}

	public int getIdLinija() {
		return this.idLinija;
	}

	public void setIdLinija(int idLinija) {
		this.idLinija = idLinija;
	}

	public int getCijenaBusiness() {
		return this.cijenaBusiness;
	}

	public void setCijenaBusiness(int cijenaBusiness) {
		this.cijenaBusiness = cijenaBusiness;
	}

	public int getCijenaEconomy() {
		return this.cijenaEconomy;
	}

	public void setCijenaEconomy(int cijenaEconomy) {
		this.cijenaEconomy = cijenaEconomy;
	}

	public String getOdrediste() {
		return this.odrediste;
	}

	public void setOdrediste(String odrediste) {
		this.odrediste = odrediste;
	}

	public String getPolaziste() {
		return this.polaziste;
	}

	public void setPolaziste(String polaziste) {
		this.polaziste = polaziste;
	}

	public Time getVrijemeDolaska() {
		return this.vrijemeDolaska;
	}

	public void setVrijemeDolaska(Time vrijemeDolaska) {
		this.vrijemeDolaska = vrijemeDolaska;
	}

	public Time getVrijemePolaska() {
		return this.vrijemePolaska;
	}

	public void setVrijemePolaska(Time vrijemePolaska) {
		this.vrijemePolaska = vrijemePolaska;
	}

	public List<Let> getLets() {
		return this.lets;
	}

	public void setLets(List<Let> lets) {
		this.lets = lets;
	}

	public Let addLet(Let let) {
		getLets().add(let);
		let.setLinija(this);

		return let;
	}

	public Let removeLet(Let let) {
		getLets().remove(let);
		let.setLinija(null);

		return let;
	}

	public Aerodrom getAerodrom1() {
		return this.aerodrom1;
	}

	public void setAerodrom1(Aerodrom aerodrom1) {
		this.aerodrom1 = aerodrom1;
	}

	public Aerodrom getAerodrom2() {
		return this.aerodrom2;
	}

	public void setAerodrom2(Aerodrom aerodrom2) {
		this.aerodrom2 = aerodrom2;
	}

}