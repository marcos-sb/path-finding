package problemas;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

class AEstrella implements Busqueda {  // A*
  private ListaOrdenada<NodoEstrella> abiertos;
  private Hashtable<String, NodoEstrella> cerrados;
  private Representacion rep;
  
  public AEstrella() {
    abiertos = new ListaOrdenada<NodoEstrella>(new OrdenEstrella());
    cerrados = new Hashtable<String, NodoEstrella>();
  }
  
  private void propagarMejora(NodoEstrella viejo) {
    Vector<NodoEstrella> nodos = new Vector<NodoEstrella>();
    nodos.add(viejo);
    Enumeration<NodoEstrella> hijos;
    while(!nodos.isEmpty()) {
      NodoEstrella nodo = nodos.remove(0);
      hijos = nodo.children();
      
      while(hijos.hasMoreElements()) {
        NodoEstrella sucesor = hijos.nextElement();
        if(sucesor.getParent().equals(viejo)) {
          sucesor.setGn((byte) ((int) viejo.getGn() +
                  (int) NodoEstrella.coste(viejo, sucesor)));
          nodos.add(sucesor);  // para seguir expandiendo en el
                               // while m�s externo
        
        } else { // si sucesor es adoptado
          if(viejo.getGn() < ((Nodo) sucesor.getParent()).getGn()) {
            sucesor.setParent(viejo);
            sucesor.setGn((byte) ((int) viejo.getGn() +
                  (int) NodoEstrella.coste(viejo, sucesor)));
            nodos.add(sucesor);
          }
        }
        
        
        if(!cerrados.containsKey(sucesor.desc)) { // si no est� en cerrados,
                                                  // como es hijo de alguien
                                                  // est� en abiertos
          abiertos.reordenar(abiertos.indexOf(sucesor));
        }
      }
    }
  }
  
  public Representacion solucionar(Problema p) {
    abiertos.clear();
    cerrados.clear();
    Representacion rep;
    Numero ini = p.inicial;
    
    if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
      return null;  // no tiene soluci�n
    }
    NodoEstrella iniNodo = new NodoEstrella(ini, 'i', p.h, (byte) 0);
    rep = new Representacion(iniNodo, new Vector<NodoEstrella>(abiertos), iniNodo);
    rep.numNodosGenerados++;
    
    if(p.esSolucion(ini)) {
      rep.setEsSolucion(true);
      return rep;
    }
    abiertos.add(iniNodo);
    
    while(!abiertos.isEmpty()) {
      NodoEstrella mejorNodo = abiertos.getFirst();  // cogemos el elemento con menor f(n)
                                                   // que es el primero porque es un conjunto ordenado
      rep.setMejorNodo(mejorNodo);
      if(p.esSolucion(mejorNodo.numero)) {
        rep.setAbiertos(new Vector<NodoEstrella>(abiertos));
        rep.setEsSolucion(true);
        return rep;
      }
      
      Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
      rep.numNodosExpandidos++;
      rep.setUltimoExp(mejorNodo);
      abiertos.removeFirst();
      cerrados.put(mejorNodo.desc, mejorNodo);
      for(Nodo sucesor : sucesores) {
        NodoEstrella suc = (NodoEstrella) sucesor;
        int indiceViejo = abiertos.indexOf(suc);
        NodoEstrella viejo;
        
        if(indiceViejo != -1) { // sucesor est� en abiertos
          viejo = abiertos.get(indiceViejo);
          if(viejo.getGn() > suc.getGn()) {
            mejorNodo.add(viejo);  // a�adimos viejo a la lista de hijos de mejorNodo
            viejo.setGn(suc.getGn());
            abiertos.reordenar(indiceViejo);
          } else {  // si el sucesor no mejora al viejo
            mejorNodo.anadirHijoAdoptado(viejo);
          }
      
        } else if(cerrados.containsKey(suc.desc)) { // sucesor est� en cerrados
          viejo = cerrados.get(suc.desc);
          if(viejo.getGn() > suc.getGn()) {
            mejorNodo.add(viejo);
            viejo.setGn(suc.getGn());
          
            // propagar mejora
            propagarMejora(viejo);
          } else {  // el sucesor no mejora al viejo
            mejorNodo.anadirHijoAdoptado(viejo);
          }
          
        } else {
          mejorNodo.add(suc);
          abiertos.add(suc);
          rep.numNodosGenerados++;
        }
      }
    }
    return null;
  }
  
  public Representacion paso(Problema p) {
    NodoEstrella mejorNodo;
    
    if(!p.getEmpezado()) {
      abiertos.clear();
      cerrados.clear();
      Numero ini = p.inicial;
      
      if(p.enTabues(ini) || p.enTabues(p.objetivo)) {
        return null;  // no tiene soluci�n
      }
      mejorNodo = new NodoEstrella(ini, 'i', p.h, (byte) 0);
      rep = new Representacion(mejorNodo, new Vector<NodoEstrella>(abiertos), mejorNodo);
      rep.numNodosGenerados++;
      
      abiertos.add(mejorNodo);

      p.setEmpezado(true);
      
      if(p.esSolucion(mejorNodo.numero)) {
        p.setEmpezado(false);
        rep.setEsSolucion(true);
        return rep;
      }
      
      Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
      rep.numNodosExpandidos++;
      rep.setUltimoExp(mejorNodo);
      abiertos.removeFirst();
      cerrados.put(mejorNodo.desc, mejorNodo);
      for(Nodo sucesor : sucesores) {
        NodoEstrella suc = (NodoEstrella) sucesor;
        int indiceViejo = abiertos.indexOf(suc);
        NodoEstrella viejo;
        if(indiceViejo != -1) { // sucesor est� en abiertos
          viejo = abiertos.get(indiceViejo);
          if(viejo.getGn() > suc.getGn()) {
            mejorNodo.add(viejo);  // a�adimos viejo a la lista de hijos de mejorNodo
            viejo.setGn(suc.getGn());
            abiertos.reordenar(indiceViejo);
          } else {  // el sucesor no mejora al viejo
            mejorNodo.anadirHijoAdoptado(viejo);
          }
          
        } else if(cerrados.containsKey(suc.desc)) { // sucesor est� en cerrados
          viejo = cerrados.get(suc.desc);
          if(viejo.getGn() > suc.getGn()) {
            mejorNodo.add(viejo);
            viejo.setGn(suc.getGn());
          
            // propagar mejora
            propagarMejora(viejo);
          } else {  // el sucesor no mejora al viejo
            mejorNodo.anadirHijoAdoptado(viejo);
          }
          
        } else {
          mejorNodo.add(suc);
          abiertos.add(suc);
          rep.numNodosGenerados++; // �nico punto en el que se considera que un nodo ha
                                    // sido expandido
        }
      }
      rep.setAbiertos(new Vector<NodoEstrella>(abiertos));
      rep.setMejorNodo(abiertos.getFirst());
      return rep;
      
    } else {
      if(!abiertos.isEmpty()) {
        mejorNodo = abiertos.getFirst();  // cogemos el elemento con menor f(n)
                                                   // que es el primero porque es un conjunto ordenado 
        if(p.esSolucion(mejorNodo.numero)) {
          rep.setMejorNodo(mejorNodo);
          p.setEmpezado(false);
          rep.setAbiertos(new Vector<NodoEstrella>(abiertos));
          rep.setEsSolucion(true);
          return rep;
        }
      
        Vector<? extends Nodo> sucesores = mejorNodo.expandir(p);
        rep.numNodosExpandidos++;
        rep.setUltimoExp(mejorNodo);
        abiertos.removeFirst();
        cerrados.put(mejorNodo.desc, mejorNodo);
        for(Nodo sucesor : sucesores) {
          NodoEstrella suc = (NodoEstrella) sucesor;
          int indiceViejo = abiertos.indexOf(suc);
          NodoEstrella viejo;
          if(indiceViejo != -1) { // sucesor est� en abiertos
            viejo = abiertos.get(indiceViejo);
            if(viejo.getGn() > suc.getGn()) {
              mejorNodo.add(viejo);  // a�adimos viejo a la lista de hijos de mejorNodo
              viejo.setGn(suc.getGn());
              abiertos.reordenar(indiceViejo);
            } else {  // el sucesor no mejora al viejo
              mejorNodo.anadirHijoAdoptado(viejo);
            }
          
          } else if(cerrados.containsKey(suc.desc)) { // sucesor est� en cerrados
            viejo = cerrados.get(suc.desc);
            if(viejo.getGn() > suc.getGn()) {
              mejorNodo.add(viejo);
              viejo.setGn(suc.getGn());
            
              // propagar mejora
              propagarMejora(viejo);
            } else {  // el sucesor no mejora al viejo
              mejorNodo.anadirHijoAdoptado(viejo);
            }
          
          } else {
            mejorNodo.add(suc);
            abiertos.add(suc);
            rep.numNodosGenerados++;
          }
        }
        rep.setAbiertos(new Vector<NodoEstrella>(abiertos));
        rep.setMejorNodo(abiertos.getFirst());
        return rep;
      }
    }
    return null;
  }
  
  public String toString() {
    return "A*";
  }
}

