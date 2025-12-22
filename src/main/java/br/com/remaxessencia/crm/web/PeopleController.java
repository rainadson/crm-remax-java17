package br.com.remaxessencia.crm.web;

import br.com.remaxessencia.crm.domain.Person;
import br.com.remaxessencia.crm.domain.Enums.*;
import br.com.remaxessencia.crm.repo.PersonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/people")
public class PeopleController {

  private final PersonRepository people;

  public PeopleController(PersonRepository people) {
    this.people = people;
  }

  @GetMapping
  public String list(Model model) {
    model.addAttribute("items", people.findAll());
    return "people/list";
  }

  @GetMapping("/new")
  public String createForm(Model model) {
    model.addAttribute("person", new Person());
    model.addAttribute("categories", Category.values());
    model.addAttribute("segments", Segment.values());
    model.addAttribute("influences", Influence.values());
    model.addAttribute("potentials", Potential.values());
    return "people/form";
  }

  @PostMapping
  public String create(@Valid @ModelAttribute("person") Person person,
                       BindingResult br,
                       Model model) {
    if (br.hasErrors()) {
      model.addAttribute("categories", Category.values());
      model.addAttribute("segments", Segment.values());
      model.addAttribute("influences", Influence.values());
      model.addAttribute("potentials", Potential.values());
      return "people/form";
    }
    people.save(person);
    return "redirect:/people";
  }
}
