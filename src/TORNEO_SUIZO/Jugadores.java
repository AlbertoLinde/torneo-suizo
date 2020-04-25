package TORNEO_SUIZO;

// @ALBERTO ABREU LINDES
import static TORNEO_SUIZO.Torneo.lecturaDeTeclado;
import java.util.ArrayList;
import java.util.List;

public class Jugadores {

    private int idJugador;
    private String nombreJugador;
    private int puntuacionJugador;
    protected ArrayList<Jugadores> listadoJugadores = new ArrayList<Jugadores>();
    private String numerosID;

    public Jugadores() {

    }

    public Jugadores(int idJugador, String nombreJugador, int puntuacionJugador, String numerosID) {
        this.idJugador = idJugador;
        this.nombreJugador = nombreJugador;
        this.puntuacionJugador = 0;
        this.numerosID = numerosID;
    }

    public void crearJugadores(int numeroJugadores) {

        String nombreJugador;

        for (int i = 0; i < numeroJugadores; i++) {
            System.out.print("- Introduce el nombre del Jugador " + (i + 1) + ": ");
            nombreJugador = lecturaDeTeclado.next();
            añadirNombreJugadores(listadoJugadores, i, nombreJugador, puntuacionJugador, numerosID);
        }

        listadoJugadores(listadoJugadores);
    }

    public void añadirNombreJugadores(List listadoJugadores, int idJugador, String nombreJugador, int puntuacion, String numerosID) {
        listadoJugadores.add(new Jugadores(idJugador, nombreJugador, puntuacionJugador, numerosID));
    }

    // Imprimimos por consola cada uno de los nombres de los jugadores con su ID.
    public String listadoJugadores(List listadoJugadores) {
        System.out.println("-----------------------------------------");
        System.out.printf("%-20s %-15s\n", "| ID JUGADOR", "NOMBRE DEL JUGADOR |");
        System.out.println("-----------------------------------------");
        Jugadores jugador;
        for (Object listadoJugadore : listadoJugadores) {
            jugador = (Jugadores) listadoJugadore;
            System.out.printf("%-20s %-15s\n", jugador.getIdJugador(), jugador.getNombreJugador());
        }
        System.out.println("-----------------------------------------");

        return "";
    }

    public void comenzarPartida(int numeroRondas) {
        Partidas nuevaPartida = new Partidas();
        //nuevaPartida.asignarPrimeraRonda(listadoJugadores);
        nuevaPartida.primeraPartidaRandom(listadoJugadores);

        for (int i = 0; i < numeroRondas-1; i++) {
            nuevaPartida.nuevoEnfrentamiento(listadoJugadores);
            nuevaPartida.imprimirResultados(listadoJugadores);
        }

    }

    // Getter y Setters
    public int getPuntuacionJugador() {
        return puntuacionJugador;
    }

    public void setPuntuacionJugador(int puntuacionJugador) {
        this.puntuacionJugador = puntuacionJugador;
    }

    public int getIdJugador() {
        return idJugador;
    }

    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getNumerosID() {
        return numerosID;
    }

    public void setNumerosID(String numerosID) {
        this.numerosID = numerosID;
    }

}
