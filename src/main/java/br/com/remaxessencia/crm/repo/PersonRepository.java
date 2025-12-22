package br.com.remaxessencia.crm.repo;

import br.com.remaxessencia.crm.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
