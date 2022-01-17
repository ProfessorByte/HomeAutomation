package auxiliaryElements;

public class NodoSE<T> {

    private NodoSE<T> suc;
    private T dato;

    public NodoSE(T dato) {
        this.dato = dato;
    }

    public void setSuc(NodoSE<T> n) {
        suc = n;
    }

    public void setDato(T dato) {
        this.dato = dato;
    }

    public NodoSE<T> getSuc() {
        return suc;
    }

    public T getDato() {
        return dato;
    }
}
