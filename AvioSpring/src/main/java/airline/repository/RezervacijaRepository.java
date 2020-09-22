package airline.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Korisnik;
import model.Rezervacija;

public interface RezervacijaRepository extends JpaRepository<Rezervacija, Integer> {
	List<Rezervacija> findByKorisnik(Korisnik k);
	
	@Query("select r from Rezervacija r where r.korisnik.idKorisnik=:id and r.let.datumPolaska<:datum order by r.let.datumPolaska, r.let.linija.vrijemePolaska")
	List<Rezervacija> prosleRezervacije(@Param("id")Integer id, @Param("datum")Date datum);
	
	@Query("select r from Rezervacija r where r.korisnik.idKorisnik=:id and r.let.datumPolaska>=:datum order by r.let.datumPolaska, r.let.linija.vrijemePolaska")
	List<Rezervacija> aktuelneRezervacije(@Param("id")Integer id, @Param("datum")Date datum);
	

}
