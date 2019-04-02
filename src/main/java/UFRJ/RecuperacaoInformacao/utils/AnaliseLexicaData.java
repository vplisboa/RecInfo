package UFRJ.RecuperacaoInformacao.utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnaliseLexicaData
{
	private static final String FORMATO_PADRAO_DATA = "\\d{2}.\\d{2}.\\d{4}";

	private	Map<String,String> mapaMeses;
	
	public AnaliseLexicaData()
	{
		mapaMeses = new HashMap<>();
		
		mapaMeses.put("janeiro","01");
		mapaMeses.put("fevereiro","02");
		mapaMeses.put("março","03");
		mapaMeses.put("abril","04");
		mapaMeses.put("maio","05");
		mapaMeses.put("junho","06");
		mapaMeses.put("julho","07");
		mapaMeses.put("agosto","08");
		mapaMeses.put("setembro","09");
		mapaMeses.put("outubro","10");
		mapaMeses.put("novembro","11");
		mapaMeses.put("dezembro","12");
	}
	
	public String transformaDataComTracoEmDataComBarra(String dataASerFormatada)
	{
		return dataASerFormatada.replace("-", "/");
	}
	
	public String removeDeDaString(String palavra)
	{
		palavra = removeEspacosEmBranco(palavra);
		return palavra.trim().toLowerCase().replace("de", "");
	}
	
	public String removeEspacosEmBranco(String palavra)
	{
		return palavra.replaceAll("\\s","");
	}
	
	public Boolean verificaSeEhData(String data)
	{
		boolean dataValida = Pattern.matches(FORMATO_PADRAO_DATA,data);
		return dataValida;
	}
	
	public String verificaSeparadorData(String data)
	{
		boolean ehTraco = data.contains("/");
		if(ehTraco)
		{
			return "/";
		}else {
			return "-";
		}
	}
	
	public String encontraDatasNoTexto(String texto)
	{
		String dataASerFormatada = "";
		String separador = "";
		String dataFormatada = "";
		
		Pattern pattern = Pattern.compile(FORMATO_PADRAO_DATA);
		Matcher matcher = pattern.matcher(texto);
		while(matcher.find())
		{
			dataASerFormatada = texto.substring(matcher.start(), matcher.end());
			separador = verificaSeparadorData(dataASerFormatada);
			if(!separador.equals("/"))
			{
				dataFormatada = transformaDataComTracoEmDataComBarra(dataASerFormatada);
				texto = texto.replace(dataASerFormatada, dataFormatada);
			}
		}
		return texto;
	}
	
	public String transformaDataPorExtensoEmDataNumerica(String dataPorExtenso) throws ParseException
	{
		String dataFormatada = "";
		dataPorExtenso = removeEspacosEmBranco(dataPorExtenso.toLowerCase());
		Set<String> meses = mapaMeses.keySet();
		for (String mes : meses)
		{
			if(dataPorExtenso.contains(mes))
			{
				dataFormatada = dataPorExtenso.replace(mes, "/"+mapaMeses.get(mes).toString()+"/");
				dataFormatada = removeDeDaString(dataFormatada);
				return dataFormatada;
			}
		}
		
		return dataFormatada;
	}
	
	public String converteMesParaNumero(String mes) throws ParseException
	{
		return mapaMeses.get(mes.toLowerCase());
	}
}


