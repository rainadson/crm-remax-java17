package br.com.remaxessencia.crm.domain;

public class Enums {
  public enum Category { CLIENTE_ATIVO, RELACIONAMENTO, PARCEIRO, PROPRIETARIO_CAPTACAO }
  public enum Segment { A, B, C }
  public enum Influence { BAIXO, MEDIO, ALTO }
  public enum Potential { TRANSACAO, INDICACAO, PARCERIA, CAPTACAO, INVESTIDOR, POS_VENDA }
  public enum OpportunityType { COMPRA, VENDA, LOCACAO, INVESTIMENTO }
  public enum Stage { NOVO, QUALIFICACAO, ATENDIMENTO, VISITA, PROPOSTA, NEGOCIACAO, CONTRATO, FECHADO, PERDIDO }
  public enum OpportunityStatus { ABERTO, FECHADO, PERDIDO }
}
