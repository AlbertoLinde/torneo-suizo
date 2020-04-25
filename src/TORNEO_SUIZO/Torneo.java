package TORNEO_SUIZO;

// @Alberto Abreu Lindes
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Torneo {

    private int numeroRondas;
    private int rondaActual = 0;
    private int numeroJugadores = 0;
    private int NUMERO_MIN = 0;
    private int NUMERO_MAX_JUGADORES = 10;
    private int NUMERO_MAX_RONDAS = numeroJugadores;

    static Scanner lecturaDeTeclado = new Scanner(System.in);

    public void solicitarNumeroJugadores() {

        System.out.println("----- NUMERO DE JUGADORES -----");

        do {
            System.out.print("- Introduce el número de jugadores totales: ");
          while (!lecturaDeTeclado.hasNextInt()) {
                System.out.println("- ERROR: No es un número.");
                System.out.print("- Introduce un valor númerico: ");
                lecturaDeTeclado.next();
            }

            numeroJugadores = lecturaDeTeclado.nextInt();
        } while (numeroJugadores <= NUMERO_MIN || numeroJugadores > NUMERO_MAX_JUGADORES);
    }

    public void solicitarNumeroRondas() {

        System.out.println("----- NUMERO DE RONDAS -----");

        do {
            System.out.print("- Introduce un valor entre 1-" + numeroJugadores + ": ");
            while (!lecturaDeTeclado.hasNextInt()) {
                System.out.println("- ERROR: No es un número.");
                System.out.print("- Introduce un valor entre 1-" + numeroJugadores + ": ");
                lecturaDeTeclado.next();
            }
            numeroRondas = lecturaDeTeclado.nextInt();
        } while (numeroRondas <= NUMERO_MIN);
    }

    public void comenzarTorneo() {
        solicitarNumeroJugadores();
        solicitarNumeroRondas();
        Jugadores nuevos = new Jugadores();
        nuevos.crearJugadores(numeroJugadores);
        nuevos.comenzarPartida(numeroRondas);
    }

}
