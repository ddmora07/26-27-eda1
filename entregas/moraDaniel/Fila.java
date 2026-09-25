
public class Fila {

    private Nodo primerNodo;
    private int cantidadDePersonasEnFila = 0;

    public int getCantidadDePersonasEnFila() {
        return cantidadDePersonasEnFila;
    }

    public boolean estaVacia() {
        return cantidadDePersonasEnFila == 0;
    }

    public Persona getPersonaEnPosicion(int posicion) {
        Nodo nodoEnEsaPosicion = obtenerNodoEnPosicion(posicion);
        return nodoEnEsaPosicion.getPersona();
    }


    private Nodo obtenerNodoEnPosicion(int posicion) {
        Nodo nodoActual = primerNodo;
        for (int cantidadDeAvances = 0; cantidadDeAvances < posicion; cantidadDeAvances++) {
            nodoActual = nodoActual.getSiguienteNodo();
        }
        return nodoActual;
    }

    public void insertarEnPosicion(int posicionDondeInsertar, Persona personaAInsertar) {
        Nodo nodoNuevo = new Nodo(personaAInsertar);

        if (posicionDondeInsertar == 0) {
            nodoNuevo.setSiguienteNodo(primerNodo);
            primerNodo = nodoNuevo;
        } else {
            Nodo nodoAnterior = obtenerNodoEnPosicion(posicionDondeInsertar - 1);
            nodoNuevo.setSiguienteNodo(nodoAnterior.getSiguienteNodo());
            nodoAnterior.setSiguienteNodo(nodoNuevo);
        }

        cantidadDePersonasEnFila++;
    }


    public void insertarAlFinal(Persona personaAInsertar) {
        insertarEnPosicion(cantidadDePersonasEnFila, personaAInsertar);
    }

   
    public Persona eliminarEnPosicion(int posicionAEliminar) {
        Nodo nodoAEliminar;

        if (posicionAEliminar == 0) {
            nodoAEliminar = primerNodo;
            primerNodo = primerNodo.getSiguienteNodo();
        } else {
            Nodo nodoAnterior = obtenerNodoEnPosicion(posicionAEliminar - 1);
            nodoAEliminar = nodoAnterior.getSiguienteNodo();
            nodoAnterior.setSiguienteNodo(nodoAEliminar.getSiguienteNodo());
        }

        cantidadDePersonasEnFila--;
        return nodoAEliminar.getPersona();
    }


    public int posicionTrasUltimoPreferente() {
        Nodo nodoActual = primerNodo;
        int posicionActual = 0;
        int posicionTrasUltimoPreferenteEncontrado = 0;

        while (nodoActual != null) {
            if (nodoActual.getPersona().tienePrioridadPreferente()) {
                posicionTrasUltimoPreferenteEncontrado = posicionActual + 1;
            }
            nodoActual = nodoActual.getSiguienteNodo();
            posicionActual++;
        }

        return posicionTrasUltimoPreferenteEncontrado;
    }
}