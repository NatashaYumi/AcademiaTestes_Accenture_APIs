package stepDefinitions;

import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.junit.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DSL;

public class HunterIO_APILeads_stepDefinition extends DSL {

	@Given("que seja enviado todos os campos do request body preenchidos e preencha o campo confident_score com o valor {int}")
	public void que_seja_enviado_todos_os_campos_do_request_body_preenchidos_e_preencha_o_campo_confident_score_com_o_valor(
			Integer int1) {
		setBody("{\r\n    \"email\": \"" + gerarAleatorio(10, "email") + "@hotmail.com\",\r\n    \"first_name\": \""
				+ gerarAleatorio(10, "letras")
				+ "\",\r\n    \"last_name\": \"Bomtorim\",\r\n    \"position\": \"Diretor\",\r\n    \"company\": \"Accenture\",\r\n    \"company_industry\": \"Consultoria\",\r\n    \"company_size\": \"674000 employees\",\r\n    \"confidence_score\": "
				+ int1 + ",\r\n    \"website\": \"Accenture.com\",\r\n    \"custom_attributes\": "
				+ "{\r\n        \"customer_id\": \"cus-647\"\r\n    }\r\n}");
	}

	@When("executar uma requisicao {string}")
	public void executar_uma_requisicao(String string) {
		String URI = DSL.urlBase + DSL.recurso + "?" + DSL.autorizacao;
		if (string.toUpperCase().contains("POST")) {
			DSL.response = given().header("Accept", "application/json").header("Content-Type", "application/json")
					.body(getBody()).when().post(URI).andReturn();
		} else if (string.toUpperCase() == "GET") {
			DSL.response = given().header("Accept", "application/json").header("Content-Type", "application/json")
					.when().get(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao + "&" + getParam())
					.andReturn();
		} else if (string.toUpperCase() == "PUT") {
			DSL.response = given().header("Accept", "application/json").header("Content-Type", "application/json")
					.body(getBody()).when().put(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao)
					.andReturn();
		} else if (string.toUpperCase() == "DELETE") {
			DSL.response = given().header("Accept", "application/json").header("Content-Type", "application/json")
					.when().delete(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao).andReturn();
		} else if (string.toUpperCase() == "PATCH") {
			DSL.response = given().header("Accept", "application/json").header("Content-Type", "application/json")
					.body(getBody()).when().patch(DSL.urlBase + DSL.recurso + "/" + getId() + "?" + DSL.autorizacao)
					.andReturn();
		} else {
			System.out.println("O valor selecionado não é um verbo HTTP valido para o teste!");
			Assert.fail("O valor selecionado não é um verbo HTTP valido para o teste!");
		}
	}

	@Then("espero que o codigo HTTP de retorno seja {int}")
	public void espero_que_o_codigo_http_de_retorno_seja(int int1) {
		try {
			Assert.assertEquals(int1, DSL.response.statusCode());
		} catch (Exception e) {
			Assert.fail("O status code retornado foi diferente do status code esperado: " + int1);
		}
	}

	@And("espero que o tempo de resposta seja menor que {long} segundos")
	public void espero_que_o_tempo_de_resposta_seja_menor_que_segundos(long long1) {
		try {
			DSL.response.then().time(lessThan(long1), TimeUnit.SECONDS);
		} catch (Exception e) {
			Assert.fail("O tempo de resposta foi maior que " + long1 + " segundos");
		}
	}

	@And("espero que a estrutura de response body esteja igual ao documento")
	public void espero_que_a_estrutura_de_response_body_esteja_igual_ao_documento() {
		JSONObject responseBody = new JSONObject(DSL.response.body().asString());
		JSONObject nodeData = responseBody.getJSONObject("data");
		int id = nodeData.getInt("id");
		String campoId = Integer.toString(id);
		Assert.assertTrue(campoId.matches("^\\d+$"));
	}

}
