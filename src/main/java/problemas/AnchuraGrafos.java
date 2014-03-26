package problemas;

import java.util.Hashtable;
import java.util.Vector;

class AnchuraGrafos implements Busqueda {
  private Hashtable<String, Nodo> cerrados;
  private Vector<Nodo> abiertos;
  private Representacion rep;
  
  public AnchuraGrafos() {
    cerrados = new Hashtable<String, Nodo>();
    abiertos = new Vector<Nodo>();
  }
  
  public Representacion solucionar(Problema p) {
    cerrados.clear();  // empezamos a contruir la soluci—n desde el principio
    abiertos.clear();
    Representacion rep;
    Numero ini = p.inicial;
    
    if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
      return null;  // no tiene soluci—n
    }
    Nodo iniNodo = new Nodo(p.inicial, 'i', (byte) 0);
    rep = new Representacion(iniNodo, abiertos, iniNodo);
    rep.numNodosGenerados++;
    
    if(p.esSolucion(ini)) {
      rep.setEsSolucion(true);
      return rep;
    }
    abiertos.add(iniNodo);
    
    while(!abiertos.isEmpty()) {
      Nodo n = abiertos.firstElement();
      if(cerrados.put(n.desc, n) == null) {  
                                    // si n no hay sido expandido y no es soluci—n lo expandimos
                                    // si ya ha sido expandido se omite por completo

        if(p.esSolucion(n.numero)) {
          rep.setAbiertos(abiertos);
          rep.setMejorNodo(n);
          rep.setEsSolucion(true);
          return rep;
        }
        
        Vector<? extends Nodo> expandidos = n.expandir(p);
        rep.numNodosExpandidos++;
        for(Nodo expandido : expandidos) {
          if(!cerrados.containsKey(expandido.desc) && 
                  !abiertos.contains(expandido)) {
            rep.numNodosGenerados++;
            rep.setUltimoExp(n);
            n.add(expandido);
            abiertos.add(expandido);
          } 
        }
      }
      abiertos.remove(0);
    }
    return null;  // no tiene soluci—n
  }
  
  public Representacion paso(Problema p) {
    Nodo n;
    Vector<? extends Nodo> expandidos;
    
    if(!p.getEmpezado()) {
      abiertos.clear();
      cerrados.clear();
      Numero ini = p.inicial;
      
      if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
        return null;  // no tiene soluci—n
      }
      n = new Nodo(ini, 'i', (byte) 0);
      rep = new Representacion(n, abiertos, n);
      rep.numNodosGenerados++;
      
      if(p.esSolucion(ini)) {  // nœmero salida == nœmero meta
        p.setEmpezado(false);
        rep.setEsSolucion(true);
        return rep;
      }
      abiertos.add(n);
      p.setEmpezado(true);
      
      if(cerrados.put(n.desc, n) == null) {  
                                  // si n no hay sido expandido y no es soluci—n lo expandimos
                                  // si ya ha sido expandido se omite por completo

        if(p.esSolucion(n.numero)) {
          rep.setAbiertos(abiertos);
          rep.setMejorNodo(n);
          p.setEmpezado(false);
          rep.setEsSolucion(true);
          return rep;
        }
        
        expandidos = n.expandir(p);
        rep.numNodosExpandidos++;
        for(Nodo expandido : expandidos) {
          if(!cerrados.containsKey(expandido.desc) && 
                  !abiertos.contains(expandido)) {
            rep.numNodosGenerados++;
            rep.setUltimoExp(n);
            n.add(expandido);
            abiertos.add(expandido);
          } 
        }
        rep.setAbiertos(abiertos);
      }
      abiertos.remove(0);
      rep.setMejorNodo(abiertos.firstElement());
      return rep;
      
    } else {
      if(!abiertos.isEmpty()) {
        n = abiertos.firstElement();
        if(cerrados.put(n.desc, n) == null) {  
                                    // si n no hay sido expandido y no es soluci—n lo expandimos
                                    // si ya ha sido expandido se omite por completo

          if(p.esSolucion(n.numero)) {
            rep.setAbiertos(abiertos);
            rep.setMejorNodo(n);
            p.setEmpezado(false);
            rep.setEsSolucion(true);
            return rep;
          }
          
          expandidos = n.expandir(p);
          rep.numNodosExpandidos++;
          for(Nodo expandido : expandidos) {
            if(!cerrados.containsKey(expandido.desc) && 
                    !abiertos.contains(expandido)) {
              rep.numNodosGenerados++;
              rep.setUltimoExp(n);
              n.add(expandido);
              abiertos.add(expandido);
            } 
          }
          rep.setAbiertos(abiertos);
        }
        abiertos.remove(0);
        rep.setMejorNodo(abiertos.firstElement());
        return rep;
      }
    }
    return null;
  }
  
  public String toString() {
    return "anchura en grafos";
  }
}
