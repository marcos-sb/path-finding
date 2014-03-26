package problemas;

import javax.swing.tree.DefaultTreeModel;
import java.util.Vector;

class Representacion {
  protected int numNodosExpandidos, numNodosGenerados;
  private DefaultTreeModel arbolExpansion, ultimosExp;
  protected final Nodo padre;
  private Nodo ultimoExp;
  private Vector<? extends Nodo> abiertos;
  private Nodo mejorNodo;
  private boolean esSolucion;
  
  public Representacion(Nodo padre, Vector<? extends Nodo> abiertos, Nodo ultimoExp, Nodo mejorNodo) {
    this.padre = padre;
    arbolExpansion = new DefaultTreeModel(padre);
    this.ultimoExp = ultimoExp;
    ultimosExp = new DefaultTreeModel(ultimoExp);
    this.abiertos = abiertos;
    this.mejorNodo = mejorNodo;
    esSolucion = false;
    numNodosExpandidos = 0;
  }
  public Representacion(Nodo padre, Vector<? extends Nodo> abiertos, Nodo mejorNodo) {
    this(padre, abiertos, padre, mejorNodo);
  }
  
  public void setMejorNodo(Nodo mejorNodo) {
    this.mejorNodo = mejorNodo;
  }
  public void setAbiertos(Vector<? extends Nodo> abiertos) {
    this.abiertos = abiertos;
  }
  public void setUltimoExp(Nodo n) {
    ultimoExp = n;
  }
  public void setEsSolucion(boolean b) {
    esSolucion = b;
  }
  public Vector<? extends Nodo> getAbiertos() {
    return abiertos;
  }
  public boolean esSolucion() {
    return esSolucion;
  }
  public Nodo getMejorNodo() {
    return mejorNodo;
  }
  public DefaultTreeModel getArbolExp() {
    return arbolExpansion;
  }
  public int getCosteCamino() {
    return mejorNodo.getLevel();
  }
  public DefaultTreeModel getUltimosExp() {
    ultimosExp = new DefaultTreeModel(ultimoExp);
    return ultimosExp;
  }
}
