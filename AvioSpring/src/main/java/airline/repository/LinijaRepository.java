package airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Let;
import model.Linija;

public interface LinijaRepository extends JpaRepository<Linija, Integer> {
	@Query("select l from Linija l where l.odrediste like :odr and l.polaziste like :pol order by l.vrijemePolaska")
	List<Linija> nadjiLinije(@Param("odr")String odr, @Param("pol")String pol);

}
