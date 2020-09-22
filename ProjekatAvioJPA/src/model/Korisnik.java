package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


/**
 * The persistent class for the korisnik database table.
 * 
 */
@Entity
@NamedQuery(name="Korisnik.findAll", query="SELECT k FROM Korisnik k")
public class Korisnik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idKorisnik;

	@Temporal(TemporalType.DATE)
	@Past(message="Datum mora biti u prošlosti!")
	private Date datumRodj;
    
	@NotEmpty(message="Polje ne može biti prazno")
	private String ime;

	private String karticaBroj;

	private String korisnickoIme;
    
	@Size(min=5, max=70, message="Dužina lozinke mora biti barem 5 karaktera" )
	private String lozinka;

	private String pol;

	private String prezime;
	
	@Email(message="Nepravilan format email adrese!")
	private String email;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	//bi-directional many-to-one association to Rezervacija
	@OneToMany(mappedBy="korisnik")
	private List<Rezervacija> rezervacijas;

	//bi-directional many-to-many association to Role
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="korisniks")
	private List<Role> roles;

	public Korisnik() {
	}

	public int getIdKorisnik() {
		return this.idKorisnik;
	}

	public void setIdKorisnik(int idKorisnik) {
		this.idKorisnik = idKorisnik;
	}

	public Date getDatumRodj() {
		return this.datumRodj;
	}

	public void setDatumRodj(Date datumRodj) {
		this.datumRodj = datumRodj;
	}

	public String getIme() {
		return this.ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getKarticaBroj() {
		return this.karticaBroj;
	}

	public void setKarticaBroj(String karticaBroj) {
		this.karticaBroj = karticaBroj;
	}

	public String getKorisnickoIme() {
		return this.korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getLozinka() {
		return this.lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPol() {
		return this.pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getPrezime() {
		return this.prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public List<Rezervacija> getRezervacijas() {
		return this.rezervacijas;
	}

	public void setRezervacijas(List<Rezervacija> rezervacijas) {
		this.rezervacijas = rezervacijas;
	}

	public Rezervacija addRezervacija(Rezervacija rezervacija) {
		getRezervacijas().add(rezervacija);
		rezervacija.setKorisnik(this);

		return rezervacija;
	}

	public Rezervacija removeRezervacija(Rezervacija rezervacija) {
		getRezervacijas().remove(rezervacija);
		rezervacija.setKorisnik(null);

		return rezervacija;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}