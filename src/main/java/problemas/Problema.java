package problemas;

import java.util.Hashtable;

class Problema {
  protected final Numero inicial, objetivo;
  private boolean empezado;
  private Hashtable<Short, Numero> tabues;
  protected final Heuristica h;
  private Busqueda bus;
  
  public Problema(Numero inicial, Numero objetivo, Busqueda bus, 
          Heuristica h, Hashtable<Short, Numero> tabues) {
    this.inicial = inicial;
    this.objetivo = objetivo;
    this.h = h;
    this.bus = bus;
    this.tabues = tabues;
    empezado = false;
  }
  public Problema(Numero inicial, Numero objetivo, Busqueda bus,
          Hashtable<Short, Numero> tabues) {
    this(inicial, objetivo, bus, null, tabues);
  }
  
  public void setEmpezado(boolean empezado) {
    this.empezado = empezado;
  }
  public Representacion solucionar() {
    return bus.solucionar(this);
  }
  public Representacion paso() {
    return bus.paso(this);
  }
  public boolean enTabues(Numero n) {
    return tabues.containsKey(n.valor);
  }
  public boolean esSolucion(Numero n) {
    return n.equals(objetivo);
  }
  public boolean getEmpezado() {
    return empezado;
  }
}
