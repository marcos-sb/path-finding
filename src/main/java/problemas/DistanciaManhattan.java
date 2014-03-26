package problemas;

class DistanciaManhattan extends Heuristica {
  
  public DistanciaManhattan(Numero objetivo) {
    super(objetivo);
  }
  
  public int valor(Numero num) {
    return Math.abs(objetivo.getCentenas() - num.getCentenas()) +
      Math.abs(objetivo.getDecenas() - num.getDecenas()) +
      Math.abs(objetivo.getUnidades() - num.getUnidades());
  }
  
  public String toString() {
    return "distancia de Manhattan";
  }
}
