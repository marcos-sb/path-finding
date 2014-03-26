package problemas;

class OrdenEstrella implements java.util.Comparator<NodoEstrella> {
  public int compare(NodoEstrella n1, NodoEstrella n2) {
    int resultado = n1.getFn() - n2.getFn();
    if(resultado != 0) {
      return resultado;
    }
    return n1.hn - n2.hn;
  }
}
