package tsi.lpv.tipo;

/** 
 * Classe que define a estrutura b�sica de uma filmoteca.
 * 
 * @author V�tor e Renato.
 * 
 * @version 1.0
 * 
 * */

public class Filme {
	
	private int codFilme, claIMDB, claPessoal;
	private String titulo, sinopse, ano, dataLancamento, claEtaria, midia, diretor, autor, elenco;
	private float duracao;
	private String posterUrl, pais, genero;
	
	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getElenco() {
		return elenco;
	}

	public void setElenco(String elenco) {
		this.elenco = elenco;
	}

	/** Inicia o objeto default. */
	public Filme(){}

	/** 
	 * Obt�m o c�digo do filme.
	 * 
	 * @return um <code>int</code> com o valor do c�digo.
	 * 
	 * */
	public int getCodFilme() {
		return codFilme;
	}

	/**
	 * Altera o valor do c�digo do filme.
	 * 
	 * @param codFilme um <code>int</code> com c�digo do filme.
	 *  */
	public void setCodFilme(int codFilme) {
		this.codFilme = codFilme;
	}

	/** 
	 * Obt�m classifica��o et�ria do filme.
	 * 
	 * @return uma <code>String</code> com o valor da classifica��o et�ria.
	 * 
	 * */
	public String getClaEtaria() {
		return claEtaria;
	}
	
	/**
	 * Altera o valor da classifica��o et�ria do filme.
	 * 
	 * @param claEtaria uma <code>String</code> que representa a classifica��o et�ria do filme.
	 *  */
	public void setClaEtaria(String claEtaria) {
		
		this.claEtaria = claEtaria;
	}

	/** 
	 * Obt�m classifica��o IMDB do filme.
	 * 
	 * @return um <code>int</code> com o valor da classifica��o IMDB.
	 * 
	 * */
	public int getClaIMDB() {
		return claIMDB;
	}

	/**
	 * Altera o valor da classifica��o IMDB do filme.
	 * 
	 * @param claIMDB um <code>int</code> que representa a classifica��o IMDB do filme.
	 *  */
	public void setClaIMDB(int claIMDB) {
		this.claIMDB = claIMDB;
	}

	/** 
	 * Obt�m classifica��o pessoal do filme.
	 * 
	 * @return um <code>int</code> com o valor da classifica��o pessoal.
	 * 
	 * */
	public int getClaPessoal() {
		return claPessoal;
	}

	/**
	 * Altera o valor da classifica��o pessoal do filme.
	 * 
	 * @param claPessoal um <code>int</code> que representa a classifica��o pessoal do filme.
	 *  */
	public void setClaPessoal(int claPessoal) {
		this.claPessoal = claPessoal;
	}
	
	
	/** 
	 * Obt�m o tipo da m�dia do filme.
	 * 
	 * @return uma <code>String</code> com o valor da m�dia.
	 * 
	 * */
	public String getMidia() {
		return midia;
	}

	/**
	 * Altera o valor da m�dia do filme.
	 * 
	 * @param midia uma <code>String</code> que representa a m�dia do filme.
	 *  */
	public void setMidia(String midia) {
		this.midia = midia;
	}

	/** 
	 * Obt�m o t�tulo do filme.
	 * 
	 * @return uma <code>String</code> com o t�tulo do filme.
	 * 
	 * */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Altera o t�tulo do filme.
	 * 
	 * @param titulo uma <code>String</code> que representa o t�tulo do filme.
	 *  */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/** 
	 * Obt�m a sinopse do filme.
	 * 
	 * @return uma <code>String</code> com a sinopse do filme.
	 * 
	 * */
	public String getSinopse() {
		return sinopse;
	}

	/**
	 * Altera a sinopse do filme.
	 * 
	 * @param sinopse uma <code>String</code> que representa a sinopse do filme.
	 *  */
	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}
	
	/** 
	 * Obt�m o ano do filme.
	 * 
	 * @return uma <code>String</code> com o ano do filme.
	 * 
	 * */
	public String getAno() {
		return ano;
	}

	/**
	 * Altera o ano do filme.
	 * 
	 * @param ano uma <code>String</code> que representa o ano do filme.
	 *  */
	public void setAno(String ano) {
		this.ano = ano;
	}

	/** 
	 * Obt�m a data de lan�amento do filme.
	 * 
	 * @return uma <code>String</code> com a data de lan�amento do filme.
	 * 
	 * */
	public String getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * Altera a data de lan�amento do filme.
	 * 
	 * @param dataLancamento uma <code>String</code> que representa a data de lan�amento do filme.
	 *  */
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	/** 
	 * Obt�m a dura��o do filme.
	 * 
	 * @return um <code>double</code> com a dura��o do filme.
	 * 
	 * */
	public float getDuracao() {
		return duracao;
	}

	/**
	 * Altera a dura��o do filme.
	 * 
	 * @param duracao um <code>double</code> que representa a dura��o do filme.
	 *  */
	public void setDuracao(float duracao) {
		this.duracao = duracao;
	}

	/** 
	 * Obt�m o p�ster do filme.
	 * 
	 * @return um <code>ImageIcon</code> com o p�ster do filme.
	 * 
	 * */
	public String getPoster() {
		return posterUrl;
	}

	/**
	 * Altera o p�ster do filme.
	 * 
	 * @param poster um <code>ImageIcon</code> que representa o p�ster do filme.
	 *  */
	public void setPoster(String poster) {
		this.posterUrl = poster;
	}

	
	@Override
	public String toString() {
		return "C�digo do Filme: " + codFilme + "\nT�tulo: " + titulo
				+ "\nSinopse: " + sinopse + "\nAno: " + ano + "\n Data de Lan�amento: "
				+ dataLancamento + "\nClassifica��o IMDB: " + claIMDB
				+ "\nClassifica��o Pessoal: " + claPessoal + "\nClassific��o Et�ria: " + claEtaria + 
				"\nM�dia: " + midia + "\nDura��o: " + duracao + "\n";
	}

}// Fim da classe Filme.
