package problemas;

import java.util.Vector;

class NodoEstrella extends Nodo {
  protected final byte hn;
  private byte fn;

  public NodoEstrella(Nodo n, Heuristica h) {
    super(n);
    hn = (byte) h.valor(n.numero);
    fn = (byte) ((int) super.getGn() + (int) hn);
  }

  public NodoEstrella(Numero num, char digitoFijo, Heuristica h, byte gn) {
    super(num, digitoFijo, gn);
    hn = (byte) h.valor(num);
    fn = (byte) ((int) gn + (int) hn);
  }
  
  public static byte coste(NodoEstrella n1, NodoEstrella n2) {
    return (byte) Math.abs(n1.getGn() - n2.getGn());
  }
  
  public Vector<? extends Nodo> expandir(Problema p) {
    Vector<NodoEstrella> expandidosExt = new Vector<NodoEstrella>();
    Vector<? extends Nodo> generados = super.expandir(p);
    for(Nodo n : generados) {
      NodoEstrella nExt = new NodoEstrella(n, p.h);
      expandidosExt.add(nExt);
    }
    return expandidosExt;
  }
  
  public void setGn(byte valor) {
    super.setGn(valor);
    fn = (byte) ((int) hn + (int) super.getGn());
  }
  public byte getGn() {
    return super.getGn();
  }
  public byte getFn() {
    return fn;
  }
  public String toString() {
    return desc + ", f:" + fn + ", g:" + getGn() + ", h:" + hn;
  }
}
