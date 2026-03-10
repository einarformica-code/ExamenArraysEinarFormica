package examenArrays;

import java.nio.file.spi.FileSystemProvider;
import java.util.Arrays;
import java.util.Scanner;

public class Principal {
	/**
	 * 
	 * @param sala. Coge sala como parametro y la rellena con el caracter 'L'
	 */
	public static void rellenar(char [][] sala) {
		for (int filas=0; filas<sala.length;filas++) {
			for(int columnas=0; columnas<sala[0].length;columnas++) {
				sala[filas][columnas]='L';
			}
		}
		
	}
	/**
	 * 
	 * @param sala. Toma sala como parametro e imprime cada posicion del array.
	 */
	public static void pintarSala(char [][] sala) {
		
		for(int pintarNumeradorColumnas=0; pintarNumeradorColumnas<sala[0].length; pintarNumeradorColumnas++) {
			System.out.print((pintarNumeradorColumnas +1) + " " );
		}
		System.out.println();
		for (int filas=0; filas<sala.length;filas++) {
			for(int columnas=0; columnas<sala[filas].length;columnas++) {
				
				
				if (columnas==0) {
					System.out.print(filas + 1 );
				}
				
				
				System.out.print(sala[filas][columnas] +" ");
			}
			
			System.out.println();
		}
	}
	
	/**
	 * 
	 * @param sala Array con las estado en cada posicion
	 * @param fila. Fila especifica a validar
	 * @param columna. Columna especifica a validar
	 * @return True si no se sale de los límtes (numeros negativos // maximos de longitud para filas ó columnas.)
	 */
	public static boolean esPosicionValida(char[][]sala, int fila, int columna) {
		return fila<sala.length && columna<sala[fila].length && fila>-1 && columna>-1;
		
	}
	/**
	 * 
	 * @param sala. Array tablero.
	 * @param estadoComprobar char (L, R u 0) a contar
	 * @return devuelve las veces que ese caracter se repite
	 */
	public static int contarEstado(char[][]sala, char estadoComprobar) {
		int contarEstado=0;
		
		for (int filas=0; filas<sala.length;filas++) {
			for(int columnas=0; columnas<sala[0].length;columnas++) {
				if(sala[filas][columnas]==estadoComprobar) {
					++contarEstado;
					}
				}
		}
		return contarEstado;
		
		
	}
	
	/**
	 * 
	 * @param sala
	 * @param fila
	 * @param numeroPersonas
	 * @return si se ha conseguido, se devuelve un array con la columna inicial e final que se hareservado. 
	 * En caso contario devuelve nulo.
	 */
	public static int[] reservarGrupoEnFila(char[][] sala, int fila, int numeroPersonas) {
		int[] columnasInicioFin = new int [2];
		int posicionesLibresConsecutivas=0;
		int primeraPosicion = 0;
		
		for (int recorrerPosiciones=0;recorrerPosiciones<sala.length; recorrerPosiciones++) {
			if (sala[fila][recorrerPosiciones] == 'L') {
				++posicionesLibresConsecutivas;
				if(posicionesLibresConsecutivas==0) {
					primeraPosicion=recorrerPosiciones;
				}
			}else {
				posicionesLibresConsecutivas=0;
			}		
			
		}
		
		if(posicionesLibresConsecutivas>= numeroPersonas) {
			for (int rellenarPosiciones=0; rellenarPosiciones<numeroPersonas; rellenarPosiciones++) {
				sala[fila][primeraPosicion + rellenarPosiciones] = 'R';
			}
			columnasInicioFin[0]= primeraPosicion;
			columnasInicioFin[1]=primeraPosicion + numeroPersonas;
			return columnasInicioFin;
		}else {
			return null;
		}
		
		
		
		
	}
	/**
	 * 
	 * @param sala 
	 * @param fila que se va a reservar
	 * @param columna que se va a reservar
	 * @return true si se consigue reservar
	 */
	public static boolean reservarAsientoSuelto(char[][]sala, int fila, int columna) {
		if (!esPosicionValida(sala, fila, columna)){
			return false;
		}
		
		if (sala[fila][columna] != 'L') {
			return false;
		}
		
		sala[fila][columna]='R';
		return true;
	}
	/**
	 * 
	 * @param sala. Susituye las posiciones ya reservadas 'R' por '0'.
	 */
	
	
	public static void confirmarReservas(char[][] sala) {
		for (int filas=0; filas<sala.length;filas++) {
			for(int columnas=0; columnas<sala[filas].length;columnas++) {
				if (sala[filas][columnas]=='R') {
					sala[filas][columnas]='0';
				}
				
	
			}
			
		}
		
	}
	/**
	 * 
	 * @param sala. Imprime asientos libres, reservados inicialmente y reservados completamente.
	 */
public static void mostrarEstadisticas(char[][]sala) {
		
		int asientosLibres =contarEstado(sala, 'L');
		int asientosReservadoInicial = contarEstado(sala, 'R');
		int asientosReservadoFinal=contarEstado(sala, '0');
		double porcentajeReservadosReales = asientosReservadoFinal/asientosReservadoInicial * 100;
		
		System.out.println("Asientos libres: " +asientosLibres);
		System.out.println("Asientos pre-reservados : " +asientosReservadoInicial);
		System.out.println("Asientos reservados: " + asientosReservadoFinal);
		System.out.println("Porcentaje de reservas reales: " + porcentajeReservadosReales + "%.");
		
	}
	
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int respuesta;
		
		char[][] sala =new char[6][10];
		
		rellenar(sala);
		
		do {
			
		
		System.out.println("1.Mostrar sala");
		System.out.println("2.Reservar Asiento Suelto");
		System.out.println("3.Reservar Grupo en una fila");
		System.out.println("4.Confirmar Reservas");
		System.out.println("5.Cancelar Reservas");
		System.out.println("6.Estadísticas");
		System.out.println("7.Salir");
		
		
		
		respuesta=sc.nextInt();
		
			switch (respuesta){
		case 1: pintarSala(sala);
		break;
		case 2:
			System.out.println("Fila?");
			int fila = sc.nextInt() -1;
			System.out.println("Columna?");
			int columna=sc.nextInt() -1;
			if (reservarAsientoSuelto(sala, fila, columna)) {
				System.out.println("Se ha reservado el asiento " + (fila +1) + ", " +(columna +1));
			}else {
				System.out.println("No se ha podido reservar (Posicion invalida u asiento ya reservado)");
			}
			break;
		case 3:
			System.out.println("Fila?");
			fila = sc.nextInt() -1;
			
			System.out.println("Numero de personas?");
			int numeroPersonas = sc.nextInt();
			
			if (reservarGrupoEnFila(sala, fila, numeroPersonas) !=null) {
				System.out.println("Se ha reservado la fila.");
				
			}else {
				System.out.println("No se ha podido reservar la fila");
			}
			break;
		case 4:
			confirmarReservas(sala);
			System.out.println("Se han confirmado las reservas");
			break;
		case 5:
			rellenar(sala);
			System.out.println("Se han cancelado las reservas");
			break;
			
		case 6:
		mostrarEstadisticas(sala);
			}
		
	
		}while(respuesta!=7);
	
		sc.close();
		
		
		
		
		
		
	}

}
