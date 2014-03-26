package problemas;

class OrdenColina implements java.util.Comparator<NodoColina> {
  public int compare(NodoColina n1, NodoColina n2) {
    return n1.hn - n2.hn;
  }
}

