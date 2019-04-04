package UFRJ.RecuperacaoInformacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import UFRJ.RecuperacaoInformacao.utils.ModeloBooleano;

public class App 
{
	private static ModeloBooleano modeloBooleano = new ModeloBooleano(); 
	
	private static final String FRASE = "O peã e o caval são pec de xadrez. O caval é o melhor do jog.";
	private static final String FRASE_DOIS = "A jog envolv a torr, o peã e o rei.";
	private static final String FRASE_TRES = "O peã lac o boi.";
	private static final String FRASE_QUATRO = "Caval de rodei!";
	private static final String FRASE_CINCO = "Polic o jog no xadrez.";
	
	private static final String CONSULTA_UM = "xadrez and peã and caval and torr";
	private static final String CONSULTA_DOIS = "xadrez or peã or caval or torr";
	
    public static void main( String[] args )
    {
    	List<String> frases = new ArrayList<>();
    	frases.add(FRASE);
       	frases.add(FRASE_DOIS);
       	frases.add(FRASE_TRES);
       	frases.add(FRASE_QUATRO);
       	frases.add(FRASE_CINCO);
       	
       	int[][] saida = modeloBooleano.montarMatrizDeIncidencia(frases);
       	
       	modeloBooleano.transformarLinhaMatrizEmIndiceInvertido(saida);
       	
       	Map<String,List<String>> indiceInvertido = modeloBooleano.getIndiceInvertido();
       	
       	System.out.println("\n Matriz de Incidencia \n");

       	for(int i=0; i<modeloBooleano.getLinhas();i++)
       	{
       		for(int j=0; j<modeloBooleano.getColunas();j++)
       		{
       			System.out.print(saida[i][j] + "\t");
       		}
       		System.out.println();
       	}
       	
       	System.out.println("\n Indice Invertido \n");
       	for (String chave : indiceInvertido.keySet())
		{
			System.out.print(chave + " ");
			indiceInvertido.get(chave).forEach(System.out::print);
			System.out.println();
		}

       	List<String> resultadoConsulta = modeloBooleano.executarConsulta(CONSULTA_UM);
       	List<String> resultadoConsultaDois = modeloBooleano.executarConsulta(CONSULTA_DOIS);

       	System.out.println("resultado da consulta 1(and) :"+resultadoConsulta);
       	System.out.println("resultado da consulta 2(or) :"+resultadoConsultaDois);
    }
}