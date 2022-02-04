package utils;

import java.util.Random;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DSL {

	public String body;
	public String param;
	public Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public static String urlBase = "https://api.hunter.io";
	public static String recurso = "/v2/leads";
	public static String autorizacao = "api_key=f65a6b986bab11d49472018b1a9be983193eafde";
	public static Response response;
	public static RequestSpecification request;

	public static String gerarAleatorio(int tam, String tipo) {
		char[] elementosMisto = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g',
				'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B',
				'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
				'X', 'Y', 'Z' };
		char[] elementoAlfa = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
				'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
				'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
		char[] conteudoGerado = new char[tam];
		int tamanhoArray = elementosMisto.length;
		int tamanhoArrayDominio = elementoAlfa.length;

		Random random = new Random();

		if (tipo == "letras") {
			for (int i = 0; i < tam; i++) {
				conteudoGerado[i] = elementoAlfa[random.nextInt(tamanhoArrayDominio)];
			}
		} else if (tipo == "email") {
			for (int i = 0; i < tam; i++) {
				conteudoGerado[i] = elementosMisto[random.nextInt(tamanhoArray)];
			}
		}

		return new String(conteudoGerado);
	}

}
