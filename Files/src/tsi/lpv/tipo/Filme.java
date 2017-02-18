package tsi.lpv.tipo;

/** 
 * Classe que define a estrutura básica de uma filmoteca.
 * 
 * @author Vítor e Renato.
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
	 * Obtém o código do filme.
	 * 
	 * @return um <code>int</code> com o valor do código.
	 * 
	 * */
	public int getCodFilme() {
		return codFilme;
	}

	/**
	 * Altera o valor do código do filme.
	 * 
	 * @param codFilme um <code>int</code> com código do filme.
	 *  */
	public void setCodFilme(int codFilme) {
		this.codFilme = codFilme;
	}

	/** 
	 * Obtém classificação etária do filme.
	 * 
	 * @return uma <code>String</code> com o valor da classificação etária.
	 * 
	 * */
	public String getClaEtaria() {
		return claEtaria;
	}
	
	/**
	 * Altera o valor da classificação etária do filme.
	 * 
	 * @param claEtaria uma <code>String</code> que representa a classificação etária do filme.
	 *  */
	public void setClaEtaria(String claEtaria) {
		
		this.claEtaria = claEtaria;
	}

	/** 
	 * Obtém classificação IMDB do filme.
	 * 
	 * @return um <code>int</code> com o valor da classificação IMDB.
	 * 
	 * */
	public int getClaIMDB() {
		return claIMDB;
	}

	/**
	 * Altera o valor da classificação IMDB do filme.
	 * 
	 * @param claIMDB um <code>int</code> que representa a classificação IMDB do filme.
	 *  */
	public void setClaIMDB(int claIMDB) {
		this.claIMDB = claIMDB;
	}

	/** 
	 * Obtém classificação pessoal do filme.
	 * 
	 * @return um <code>int</code> com o valor da classificação pessoal.
	 * 
	 * */
	public int getClaPessoal() {
		return claPessoal;
	}

	/**
	 * Altera o valor da classificação pessoal do filme.
	 * 
	 * @param claPessoal um <code>int</code> que representa a classificação pessoal do filme.
	 *  */
	public void setClaPessoal(int claPessoal) {
		this.claPessoal = claPessoal;
	}
	
	
	/** 
	 * Obtém o tipo da mídia do filme.
	 * 
	 * @return uma <code>String</code> com o valor da mídia.
	 * 
	 * */
	public String getMidia() {
		return midia;
	}

	/**
	 * Altera o valor da mídia do filme.
	 * 
	 * @param midia uma <code>String</code> que representa a mídia do filme.
	 *  */
	public void setMidia(String midia) {
		this.midia = midia;
	}

	/** 
	 * Obtém o título do filme.
	 * 
	 * @return uma <code>String</code> com o título do filme.
	 * 
	 * */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Altera o título do filme.
	 * 
	 * @param titulo uma <code>String</code> que representa o título do filme.
	 *  */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	/** 
	 * Obtém a sinopse do filme.
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
	 * Obtém o ano do filme.
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
	 * Obtém a data de lançamento do filme.
	 * 
	 * @return uma <code>String</code> com a data de lançamento do filme.
	 * 
	 * */
	public String getDataLancamento() {
		return dataLancamento;
	}

	/**
	 * Altera a data de lançamento do filme.
	 * 
	 * @param dataLancamento uma <code>String</code> que representa a data de lançamento do filme.
	 *  */
	public void setDataLancamento(String dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	/** 
	 * Obtém a duração do filme.
	 * 
	 * @return um <code>double</code> com a duração do filme.
	 * 
	 * */
	public float getDuracao() {
		return duracao;
	}

	/**
	 * Altera a duração do filme.
	 * 
	 * @param duracao um <code>double</code> que representa a duração do filme.
	 *  */
	public void setDuracao(float duracao) {
		this.duracao = duracao;
	}

	/** 
	 * Obtém o pôster do filme.
	 * 
	 * @return um <code>ImageIcon</code> com o pôster do filme.
	 * 
	 * */
	public String getPoster() {
		return posterUrl;
	}

	/**
	 * Altera o pôster do filme.
	 * 
	 * @param poster um <code>ImageIcon</code> que representa o pôster do filme.
	 *  */
	public void setPoster(String poster) {
		this.posterUrl = poster;
	}

	
	@Override
	public String toString() {
		return "Código do Filme: " + codFilme + "\nTítulo: " + titulo
				+ "\nSinopse: " + sinopse + "\nAno: " + ano + "\n Data de Lançamento: "
				+ dataLancamento + "\nClassificação IMDB: " + claIMDB
				+ "\nClassificação Pessoal: " + claPessoal + "\nClassificção Etária: " + claEtaria + 
				"\nMídia: " + midia + "\nDuração: " + duracao + "\n";
	}

}// Fim da classe Filme.
