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

	private List<List<String>> listaFrases = new ArrayList<>();
	
	private List<String> listaPalavrasFormatadas = new ArrayList<>();

	private Map<String,List<String>> indiceInvertido = new HashMap<>();
	
	private int linhas;
	private int colunas;
	
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
	
	public int[][] montarMatrizDeIncidencia(List<String> frases)
	{
		List<String> fraseSeparada = new ArrayList<>();
		List<String> listaPalavras = new ArrayList<>();
		frases.replaceAll(String::toUpperCase);
		for (String frase : frases)
		{
			fraseSeparada = separarFraseEmListaPalavras(frase);
			listaFrases.add(fraseSeparada);
			listaPalavras.addAll(fraseSeparada);
		}
		listaPalavrasFormatadas = listaPalavras.stream().distinct().collect(Collectors.toList());
		
		linhas = listaPalavrasFormatadas.size();
		colunas = frases.size();
		int[][] matriz = new int[linhas][colunas];
		
		for (int i=0;i<linhas;i++)
		{
			for (int j =0; j<colunas;j++)
			{
				matriz[i][j] = verificarSeTermoExisteNaFrase(listaPalavrasFormatadas.get(i), frases.get(j));
			}
		}
		return matriz;
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
	
	public void transformarLinhaMatrizEmIndiceInvertido(int[][] matrizIncidencia)
	{
		List<String> listaPostings = new ArrayList<>();
		
		for (int i=0; i<linhas;i++)
		{
			listaPostings = new ArrayList<>();
			for (int j=0 ; j<colunas;j++)
			{
				if(matrizIncidencia[i][j]> 0)
				{
					listaPostings.add(String.valueOf(j+1));
				}
			}
			indiceInvertido.put(listaPalavrasFormatadas.get(i),listaPostings);
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
		
		for(int j=3,i =4; i<=termosConsulta.size();i+=2, j+=2)
		{
			operacao = termosConsulta.get(j);
			segundoElemento = termosConsulta.get(i);
			if(operacao.equals("and"))
			{
				resultado = intersecaoEntreListas(resultado, indiceInvertido.get(segundoElemento));
			}else {
				resultado = uniaoEntreListas(resultado, indiceInvertido.get(segundoElemento));
			}
		}
		return resultado;
	}
 
	public Map<String, List<String>> getIndiceInvertido()
	{
		return indiceInvertido;
	}

	public int getLinhas()
	{
		return linhas;
	}

	public void setLinhas(int linhas)
	{
		this.linhas = linhas;
	}

	public int getColunas()
	{
		return colunas;
	}

	public void setColunas(int colunas)
	{
		this.colunas = colunas;
	}

	public List<String> getListaPalavrasFormatadas()
	{
		return listaPalavrasFormatadas;
	}

	public void setListaPalavrasFormatadas(List<String> listaPalavrasFormatadas)
	{
		this.listaPalavrasFormatadas = listaPalavrasFormatadas;
	}

	public List<List<String>> getListaFrases()
	{
		return listaFrases;
	}

	public void setListaFrases(List<List<String>> listaFrases)
	{
		this.listaFrases = listaFrases;
	}
}