package UFRJ.RecuperacaoInformacao.ponderacaoTermos;

public class PonderacaoTFIDF
{

	public static Double terceiroEsquemaPonderacao()
	{
		Double peso = calculaPesoParaTermosDocumento(2,2,2);
		return peso;
	}
	/***
	 * 
	 * @param fij - frequencia do termo no documento(quantas vezes apareceu / total de palavras nele)
	 * @param N - numero total de documentos
	 * @param ni - quantidade de documentos em que o termo aparece
	 * @return
	 */
	public static Double calculaPesoParaTermosDocumento(int fij, int N, int ni)
	{
		int termo2 = N/ni;
		
		Double peso = (1.0 + calcularLog2(fij)) * calcularLog2(termo2);
		return peso;
	}
	
	/***
	 * 
	 * @param fiq - frequencia do termo no documento(quantas vezes apareceu / total de palavras nele)
	 * @param N - numero total de documentos
	 * @param ni - quantidade de documentos em que o termo aparece
	 * @return
	 */
	private static Double calcularPesoParaTermosConsulta(int fiq, int N, int ni)
	{
		int termo2 = N/ni;
		
		Double peso = (1.0 + calcularLog2(fiq)) * calcularLog2(termo2);
		return peso;
	}
	
	private static Double calcularLog2(int n)
	{
		return (Math.log(n)/Math.log(2));	
	}
}
