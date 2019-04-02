package UFRJ.RecuperacaoInformacao.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModeloBooleano
{
	private static final String REGEX_SEPARAR_TOKENS = "[\\s\\.,!?]";

	private List<String> listaPalavrasFormatadas = new ArrayList<>();

	private Map<String,List<String>> indiceInvertido = new HashMap<>();
	
	public List<String> separarFraseEmListaPalavras(String frase)
	{
		List<String> stopWords = Stream.of("a","o","e","é","de","do","no","são").collect(Collectors.toList());
		List<String> listaPalavras = Arrays.asList(frase.split(REGEX_SEPARAR_TOKENS));
		List<String> saida = new ArrayList<>();
		Boolean deveAdicionar = true;
		for (String palavra : listaPalavras)
		{
			for (String stopWord : stopWords)
			{
				if(palavra.toLowerCase().equals(stopWord.toLowerCase()))
				{
					deveAdicionar = false;
					break;
				}
				deveAdicionar = true;
			}
			if(deveAdicionar && !palavra.equals(""))
				saida.add(palavra.toLowerCase());
		}
		return saida;
	}
	
	public List<String> intersecaoEntreListas(List<String> lista1, List<String> lista2)
	{
		List<String> intersecao = lista1.stream().filter(lista2::contains).sorted().collect(Collectors.toList());	
		return intersecao;
	}

	public List<String> uniaoEntreListas(List<String> lista1, List<String> lista2)
	{
		List<String> uniao = Stream.concat(lista1.stream(), lista2.stream()).distinct().sorted().collect(Collectors.toList());
		return uniao;
	}
	
	public List<List<Integer>> montarMatrizDeIncidencia(List<String> frases)
	{
		List<String> listaPalavras = new ArrayList<>();
		List<List<Integer>> matrizIncidencia = new ArrayList<>();
		List<Integer> fraseContemTermo = new ArrayList<>();
		frases.replaceAll(String::toUpperCase);
		for (String frase : frases)
		{
			listaPalavras.addAll(separarFraseEmListaPalavras(frase));
		}
		listaPalavrasFormatadas = listaPalavras.stream().distinct().collect(Collectors.toList());
		System.out.println(listaPalavrasFormatadas);
		for (String palavra : listaPalavrasFormatadas)
		{
			fraseContemTermo = new ArrayList<>();
			for (String frase : frases)
			{
				fraseContemTermo.add(verificarSeTermoExisteNaFrase(palavra, frase));
			}
			matrizIncidencia.add(fraseContemTermo);
		}
		return matrizIncidencia;
	}
	
	public Integer verificarSeTermoExisteNaFrase(String termo, String frase)
	{
		Integer contagemTermos = 0;
		List<String> listaPalavras = separarFraseEmListaPalavras(frase);
		for (String palavra : listaPalavras)
		{
			if(palavra.equals(termo))
				contagemTermos++;
		}
		return contagemTermos;
	}
	
	public void transformarLinhaMatrizEmIndiceInvertido(List<List<Integer>> matrizIncidencia)
	{
		List<String> listaPostings = new ArrayList<>();
		
		int contagemLinhas = 0;
		int contagemColunas = 1;
		
		for (List<Integer> linha : matrizIncidencia)
		{
			contagemColunas = 1;
			listaPostings = new ArrayList<>();
			for (Integer elemento : linha)
			{
				if(elemento > 0)
				{
					listaPostings.add(String.valueOf(contagemColunas));
				}
				contagemColunas++;
			}
			indiceInvertido.put(listaPalavrasFormatadas.get(contagemLinhas),listaPostings);
			contagemLinhas++;
		}
	}
	
	public List<String> executarConsulta(String consulta)
	{
		List<String> termosConsulta = separarFraseEmListaPalavras(consulta);
		List<String> resultado = new ArrayList<>();
		
		String temp = termosConsulta.get(0);
		String operacao = termosConsulta.get(1);
		String segundoElemento = termosConsulta.get(2);
		
		if(operacao.equals("and"))
		{
			resultado = intersecaoEntreListas(indiceInvertido.get(temp), indiceInvertido.get(segundoElemento));
		}else {
			resultado = uniaoEntreListas(indiceInvertido.get(temp), indiceInvertido.get(segundoElemento));
		}
		
		for(int j=3,i =4; i==termosConsulta.size();i+=2, j+=2)
		{
			operacao = termosConsulta.get(j);
			segundoElemento = termosConsulta.get(i);
			if(operacao.equals("and"))
			{
				resultado = intersecaoEntreListas(resultado, indiceInvertido.get(segundoElemento));
			}else {
				resultado = uniaoEntreListas(indiceInvertido.get(temp), indiceInvertido.get(segundoElemento));
			}
		}
		return resultado;
	}
 
	public Map<String, List<String>> getIndiceInvertido()
	{
		return indiceInvertido;
	}
}