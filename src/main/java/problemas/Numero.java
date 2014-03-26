package problemas;

class Numero {
  private byte [] cifras;
  protected final short valor;
  protected final byte costeCreacion;
  
  public Numero(byte [] cifras, byte costeCreacion) {
    this.cifras = cifras;
    valor = (short) (cifras[0] * 100 + cifras[1] * 10 + cifras[2]);
    this.costeCreacion = costeCreacion;
  }
  public Numero(byte centenas, byte decenas, byte unidades, byte costeCreacion) {
    cifras = new byte [3];
    cifras[0] = centenas;
    cifras[1] = decenas;
    cifras[2] = unidades;
    valor = (short) (cifras[0] * 100 + cifras[1] * 10 + cifras[2]);
    this.costeCreacion = costeCreacion;
  }
  public Numero(short valor, byte costeCreacion) {
    this.valor = valor;
    cifras = new byte [3];
    cifras[0] = (byte) ((int) valor / 100);
    cifras[1] = (byte) ( (valor % 100) / 10);
    cifras[2] = (byte) ((int) valor % 10);
    this.costeCreacion = costeCreacion;
  }
  
  public Numero sumaCentena() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[0] < 9) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = (byte) ((int)this.cifras[0] + 1);
      nuevasCifras[1] = this.cifras[1];
      nuevasCifras[2] = this.cifras[2];
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public Numero sumaDecena() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[1] < 9) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = this.cifras[0];
      nuevasCifras[1] = (byte) ((int)this.cifras[1] + 1);
      nuevasCifras[2] = this.cifras[2];
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public Numero sumaUnidad() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[2] < 9) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = this.cifras[0];
      nuevasCifras[1] = this.cifras[1];
      nuevasCifras[2] = (byte) ((int)this.cifras[2] + 1);
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public Numero restaCentena() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[0] > 1) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = (byte) ((int)this.cifras[0] - 1);
      nuevasCifras[1] = this.cifras[1];
      nuevasCifras[2] = this.cifras[2];
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public Numero restaDecena() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[1] > 0) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = this.cifras[0];
      nuevasCifras[1] = (byte) ((int)this.cifras[1] - 1);
      nuevasCifras[2] = this.cifras[2];
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public Numero restaUnidad() {
    byte nuevasCifras [],
         costeOperacion = 1;
    if(this.cifras[2] > 0) {
      nuevasCifras = new byte [3];
      nuevasCifras[0] = this.cifras[0];
      nuevasCifras[1] = this.cifras[1];
      nuevasCifras[2] = (byte) ((int)this.cifras[2] - 1);
      return new Numero(nuevasCifras, costeOperacion);
    } else {
      return null;
    }
  }
  
  public short getCentenas() {
    return cifras[0];
  }
  
  public short getDecenas() {
    return cifras[1];
  }

  public short getUnidades() {
    return cifras[2];
  }
  
  public String toString() {
    return Short.toString(valor);
  }
  
  public boolean equals(Numero num) {
    return valor == num.valor;
  }
}