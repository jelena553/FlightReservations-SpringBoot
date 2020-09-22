package airline.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Avion;

public interface AvionRepository extends JpaRepository<Avion, Integer> {

}
