package problemas;

abstract class Heuristica {
  protected Numero objetivo;
  
  public Heuristica(Numero objetivo) {
    this.objetivo = objetivo;
  }
  
  public abstract int valor(Numero num);
}
