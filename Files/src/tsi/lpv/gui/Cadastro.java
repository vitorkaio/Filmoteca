package tsi.lpv.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import tsi.lpv.bd.Banco;
import tsi.lpv.tipo.Filme;
import tsi.lpv.tipo.Verificacoes;
import java.awt.Point;

@SuppressWarnings("serial")
public class Cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField campoTextoDiretor;
	private JLabel labelAutor;
	private JTextField campoTextoAutor;
	private JLabel labelGenero;
	private JTextField campoTextoPais;
	private JTextField campoTextoTitulo;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxGenero;
	private JTextField campoElenco;
	private JPanel painelCapa;
	private JTextField campoDuracao;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMidia;
	private JLabel labelNotaIMDB;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxNotaPessoal;
	private JLabel labelNotaPessoal;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxNotaIMDB;
	private JLabel labelClassificacao;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxEtaria;
	private JButton botaoConfirmar;
	private JButton botaoCancelar;
	private JScrollPane scrollSinopse;
	private JTextArea areaTextoSinopse;
	private JLabel labelData;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDia;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMes;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxAno;
	private String url;
	private JLabel labelCapa;
	private JPanel painelCadastro;
	private JTabbedPane tabbedPane;
	private JTextField campoTextoPesquisa;
	private JTextArea areaTextoConsulta;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JPanel superPainelMelhores;
	private JScrollPane scrollPaneMelhores;
	private JTextArea areaTextoMelhores;
	private JProgressBar progressBarGrava;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cadastro frame = new Cadastro();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Cadastro() {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		
		
		// Configurando ícones botões
		
		// Gravar
		ImageIcon icon = new ImageIcon(Cadastro.class.getResource("/tsi/lpv/img/iconeGravar.png"));
		//lblNewLabel.setIcon(new ImageIcon(MenuIg.class.getResource("/tsi/too/imagem/BVB.jpg")));
		Image image = icon.getImage();
		Image newimg = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		ImageIcon acc = new ImageIcon(newimg);
		
		// Cancelar
		icon = new ImageIcon(Cadastro.class.getResource("/tsi/lpv/img/iconeCancelar.png"));
		image = icon.getImage();
		newimg = image.getScaledInstance(35, 35, Image.SCALE_DEFAULT);
		ImageIcon cancel = new ImageIcon(newimg);
		
		// Pesquisar
		icon = new ImageIcon(Cadastro.class.getResource("/tsi/lpv/img/iconePesquisa.png"));
		image = icon.getImage();
		newimg = image.getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon pes = new ImageIcon(newimg);
		
		// Termino dos icones dos botões
		setResizable(false);
		setTitle("Cadastrar Filme");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 609, 665);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 6, 591, 607);
		contentPane.add(tabbedPane);

		painelCadastro = new JPanel();
		painelCadastro.setBackground(SystemColor.control);
		tabbedPane.addTab("Cadastro", null, painelCadastro, null);
		painelCadastro.setLayout(null);

		JLabel labelTitulo = new JLabel("T\u00EDtulo:");
		labelTitulo.setBounds(0, 12, 33, 16);
		painelCadastro.add(labelTitulo);

		campoTextoTitulo = new JTextField();
		campoTextoTitulo.setBounds(60, 6, 238, 28);
		painelCadastro.add(campoTextoTitulo);
		campoTextoTitulo.setColumns(10);

		JLabel labelDiretor = new JLabel("Diretor:");
		labelDiretor.setBounds(0, 46, 40, 16);
		painelCadastro.add(labelDiretor);

		campoTextoDiretor = new JTextField();
		campoTextoDiretor.setBounds(60, 40, 238, 28);
		painelCadastro.add(campoTextoDiretor);
		campoTextoDiretor.setColumns(10);

		labelAutor = new JLabel("Autor:");
		labelAutor.setBounds(0, 86, 50, 16);
		painelCadastro.add(labelAutor);

		campoTextoAutor = new JTextField();
		campoTextoAutor.setBounds(60, 80, 238, 28);
		painelCadastro.add(campoTextoAutor);
		campoTextoAutor.setColumns(10);

		labelGenero = new JLabel("G\u00EAnero:");
		labelGenero.setBounds(0, 241, 55, 16);
		painelCadastro.add(labelGenero);

		// Trata o comboBox do genêro.
		comboBoxGenero = new JComboBox();
		comboBoxGenero.setBounds(60, 236, 142, 26);
		painelCadastro.add(comboBoxGenero);
		comboBoxGenero.addItemListener(

				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					}
				});

		comboBoxGenero.setMaximumRowCount(10);
		comboBoxGenero.setModel(new DefaultComboBoxModel(new String[] {"A\u00E7\u00E3o", "Anima\u00E7\u00E3o", "Aventura", "Chanchada", "Cinema cat\u00E1strofe", "Com\u00E9dia", "Com\u00E9dia rom\u00E2ntica", "Com\u00E9dia dram\u00E1tica", "Com\u00E9dia de a\u00E7\u00E3o", "Cult", "Dan\u00E7a", "Document\u00E1rios (n\u00E3o \u00E9 um g\u00EAnero, \u00E9 uma categoria)", "Drama", "Espionagem", "Er\u00F3tico", "Fantasia", "Faroeste (ou western)", "Fic\u00E7\u00E3o cient\u00EDfica", "Franchise/S\u00E9ries", "Guerra", "Machinima", "Masala", "Musical", "Filme noir", "Policial", "Pornochanchada", "Pornogr\u00E1fico", "Romance", "Suspense", "Terror (ou horror)", "Trash"}));

		JLabel labelPais = new JLabel("Pa\u00EDs:");
		labelPais.setBounds(0, 169, 55, 16);
		painelCadastro.add(labelPais);

		campoTextoPais = new JTextField();
		campoTextoPais.setBounds(59, 163, 137, 28);
		painelCadastro.add(campoTextoPais);
		campoTextoPais.setColumns(10);

		JLabel labelSinopse = new JLabel("Sinopse:");
		labelSinopse.setBounds(259, 356, 55, 16);
		painelCadastro.add(labelSinopse);

		// Trata o botão confirmar.
		botaoConfirmar = new JButton("Gravar", acc);
		botaoConfirmar.setBounds(290, 516, 128, 39);
		painelCadastro.add(botaoConfirmar);
		botaoConfirmar.setMnemonic(KeyEvent.VK_ENTER);
		botaoConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// Verifica se algum campo está vazio.
				if(verificaCampos(campoTextoTitulo.getText(), campoTextoDiretor.getText(),
						campoTextoAutor.getText(), campoTextoPais.getText(), 
						campoDuracao.getText(), campoElenco.getText(),areaTextoSinopse.getText()) == false)
					return;

				// Verifica se a data está ok.
				if(Verificacoes.DataOk(comboBoxDia.getSelectedItem().toString(), comboBoxMes.getSelectedItem().toString(), 
						comboBoxAno.getSelectedItem().toString()) == false)
					JOptionPane.showMessageDialog(null, "Data inválida");

				// Pega todos os dados e envia para o banco de dados.
				else{

					progressBarGrava.setIndeterminate(true);
					
					if(ligacaoBanco() == true){
						JOptionPane.showMessageDialog(null, "Item Gravado");
						zeraCampos();
					}
					
					progressBarGrava.setIndeterminate(false);
				}



			}
		});
		botaoConfirmar.setForeground(SystemColor.desktop);
		botaoConfirmar.setBackground(new Color(46, 139, 87));

		JLabel labelElenco = new JLabel("Elenco:");
		labelElenco.setBounds(0, 126, 55, 16);
		painelCadastro.add(labelElenco);

		campoElenco = new JTextField();
		campoElenco.setBounds(60, 120, 238, 28);
		painelCadastro.add(campoElenco);
		campoElenco.setColumns(10);

		painelCapa = new JPanel();
		painelCapa.setBounds(363, 6, 218, 197);
		painelCadastro.add(painelCapa);
		painelCapa.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		painelCapa.setBackground(SystemColor.control);
		painelCapa.setLayout(null);

		labelCapa = new JLabel("Capa: 214 x 317");
		labelCapa.setForeground(SystemColor.controlShadow);
		labelCapa.setBounds(64, 79, 98, 16);
		painelCapa.add(labelCapa);

		JLabel labelDuracao = new JLabel("Dura\u00E7\u00E3o:");
		labelDuracao.setBounds(0, 208, 55, 16);
		painelCadastro.add(labelDuracao);

		campoDuracao = new JTextField();
		campoDuracao.setBounds(60, 203, 137, 26);
		painelCadastro.add(campoDuracao);
		campoDuracao.setColumns(10);

		JLabel labemMidia = new JLabel("M\u00EDdia:");
		labemMidia.setBounds(363, 286, 55, 16);
		painelCadastro.add(labemMidia);

		// Trata o comboBox da mídia.
		comboBoxMidia = new JComboBox();
		comboBoxMidia.setBounds(449, 281, 89, 26);
		painelCadastro.add(comboBoxMidia);
		comboBoxMidia.addItemListener(

				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					}
				});

		comboBoxMidia.setModel(new DefaultComboBoxModel(new String[] {"DVD", "Blu-Ray", "Digital"}));

		labelNotaIMDB = new JLabel("Nota IMDB: ");
		labelNotaIMDB.setBounds(363, 248, 64, 16);
		painelCadastro.add(labelNotaIMDB);

		// Trata o comboBox da nota pessoal.
		comboBoxNotaPessoal = new JComboBox();
		comboBoxNotaPessoal.setBounds(449, 209, 89, 26);
		painelCadastro.add(comboBoxNotaPessoal);
		comboBoxNotaPessoal.setMaximumRowCount(5);
		comboBoxNotaPessoal.addItemListener(

				new ItemListener() {

					// handle JComboBox event
					public void itemStateChanged( ItemEvent event ){

						// determine whether item selected
						if ( event.getStateChange() == ItemEvent.SELECTED )
							;
						/* Add Aqui o que será escrito */

					} // end method itemStateChanged



				});

		comboBoxNotaPessoal.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));

		labelNotaPessoal = new JLabel("Nota Pessoal:");
		labelNotaPessoal.setBounds(363, 213, 83, 16);
		painelCadastro.add(labelNotaPessoal);

		// Trata o comboBox da nota IMDB.
		comboBoxNotaIMDB = new JComboBox();
		comboBoxNotaIMDB.setBounds(449, 243, 89, 26);
		painelCadastro.add(comboBoxNotaIMDB);
		comboBoxNotaIMDB.setMaximumRowCount(5);
		comboBoxNotaIMDB.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"}));

		labelClassificacao = new JLabel("Classifica\u00E7\u00E3o: ");
		labelClassificacao.setBounds(0, 274, 83, 16);
		painelCadastro.add(labelClassificacao);

		// Trata o comboBox da classificação etária.
		comboBoxEtaria = new JComboBox();
		comboBoxEtaria.setBounds(82, 269, 120, 26);
		painelCadastro.add(comboBoxEtaria);
		comboBoxEtaria.addItemListener(

				new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
					}
				});

		comboBoxEtaria.setMaximumRowCount(6);
		comboBoxEtaria.setModel(new DefaultComboBoxModel(new String[] {"Livre", "10", "12", "14", "16", "18"}));

		botaoCancelar = new JButton("Cancelar", cancel);
		botaoCancelar.setBounds(148, 516, 128, 39);
		painelCadastro.add(botaoCancelar);
		botaoCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				zeraCampos();

			}
		});
		botaoCancelar.setBackground(new Color(255, 0, 0));

		scrollSinopse = new JScrollPane();
		scrollSinopse.setBounds(0, 373, 581, 137);
		painelCadastro.add(scrollSinopse);

		areaTextoSinopse = new JTextArea();
		areaTextoSinopse.setLineWrap(true);
		scrollSinopse.setViewportView(areaTextoSinopse);

		labelData = new JLabel("Data:");
		labelData.setBounds(0, 319, 55, 16);
		painelCadastro.add(labelData);

		comboBoxDia = new JComboBox();
		comboBoxDia.setBounds(60, 314, 50, 26);
		painelCadastro.add(comboBoxDia);
		comboBoxDia.setMaximumRowCount(10);
		comboBoxDia.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));

		comboBoxMes = new JComboBox();
		comboBoxMes.setBounds(116, 314, 50, 26);
		painelCadastro.add(comboBoxMes);
		comboBoxMes.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMes.setMaximumRowCount(6);

		comboBoxAno = new JComboBox();
		comboBoxAno.setBounds(169, 314, 64, 26);
		painelCadastro.add(comboBoxAno);
		comboBoxAno.setMaximumRowCount(10);
		comboBoxAno.setModel(new DefaultComboBoxModel(new String[] {"2030", "2029", "2028", "2027", "2026", "2025", "2024", "2023", "2022", "2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008 ", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960 ", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935", "1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917", "1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901"}));
		
		progressBarGrava = new JProgressBar();
		progressBarGrava.setBounds(6, 558, 581, 19);
		painelCadastro.add(progressBarGrava);
		

		JPanel superPainelConsulta = new JPanel();
		tabbedPane.addTab("Consulta", null, superPainelConsulta, null);
		superPainelConsulta.setLayout(null);
		superPainelConsulta.setBorder(new EmptyBorder(5, 5, 5, 5));
		superPainelConsulta.setBackground(SystemColor.menu);

		campoTextoPesquisa = new JTextField();
		campoTextoPesquisa.setColumns(10);
		campoTextoPesquisa.setBounds(94, 9, 304, 28);
		superPainelConsulta.add(campoTextoPesquisa);

		JPanel painelRadio = new JPanel();
		painelRadio.setBackground(SystemColor.menu);
		painelRadio.setBounds(41, 46, 423, 37);
		superPainelConsulta.add(painelRadio);

		JRadioButton radioFilme = new JRadioButton("Filme");
		buttonGroup.add(radioFilme);
		painelRadio.add(radioFilme);

		JRadioButton radioArtista = new JRadioButton("Artista");
		buttonGroup.add(radioArtista);
		painelRadio.add(radioArtista);

		JRadioButton radioDiretor = new JRadioButton("Diretor");
		buttonGroup.add(radioDiretor);
		painelRadio.add(radioDiretor);

		JRadioButton radioAutor = new JRadioButton("Autor");
		buttonGroup.add(radioAutor);
		painelRadio.add(radioAutor);

		JRadioButton radioGenero = new JRadioButton("G\u00EAnero");
		buttonGroup.add(radioGenero);
		painelRadio.add(radioGenero);

		JPanel painelConsulta = new JPanel();
		painelConsulta.setLayout(null);
		painelConsulta.setBackground(SystemColor.menu);
		painelConsulta.setBounds(10, 95, 575, 459);
		superPainelConsulta.add(painelConsulta);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 569, 453);
		painelConsulta.add(scrollPane);

		areaTextoConsulta = new JTextArea();
		areaTextoConsulta.setEditable(false);
		scrollPane.setViewportView(areaTextoConsulta);

		JButton botaoPesquisar = new JButton("Pesquisar", pes);
		botaoPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {


				/*
				 * Aqui que será chamado os métodos ou a classe que irá tratar a pesquisa no banco
				 * de dados.
				 * 
				 * */

				// Se o radio Filme foi escolhido recupera o objeto do bd e envia para MostraFilme.
				if(radioFilme.isSelected()){

					// Filme filme = objeto vindo do método que recupera dados do banco de dados.

					Filme mov = Banco.leFilme(campoTextoPesquisa.getText());

					// Verifica se o filme existe.
					if(mov.getCodFilme() == -1)
						areaTextoConsulta.setText(" Filme não Encontrado");

					else{

						areaTextoConsulta.setText("");
						new MostraFilme(mov);

					}


				}


				// Se o radio do artista tiver sido marcado, recebe um array de string com os nomes do filme.
				if(radioArtista.isSelected()){

					/* String filmes[] = Recebe do bd um array com os nomes do filme.
					 * 
					 * De prefêrencia a função que manipula o bd e retorna o array com os nomes do filmes,
					 * deve ter como parâmetro o retorno da variável campoTextoPesquisa.getText();
					 * caso não encontre o artista, deve ser retornado null, e exibida a msg Artista não encontrado.
					 * 
					 * */

					String artistas = Banco.consultaArtista(campoTextoPesquisa.getText());

					if(artistas.equalsIgnoreCase("") == true)
						areaTextoConsulta.setText(" Artista não Encontrado");

					else
						areaTextoConsulta.setText(artistas);

				}

				// Se o radio do Diretor tiver sido marcado, recebe um array de string com os nomes do filme.
				if(radioDiretor.isSelected()){

					// Mesma coisa do Artista
					String diretores = Banco.consultaDiretor(campoTextoPesquisa.getText());

					if(diretores.equalsIgnoreCase("") == true)
						areaTextoConsulta.setText(" Diretor não Encontrado");

					else
						areaTextoConsulta.setText(diretores);

				}

				// Se o radio do Autor tiver sido marcado, recebe um array de string com os nomes do filme.
				if(radioAutor.isSelected()){

					// Mesma coisa do Artista
					String autores = Banco.consultaAutor(campoTextoPesquisa.getText());

					if(autores.equalsIgnoreCase("") == true)
						areaTextoConsulta.setText(" Autor não Encontrado");

					else
						areaTextoConsulta.setText(autores);

				}

				// Se o radio do Gênero tiver sido marcado, recebe um array de string com os nomes do filme.
				if(radioGenero.isSelected()){

					// Mesma coisa do Artista
					// Mesma coisa do Artista
					String generos = Banco.consultaGenero(campoTextoPesquisa.getText());

					if(generos.equalsIgnoreCase("") == true)
						areaTextoConsulta.setText(" Gênero não Encontrado");

					else
						areaTextoConsulta.setText(generos);

				}

			}

		});
		botaoPesquisar.setBounds(410, 9, 115, 28);
		superPainelConsulta.add(botaoPesquisar);

		superPainelMelhores = new JPanel();
		superPainelMelhores.setBackground(SystemColor.control);
		tabbedPane.addTab("Melhores", null, superPainelMelhores, null);
		superPainelMelhores.setLayout(null);

		scrollPaneMelhores = new JScrollPane();
		scrollPaneMelhores.setBounds(0, 71, 585, 483);
		superPainelMelhores.add(scrollPaneMelhores);

		areaTextoMelhores = new JTextArea();
		areaTextoMelhores.setEditable(false);
		areaTextoMelhores.setLineWrap(true);
		scrollPaneMelhores.setViewportView(areaTextoMelhores);

		JButton botaoBuscarMelhores = new JButton("Buscar Melhores");
		botaoBuscarMelhores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String texto = Banco.ordenaFilmes();

				areaTextoMelhores.setText(texto);
			}
		});
		botaoBuscarMelhores.setBounds(211, 22, 129, 28);
		superPainelMelhores.add(botaoBuscarMelhores);
		comboBoxNotaIMDB.addItemListener(

				new ItemListener() {

					public void itemStateChanged(ItemEvent e) {
					}
				});

		painelCapa.addMouseListener(

				new MouseAdapter() {

					public void mouseReleased(java.awt.event.MouseEvent e) {


						JFileChooser file = new JFileChooser(); 
						file.setFileSelectionMode(JFileChooser.FILES_ONLY);
						FileNameExtensionFilter filtro = 
								new FileNameExtensionFilter("Imagem", "jpg", "jpeg", "gif", "png");

						file.setFileFilter(filtro);
						int i= file.showSaveDialog(null);

						if (i==1){
							;
						} else {
							url = file.getSelectedFile().getPath();
							ImageIcon img = new ImageIcon(url);

							Image image = img.getImage();
							Image newimg = image.getScaledInstance(218, 197, Image.SCALE_DEFAULT);  
							labelCapa.setIcon(new ImageIcon(newimg));
							
							//JLabel imagem = new JLabel(img);
							//painelCapa.add(imagem);
							//imagem.setBounds(5, 5, 165, 188);
							labelCapa.setBounds(5, 5, 207, 188);
							//labelCapa.setIcon(img);

						}

					};

				}


				);

		/* Add eventos do teclado ao programa*/

		addKeyListener(

				new KeyAdapter() {

					public void keyReleased(KeyEvent e) {

						int cod = e.getKeyCode();

						if(e.getSource() == campoTextoTitulo && cod == 10){

							// Verifica se algum campo está vazio.
							if(verificaCampos(campoTextoTitulo.getText(), campoTextoDiretor.getText(),
									campoTextoAutor.getText(), campoTextoPais.getText(), 
									campoDuracao.getText(), campoElenco.getText(),areaTextoSinopse.getText()) == false)
								;

							else
								System.exit(0);

						}

					};

				}

				);
		
		setLocationRelativeTo(null);
		setVisible(true);
	}// Fim do construtor.




	/** Verifica se o campo passado está vazio, se sim retorna false, caso contrárioretorna true. */
	private boolean verificaCampos(String campoTitulo, String campoDiretor, String campoAutor, String campoPais, String
			campoDuracao, String campoElenco, String campoSinopse){

		ArrayList<String> texto = new ArrayList<String>();

		if(campoTitulo.equals("") == true)
			texto.add("Título\n");

		if(campoDiretor.equals("") == true)
			texto.add("Diretor\n");

		if(campoAutor.equals("") == true)
			texto.add("Autor\n");

		if(campoPais.equals("") == true)
			texto.add("País\n");

		if(campoDuracao.equals("") == true)
			texto.add("Duração\n");

		if(campoElenco.equals("") == true)
			texto.add("Elenco\n");

		if(campoSinopse.equals("") == true)
			texto.add("Sinopse\n");

		if(texto.isEmpty() == true)
			return true;


		String area = "";

		for(int x = 0; x < texto.size(); x++)
			area += texto.get(x);

		JOptionPane.showMessageDialog(null, "Os seguintes campos estão vazios:\n\n" + 
				area);
		return false;

	}

	// Função que pega todos os dados e envia para o banco de dados.
	private boolean ligacaoBanco(){

		Filme filme = new Filme();
		String aux;

		// Pega o nome do filme e encapsula.
		aux = campoTextoTitulo.getText();
		filme.setTitulo(aux);

		// Pega o nome do diretor.
		aux = campoTextoDiretor.getText();
		filme.setDiretor(aux);

		// Pega o nome do autor.
		aux = campoTextoAutor.getText();
		filme.setAutor(aux);

		// Pega o nome do elenco.
		aux = campoElenco.getText();
		filme.setElenco(aux);

		// Pega a duração do filme.
		aux = campoDuracao.getText();

		try{

			// Converte a duração para float.
			float duracao = Float.parseFloat(aux);
			filme.setDuracao(duracao);

		}
		catch(NumberFormatException ex){

			JOptionPane.showMessageDialog(null, "Duração inválida");
			return false;

		}

		// Pega o país de origem.
		aux = campoTextoPais.getText();
		filme.setPais(aux);

		// Pega a data do filme.
		aux = Verificacoes.formataData(comboBoxDia.getSelectedItem().toString(), comboBoxMes.getSelectedItem().toString(), comboBoxAno.getSelectedItem().toString());
		filme.setDataLancamento(aux);

		// Pega o ano do filme.
		aux = comboBoxAno.getSelectedItem().toString();
		filme.setAno(aux);

		// Pega a classificação etaria do filme.
		aux = comboBoxEtaria.getSelectedItem().toString();
		filme.setClaEtaria(aux);

		// Pega a nota pessoal
		aux = comboBoxNotaPessoal.getSelectedItem().toString();
		filme.setClaPessoal(Integer.parseInt(aux));

		// Pega a nota IMDB.
		aux = comboBoxNotaIMDB.getSelectedItem().toString();
		filme.setClaIMDB(Integer.parseInt(aux));

		// Pega a midia.
		aux = comboBoxMidia.getSelectedItem().toString();
		filme.setMidia(aux);

		// Pega a url do poster.
		aux = url;
		filme.setPoster(aux);

		// Pega a sinopse.
		aux = areaTextoSinopse.getText();
		filme.setSinopse(aux);

		// Pega o gênero
		aux = comboBoxGenero.getSelectedItem().toString();
		filme.setGenero(aux);

		// Gera um código para o filme.
		Random r = new Random();
		int cod = r.nextInt();
		filme.setCodFilme(cod);

		// Envia o objeto para a classe que manipula o banco de dados.
		if(Banco.cadastraFilme(filme) == false)
			JOptionPane.showMessageDialog(null, "Erro ao Gravar");

		return true;

	}

	// Zera todos os campos do cadastro.
	private void zeraCampos(){


		campoDuracao.setText("");
		campoElenco.setText("");
		campoTextoAutor.setText("");
		campoTextoDiretor.setText("");
		campoTextoPais.setText("");
		campoTextoTitulo.setText("");
		labelCapa.setIcon(null);
		labelCapa.setBounds(64, 79, 98, 16);
		labelCapa.setText("Capa: 214 x 317");
		url = "";

		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAno.setSelectedIndex(0);

		comboBoxGenero.setSelectedIndex(0);
		comboBoxMidia.setSelectedIndex(0);
		comboBoxEtaria.setSelectedIndex(0);
		comboBoxNotaPessoal.setSelectedIndex(0);
		comboBoxNotaIMDB.setSelectedIndex(0);
		areaTextoSinopse.setText("");

	}
}
