package br.com.remaxessencia.crm.web;

import br.com.remaxessencia.crm.domain.Enums.Stage;
import br.com.remaxessencia.crm.repo.OpportunityRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class KanbanController {

  private final OpportunityRepository opps;

  public KanbanController(OpportunityRepository opps) {
    this.opps = opps;
  }

  @GetMapping("/kanban")
  public String board(Model model) {
    Map<Stage, Object> columns = new LinkedHashMap<>();
    for (Stage s : Stage.values()) {
      columns.put(s, opps.findByStageOrderByPositionAsc(s));
    }
    model.addAttribute("columns", columns);
    model.addAttribute("stages", Stage.values());
    return "kanban/board";
  }

  @PostMapping("/kanban/move")
  public String move(@RequestParam("id") Long id,
                     @RequestParam("stage") Stage stage) {
    var op = opps.findById(id).orElseThrow();
    op.setStage(stage);
    opps.save(op);
    return "redirect:/kanban";
  }
}
