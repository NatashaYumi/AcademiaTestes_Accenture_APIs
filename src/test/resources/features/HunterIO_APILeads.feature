@Leads
Feature: HunterIO_APILeads
  API com um CRUD para leads da Hunter.io

  @Ct01
  Scenario Template: Requisicao de criacao de leads com todos os campos preenchidos
    Given que seja enviado todos os campos do request body preenchidos e preencha o campo confident_score com o valor <confidentScore>
    When executar uma requisicao <Metodo>
    Then espero que o codigo HTTP de retorno seja <StatusCode>
    And espero que o tempo de resposta seja menor que <Segundos> segundos
		And espero que a estrutura de response body esteja igual ao documento
  
  Examples:
  |confidentScore|Metodo|StatusCode|Segundos|
  |0						 |"POST"	|201			 |5				|