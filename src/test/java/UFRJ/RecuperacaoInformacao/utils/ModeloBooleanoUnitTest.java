package UFRJ.RecuperacaoInformacao.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ModeloBooleanoUnitTest
{
	private ModeloBooleano modeloBooleano = new ModeloBooleano();
	
	private static final String FRASE = "a aula de recInfo hoje e sobre o modelo booleano";
	
	@Test
	public void testSepararFraseEmListaPalavras_fraseComDezPalavras_deveRetornarListaComDezElementos()
	{
		int tamanhoEsperadoLista = 6;
		List<String> listaPalavras = modeloBooleano.separarFraseEmListaPalavras(FRASE);
		assertEquals(tamanhoEsperadoLista,listaPalavras.size());
	}

	@Test
	public void testIntersecaoEntreListas_listasComDoisElementosEmComum_deveRetornarListaTamanhoDois()
	{
		List<String> lista1 = new ArrayList<>();
		lista1.add("1");
		lista1.add("2");
		lista1.add("3");
		lista1.add("4");
		lista1.add("5");
		
		List<String> lista2 = new ArrayList<>();
		lista2.add("3");
		lista2.add("5");
		
		List<String> intersecao = modeloBooleano.intersecaoEntreListas(lista1, lista2);
		assertEquals(2,intersecao.size());
		assertTrue(intersecao.contains("3"));
		assertTrue(intersecao.contains("5"));
	}
	
	@Test
	public void testUniaoEntreListas_listasComElementosEmComum_deveRetornarListaOrdenadaSemRepetirElementos()
	{
		List<String> lista1 = new ArrayList<>();
		lista1.add("1");
		lista1.add("2");
		lista1.add("4");
		lista1.add("5");
		
		List<String> lista2 = new ArrayList<>();
		lista2.add("3");
		lista2.add("5");
		
		List<String> uniao = modeloBooleano.uniaoEntreListas(lista1, lista2);
		assertEquals(5,uniao.size());
		assertTrue(uniao.contains("1"));
		assertTrue(uniao.contains("2"));
		assertTrue(uniao.contains("3"));
		assertTrue(uniao.contains("4"));
		assertTrue(uniao.contains("5"));
	}
	
	@Test
	public void testVerificarSeTermoExisteNaFrase_fraseContemTermoBuscado_deveRetornarTrue()
	{
		String termo = "aula";
		Integer termoExisteNaFrase = modeloBooleano.verificarSeTermoExisteNaFrase(termo, FRASE);
		
		assertEquals(new Integer(1),termoExisteNaFrase);
	}
	
	@Test
	public void testVerificarSeTermoExisteNaFrase_fraseNaoContemTermoBuscado_deveRetornarFalse()
	{
		String termo = "au";
		Integer termoExisteNaFrase = modeloBooleano.verificarSeTermoExisteNaFrase(termo, FRASE);
		
		assertEquals(new Integer(0),termoExisteNaFrase);
	}
}