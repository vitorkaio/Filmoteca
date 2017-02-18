package tsi.lpv.tipo;

import java.util.StringTokenizer;

public class Verificacoes {

	// Verifica a data.
	public static boolean DataOk(String diaa, String mmes, String aano) {  

		//variaveis que recebem o valor  
		int dia,mes;  
		Integer ano;    

		dia = Integer.parseInt(diaa); 
		mes = Integer.parseInt(mmes); 
		ano = Integer.parseInt(aano);

		if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12) && (ano >= 1900 && ano <= 2100)) //verifica se os numeros sao validos
		{
			if ((dia == 29 && mes == 2) && ((ano % 4) == 0)) //verifica se o ano e bissexto
			{
				return true;
			}
			if (dia <= 28 && mes == 2) //verifica o mes de feveireiro
			{
				return true;
			}
			if ((dia <= 30) && (mes == 4 || mes == 6 || mes == 9 || mes == 11)) //verifica os meses de 30 dias
			{
				return true;
			}
			if ((dia <= 31) && (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12)) //verifica os meses de 31 dias
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}  


	}  


	// Formata a data no modelo dd/mm/aaaa
	public static String formataData(String dia, String mes, String ano){

		String data[] = new String[5];

		data[0] = dia;
		data[1] = "/";
		data[2] = mes;
		data[3] = "/";
		data[4] = ano;

		String d = data[0] + data[1] + data[2] + data[3] + data[4];

		return d;

	}

	//  Quebra a string em varias e retorna um vetor.
	public static String[] obterTokens(String texto, String separador){

		StringTokenizer  tokens = new StringTokenizer(texto, separador);
		String saida[] = new String[tokens.countTokens()];
		int i = 0;

		// Obtém todo os tokens da string frase.
		while (tokens.hasMoreTokens()){
			
			saida[i] = tokens.nextToken().trim();
			i++;
			
		}
		return saida;

	}

}
