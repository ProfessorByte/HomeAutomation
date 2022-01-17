package dataStructures;

import auxiliaryElements.NodoDE;
import auxiliaryElements.NodoSE;

public class ListaCDE<T> {

    private NodoDE<T> ini;

    public ListaCDE() {
        ini = null;
    }

    public boolean vacia() {
        return ini == null;
    }

    public void insertar(T dato) {
        NodoDE<T> p = new NodoDE<T>(dato);
        NodoDE<T> ult;
        if (vacia()) {
            ini = p;
            p.setSuc(p);
            p.setAnt(p);
        } else {
            ult = ini.getAnt();
            ult.setSuc(p);
            p.setSuc(ini);
            ini.setAnt(p);
            p.setAnt(ult);
        }
    }

    public T eliminar(int pos) {
        T dato;
        NodoDE<T> q, r, s;
        if (vacia()) {
            dato = null;
        } else {
            q = buscarNodo(pos, ini);
            s = q.getSuc();
            r = q.getAnt();
            r.setSuc(s);
            s.setAnt(r);
            if (q == ini) {
                ini = s;
            }
            if (q == s) {
                ini = null;
            }
            dato = q.getDato();
        }
        return dato;

    }

    private NodoDE<T> buscarNodo(int pos, NodoDE<T> q) {
        NodoDE<T> nodo;
        if (pos == 0) {
            nodo = q;
        } else {
            nodo = buscarNodo(pos - 1, q.getSuc());
        }
        return nodo;
    }

    public T acceder(int pos) {
        T dato;
        NodoDE<T> q;
        if (vacia()) {
            dato = null;
        } else {
            q = buscarNodo(pos, ini);
            dato = q.getDato();
        }
        return dato;
    }

    public int longitud() {
        int longitud = 0;
        if (!vacia()) {
            longitud = contarNodos(ini);
        }
        return longitud;
    }

    public int contarNodos(NodoDE<T> q) {
        int cant;
        if (q.getSuc() == ini) {
            cant = 1;
        } else {
            cant = 1 + contarNodos(q.getSuc());
        }
        return cant;
    }

    public boolean buscar(T dato) {
        boolean res = false;
        NodoDE<T> q = ini;
        if (!vacia()) {

            while (q.getSuc() != ini) {
                if (q.getDato().equals(dato)) {
                    res = true;
                }
                q = q.getSuc();
            }
            if (q.getDato().equals(dato)) {
                res = true;
            }
        }
        return res;
    }

    public String toString() {
        String res = "[";
        NodoDE<T> q = ini;
        if (!vacia()) {
            while (q.getSuc() != ini) {

                res += q.getDato() + ",";
                q = q.getSuc();
            }
            res += q.getDato();
        }
        res += "]";
        return res;
    }

    public void unir(ListaCDE<T> datos) {
        if (!vacia()) {
            NodoDE<T> p = ini;
            unir(p, datos, 0);
        }
    }

    private void unir(NodoDE<T> p, ListaCDE<T> datos, int pos) {
        if (pos >= datos.longitud()) {
            p = buscarNodo(longitud() - 1, ini);
            p.setSuc(ini);
        } else {
            p = buscarNodo(longitud() - 1, ini);
            NodoSE<T> q = new NodoSE<T>(datos.acceder(pos));
            insertar(q.getDato());
            unir(p.getSuc(), datos, pos + 1);
        }

    }

    public ListaCDE<ListaCDE<T>> dividir(int pos) {
        ListaCDE<ListaCDE<T>> listas = new ListaCDE<>();
        ListaCDE<T> lista1, lista2;
        lista1 = new ListaCDE<>();
        lista2 = new ListaCDE<>();
        if (!vacia()) {
            dividir(lista1, lista2, pos, this, 0);
            listas.insertar(lista1);
            listas.insertar(lista2);
        }
        return listas;
    }

    private void dividir(ListaCDE<T> l1, ListaCDE<T> l2, int pos, ListaCDE<T> l, int c) {
        if (c < longitud()) {
            if (pos != 0) {
                l1.insertar(l.acceder(c));
                dividir(l1, l2, pos - 1, l, c + 1);
            } else {
                l2.insertar(l.acceder(c));
                dividir(l1, l2, pos, l, c + 1);
            }
        }

    }

    public int indiceDe(T dato) {
        NodoDE<T> p = ini;
        int res = -1;
        for (int i = 0; i < longitud(); i++) {
            if (acceder(i).equals(dato)) {
                res = i;
            }
        }
        return res;
    }

    public void insertarEn(T dato, int pos) {
        insertar(dato);
        intercambia(pos, longitud() - 1);
    }

    private void intercambia(int pos1, int pos2) {
        if (pos2 > pos1) {
            intercambiar(pos1, pos2);
            intercambia(pos1 + 1, pos2);
        }
    }

    public void intercambiar(int pos1, int pos2) {
        if (pos1 < longitud() && pos2 < longitud()) {
            T p = buscarNodo(pos1, ini).getDato();
            T q = buscarNodo(pos2, ini).getDato();
            NodoDE<T> r = ini;
            while (r.getSuc() != ini) {
                if ((r.getDato()).equals(p)) {
                    r.setDato(q);
                } else if ((r.getDato()).equals(q)) {
                    r.setDato(p);
                }
                r = r.getSuc();
            }
            if ((r.getDato()).equals(p)) {
                r.setDato(q);
            } else if ((r.getDato()).equals(q)) {
                r.setDato(p);
            }
        }
    }

}