package br.com.remaxessencia.crm.web;

import br.com.remaxessencia.crm.domain.Opportunity;
import br.com.remaxessencia.crm.domain.Enums.*;
import br.com.remaxessencia.crm.repo.OpportunityRepository;
import br.com.remaxessencia.crm.repo.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/opportunities")
public class OpportunitiesController {

  private final OpportunityRepository opps;
  private final PersonRepository people;

  public OpportunitiesController(OpportunityRepository opps, PersonRepository people) {
    this.opps = opps;
    this.people = people;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("items", opps.findAll(Sort.by(Sort.Direction.DESC, "id")));
    return "opportunities/list";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("opportunity", new Opportunity());
    model.addAttribute("types", OpportunityType.values());
    model.addAttribute("stages", Stage.values());
    model.addAttribute("people", people.findAll(Sort.by(Sort.Direction.ASC, "name")));
    model.addAttribute("selectedPersonId", null);
    return "opportunities/form";
  }

  @PostMapping
  public String create(@Valid @ModelAttribute("opportunity") Opportunity opportunity,
                       BindingResult br,
                       @RequestParam(value = "personId", required = false) Long personId,
                       Model model) {

    if (br.hasErrors()) {
      model.addAttribute("types", OpportunityType.values());
      model.addAttribute("stages", Stage.values());
      model.addAttribute("people", people.findAll(Sort.by(Sort.Direction.ASC, "name")));
      model.addAttribute("selectedPersonId", personId);
      return "opportunities/form";
    }

    if (personId != null) {
      people.findById(personId).ifPresent(opportunity::setPerson);
    } else {
      opportunity.setPerson(null);
    }

    Integer maxPos = opps.findMaxPositionByStage(opportunity.getStage());
    opportunity.setPosition(maxPos == null ? 0 : maxPos + 1);

    if (opportunity.getStatus() == null) {
      opportunity.setStatus(OpportunityStatus.ABERTO);
    }

    opps.save(opportunity);
    return "redirect:/kanban";
  }

  @GetMapping("/{id}/edit")
  public String editForm(@PathVariable Long id, Model model) {
    Opportunity op = opps.findById(id).orElseThrow();
    model.addAttribute("opportunity", op);
    model.addAttribute("types", OpportunityType.values());
    model.addAttribute("stages", Stage.values());
    model.addAttribute("people", people.findAll(Sort.by(Sort.Direction.ASC, "name")));
    model.addAttribute("selectedPersonId", op.getPerson() != null ? op.getPerson().getId() : null);
    return "opportunities/form";
  }

  @PostMapping("/{id}")
  public String update(@PathVariable Long id,
                       @Valid @ModelAttribute("opportunity") Opportunity form,
                       BindingResult br,
                       @RequestParam(value = "personId", required = false) Long personId,
                       Model model) {

    if (br.hasErrors()) {
      model.addAttribute("types", OpportunityType.values());
      model.addAttribute("stages", Stage.values());
      model.addAttribute("people", people.findAll(Sort.by(Sort.Direction.ASC, "name")));
      model.addAttribute("selectedPersonId", personId);
      return "opportunities/form";
    }

    Opportunity op = opps.findById(id).orElseThrow();

    Stage oldStage = op.getStage();
    Stage newStage = form.getStage();
    if (newStage != null && oldStage != newStage) {
      Integer maxPos = opps.findMaxPositionByStage(newStage);
      op.setPosition(maxPos == null ? 0 : maxPos + 1);
    }

    if (personId != null) {
      people.findById(personId).ifPresent(op::setPerson);
    } else {
      op.setPerson(null);
    }

    op.setTitle(form.getTitle());
    op.setType(form.getType());
    op.setStage(form.getStage());
    op.setRegion(form.getRegion());
    op.setEstimatedValue(form.getEstimatedValue());
    op.setNextAction(form.getNextAction());
    op.setNextDate(form.getNextDate());

    opps.save(op);
    return "redirect:/kanban";
  }

  @PostMapping("/{id}/delete")
  public String delete(@PathVariable Long id) {
    opps.deleteById(id);
    return "redirect:/kanban";
  }
}
