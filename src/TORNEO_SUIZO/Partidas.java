package TORNEO_SUIZO;

// @Alberto Abreu Lindes
import static TORNEO_SUIZO.Torneo.lecturaDeTeclado;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Partidas extends Jugadores {

    private int ronda;
    private Jugadores jugadorPrimero;
    private Jugadores jugadorSegundo;
    private Jugadores jugadorImpar;

    private final int PARTIDA_GANADA = 3;
    private final int PARTIDA_EMPATADA = 1;

    Random random = new Random();

    /*             
            PARTIDO SIN RESULTADO.    
            JUGADOR 1 GANA.
            JUGADOR 2 GANA.
            EMPATE 0.5 PUNTO PARA CADA UNO.      
     */
    public Partidas() {

    }

    public Partidas(int ronda, Jugadores jugadorPrimero, Jugadores jugadorSegundo, Jugadores jugadorImpar, int idJugador, String nombreJugador, int puntuacionJugador, ArrayList<Integer> idOponentes, String numerosID) {
        super(idJugador, nombreJugador, puntuacionJugador, numerosID);
        this.ronda = ronda;
        this.jugadorPrimero = jugadorPrimero;
        this.jugadorSegundo = jugadorSegundo;
        this.jugadorImpar = jugadorImpar;
    }

    public void imprimirResultados(List listadoJugadores) {
        ordenarListadoPorPuntuacion(listadoJugadores);
        puntuacionTotal(listadoJugadores);

    }

    public void primeraPartidaRandom(List listadoJugadores) {

        ArrayList<Jugadores> jugadoresElegidos = new ArrayList<Jugadores>(listadoJugadores);

        while (jugadoresElegidos.size() > 1) {

            int indexPrimerJugador = random.nextInt(jugadoresElegidos.size());
            Jugadores jugadorUno = jugadoresElegidos.get(indexPrimerJugador);
            int idEvitarUno = jugadorUno.getIdJugador();
            jugadoresElegidos.remove(indexPrimerJugador);

            int indexSegundoJugador = random.nextInt(jugadoresElegidos.size());
            Jugadores jugadorDos = jugadoresElegidos.get(indexSegundoJugador);
            int idEvitarDos = jugadorDos.getIdJugador();
            jugadoresElegidos.remove(indexSegundoJugador);

            // [PDT MEJORAR] Pasar String -> Array.
            jugadorUno.setNumerosID(idEvitarDos + ",");
            jugadorDos.setNumerosID(idEvitarUno + ",");

            asignarPuntuacion(jugadorUno, jugadorDos);
        }

        if (jugadoresElegidos.size() == 1) {
            ultimoJugador(jugadoresElegidos);
        }
        imprimirResultados(listadoJugadores);
    }

    public int sumarPuntuacion(int puntuacionObtenida, Jugadores jugador) {
        int puntuacionTotal = jugador.getPuntuacionJugador() + puntuacionObtenida;
        return puntuacionTotal;
    }

    public void asignarPuntuacion(Jugadores jugadorUno, Jugadores jugadorDos) {

        System.out.print(jugadorUno.getNombreJugador().toUpperCase() + " vs " + jugadorDos.getNombreJugador().toUpperCase() + ": ");
        int resultadoPartida = lecturaDeTeclado.nextInt();

        if (resultadoPartida == 1) {
            jugadorUno.setPuntuacionJugador(sumarPuntuacion(PARTIDA_GANADA, jugadorUno));
        } else if (resultadoPartida == 2) {
            jugadorUno.setPuntuacionJugador(sumarPuntuacion(PARTIDA_EMPATADA, jugadorUno));
            jugadorDos.setPuntuacionJugador(sumarPuntuacion(PARTIDA_EMPATADA, jugadorDos));
        } else if (resultadoPartida == 3) {
            jugadorDos.setPuntuacionJugador(sumarPuntuacion(PARTIDA_GANADA, jugadorDos));
        }
    }

    public void puntuacionTotal(List listadoJugadores) {

        System.out.println("---------------------------------");
        System.out.printf("%-20s %-15s\n", "| JUGADOR", "PUNTUACIÓN |");
        System.out.println("---------------------------------");
        for (Object listado : listadoJugadores) {
            Jugadores jugador = (Jugadores) listado;
            System.out.printf("%-20s %-15s\n", jugador.getNombreJugador().toUpperCase(), jugador.getPuntuacionJugador());
        }
        System.out.println("---------------------------------");
    }

    public void ordenarListadoPorPuntuacion(List listadoJugadores) {
        Collections.sort(listadoJugadores, new Comparator<Jugadores>() {

            @Override
            public int compare(Jugadores t, Jugadores t1) {
                return Integer.valueOf(t1.getPuntuacionJugador()).compareTo(t.getPuntuacionJugador());
            }
        });
    }

    // [METODO PRIMERA RONDA] Este metodo simplemente recibe el array de los jugadores elegidos
    // en la primera ronda y le asigna la puntuación de 1 en el caso de que sea impar el torneo.
    public void ultimoJugador(List jugElegidos) {
        for (Object listado : jugElegidos) {
            Jugadores jugador = (Jugadores) listado;
            int puntuacionSumar = jugador.getPuntuacionJugador() + 1;
            String partidaRegalada = "PARTIDA_IMPAR,";
            jugador.setNumerosID(partidaRegalada);
            jugador.setPuntuacionJugador(puntuacionSumar);
            System.out.println("[AVISO] En la primera ronda el jugador " + jugador.getNombreJugador().toUpperCase()
                    + " obtiene 1 punto ya que el número total de jugadores es impar.");
        }

    }

    public void oponentesEnfrentados(Jugadores jugadorUno, Jugadores jugadorDos) {
        String opoUno = jugadorUno.getNumerosID() + Integer.toString(jugadorDos.getIdJugador()) + ",";
        String opoDos = jugadorDos.getNumerosID() + Integer.toString(jugadorUno.getIdJugador()) + ",";
        jugadorUno.setNumerosID(opoUno);
        jugadorDos.setNumerosID(opoDos);

    }

    public void nuevoEnfrentamiento(List listadoJugadores) {

        ArrayList<Jugadores> jugadoresElegidos = new ArrayList<Jugadores>(listadoJugadores);

        while (jugadoresElegidos.size() > 1) {
            Jugadores jugadorUno = (Jugadores) jugadoresElegidos.get(0);
            int idJugador = jugadorUno.getIdJugador();
            Jugadores jugadorDos = null;
            for (int i = 1; i < jugadoresElegidos.size(); i++) {
                jugadorDos = jugadoresElegidos.get(i);
                if (jugadorDos.getNumerosID().contains(Integer.toString(idJugador))) {
                    continue;
                } else if (!jugadorDos.getNumerosID().contains(Integer.toString(idJugador))) {
                    jugadorDos = jugadoresElegidos.get(i);
                    break;
                } else if (jugadorDos.getNumerosID().contains(Integer.toString(idJugador)) && i == jugadoresElegidos.size()) {
                    jugadorDos = jugadoresElegidos.get(i);
                }
            }

            if (jugadoresElegidos.size() == 1) {
                ultimoJugador(jugadoresElegidos);
            }

            asignarPuntuacion(jugadorUno, jugadorDos);
            oponentesEnfrentados(jugadorUno, jugadorDos);
            jugadoresElegidos.remove(jugadorUno);
            jugadoresElegidos.remove(jugadorDos);

        }

    }

    // Rondas
    public void pedirMenuRondas(int opcMenu) {
        switch (opcMenu) {
            case 1:
                imprimirResultados(listadoJugadores);
                break;
            case 2:
                System.exit(0);
        }

    }

    // Getter y Setter
    public int getRonda() {
        return ronda;
    }

    public void setRonda(int ronda) {
        this.ronda = ronda;
    }

    public Jugadores getJugadorPrimero() {
        return jugadorPrimero;
    }

    public void setJugadorPrimero(Jugadores jugadorPrimero) {
        this.jugadorPrimero = jugadorPrimero;
    }

    public Jugadores getJugadorSegundo() {
        return jugadorSegundo;
    }

    public void setJugadorSegundo(Jugadores jugadorSegundo) {
        this.jugadorSegundo = jugadorSegundo;
    }

    public ArrayList<Jugadores> getListadoJugadores() {
        return listadoJugadores;
    }

    public void setListadoJugadores(ArrayList<Jugadores> listadoJugadores) {
        this.listadoJugadores = listadoJugadores;
    }

}
