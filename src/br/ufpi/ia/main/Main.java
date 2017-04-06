package br.ufpi.ia.main;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import br.ufpi.ia.buscas.BuscaAEstrela;
import br.ufpi.ia.buscas.BuscaEmLargura;
import br.ufpi.ia.buscas.BuscaEmProfundidade;
import br.ufpi.ia.buscas.BuscaGulosa;
import br.ufpi.ia.estados.Estados;

public class Main {

	private JFrame frame;
	JTextArea textArea;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 682, 463);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 639, 347);
		frame.getContentPane().add(scrollPane);

		textArea = new JTextArea(20, 20);
		scrollPane.setViewportView(textArea);

		JLabel lblMissionarios = new JLabel("Missionarios = 03");
		lblMissionarios.setBounds(10, 15, 108, 29);
		frame.getContentPane().add(lblMissionarios);

		JLabel lblCanibais = new JLabel("Canibais = 03");
		lblCanibais.setBounds(120, 20, 89, 19);
		frame.getContentPane().add(lblCanibais);

		JLabel lblBarco = new JLabel("Barco = margem direita");
		lblBarco.setBounds(220, 20, 160, 19);
		frame.getContentPane().add(lblBarco);

		JButton btnBuscar = new JButton("Buscar");

		JButton btnResetar = new JButton("Resetar");

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = "";
				Estados.resultado = str;
				long inicioPesquisa, fimPesquisa;
				Estados resultado;

				// Valor inicial de mission�rios,canibais e capacidade de
				// passageiros do barco.
				Estados.inicializar(3, 3, 2);

				Estados.resultado += "--------------------------------------------------\n";
				Estados.resultado += "Buscar em largura iniciada:\n";
				Estados.resultado += "--------------------------------------------------\n";

				// Busca em largura.
				BuscaEmLargura buscaEmLargura = new BuscaEmLargura();
				inicioPesquisa = System.currentTimeMillis();
				resultado = buscaEmLargura.buscar();
				fimPesquisa = System.currentTimeMillis();
				buscaEmLargura.imprimirResultado(resultado);
//				Estados.resultado += bl.getQuantidadeDeNosExpandidos() + "\n";
				Estados.resultado += "Tempo da busca em largura: "
						+ (fimPesquisa - inicioPesquisa) + " milisegundos.\n";
				textArea.setText(Estados.resultado);

				Estados.resultado += "--------------------------------------------------\n";
				Estados.resultado += "Buscar em profundidade iniciada:\n";
				Estados.resultado += "--------------------------------------------------\n";

				// Busca em profundidade.
				BuscaEmProfundidade buscaEmProfundidade = new BuscaEmProfundidade();
				inicioPesquisa = System.currentTimeMillis();
				resultado = buscaEmProfundidade.buscar();
				//Estados.resultado += bl.getQuantidadeDeNosExpandidos() + "\n";
				fimPesquisa = System.currentTimeMillis();
				buscaEmProfundidade.imprimirResultado(resultado);
				Estados.resultado += "Tempo da busca em profundidade: "
						+ (fimPesquisa - inicioPesquisa) + " milisegundos.\n";

				Estados.resultado += "--------------------------------------------------\n";
				Estados.resultado += "Buscar A* iniciada:\n";
				Estados.resultado += "--------------------------------------------------\n";

				// Busca A*.
				BuscaAEstrela buscaAEstrela = new BuscaAEstrela();
				inicioPesquisa = System.currentTimeMillis();
				resultado = buscaAEstrela.buscar();
				fimPesquisa = System.currentTimeMillis();
				buscaAEstrela.imprimirResultado(resultado);
//				Estados.resultado += buscaAEstrela.getQuantidadeDeNosExpandidos() + "\n";
				Estados.resultado += "Tempo da busca A*: "
						+ (fimPesquisa - inicioPesquisa) + " milisegundos.\n";

				Estados.resultado += "--------------------------------------------------\n";
				Estados.resultado += "Buscar gulosa iniciada: \n";
				Estados.resultado += "--------------------------------------------------\n";

				// Busca gulosa.
				BuscaGulosa buscaGulosa = new BuscaGulosa();
				inicioPesquisa = System.currentTimeMillis();
				resultado = buscaGulosa.buscar();
				fimPesquisa = System.currentTimeMillis();
				buscaGulosa.imprimirResultado(resultado);
//				Estados.resultado += bl.getQuantidadeDeNosExpandidos() + "\n";
				Estados.resultado += "Tempo da busca gulosa: "
						+ (fimPesquisa - inicioPesquisa) + " milisegundos.\n";

				textArea.setText(Estados.resultado);
				System.out.println(Estados.resultado);
			}
		});

		btnBuscar.setBounds(450, 15, 89, 23);
		frame.getContentPane().add(btnBuscar);

		btnResetar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String str = "";

				textArea.setText(str);
			}
		});

		btnResetar.setBounds(548, 15, 89, 23);
		frame.getContentPane().add(btnResetar);
	}
}
