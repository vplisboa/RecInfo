package UFRJ.RecuperacaoInformacao.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;

import org.junit.Test;

public class AnaliseLexicaDataUnitTest
{
	private AnaliseLexicaData analiseLexicaData = new AnaliseLexicaData();
	
	@Test
	public void testTransformaDataComTracoEmDataComBarra_dataComTracoSeparandoNumeros_deveSubstituirTracoPorBarra()
	{
		String data = "20-01-1995";
		String dataFormatada = analiseLexicaData.transformaDataComTracoEmDataComBarra(data);
		assertEquals(dataFormatada,"20/01/1995");
	}
	
	@Test
	public void testVerificaSeEhData_dataNoFormatoCertoSeparadaPorTraco_deveRetornarTrue()
	{
		String data = "20-01-1995";
		assertTrue(analiseLexicaData.verificaSeEhData(data));
	}
	
	@Test
	public void testVerificaSeEhData_dataNoFormatoCertoSeparadaPorBarra_deveRetornarTrue()
	{
		String data = "20/01/1995";
		assertTrue(analiseLexicaData.verificaSeEhData(data));
	}
	
	@Test
	public void testVerificarSeparadorData_dataSeparadaPorTraco_deveRetornarTraco()
	{
		String data = "20-01-1995";
		String separador = analiseLexicaData.verificaSeparadorData(data);
		assertEquals(separador,"-");
	}
	
	@Test
	public void testVerificarSeparadorData_dataSeparadaPorBarra_deveRetornarBarra()
	{
		String data = "20/01/1995";
		String separador = analiseLexicaData.verificaSeparadorData(data);
		assertEquals(separador,"/");
	}
	
	@Test
	public void testEncontraDatasNoTexto_textoPossuiUmaData_deveRetornarTextComDataFormatoCerto()
	{
		String texto = "Aula do dia 21-03-2019";
		String textoEsperado = "Aula do dia 21/03/2019";
		String textoObtido = analiseLexicaData.encontraDatasNoTexto(texto);
		
		assertEquals(textoObtido,textoEsperado);
	}
	
	@Test
	public void testEncontraDatasNoTexto_textoPossuiMaisDeUmaData_deveRetornarDataFormatoCerto()
	{
		String texto = "Aula do dia 21-03-2019 e do dia 19-03-2019 foram canceladas, voltando somente no dia 26-03-2019. Prova seria dia 28/03/2019.";
		String textoEsperado = "Aula do dia 21/03/2019 e do dia 19/03/2019 foram canceladas, voltando somente no dia 26/03/2019. Prova seria dia 28/03/2019.";
		String textoObtido = analiseLexicaData.encontraDatasNoTexto(texto);
		
		assertEquals(textoObtido,textoEsperado);
	}
	
	@Test
	public void testConverteMesParaNumero_janeiro_deveRetornarUm() throws ParseException
	{
		String mes = "Janeiro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "01";
		
		assertEquals(mesObtido,mesEsperado);
	}
	
	@Test
	public void testConverteMesParaNumero_Fevereiro_DeveRetornarDois() throws ParseException
	{
		String mes = "Fevereiro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "02";
		
		assertEquals(mesObtido,mesEsperado);
	}
	
	@Test
	public void testConverteMesParaNumero_Marco_deveRetornarTres() throws ParseException
	{
		String mes = "Março";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "03";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Abril_deveRetornarQuatro() throws ParseException
	{
		String mes = "Abril";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "04";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Maio_deveRetornarCinco() throws ParseException
	{
		String mes = "Maio";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "05";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Junho_deveRetornarSeis() throws ParseException
	{
		String mes = "Junho";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "06";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Julho_deveRetornarSete() throws ParseException
	{
		String mes = "Julho";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "07";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Agosto_deveRetornarOito() throws ParseException
	{
		String mes = "Agosto";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "08";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Setembro_deveRetornarNove() throws ParseException
	{
		String mes = "Setembro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "09";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Outubro_deveRetornarDez() throws ParseException
	{
		String mes = "Outubro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "10";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Novembro_deveRetornarOnze() throws ParseException
	{
		String mes = "Novembro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "11";
		
		assertEquals(mesObtido,mesEsperado);
	}
	@Test
	public void testConverteMesParaNumero_Dezembro_deveRetornarDoze() throws ParseException
	{
		String mes = "Dezembro";
		String mesObtido = analiseLexicaData.converteMesParaNumero(mes);
		String mesEsperado = "12";
		
		assertEquals(mesEsperado,mesObtido);
	}
	
	@Test
	public void testRemoveEspacosEmBranco_EspacoEmBrancoEntrePalavras_deveRetornarPalavraSemEspacoEmBrancoEntreElas()
	{
		String palavra = " de Janeiro";
		String resultado = analiseLexicaData.removeEspacosEmBranco(palavra);
		String resultadoEsperado = "deJaneiro";
		
		assertEquals(resultado,resultadoEsperado);
	}
	
	@Test
	public void testRemoveDeDaString_entradaComCaracteresDE_deveRetornarVazio()
	{
		String palavra = " de ";
		String resultado = analiseLexicaData.removeDeDaString(palavra);
		String resultadoEsperado = "";
		
		assertEquals(resultado,resultadoEsperado);
	}
	
	@Test
	public void testRemoveDeDaString_entradaComCaracteresDECaixaAlta_deveRetornarVazio()
	{
		String palavra = " DE ";
		String resultado = analiseLexicaData.removeDeDaString(palavra);
		String resultadoEsperado = "";
		
		assertEquals(resultado,resultadoEsperado);
	}
	
	@Test
	public void testRemoveDeDaString_entradaComCaracteresDECaixaAlta_deveRetornarPalavraSemDE()
	{
		String palavra = " DE Janeiro";
		String resultado = analiseLexicaData.removeDeDaString(palavra);
		String resultadoEsperado = "janeiro";
		
		assertEquals(resultado,resultadoEsperado);
	}
	
	@Test
	public void testTransformaDataPorExtensoEmDataNumerica() throws ParseException
	{
		String dataPorExtenso = "20 de janeiro de 1995";
		String dataEsperada = "20/01/1995";
		String dataObtida = analiseLexicaData.transformaDataPorExtensoEmDataNumerica(dataPorExtenso);
		
		assertEquals(dataEsperada,dataObtida);
	}

	@Test
	public void testTransformaDataPorExtensoEmDataNumerica_MesComLetraMaiuscula() throws ParseException
	{
		String dataPorExtenso = "20 de Janeiro de 1995";
		String dataEsperada = "20/01/1995";
		String dataObtida = analiseLexicaData.transformaDataPorExtensoEmDataNumerica(dataPorExtenso);
		
		assertEquals(dataEsperada,dataObtida);
	}

}
