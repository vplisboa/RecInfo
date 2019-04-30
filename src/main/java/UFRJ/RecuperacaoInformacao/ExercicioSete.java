package UFRJ.RecuperacaoInformacao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import UFRJ.RecuperacaoInformacao.utils.ModeloBooleano;
import UFRJ.RecuperacaoInformacao.utils.ModeloProbabilistico;

public class ExercicioSete
{
	private static ModeloBooleano modeloBooleano = new ModeloBooleano();

	private static final double b = 0.75;
	private static final double k1 = 1;

	private static final String FRASE = "O peã e o caval são pec de xadrez. O caval é o melhor do jog.";
	private static final String FRASE_DOIS = "A jog envolv a torr, o peã e o rei.";
	private static final String FRASE_TRES = "O peã lac o boi.";
	private static final String FRASE_QUATRO = "Caval de rodei!";
	private static final String FRASE_CINCO = "Polic o jog no xadrez.";

	private static final String CONSULTA_UM = "xadrez peã caval torr";

	// estimativa do tamanho do documento deve ser realizada pelo somatorio das
	// frequencias dos
	// termos de indexacao presentes nele.

	public static void main(String[] args)
	{
		List<String> frases = new ArrayList<>();
		frases.add(FRASE);
		frases.add(FRASE_DOIS);
		frases.add(FRASE_TRES);
		frases.add
		(FRASE_QUATRO);
		frases.add(FRASE_CINCO);

		int[][] saida = modeloBooleano.montarMatrizDeIncidencia(frases);
       	modeloBooleano.transformarLinhaMatrizEmIndiceInvertido(saida);
		double[] somatorioPesos = new double[modeloBooleano.getColunas()];
		int[] quantidadeVezesTermoAparece = new int[modeloBooleano.getLinhas()];

		double[] tamanhos = calculaTamanhoDocumentos(saida);
		double media = calculaMediaTamanhoDocumentos(tamanhos);
		double[][] bij = new double[modeloBooleano.getLinhas()][modeloBooleano.getColunas()];
		for (int i = 0; i < modeloBooleano.getLinhas(); i++)
		{
			for (int j = 0; j < modeloBooleano.getColunas(); j++)
			{
				if (saida[i][j] != 0)
				{
					quantidadeVezesTermoAparece[i] += 1;
				}
				bij[i][j] = ModeloProbabilistico.BM25Model(k1, b, tamanhos[j], media, saida[i][j]);
				//System.out.print(bij[i][j] + "\t");
				somatorioPesos[j] += bij[i][j];
			}
			//System.out.println();
		}
		
		double[] n = calculaTamanhoDocumentos(saida);
		for(double a : calculaRanking(bij, n))
		{
			System.out.println(a);
		}
	}
	
	public static double[] calculaRanking(double[][] bij, double[] n)
	{
		List<String> termosConsulta = Arrays.asList(CONSULTA_UM.split(" "));
		double[] ranking = new double[modeloBooleano.getColunas()];
		double ni;
		for(int j = 0 ; j<modeloBooleano.getColunas();j++)
		{
			for(int i = 0; i<modeloBooleano.getListaPalavrasFormatadas().size(); i++ )
			{
				if(termosConsulta.contains(modeloBooleano.getListaPalavrasFormatadas().get(i)))
				{
					ni = calculaQuantidadeVezesTermoConsultaApareceNoDocumento(modeloBooleano.getListaPalavrasFormatadas().get(i));
					ranking[j] += ModeloProbabilistico.calculaPeso(n[j], ni, bij[i][j]) ;
				}
			}
		}
		return ranking;
	}
	public static double[] calculaTamanhoDocumentos(int[][] matriz)
	{
		double[] tamanho = new double[modeloBooleano.getColunas()];
		for (int i = 0; i < modeloBooleano.getColunas(); i++)
		{
			for (int j = 0; j < modeloBooleano.getLinhas(); j++)
			{
				tamanho[i] += matriz[j][i];
			}
		}
		return tamanho;
	}

	public static Double calculaMediaTamanhoDocumentos(double[] tamanhos)
	{
		Double somatorioTamanhos = 0.0;

		for (int i = 0; i < tamanhos.length; i++)
		{
			somatorioTamanhos = +tamanhos[i];
		}

		return somatorioTamanhos / tamanhos.length;
	}
	
	public static int calculaQuantidadeVezesTermoConsultaApareceNoDocumento(String termo)
	{
		Map<String,List<String>> indiceInvertido = modeloBooleano.getIndiceInvertido();
		if(indiceInvertido.get(termo) != null)
		{
			return indiceInvertido.get(termo).size();
		}
		return 0;
	}
}
