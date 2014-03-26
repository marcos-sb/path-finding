package problemas;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.util.Vector;

class Nodo extends DefaultMutableTreeNode {
  private byte gn;
  protected final Numero numero;
  protected final char digitoFijo; // 'u', 'd' , 'c' o 'i' -> i: estado inicial => no hay digito fijo
  protected final String desc;
  
  public Nodo(Numero num, char digitoFijo, byte gn) {
    super();
    this.gn = gn;
    numero = num;
    this.digitoFijo = digitoFijo;
    desc = num.toString() + digitoFijo;
  }
  public Nodo(Nodo n) {
    this(n.numero, n.digitoFijo, n.gn);
  }
  
  public void anadirHijoAdoptado(MutableTreeNode nodoHijo)
                                   throws RuntimeException {
    if(nodoHijo != null) {
      if(allowsChildren) {
        if(children == null) {
          children = new Vector();
        }
        children.add(nodoHijo);
        if(nodoHijo.getParent() == null) {
          nodoHijo.setParent(this);
        }
      } else {
        throw new IllegalStateException();
      }
    } else {
      throw new IllegalArgumentException();
    }
  }
  
  protected Vector<? extends Nodo> expandir(Problema p) {
    Vector<Nodo> expandidos = new Vector<Nodo>();
    Numero [] temp = new Numero [6];
    char [] tempOp = new char [6];
    int i;
    
    switch(digitoFijo) {
      case 'u':
      case 'U':
        temp[0] = numero.sumaCentena();
        tempOp[0] = 'c';
        temp[1] = numero.sumaDecena();
        tempOp[1] = 'd';
        temp[2] = numero.restaCentena();
        tempOp[2] = 'c';
        temp[3] = numero.restaDecena();
        tempOp[3] = 'd';
        break;
      case 'd':
      case 'D':
        temp[0] = numero.sumaCentena();
        tempOp[0] = 'c';
        temp[1] = numero.sumaUnidad();
        tempOp[1] = 'u';
        temp[2] = numero.restaCentena();
        tempOp[2] = 'c';
        temp[3] = numero.restaUnidad();
        tempOp[3] = 'u';
        break;
      case 'c':
      case 'C':
        temp[0] = numero.sumaDecena();
        tempOp[0] = 'd';
        temp[1] = numero.sumaUnidad();
        tempOp[1] = 'u';
        temp[2] = numero.restaDecena();
        tempOp[2] = 'd';
        temp[3] = numero.restaUnidad();
        tempOp[3] = 'u';
        break;
      case 'i':
      case 'I':
        temp[0] = numero.sumaCentena();
        tempOp[0] = 'c';
        temp[1] = numero.sumaDecena();
        tempOp[1] = 'd';
        temp[2] = numero.sumaUnidad();
        tempOp[2] = 'u';
        temp[3] = numero.restaCentena();
        tempOp[3] = 'c';
        temp[4] = numero.restaDecena();
        tempOp[4] = 'd';
        temp[5] = numero.restaUnidad();
        tempOp[5] = 'u';
        
        for(i = 0; i < 6; i++) {
          if(temp[i] != null && !p.enTabues(temp[i])) {
            expandidos.add(new Nodo(temp[i], tempOp[i],
                    (byte) ((int) this.gn + (int) temp[i].costeCreacion)));
          }
        }
        return expandidos;
    }
    
    for(i = 0; i < 4; i++) {
      if(temp[i] != null && !p.enTabues(temp[i])) {
        expandidos.add(new Nodo(temp[i], tempOp[i],
                    (byte) ((int) this.gn + (int) temp[i].costeCreacion)));
      }
    }
    return expandidos;
  }
  
  public void setGn(byte valor) {
    gn = valor;
  }
  public byte getGn() {
    return gn;
  }
  
  public String toString() {
    return desc;
  }
  public boolean equals(Object o) {
    return this.digitoFijo == ((Nodo) o).digitoFijo && 
            this.numero.equals(((Nodo) o).numero);
            
  }
}
