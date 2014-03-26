package problemas;

import java.util.Vector;

class NodoColina extends Nodo {
  protected final byte hn;
  
  public NodoColina(Nodo n, Heuristica h) {
    super(n);
    hn = (byte) h.valor(n.numero);
  }
  
  public NodoColina(Numero num, char digitoFijo, Heuristica h, byte gn) {
    super(num, digitoFijo, gn);
    hn = (byte) h.valor(num);
  }
  
  public Vector<? extends Nodo> expandir(Problema p) {
    Vector<NodoColina> expandidosExt = new Vector<NodoColina>();
    Vector<? extends Nodo> generados = super.expandir(p);
    for(Nodo n : generados) {
      NodoColina nExt = new NodoColina(n, p.h);
      expandidosExt.add(nExt);
    }
    return expandidosExt;
  }
  
  public String toString() {
    return desc + ", h:" + hn;
  }
}
