package problemas;

import java.util.Vector;

class AscensionPorMaximaPendiente implements Busqueda { 
  private ListaOrdenada<NodoColina> abiertos, tiosAbiertos;
  private Representacion rep;
  private NodoColina mejorNodo;
                        // implementaci�n basada en el pseudoc�digo
                        // del libro de IA de la Universidad de la
                        // p�gina 46 con capacidad de backtracking
                        // a un solo nivel superior
  
  public AscensionPorMaximaPendiente() {
    abiertos = new ListaOrdenada<NodoColina>(new OrdenColina());
    tiosAbiertos = new ListaOrdenada<NodoColina>(new OrdenColina());
  }
  
  public Representacion solucionar(Problema p) {
    abiertos.clear();
    tiosAbiertos.clear();
    Representacion rep;
    Numero ini = p.inicial;
    
    if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
      return null;  // no tiene soluci�n
    }
    NodoColina mejorNodo = new NodoColina(p.inicial, 'i', p.h, (byte) 0);
    rep = new Representacion(mejorNodo, new Vector<NodoColina>(abiertos), mejorNodo);
    rep.numNodosGenerados++;
    
    while(true) {
      if(p.esSolucion(mejorNodo.numero)) {
        rep.setMejorNodo(mejorNodo);
        rep.setAbiertos(new Vector<NodoColina>(abiertos));
        rep.setEsSolucion(true);
        return rep;
      }
      
      Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
      rep.numNodosExpandidos++;
      rep.setUltimoExp(mejorNodo);
      abiertos.clear();
      for(Nodo sucesor : sucesores) {  // los abiertos son los sucesores;
                                       // se ordenan y se toma el primero
        mejorNodo.add(sucesor);
        abiertos.add((NodoColina) sucesor);
      }
      rep.numNodosGenerados += sucesores.size();
      
      NodoColina n = abiertos.peek();
      if(n.hn <= mejorNodo.hn) {
        mejorNodo = n;
        tiosAbiertos.clear();
        tiosAbiertos.addAll(abiertos);
        tiosAbiertos.removeFirst();
      } else {
        if(tiosAbiertos.size() == 0) {  // si ya hemos terminado de visitar a
                                        // todos los posibles candidatos a ser
                                        // mejorNodo el metodo de ascension no
                                        // es capaz de solucionar el problema :(
          return null;
        }
        mejorNodo = tiosAbiertos.peek();
        tiosAbiertos.removeFirst();
      }
    }
  }
  
  public Representacion paso(Problema p) {
    
    if(!p.getEmpezado()) {
      abiertos.clear();
      tiosAbiertos.clear();
      Numero ini = p.inicial;
      
      if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
        return null;  // no tiene soluci�n
      }
      mejorNodo = new NodoColina(ini, 'i', p.h, (byte) 0);
      rep = new Representacion(mejorNodo, new Vector<NodoColina>(abiertos), mejorNodo);
      rep.numNodosGenerados++;
      if(p.esSolucion(ini)) {  // n�mero salida == n�mero meta
        p.setEmpezado(false);
        rep.setEsSolucion(true);
        return rep;
      }

      p.setEmpezado(true);
      
      Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
      rep.numNodosExpandidos++;
      rep.setUltimoExp(mejorNodo);
      abiertos.clear();
      for(Nodo sucesor : sucesores) {  // los abiertos son los sucesores;
                                       // se ordenan y se toma el primero
        mejorNodo.add(sucesor);
        abiertos.add((NodoColina) sucesor);
      }
      
      rep.numNodosGenerados += sucesores.size();
      rep.setAbiertos(new Vector<NodoColina>(abiertos));      
      NodoColina n = abiertos.peek();
      
      if(n.hn <= mejorNodo.hn) {
        mejorNodo = n;
        tiosAbiertos.clear();
        tiosAbiertos.addAll(abiertos);
        tiosAbiertos.removeFirst();
      } else {
        if(tiosAbiertos.size() == 0) {
          return null;
        }
        mejorNodo = tiosAbiertos.peek();
        tiosAbiertos.removeFirst();
      }
      rep.setMejorNodo(mejorNodo);
      return rep;
    
    } else {
      if(p.esSolucion(mejorNodo.numero)) {  // n�mero salida == n�mero meta
        p.setEmpezado(false);
        rep.setEsSolucion(true);
        return rep;
      }
      
      Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
      rep.numNodosExpandidos++;
      rep.setUltimoExp(mejorNodo);
      abiertos.clear();
      for(Nodo sucesor : sucesores) {  // los abiertos son los sucesores;
                                       // se ordenan y se toma el primero
        mejorNodo.add(sucesor);
        abiertos.add((NodoColina) sucesor);
      }
      
      rep.numNodosGenerados += sucesores.size();
      rep.setAbiertos(new Vector<NodoColina>(abiertos));
      NodoColina n = abiertos.peek();
      if(n.hn <= mejorNodo.hn) {
        mejorNodo = n;
        tiosAbiertos.clear();
        tiosAbiertos.addAll(abiertos);
        tiosAbiertos.removeFirst();
      } else {
        if(tiosAbiertos.size() == 0) {
          return null;
        }
        mejorNodo = tiosAbiertos.peek();
        tiosAbiertos.removeFirst();
      }
      rep.setMejorNodo(mejorNodo);
      return rep;
    }
  }
  
  public String toString() {
    return "ascensi�n por la m�xima pendiente";
  }
}
