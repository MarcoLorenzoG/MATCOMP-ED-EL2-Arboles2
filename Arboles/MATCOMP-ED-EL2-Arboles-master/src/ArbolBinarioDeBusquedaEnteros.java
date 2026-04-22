public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {

    public ArbolBinarioDeBusquedaEnteros() {
        super();
    }

    public int getSuma() {
        return getSumaRec(raiz);
    }

    private int getSumaRec(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        return nodo.dato + getSumaRec(nodo.izq) + getSumaRec(nodo.der);
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolIzquierda() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null) {
            sub.raiz = raiz.izq;
        }
        return sub;
    }

    @Override
    public ArbolBinarioDeBusquedaEnteros getSubArbolDerecha() {
        ArbolBinarioDeBusquedaEnteros sub = new ArbolBinarioDeBusquedaEnteros();
        if (raiz != null) {
            sub.raiz = raiz.der;
        }
        return sub;
    }
}