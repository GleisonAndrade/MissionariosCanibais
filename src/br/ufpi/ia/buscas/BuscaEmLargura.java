package br.ufpi.ia.buscas;

import java.util.HashMap;
import java.util.LinkedList;

import br.ufpi.ia.estados.Estados;

public class BuscaEmLargura {

	private static BuscaEmLargura busca = new BuscaEmLargura();

	// Criar os estados iniciais: mission�rios=3,canibais=3,margem=1.
	public Estados inicio = new Estados(Estados.getMissionarios(),
			Estados.getCanibais(), 1, null);

	public Estados buscar() {

		// Iniciar o algoritimo de Busca em Largura.
		Estados resultado = busca.buscaEmLargura(inicio);
		return resultado;
	}

	public void imprimirResultado(Estados resultado) {
		// Verificar se a solu��o foi encontrada.
		if (resultado == Estados.getNaoEncontrada()) {
			Estados.resultado += "Solucao nao encontrada.\n";

		} else {
			// Uma boa solu��o foi encontrada? Salvar o caminho.
			LinkedList<Estados> caminho = resultado.getCaminho();
			Estados.resultado += "MISSIONARIOS | CANIBAIS\t\tMISSIONARIOS | CANIBAIS\n";
			String string = "";
			// Mostrar o caminho.
			for (Estados cam : caminho) {
				string += "\t" + (3 - cam.getMis()) + " | "
						+ (3 - cam.getCan()) + "\t\t\t" + cam.getMis() + " | "
						+ cam.getCan() + "\t|| ";
				if (cam.getMargem() == 1) {
					string += "Margem direita.\n";
				} else {
					string += "Margem esquerda.\n";
				}
			}

			Estados.resultado += string + "\n";
		}

	}

	private Estados buscaEmLargura(Estados estado) {

		LinkedList<Estados> salvar = new LinkedList<Estados>();
		HashMap<String, Estados> visitados = new HashMap<String, Estados>();

		// Adicionar o estado inicial.
		salvar.addLast(estado);
		int qtdMovimentos = 0;
		int quantidadeDeNosExpandidos = 0;
		
		while (salvar.size() != 0) {
			// Descartar o no que ja foi visitado.
			Estados atual = salvar.poll();

			// Adicionar o elemento aos j� visitados.
			visitados.put(atual.toString(), atual);

			Estados.resultado += "\nEstado atual: " + atual.toString() + "\n";
			qtdMovimentos++;
			// Verificar se esse � o objetivo.
			if (atual.equals(Estados.getSolucao())) {
				Estados.resultado += "Busca em largura finalizada, total de movimentos: "
						+ Integer.toString(qtdMovimentos) + ".\n";
				Estados.resultado += "Busca em largura finalizada, total nos gerados: "
						+ Integer.toString(quantidadeDeNosExpandidos) + ".\n";
				return atual;
			}

			// Verificar se � um sucessor valido.
			for (Estados sucessorValido : atual.proxMovimentos()) {
				quantidadeDeNosExpandidos += 1;
				if (visitados.containsKey(sucessorValido.toString())) {
					continue;
				}
				// Adicionar como retorno.
				salvar.addLast(sucessorValido);
//				quantidadeDeNosExpandidos ++;
			}
		}
		return Estados.getNaoEncontrada();
	}	
	
}