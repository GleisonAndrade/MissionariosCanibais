package br.ufpi.ia.estados;
import java.util.LinkedList;

public class Estados {
	// Total de canibais.
	private static int canibais = 3;

	// Total de vagas no barco.
	private static int barco = 2;

	// Total de mission�rios.
	private static int missionarios = 3;

	// Vari�veis quantitativas.
	private final int mis;
	private final int can;
	private final int margem;
	private final Estados prox;
	
//	private final int quantidadeNosGerados = 0;

	public static String resultado = "";
	
	// Solu��o n�o encontrada.
	static private Estados naoEncontrada = new Estados(-1, -1, -1, null);

	// Solu��o encontrada
	static private Estados solucao = new Estados(0, 0, 0, null);

	// Movimento para esquerda. Para padr�o � 0,1,2.
	static private int[] esquerda = new int[] { 0, 1, 2 };

	// Movimento para direita. Para padr�o � 0,-1,-2.
	static private int[] direita = new int[] { 0, -1, -2 };
	
	// Estado inicial com os mission�rios e os canibais no lado direito do rio.
	public Estados(int mis, int can, int margem, Estados prox) {
		this.mis = mis;
		this.can = can;
		this.margem = margem;
		this.prox = prox;
	}

	// Inicializar um estado.
	public static void inicializar(int totalM, int totalC, int barcoC) {
		Estados.setCanibais(totalC);
		Estados.setMissionarios(totalM);
		Estados.setBarco(barcoC);

		// Viagens do barco.
		esquerda = new int[barcoC + 1];
		for (int i = 0; i <= barcoC; i++) {
			esquerda[i] = i;
		}
		
		direita = new int[barcoC + 1];
		for (int i = 0; i <= barcoC; i++) {
			direita[i] = i * -1;
		}
	}

	// Retorna true se os mission�rios est�o seguros.
	public boolean missionariosSeguros() {
		// Checa o lado direito.
		if (mis > 0) {
			if (mis < can) {
				return false;
			}
		}

		// Checa o lado esquerdo.
		if ((getMissionarios() - mis) > 0) {
			if ((getMissionarios() - mis) < (getCanibais() - can)) {
				return false;
			}
		}

		// Retorna true se os mission�rios est�o seguras.
		return true;
	}

	// Salvando o caminho percorrido at� o estado corrente.
	public LinkedList<Estados> getCaminho() {
		LinkedList<Estados> caminho = new LinkedList<Estados>();
		caminho.addFirst(this);
		Estados prox = this.prox;
		while (prox != null) {
			caminho.addFirst(prox);
			prox = prox.prox;
		}
		return caminho;
	}

	// Checando a igualdade.
	@Override
	public boolean equals(Object arg0) {
		Estados b = (Estados) arg0;
		return mis == b.mis && can == b.can && margem == b.margem;
	}

	// Imprimir.
	@Override
	public String toString() {
		String s = "Missionarios: " + Integer.toString(mis) + " | " + "Canibais: "
				+ Integer.toString(can) + " | " + "Barco: ";
		if (margem == 1) {
			return s + "Margem direita.";
		} else {
			return s + "Margem esquerda.";
		}
	}

	// Pr�ximo estado v�lido.
	public LinkedList<Estados> proxMovimentos() {
		LinkedList<Estados> proMovimento = new LinkedList<Estados>();

		// Movimentos.
		int[] mover;
		if (margem == 1) {
			mover = direita;
		} else {
			mover = esquerda;
		}
		// Para todos os movimentos dos mission�rios.
		for (int i : mover) {
			// Para todos os movimentos dos canibais.
			for (int j : mover) {
				// Mover apenas 1 de cada.
				if ((Math.abs(i + j) < 1) || (Math.abs(i + j) > barco)) {
					continue;
				}

				// N�o mover mission�rios se valor for igual a 0.
				if ((i + mis < 0 || j + can < 0)
						|| (i + mis > getMissionarios() || j + can > getCanibais())) {
					continue;
				}

				// Estados sucessores.
				Estados proEstado = new Estados(i + mis, j + can, mover == esquerda ? 1 : 0, this);
				// Checar se os mission�rios estar�o seguraros com esse movimentos.
				if (proEstado.missionariosSeguros()) {
					// Se seguros adicionar como pr�ximo movimento.
					proMovimento.add(proEstado);
					resultado += "Proximo movimento valido: " + proEstado + ".\n";
				}
			}
		}
		return proMovimento;
	}

	public static int getMissionarios() {
		return missionarios;
	}

	public static void setMissionarios(int missionarios) {
		Estados.missionarios = missionarios;
	}

	public static int getCanibais() {
		return canibais;
	}

	public static void setCanibais(int canibais) {
		Estados.canibais = canibais;
	}

	public static int getBarco() {
		return barco;
	}

	public static void setBarco(int barco) {
		Estados.barco = barco;
	}

	public int getMis() {
		return mis;
	}

	public int getCan() {
		return can;
	}

	public int getMargem() {
		return margem;
	}

	public Estados getProx() {
		return prox;
	}

	public static Estados getNaoEncontrada() {
		return naoEncontrada;
	}

	public static void setNaoEncontrada(Estados naoEncontrada) {
		Estados.naoEncontrada = naoEncontrada;
	}

	public static Estados getSolucao() {
		return solucao;
	}

	public static void setSolucao(Estados solucao) {
		Estados.solucao = solucao;
	}

	public static int[] getEsquerda() {
		return esquerda;
	}

	public static void setEsquerda(int[] esquerda) {
		Estados.esquerda = esquerda;
	}

	public static int[] getDireita() {
		return direita;
	}

	public static void setDireita(int[] direita) {
		Estados.direita = direita;
	}
}