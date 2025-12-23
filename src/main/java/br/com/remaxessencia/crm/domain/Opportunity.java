package br.com.remaxessencia.crm.domain;

import br.com.remaxessencia.crm.domain.Enums.OpportunityStatus;
import br.com.remaxessencia.crm.domain.Enums.OpportunityType;
import br.com.remaxessencia.crm.domain.Enums.Stage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "opportunities")
public class Opportunity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OpportunityType type = OpportunityType.COMPRA;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Stage stage = Stage.NOVO;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private OpportunityStatus status = OpportunityStatus.ABERTO;

  private String region;

  /**
   * Campo monetário da oportunidade.
   * - No form HTML, normalmente está como "amount"
   * - Em versões anteriores do projeto, pode existir "estimatedValue"
   * Mantemos compatibilidade com ambos via getters/setters abaixo.
   */
  @Column(name = "estimated_value", precision = 14, scale = 2)
  private BigDecimal amount;

  @Column(length = 600)
  private String nextAction;

  private LocalDate nextDate;

  @Column(nullable = false)
  private Integer position = 0;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "person_id")
  private Person person;

  public Opportunity() {}

  // ===== GETTERS / SETTERS =====

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public OpportunityType getType() {
    return type;
  }

  public void setType(OpportunityType type) {
    this.type = type;
  }

  public Stage getStage() {
    return stage;
  }

  public void setStage(Stage stage) {
    this.stage = stage;
  }
 
  public OpportunityStatus getStatus() {
    return status;
  }

  public void setStatus(OpportunityStatus status) {
    this.status = status;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  // ===== Nome "novo"/usado no form: amount =====
  public BigDecimal getAmount() {
    return amount;
  }

  public void setAmount(BigDecimal amount) {
    this.amount = amount;
  }

  // ===== Compatibilidade com código antigo: estimatedValue =====
  public BigDecimal getEstimatedValue() {
    return this.amount;
  }

  public void setEstimatedValue(BigDecimal estimatedValue) {
    this.amount = estimatedValue;
  }

  public String getNextAction() {
    return nextAction;
  }

  public void setNextAction(String nextAction) {
    this.nextAction = nextAction;
  }

  public LocalDate getNextDate() {
    return nextDate;
  }

  public void setNextDate(LocalDate nextDate) {
    this.nextDate = nextDate;
  }

  public Integer getPosition() {
    return position;
  }

  public void setPosition(Integer position) {
    this.position = position;
  }

  public Person getPerson() {
    return person;
  }

  public void setPerson(Person person) {
    this.person = person;
  }
}
