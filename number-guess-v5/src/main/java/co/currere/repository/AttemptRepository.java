package co.currere.repository;

import co.currere.domain.Attempt;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AttemptRepository extends CrudRepository<Attempt, Long> {

	List<Attempt> findByUserAliasOrderByIdDesc(String userAlias);

	@Query("SELECT AVG(e.guesses) FROM Attempt e")
	float avg();
}