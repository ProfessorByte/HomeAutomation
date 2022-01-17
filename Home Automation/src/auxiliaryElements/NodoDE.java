package auxiliaryElements;

public class NodoDE<T> {

    private NodoDE<T> ant, suc;
    private T dato;

    public NodoDE(T dato) {
        this.dato = dato;
    }

    public void setAnt(NodoDE<T> n) {
        ant = n;
    }

    public void setSuc(NodoDE<T> n) {
        suc = n;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoDE<T> getAnt() {
        return ant;
    }

    public NodoDE<T> getSuc() {
        return suc;
    }

    public T getDato() {
        return dato;
    }
}
