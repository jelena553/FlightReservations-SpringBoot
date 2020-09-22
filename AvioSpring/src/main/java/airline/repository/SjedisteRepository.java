package airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import model.Sjediste;

public interface SjedisteRepository extends JpaRepository<Sjediste, Integer> {
	
	@Query("select s from Sjediste s inner join s.avion.lets l where l.idLet=:id and s.red <=:brRedova and not exists(select r.sjediste from Rezervacija r where r.sjediste=s and r.let=l) order by s.red")
    public List<Sjediste> slobodnaSjedista(@Param("id")Integer id, @Param("brRedova")Integer brRedova);



@Query("select s from Sjediste s inner join s.avion.lets l where l.idLet=:id and s.red <=:brRedova1 and s.red >:brRedova2  and  not exists(select r.sjediste from Rezervacija r where r.sjediste=s and r.let=l) order by s.red")
public List<Sjediste> slobodnaSjedista(@Param("id")Integer id, @Param("brRedova1")Integer brRedova1, @Param("brRedova2")Integer brRedova2);
}

