package br.com.remaxessencia.crm.repo;

import br.com.remaxessencia.crm.domain.Opportunity;
import br.com.remaxessencia.crm.domain.Enums.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OpportunityRepository extends JpaRepository<Opportunity, Long> {
  List<Opportunity> findByStageOrderByPositionAsc(Stage stage);

  @Query("select max(o.position) from Opportunity o where o.stage = :stage")
  Integer findMaxPositionByStage(@Param("stage") Stage stage);
}
