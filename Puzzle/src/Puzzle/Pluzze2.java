package Puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Stack;

import Arbol.Nodo;

public class Pluzze2 {
	static int n =4;
	static final int[][] solucion = MakeSolution(n);

	public static void main(String[] args) {

		//int[][] inicio = RandomFillTokens(n);
		//int[][] inicio = { { 8, 1, 2 }, {7, 6, 4 }, { 0, 5, 3 } };
		//int[][] inicio = { { 1, 6, 2 }, {5, 8, 7 }, { 4, 0, 3 } };
		int[][] inicio = { { 4, 14, 3, 7 }, { 1, 10, 8, 0 }, { 9, 11, 2, 12 }, { 13, 6, 5, 15 } };
		
		System.out.println("sin solucionar");
		printMatriz(inicio);
		System.out.println("solucion");
		printMatriz(solucion);

		Nodo sol = findSolution(inicio, solucion, n);
		Stack<Nodo> pasos = new Stack<>();
		System.out.println("pasos:");
		printMatriz(inicio);
		System.out.println();
		while (sol.getPadre() != null) {
			pasos.add(sol);
			sol = sol.getPadre();
		}
		int count = 0;
		while (!pasos.isEmpty()) {
			printMatriz(pasos.pop().getInfo());
			System.out.println();
			count++;
		}
		System.out.println(count);

	}

	static void printMatriz(int[][] matriz) {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				System.out.print("[" + matriz[i][j] + "]");
			}
			System.out.println("");
		}
	}

	static Nodo findSolution(int[][] problem, int[][] solution, int n) {
		Nodo revisar = new Nodo(problem);
		ArrayList<Nodo> Recorridos = new ArrayList<>();
		int menorCoste;
		ArrayList<Nodo> visitados = new ArrayList<>();
		int count = 0;
		while (true) {
			visitados.add(revisar);
			
			if (Arrays.deepEquals(revisar.getInfo(), solution)) {
				System.out.println(count);
				return revisar;
			}
			int posicion = -1;
			ArrayList<Nodo> hijos = generateChildren(revisar, n,visitados);
			for (Nodo hijo : hijos) {
				Recorridos.add(hijo);
				if (hijo.getPadre().getPadre() != null) {
					if (Arrays.deepEquals(hijo.getInfo(), hijo.getPadre().getPadre().getInfo())) {
						Recorridos.remove(hijo);
						posicion = hijos.lastIndexOf(hijo);
					}
				}
			}
			if (posicion != -1) {
				hijos.remove(posicion);
				revisar.setHijos(hijos);
			}

			menorCoste = Integer.MAX_VALUE;
			for (Nodo nodo : Recorridos) {
				int auxCost = minCost(nodo, n);
				if (auxCost < menorCoste ) {
					revisar = nodo;
					menorCoste = auxCost;
				}
			}
			
			Recorridos.remove(revisar);
			count++;
		}
	}

	static ArrayList<Nodo> generateChildren(Nodo revisar, int n,ArrayList<Nodo> visitados) {
		int[] pCero = ubicarPosicionCero(revisar.getInfo());
		ArrayList<Nodo> hijos = new ArrayList<>();
		if (pCero[0] != 0) {
			Nodo hijo = new Nodo(clone(revisar.getInfo()));
			int arriba = hijo.getInfo()[pCero[0] - 1][pCero[1]];
			hijo.getInfo()[pCero[0]][pCero[1]] = arriba;
			hijo.getInfo()[pCero[0] - 1][pCero[1]] = 0;
			if (!isVisited(visitados, hijo)) {
				hijos.add(hijo);
			}
		}
		if (pCero[0] != n - 1) {
			Nodo hijo = new Nodo(clone(revisar.getInfo()));
			int abajo = hijo.getInfo()[pCero[0] + 1][pCero[1]];
			hijo.getInfo()[pCero[0]][pCero[1]] = abajo;
			hijo.getInfo()[pCero[0] + 1][pCero[1]] = 0;
			if (!isVisited(visitados, hijo)) {
				hijos.add(hijo);
			}
		}
		if (pCero[1] != 0) {
			Nodo hijo = new Nodo(clone(revisar.getInfo()));
			int izquierda = hijo.getInfo()[pCero[0]][pCero[1] - 1];
			hijo.getInfo()[pCero[0]][pCero[1]] = izquierda;
			hijo.getInfo()[pCero[0]][pCero[1] - 1] = 0;
			if (!isVisited(visitados, hijo)) {
				hijos.add(hijo);
			}
		}
		if (pCero[1] != n - 1) {
			Nodo hijo = new Nodo(clone(revisar.getInfo()));
			int derecha = hijo.getInfo()[pCero[0]][pCero[1] + 1];
			hijo.getInfo()[pCero[0]][pCero[1]] = derecha;
			hijo.getInfo()[pCero[0]][pCero[1] + 1] = 0;
			if (!isVisited(visitados, hijo)) {
				hijos.add(hijo);
			}
		}
		revisar.setHijos(hijos);
		return hijos;
	}

	static int minCost(Nodo x, int n) {
		return fatherDistance(x) + uncorredPosition(x, n);
	}

	static int fatherDistance(Nodo x) {
		int counter = 0;
		Nodo aux = x;
		while (aux.getPadre() != null) {
			counter++;
			aux = aux.getPadre();
		}
		return counter;
	}

	static int uncorredPosition(Nodo x, int n) {
		int[][] ordenada = solucion;
		int counter = 0;
		for (int i = 0; i < ordenada.length; i++) {
			for (int j = 0; j < ordenada.length; j++) {
				if (x.getInfo()[i][j] != ordenada[i][j]) {
					counter++;
				}
			}
		}
		return counter;
	}

	static int[] ubicarPosicionCero(int[][] matriz) {
		int[] vectorCero = new int[2]; // guardar la posici�n de cero
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz.length; j++) {
				if (matriz[i][j] == 0) {
					vectorCero[0] = i;
					vectorCero[1] = j;
				}
			}
		}
		// System.out.println("la posicion de cero es: " + vectorCero[0] + " " +
		// vectorCero[1]);
		return vectorCero;
	}

	static int[][] MakeSolution(int n) {
		int aux = 1;
		int[][] solucion = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == n - 1 && j == n - 1) {
					solucion[i][j] = 0;
				} else {
					solucion[i][j] = aux;
					aux++;
				}
			}
		}

		return solucion;

	}

	static int[][] RandomFillTokens(int n) {
		int[][] Ordenada = clone(solucion);
		Random rd = new Random();
		int[] pCero = ubicarPosicionCero(Ordenada);
		Nodo x = new Nodo(Ordenada);
		while(uncorredPosition(x, n) != (n*n)) {
			x.setInfo(Ordenada);
			pCero = ubicarPosicionCero(Ordenada);
			switch (rd.nextInt(4)) {
			case 0:
				if (pCero[0] != 0) {
					int arriba = Ordenada[pCero[0] - 1][pCero[1]];
					Ordenada[pCero[0]][pCero[1]] = arriba;
					Ordenada[pCero[0] - 1][pCero[1]] = 0;
				}
				break;
			case 1:
				if (pCero[0] != n - 1) {

					int abajo = Ordenada[pCero[0] + 1][pCero[1]];
					Ordenada[pCero[0]][pCero[1]] = abajo;
					Ordenada[pCero[0] + 1][pCero[1]] = 0;
				}
				break;
			case 2:
				if (pCero[1] != 0) {

					int izquierda = Ordenada[pCero[0]][pCero[1] - 1];
					Ordenada[pCero[0]][pCero[1]] = izquierda;
					Ordenada[pCero[0]][pCero[1] - 1] = 0;
				}
				break;
			case 3:
				if (pCero[1] != n - 1) {

					int derecha = Ordenada[pCero[0]][pCero[1] + 1];
					Ordenada[pCero[0]][pCero[1]] = derecha;
					Ordenada[pCero[0]][pCero[1] + 1] = 0;
				}
				break;
			}
		}
		return Ordenada;
	}

	static int[][] clone(int[][] matriz) {
		int[][] clon = new int[matriz.length][matriz.length];
		for (int i = 0; i < matriz.length; i++) {
			clon[i] = matriz[i].clone();
		}
		return clon;
	}

	private static boolean isVisited(ArrayList<Nodo> visitados, Nodo hijo) {
		for (Nodo nodo : visitados) {
			if (Arrays.deepEquals(nodo.getInfo(), hijo.getInfo())) {
				return true;
			}
		}
		return false;
	}
}