package br.com.remaxessencia.crm.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import static br.com.remaxessencia.crm.domain.Enums.*;

@Entity
@Table(name = "people")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false, length = 120)
  private String name;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, length = 40)
  private Category category;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "varchar(1)")
  private Segment segment;

  @Enumerated(EnumType.STRING)
  @Column(length = 10)
  private Influence influence;

  @Enumerated(EnumType.STRING)
  @Column(length = 30)
  private Potential potential;

  @Column(length = 30)
  private String whatsapp;

  @Column(length = 120)
  private String email;

  @Column(length = 80)
  private String city;

  @Column(length = 120)
  private String region;

  private LocalDate lastContact;
  private LocalTime lastContactTime;

  private LocalDate nextContact;
  private LocalTime nextContactTime;
  @Column(columnDefinition = "text")
  private String notes;

  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getName() { return name; }
  public void setName(String name) { this.name = name; }

  public Category getCategory() { return category; }
  public void setCategory(Category category) { this.category = category; }

  public Segment getSegment() { return segment; }
  public void setSegment(Segment segment) { this.segment = segment; }

  public Influence getInfluence() { return influence; }
  public void setInfluence(Influence influence) { this.influence = influence; }

  public Potential getPotential() { return potential; }
  public void setPotential(Potential potential) { this.potential = potential; }

  public String getWhatsapp() { return whatsapp; }
  public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }

  public String getEmail() { return email; }
  public void setEmail(String email) { this.email = email; }

  public String getCity() { return city; }
  public void setCity(String city) { this.city = city; }

  public String getRegion() { return region; }
  public void setRegion(String region) { this.region = region; }

  public LocalDate getLastContact() { return lastContact; }
  public void setLastContact(LocalDate lastContact) { this.lastContact = lastContact; }

  public LocalTime getLastContactTime() { return lastContactTime; }
  public void setLastContactTime(LocalTime lastContactTime) { this.lastContactTime = lastContactTime; }

  public LocalDate getNextContact() { return nextContact; }
  public void setNextContact(LocalDate nextContact) { this.nextContact = nextContact; }

  public LocalTime getNextContactTime() { return nextContactTime; }
  public void setNextContactTime(LocalTime nextContactTime) { this.nextContactTime = nextContactTime; }

  public String getNotes() { return notes; }
  public void setNotes(String notes) { this.notes = notes; }
}
