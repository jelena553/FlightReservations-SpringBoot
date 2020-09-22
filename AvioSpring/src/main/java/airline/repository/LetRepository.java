package airline.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Let;

public interface LetRepository extends JpaRepository<Let, Integer> {
	List<Let> findByDatumPolaska(Date datumPolaska);
	
	/*@Query("select l from Let l where l.datumPolaska like :datumPolaska and (l.avion.brSjedistaBusiness-l.popunjenostBusiness)>0")
	List<Let> pogodniLetoviBusiness(@Param("datumPolaska")Date datumPolaska);
	
	@Query("select l from Let l where l.datumPolaska like :datumPolaska and (l.avion.brSjedistaEconomy-l.popunjenostEconomy)>0")
	List<Let> pogodniLetoviEconomy(@Param("datumPolaska")Date datumPolaska);*/
	
	
	@Query("select l from Let l where l.linija.polaziste like :from and l.linija.odrediste like :to and l.datumPolaska like :datumPolaska and (l.avion.brSjedista-l.popunjenost)>0 order by l.linija.vrijemePolaska")
	List<Let> vratiLetove(@Param("from")String from, @Param("to")String to, @Param("datumPolaska")Date datumPolaska);

}
