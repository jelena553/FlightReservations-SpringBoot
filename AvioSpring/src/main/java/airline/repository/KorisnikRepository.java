package airline.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Korisnik;

@Repository
@Transactional
public interface KorisnikRepository extends JpaRepository<Korisnik, Integer> {
	
	Korisnik findByKorisnickoIme(String korisnickoIme);

}
