package tsi.lpv.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import tsi.lpv.tipo.Filme;

public class MostraFilme extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextArea areaTexto;


	/**
	 * Create the dialog.
	 */
	public MostraFilme(Filme filme) {
		setLocationByPlatform(true);
		
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
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Filme");
		setResizable(false);
		setBounds(100, 100, 665, 426);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.control);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel painelCapa = new JPanel();
		painelCapa.setBackground(SystemColor.controlHighlight);
		painelCapa.setBounds(6, 6, 217, 197);
		contentPanel.add(painelCapa);
		painelCapa.setLayout(null);
		
		JLabel labelCapa = new JLabel("Capa");
		labelCapa.setBounds(6, 6, 55, 16);
		painelCapa.add(labelCapa);
		
		JPanel painelFilme = new JPanel();
		painelFilme.setBounds(229, 6, 424, 380);
		contentPanel.add(painelFilme);
		painelFilme.setLayout(new CardLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		painelFilme.add(scrollPane, "name_1021433302720510");
		
		areaTexto = new JTextArea();
		areaTexto.setFont(new Font("SansSerif", Font.PLAIN, 14));
		areaTexto.setEditable(false);
		areaTexto.setLineWrap(true);
		scrollPane.setViewportView(areaTexto);
		
		String texto = " Título: " + filme.getTitulo()
				+ "\n Diretor: " + filme.getDiretor()
				+ "\n Autor: " + filme.getAutor()
				+ "\n Elenco: " + filme.getElenco()
				+ "\n Data de lançamento: " + filme.getDataLancamento()
				+ "\n Ano: " + filme.getAno()
				+ "\n Duração: " + filme.getDuracao() + " minutos"
				+ "\n Faixa Etária: " + filme.getClaEtaria()
				+ "\n Mídia: " + filme.getMidia()
				+ "\n Gênero: " + filme.getGenero()
				+ "\n País: " + filme.getPais()
				+ "\n Classificação Pessoal: " + filme.getClaPessoal()
				+ "\n Classificação IMDB: " + filme.getClaIMDB()
				+ "\n\n Sinopse: " + filme.getSinopse();
		

		areaTexto.setText(texto);
		
		ImageIcon img = new ImageIcon(filme.getPoster());
        //JLabel imagem = new JLabel(img);
        //painelCapa.add(imagem);
        //imagem.setBounds(5, 5, 165, 188);
		labelCapa.setText("");
		labelCapa.setBounds(5, 5, 207, 188);

		Image image = img.getImage();
		Image newimg = image.getScaledInstance(217, 197, Image.SCALE_DEFAULT);  
		labelCapa.setIcon(new ImageIcon(newimg));
		
		//labelCapa.setIcon(img);
		
		setVisible(true);
	}
}
