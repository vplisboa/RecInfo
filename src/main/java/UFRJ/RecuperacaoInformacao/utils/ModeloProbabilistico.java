package UFRJ.RecuperacaoInformacao.utils;

public class ModeloProbabilistico
{
	/***
	 * Binary - documentos representados como vetores de termos com valores binarios(incidencia)
	 * Independence - incidencia dos termos no documento, considerada de maneira independente
	 * 
	 * Teorema de Bayes
	 *  P(A|B) = P(B|A) * P(A) / P(B)
	 *  
	 * Ranqueamento:	 
	 * 
	 * 	R - conjunto de documentos relevantes para consulta
	 * 	R_ - conjunto de documentos nao relevantes
	 * 	P(R|dj) - probabilidade de que dj seja relevante para a consulta  q
	 * 	P(R_|dj) - probabilidade de que dj nao seja relevante para a consulta q
	 * 
	 * 	sim(dj,q) = P(R|dj) / P(R_|dj)
	 * 
	 *  Utilizando a regra de bayes;
	 *  	P(dj|R,q) / P(dj|R_,q)
	 *  	sendo P(dj|R,q) a probabilidade de selecionar aleatoriamente o documento dj do conjunto R
	 *  	sendo P(R,q) a probabilidade de que um documento selecionado aleatoriamente a partir de toda
	 *  		a coleçao, seja relevante a R
	 *  	P(dj|R_,q) e P(R_,q) sao analogos e complementares.
	 *  
	 *  
	 *  Tabela de contigencia
	 *  	N - numero de documentos na colecao
	 *  	ni - numero de documentos que contem o termo
	 *  	R - numero total de documentos relevantes para a consulta k
	 *  	ri - numero de documentos relevantes que contem o termo ki
	 *  
	 *  
	 *  Formula para calculo dos pesos dos termos, baseados nos dados da tabela de contigencia
	 *  	wki = log((ri + 0.5)/(R-ri+0.5)  * (N-ni-R+ri+0.5)/ni-ri+0.5 )
	 *      o peso pode ser negativo e superior `a 1.
	 *      
	 *  
	 *  calculo da similaridade
	 *  	somar os pesos dos termos da consulta que aparecem em cada documento.
	 *  
	 *  para utilizar a equacao anterior e necessario saber ri e R, uma possibilidade e assumir ri=R=0, como
	 *  	forma de inicializacao `a equacao de ranqueamento
	 *	
	 *  log(N+0.5 / ni+0.5)
	 */
	public static void BinaryIndependenceModel()
	{
		
	}
	
	/***
	 * 
	 * @param k1 - constante
	 * @param b - constante
	 * @param tamanhoDocumento - tamanho do documento j
	 * @param mediaTamanho - media de tamanho dos documentos
	 * @param fij - frequencia da palavra i no documento j
	 */
	public static Double BM25Model(double k1, double b, double tamanhoDocumento, double mediaTamanho, double fij)
	{
		//b = ( (k1+1) * fij ) / (k1 * [ (1 - b) + b(len(documento)/avglen(documentos))] + fij)
		double bij = ( (k1 + 1) * fij ) / ( k1 * ((1 - b) + b * (tamanhoDocumento/mediaTamanho)) + fij ) ; 
		
		return bij;
	}
	
	private static Double calcularLog2(double n)
	{
		return (Math.log(n) / Math.log(2));	
	}
	
	/***
	 * 
	 * @param N - numero de documentos na colecao
	 * @param ni - numero de documentos que contem o termo
	 * @param bij - 
	 */
	public static double calculaPeso(double N, double ni, double bij)
	{
		double rankingDocumento = 0;
		rankingDocumento = bij * calcularLog2((N-ni+0.5) / ni+0.5);
		
		return rankingDocumento;
	}
	
}
