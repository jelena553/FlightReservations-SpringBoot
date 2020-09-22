package airline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Aerodrom;

public interface AerodromRepository extends JpaRepository<Aerodrom, Integer> {
	Aerodrom findByKod(String kod);

}
