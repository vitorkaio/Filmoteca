package tsi.lpv.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import tsi.lpv.tipo.Filme;
import tsi.lpv.tipo.Verificacoes;

public class Banco {

	private static int idFilme;

	public static boolean cadastraFilme(Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;
		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Filme");
			return false;
		}


		String sql = "INSERT INTO filme " +
				"(titulo, duracao, ano, data_lancamento, sinopse, classificacao_etaria,"
				+ "classificacao_imdb, classificacao_pessoal, midia, poster)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// seta os valores

			stmt.setString(1, filme.getTitulo());
			stmt.setDouble(2, filme.getDuracao());
			stmt.setString(3, filme.getAno());
			stmt.setString(4, filme.getDataLancamento());
			stmt.setString(5, filme.getSinopse());
			stmt.setString(6, filme.getClaEtaria());
			stmt.setInt(7, filme.getClaIMDB());
			stmt.setInt(8, filme.getClaPessoal());
			stmt.setString(9, filme.getMidia());
			stmt.setString(10, filme.getPoster());

			// executa
			stmt.execute();

			// Pega o id do filme.
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					idFilme = rs.getInt("codigo_filme"); // teria outro modo de recuperar o id? sem usa static?
				}
			}

			catch(Exception ex){

				return false;
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Filme");
			return false;
		}

		if(gravaArtista(filme) == false)
			return false;

		if(gravaDiretor(filme) == false)
			return false;

		if(gravaAutor(filme) == false)
			return false;

		if(gravaGenero(filme) == false)
			return false;

		if(gravaPais(filme) == false)
			return false;

		return true;

	}

	// Salva os artitas no banco de dados.
	private static boolean gravaArtista(Filme filme){

		Connection con = null;

		// Pega todos os artista do elenco do filme.
		String arts[] = Verificacoes.obterTokens(filme.getElenco(), ",;.");
		int codigos[] = new int[arts.length];

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Artista");
			return false;
		}


		String sql = "INSERT INTO artista " +
				"(nome)" +
				" VALUES (?)";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			for(int cont = 0; cont < arts.length; cont++){

				// seta os valores
				stmt.setString(1, arts[cont]);

				// executa
				stmt.execute();

				// pega o id de todos os artista e salva em um array.
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						codigos[cont] = rs.getInt("codigo_artista"); // teria outro modo de recuperar o id? sem usa static?
					}
				}

				catch(Exception ex){

					return false;
				}

			}


			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Artista");
			return false;
		}

		if(gravaElenco(codigos, filme) == false)
			return false;

		return true;
	}


	// Salva os elencos no banco de dados.
	private static boolean gravaElenco(int codigos[], Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Elenco");
			return false;
		}


		String sql = "INSERT INTO elenco " +
				"(codigo_filme, codigo_artista)" +
				" VALUES (?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			for(int x = 0; x < codigos.length; x++){

				stmt.setInt(1, idFilme);
				stmt.setInt(2, codigos[x]);

				// executa
				stmt.execute();

			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Elenco");
			return false;
		}

		return true;
	}

	// Salva no banco de dados o Diretor do Filme.
	private static boolean gravaDiretor(Filme filme){

		Connection con = null;

		// Pega todos diretores.
		String arts[] = Verificacoes.obterTokens(filme.getDiretor(), ",;.");
		int codigos[] = new int[arts.length];

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Diretor");
			return false;
		}


		String sql = "INSERT INTO diretor " +
				"(nome)" +
				" VALUES (?)";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			for(int cont = 0; cont < arts.length; cont++){

				// seta os valores
				stmt.setString(1, arts[cont]);

				// executa
				stmt.execute();

				// pega o id de todos os autores e salva em um array.
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						codigos[cont] = rs.getInt("codigo_diretor"); // teria outro modo de recuperar o id? sem usa static?
					}
				}

				catch(Exception ex){

					return false;
				}

			}


			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Autor");
			return false;
		}

		if(gravaDiretorFilme(codigos, filme) == false)
			return false;

		return true;
	}

	// Grava os dados na tabela do diretorFilme.
	private static boolean gravaDiretorFilme(int codigos[], Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura DiretorFilme");
			return false;
		}


		String sql = "INSERT INTO diretor_filme " +
				"(codigo_filme, codigo_diretor)" +
				" VALUES (?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			for(int x = 0; x < codigos.length; x++){

				stmt.setInt(1, idFilme);
				stmt.setInt(2, codigos[x]);

				// executa
				stmt.execute();

			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção DiretorFilme");
			return false;
		}

		return true;

	}

	// Salva no banco de dados o Autor do Filme.
	private static boolean gravaAutor(Filme filme){

		Connection con = null;

		// Pega todos os autores do elenco do filme.
		String arts[] = Verificacoes.obterTokens(filme.getAutor(), ",;.");
		int codigos[] = new int[arts.length];

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Autor");
			return false;
		}


		String sql = "INSERT INTO autor " +
				"(nome)" +
				" VALUES (?)";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			for(int cont = 0; cont < arts.length; cont++){

				// seta os valores
				stmt.setString(1, arts[cont]);

				// executa
				stmt.execute();

				// pega o id de todos os autores e salva em um array.
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						codigos[cont] = rs.getInt("codigo_autor"); // teria outro modo de recuperar o id? sem usa static?
					}
				}

				catch(Exception ex){

					return false;
				}

			}


			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Autor");
			return false;
		}

		if(gravaAutorFilme(codigos, filme) == false)
			return false;

		return true;

	}


	// Grava os dados na tabela do autorFilme.
	private static boolean gravaAutorFilme(int codigos[], Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura AutorFilme");
			return false;
		}


		String sql = "INSERT INTO autor_filme " +
				"(codigo_filme, codigo_autor)" +
				" VALUES (?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			for(int x = 0; x < codigos.length; x++){

				stmt.setInt(1, idFilme);
				stmt.setInt(2, codigos[x]);

				// executa
				stmt.execute();

			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Autor");
			return false;
		}

		return true;

	}


	// Salva no banco de dados o Genero do Filme.
	private static boolean gravaGenero(Filme filme){

		Connection con = null;

		// Pega todos os artista do elenco do filme.
		String genero = filme.getGenero();
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura Gênero");
			return false;
		}


		String sql = "INSERT INTO genero " +
				"(descricao)" +
				" VALUES (?)";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setString(1, genero);

			// executa
			stmt.execute();

			// pega o id de todos os artista e salva em um array.
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					cod = rs.getInt("codigo_genero"); // teria outro modo de recuperar o id? sem usa static?
				}
			}

			catch(Exception ex){

				return false;
			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção Autor");
			return false;
		}

		if(gravaGeneroFilme(cod, filme) == false)
			return false;

		return true;

	}


	// Grava os dados na tabela do generoFilme.
	private static boolean gravaGeneroFilme(int codigo, Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura GeneroFilme");
			return false;
		}


		String sql = "INSERT INTO genero_filme " +
				"(codigo_filme, codigo_genero)" +
				" VALUES (?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, idFilme);
			stmt.setInt(2, codigo);

			// executa
			stmt.execute();
			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção GeneroFilme");
			return false;
		}

		return true;

	}


	// Salva no banco de dados o Genero do Filme.
	private static boolean gravaPais(Filme filme){

		Connection con = null;

		// Pega todos os artista do elenco do filme.
		String pais = filme.getPais();
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura País");
			return false;
		}


		String sql = "INSERT INTO pais " +
				"(nome)" +
				" VALUES (?)";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

			// seta os valores
			stmt.setString(1, pais);

			// executa
			stmt.execute();

			// pega o id de todos os artista e salva em um array.
			try (ResultSet rs = stmt.getGeneratedKeys()) {
				if (rs.next()) {
					cod = rs.getInt("codigo_pais"); // teria outro modo de recuperar o id? sem usa static?
				}
			}

			catch(Exception ex){

				return false;
			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção País");
			return false;
		}

		if(gravaPaisFilme(cod, filme) == false)
			return false;

		return true;

	}


	// Grava os dados na tabela do paisFilme.
	private static boolean gravaPaisFilme(int codigo, Filme filme){

		//Conexao bd = new Conexao("PostgreSql", "localhost", "5432", "tp", "caio", "123456");
		Connection con = null;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na abertura PaisFilme");
			return false;
		}


		String sql = "INSERT INTO pais_filme " +
				"(codigo_filme, codigo_pais)" +
				" VALUES (?,?)";


		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// seta os valores
			stmt.setInt(1, idFilme);
			stmt.setInt(2, codigo);

			// executa
			stmt.execute();
			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Inserção PaisFilme");
			return false;
		}

		return true;

	}


	// Recupera o filme do banco de dados.
	public static Filme leFilme(String nome){

		Connection con = null;
		String aux;
		int num;
		float f;
		Filme filme = new Filme();

		filme.setCodFilme(-1);

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão, abertura");
			return filme;
		}

		String sql = "SELECT * FROM filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getString("titulo"); // teria outro modo de recuperar o id? sem usa static?

				if(nome.equalsIgnoreCase(aux)== true){

					filme.setTitulo(aux);
					f = rs.getFloat("duracao");
					filme.setDuracao(f);
					aux = rs.getString("ano");
					filme.setAno(aux);
					aux = rs.getString("data_lancamento");
					filme.setDataLancamento(aux);
					aux = rs.getString("sinopse");
					filme.setSinopse(aux);
					aux = rs.getString("classificacao_etaria");
					filme.setClaEtaria(aux);
					num = rs.getInt("classificacao_imdb");
					filme.setClaIMDB(num);
					num = rs.getInt("classificacao_pessoal");
					filme.setClaPessoal(num);
					aux = rs.getString("midia");
					filme.setMidia(aux);
					aux = rs.getString("poster");
					filme.setPoster(aux);


					num = rs.getInt("codigo_filme");

					// Retorna o elenco do filme
					String elenco = leElenco(num);
					filme.setElenco(elenco);

					// Retorna os diretores do filme
					elenco = leDiretorFilme(num);
					filme.setDiretor(elenco);

					// Retorna os autores do filme.
					elenco = leAutorFilme(num);
					filme.setAutor(elenco);

					// Retorna o Gênero do filme.
					elenco = leGenero(num);
					filme.setGenero(elenco);

					// Retorna o páis do filme
					elenco = lePais(num);
					filme.setPais(elenco);

					filme.setCodFilme(1);
					stmt.close();

					return filme;

				}

			}

			stmt.close();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do Filme execucao");
			return filme;
		}


		return filme;

	}


	// Recupera o elenco do filme.
	private static String leElenco(int codigoFilme){

		Connection con = null;
		ArrayList<Integer>artistas = new ArrayList<Integer>();
		int aux;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão elenco, abertura");
			return "";
		}

		String sql = "SELECT * FROM elenco";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				if(aux == codigoFilme){

					artistas.add(rs.getInt("codigo_artista"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do elenco execucao");
			return "";
		}


		sql = "SELECT * FROM artista";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_artista");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < artistas.size(); i++){

					if(aux == artistas.get(i)){

						elenco += rs.getString("nome") + ",";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do elenco execucao");
			return "";
		}

		return elenco;

	}


	// Recupera os diretores do filme.
	private static String leDiretorFilme(int codigoFilme){

		Connection con = null;
		ArrayList<Integer>artistas = new ArrayList<Integer>();
		int aux;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão Diretores, abertura");
			return "";
		}

		String sql = "SELECT * FROM diretor_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os diretores e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				if(aux == codigoFilme){

					artistas.add(rs.getInt("codigo_diretor"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do Diretor execucao");
			return "";
		}


		sql = "SELECT * FROM diretor";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_diretor");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < artistas.size(); i++){

					if(aux == artistas.get(i)){

						elenco += rs.getString("nome") + ",";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do diretor execucao");
			return "";
		}

		return elenco;

	}


	// Recupera os autores do filme.
	private static String leAutorFilme(int codigoFilme){

		Connection con = null;
		ArrayList<Integer>artistas = new ArrayList<Integer>();
		int aux;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão Autores, abertura");
			return "";
		}

		String sql = "SELECT * FROM autor_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os diretores e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				if(aux == codigoFilme){

					artistas.add(rs.getInt("codigo_autor"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do Autor execucao");
			return "";
		}


		sql = "SELECT * FROM autor";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_autor");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < artistas.size(); i++){

					if(aux == artistas.get(i)){

						elenco += rs.getString("nome") + ",";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do autor execucao");
			return "";
		}

		return elenco;

	}


	// Recupera o Gênero do filme.
	private static String leGenero(int codigo){

		Connection con = null;
		int aux, cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão Gêneros, abertura");
			return "";
		}

		String sql = "SELECT * FROM genero_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os diretores e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				if(aux == codigo){

					cod = rs.getInt("codigo_genero");

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do Gênero execucao");
			return "";
		}


		sql = "SELECT * FROM genero";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_genero");

				// Verifica os artista na tabela de elenco

				if(aux == cod){

					elenco += rs.getString("descricao") + ",";

				}


			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do Gênero execucao");
			return "";
		}

		return elenco;

	}

	// Recupera o País do filme.
	private static String lePais(int codigo){

		Connection con = null;
		int aux, cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperacão País, abertura");
			return "";
		}

		String sql = "SELECT * FROM pais_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os diretores e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				if(aux == codigo){

					cod = rs.getInt("codigo_pais");

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do País execucao");
			return "";
		}


		sql = "SELECT * FROM pais";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getInt("codigo_pais");

				// Verifica os artista na tabela de elenco

				if(aux == cod){

					elenco += rs.getString("nome") + ",";

				}


			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na recuperação do País execucao");
			return "";
		}

		return elenco;


	}


	// Consulta todos os filme em que o artista trabalha.
	public static String consultaArtista(String nome){

		Connection con = null;
		ArrayList<Integer>artistas = new ArrayList<Integer>();
		ArrayList<Integer>filmes = new ArrayList<Integer>();
		String aux;
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta Artista, abertura");
			return "";
		}

		String sql = "SELECT * FROM artista";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id dos filme em que o artista trabalha.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getString("nome");

				// Verifica os artista na tabela de elenco
				if(nome.equalsIgnoreCase(aux) == true){

					artistas.add(rs.getInt("codigo_artista"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta do artista execucao");
			return "";
		}


		if(artistas.size() == 0)
			return "";

		sql = "SELECT * FROM elenco";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_artista");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < artistas.size(); i++){

					if(cod == artistas.get(i)){

						filmes.add(rs.getInt("codigo_filme"));

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta Artista, execucao");
			return "";
		}


		sql = "SELECT * FROM filme";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < filmes.size(); i++){

					if(cod == filmes.get(i)){

						elenco += rs.getString("titulo") + "\n";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta artista execucao");
			return "";
		}

		return elenco;


	}


	// Consulta todos os filme em que o artista trabalha.
	public static String consultaDiretor(String nome){

		Connection con = null;
		ArrayList<Integer>diretores = new ArrayList<Integer>();
		ArrayList<Integer>filmes = new ArrayList<Integer>();
		String aux;
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta Diretor, abertura");
			return "";
		}

		String sql = "SELECT * FROM diretor";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id dos filme em que o artista trabalha.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getString("nome");

				// Verifica os artista na tabela de elenco
				if(nome.equalsIgnoreCase(aux) == true){

					diretores.add(rs.getInt("codigo_diretor"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta do diretor execucao");
			return "";
		}


		if(diretores.size() == 0)
			return "";

		sql = "SELECT * FROM diretor_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_diretor");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < diretores.size(); i++){

					if(cod == diretores.get(i)){

						filmes.add(rs.getInt("codigo_filme"));

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta diretor, execucao");
			return "";
		}


		sql = "SELECT * FROM filme";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < filmes.size(); i++){

					if(cod == filmes.get(i)){

						elenco += rs.getString("titulo") + "\n";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta diretor execucao");
			return "";
		}

		return elenco;


	}

	// Consulta todos os filme em que o artista trabalha.
	public static String consultaAutor(String nome){

		Connection con = null;
		ArrayList<Integer>autores = new ArrayList<Integer>();
		ArrayList<Integer>filmes = new ArrayList<Integer>();
		String aux;
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta Autor, abertura");
			return "";
		}

		String sql = "SELECT * FROM autor";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id dos filme em que o artista trabalha.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getString("nome");

				// Verifica os artista na tabela de elenco
				if(nome.equalsIgnoreCase(aux) == true){

					autores.add(rs.getInt("codigo_autor"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta do autor execucao");
			return "";
		}


		if(autores.size() == 0)
			return "";

		sql = "SELECT * FROM autor_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_autor");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < autores.size(); i++){

					if(cod == autores.get(i)){

						filmes.add(rs.getInt("codigo_filme"));

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta autor, execucao");
			return "";
		}


		sql = "SELECT * FROM filme";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < filmes.size(); i++){

					if(cod == filmes.get(i)){

						elenco += rs.getString("titulo") + "\n";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta autor execucao");
			return "";
		}

		return elenco;

	}


	// Consulta todos os filme em que o artista trabalha.
	public static String consultaGenero(String nome){

		Connection con = null;
		ArrayList<Integer>generos = new ArrayList<Integer>();
		ArrayList<Integer>filmes = new ArrayList<Integer>();
		String aux;
		int cod = 0;

		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta genero, abertura");
			return "";
		}

		String sql = "SELECT * FROM genero";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id dos filme em que o artista trabalha.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				aux = rs.getString("descricao");

				// Verifica os artista na tabela de elenco
				if(nome.equalsIgnoreCase(aux) == true){

					generos.add(rs.getInt("codigo_genero"));

				}
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta do genero execucao");
			return "";
		}


		if(generos.size() == 0)
			return "";

		sql = "SELECT * FROM genero_filme";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_genero");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < generos.size(); i++){

					if(cod == generos.get(i)){

						filmes.add(rs.getInt("codigo_filme"));

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na Consulta genero, execucao");
			return "";
		}


		sql = "SELECT * FROM filme";
		String elenco = "";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os artista e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				cod = rs.getInt("codigo_filme");

				// Verifica os artista na tabela de elenco
				for(int i = 0; i < filmes.size(); i++){

					if(cod == filmes.get(i)){

						elenco += rs.getString("titulo") + "\n";

					}

				}

			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na consulta genero execucao");
			return "";
		}

		return elenco;


	}
	
	
	// Retorna uma String contendo todos os filmes ordenados pela nota Pessoal.
	public static String ordenaFilmes(){
		
		String filmes = "";
		Connection con = null;
		
		try {

			con = conecta();
		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na ordenação de filmes, abertura");
			return "";
		}

		String sql = "SELECT * FROM filme ORDER BY classificacao_pessoal DESC";

		try{

			// prepared statement para inserção
			PreparedStatement stmt = con.prepareStatement(sql);

			// pega o id de todos os diretores e salva em um array.
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				filmes += rs.getString("titulo") + " = " + rs.getInt("classificacao_pessoal") + "\n";
			}

			stmt.close();

		}

		catch(SQLException ex){

			JOptionPane.showMessageDialog(null, "Na ordenação melhores filmes execucao");
			return "";
		}
		
		return filmes;
	}

	// Conecta com o Banco de Dados.
	private static Connection conecta() throws SQLException{

		Connection con = DriverManager.getConnection("jdbc:postgresql://localhost/tp?"
				+ "user=caio&password=123456");

		return con;

	}
}// Fim da classe Banco
