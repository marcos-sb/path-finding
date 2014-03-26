package problemas;

class MaximaDistanciaManhattan extends Heuristica {
  
  public MaximaDistanciaManhattan(Numero objetivo) {
    super(objetivo);
  }
  
  public int valor(Numero num) {
    /*if((objetivo.getCentenas() - num.getCentenas()) % 2 == 1) {
      return Math.abs(objetivo.getCentenas() - num.getCentenas()) * 3 +
        Math.abs(objetivo.getDecenas() - num.getDecenas()) +
        Math.abs(objetivo.getUnidades() - num.getUnidades());
    } else {
      return Math.abs(objetivo.getCentenas() - num.getCentenas()) +
        Math.abs(objetivo.getDecenas() - num.getDecenas()) +
        Math.abs(objetivo.getUnidades() - num.getUnidades());
    }*/
    return Math.max(Math.max(Math.abs(objetivo.getCentenas() - num.getCentenas()),
      Math.abs(objetivo.getDecenas() - num.getDecenas())),
      Math.abs(objetivo.getUnidades() - num.getUnidades()));
  }
 
  public String toString() {
    return "m‡xima distancia de Manhattan";
  }
}
