package problemas;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;

class ListaOrdenada<E> extends LinkedList<E> {
  private Comparator<E> comparador;
  
  public ListaOrdenada(Comparator<E> comparador) {
    super();
    this.comparador = comparador;
  }
  
  public boolean add(E o1) {
    ListIterator<E> it = super.listIterator();
    while(it.hasNext()) {
      E o2 = it.next();
      if(comparador.compare(o1, o2) <= 0) { // o2 > o1
        it.previous();
        it.add(o1);
        return true;
      }
    }
    it.add(o1);
    return true;  // si no se dispara ninguna excepcion
                  // todos correctos
  }
  
  public void reordenar(int indice) {
    E o = super.get(indice);
    super.remove(indice);
    add(o);
  }
}
