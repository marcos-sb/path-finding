package problemas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Vector;

class Controlador {
  private Interfaz gui1;
  private Window gui2;
  private Problema p;
  private Representacion rep;
  private Busqueda bus;
  private Heuristica h;
  
  public Controlador(Interfaz gui1) {
    this.gui1 = gui1;
    gui2 = new Window(this);
  }
  
  public void iniciar() throws Exception {
    int eleccion = 0;
    if(gui2.isVisible()) {
      // 0: yes, 1: no
      eleccion = gui1.mostrarMensaje();
    }
    if(eleccion == 0) {
      Numero inicial, objetivo;
    
      inicial = new Numero(gui1.getInicial(), (byte) 0);
      objetivo = new Numero(gui1.getObjetivo(), (byte) 0);
      switch(gui1.getBusqueda()) {
        case 1:
          bus = new AEstrella();
          break;
        case 2:
          bus = new AnchuraGrafos();
          break;
        case 3:
          bus = new AscensionPorMaximaPendiente();
          break;
        default:
          bus = new AEstrella();
          throw new Exception("ermm, esto nunca deber�a haber sucedido...");
      }
      switch(gui1.getHeuristica()) {
        case -1:
          p = new Problema(inicial, objetivo, bus,
                  transformar(gui1.getTabues()));
          break;
        case 0:
          h = new DistanciaManhattan(objetivo);
          p = new Problema(inicial, objetivo, bus, h,
                  transformar(gui1.getTabues()));
          break;
        case 1:
          h = new MaximaDistanciaManhattan(objetivo);
          p = new Problema(inicial, objetivo, bus, h,
                  transformar(gui1.getTabues()));
          break;
        default:
          throw new Exception("ermm, esto nunca deber�a haber sucedido...");
      }
      gui2.iniciarVistas(new Nodo(inicial, 'i', (byte) 0));
      gui2.setVisible(true);
    }
  }
  
  private Hashtable<Short, Numero> transformar(HashSet<Short> tabuesTemp) {
    Hashtable<Short, Numero> tabues = new Hashtable<Short, Numero>();
    for(Short s : tabuesTemp) {
      tabues.put(s, new Numero(s, (byte) 0));
    }
    return tabues;
  }
  
  public void solucionar() {
    rep = p.solucionar();
    if(rep != null) {
      gui2.setArbolExp(rep.getArbolExp(), rep.getMejorNodo());
      gui2.setListaAbiertos(rep.getAbiertos());
      gui2.setExpandidos(rep.getUltimosExp());
      //gui2.deshabilitarPasos();
      gui2.solAlcanzada(true, rep, h instanceof MaximaDistanciaManhattan);
    } else {
      gui2.solAlcanzada(false, null, h instanceof MaximaDistanciaManhattan);
    }
  }
  
  public void paso() {
    rep = p.paso();
    if(rep != null) {
      if(!rep.esSolucion()) {
        gui2.setArbolExp(rep.getArbolExp(), rep.getMejorNodo());
        gui2.setListaAbiertos(rep.getAbiertos());
        gui2.setExpandidos(rep.getUltimosExp());
      } else {  // hay soluci�n
        gui2.setArbolExp(rep.getArbolExp(), rep.getMejorNodo());
        gui2.setListaAbiertos(rep.getAbiertos());
        gui2.setExpandidos(rep.getUltimosExp());
        //gui2.deshabilitarPasos();
        gui2.solAlcanzada(true, rep, h instanceof MaximaDistanciaManhattan);
      } 
    } else { // rep == null
      gui2.solAlcanzada(false, null, h instanceof MaximaDistanciaManhattan);
    }
  }
  
  private float promedio(Vector<Integer> vec) {
    float suma = 0;
    int numNumNodosExpandidosValidos = 0;  // n�m de nodos expandidos != -1
                                       // que contiene el vector. necesario
                                       // para hacer el promedio correctamente
    for(float f : vec) {
      if(f != -1) {
        suma += f;
        numNumNodosExpandidosValidos++;
      }
    }
    return suma / numNumNodosExpandidosValidos;
  }
  
  public void rendimiento(File file, int numPruebas) throws Exception {
    Busqueda bus = new AEstrella();
    Vector<Integer> DMVec = new Vector<Integer>(),
           MaxDMVec = new Vector<Integer>(),
           costes = new Vector<Integer>();
    Hashtable<Short, Numero> tabues = new Hashtable<Short, Numero>();
    BufferedWriter out = new BufferedWriter(new FileWriter(file.getPath()));
    Numero obj = new Numero(gui1.getObjetivo(), (byte) 0);
    Heuristica DM = new DistanciaManhattan(obj),
            MaxDM = new MaximaDistanciaManhattan(obj),
            heu;
    
    out.write("an�lisis de las heur�sticas usando " +
            bus + "\n\n");
    out.write("inicial -> objetivo  " + DM + "(inicial)  " +
            MaxDM + "(inicial)  coste real(inicial)\n" +
            "----------------------------------------------------------------\n");
    
    for(int i = 0; i < numPruebas; i++) {
      Numero ini = new Numero((short) Math.round(Math.random()*(999 - 100) + 100), (byte) 0);
      Problema p = new Problema(ini, obj, bus, DM, tabues);  // se resuelve el problema de la mejor
                                                             // manera para saber el coste de la
                                                             // soluci�n �ptima
      Representacion rep = p.solucionar();
      
      if(rep == null) {
        out.write(ini + " -> " + obj + "  " + DM.valor(ini) + "  " +
               MaxDM.valor(ini) + "  " + "--" + " ha sido imposible encontrar la soluci�n\n");
      } else {
        DMVec.add(DM.valor(ini));
        MaxDMVec.add(MaxDM.valor(ini));
        costes.add(rep.getCosteCamino());
        
                        // escritura de los resultados en el fichero
        out.write(ini + " -> " + obj + "  " + DMVec.get(i) + "  " +
              MaxDMVec.get(i) + "  " + costes.get(i) + "\n");
      }
    }
    float esperanzaDM = promedio(DMVec),
          esperanzaMaxDM = promedio(MaxDMVec),
          esperanzaCoste = promedio(costes),
          errorDM = esperanzaCoste - esperanzaDM,
          errorMaxDM = esperanzaCoste - esperanzaMaxDM;
    
    if(errorDM < errorMaxDM) {
      heu = DM;
    } else {
      heu = MaxDM;
    }
             
    out.write("----------------------------------------------------------------\n" +
            "E(" + DM + ")  " + "E(" + MaxDM + ")  " + "E(coste real)\n" +
            esperanzaDM + "  " + esperanzaMaxDM + "  " + esperanzaCoste + "\n" +
            "----------------------------------------------------------------\n" +
            "error(" + DM + ")  " + "error(" + MaxDM + ")\n" +
            errorDM + "  " + errorMaxDM + "\n\n" +
            "se concluye que es mejor " + heu + " porque comete menos errores");
    out.write("\n\n\n-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --\n\n\n");
    
    
    // segunda parte del test (apartado c)
    Busqueda b1 = new AEstrella();
    Busqueda b2 = new AnchuraGrafos();
    Busqueda b3 = new AscensionPorMaximaPendiente();
    Vector<Numero> inis = new Vector<Numero>(),
                   objs = new Vector<Numero>();
    Vector<Representacion> repVec = new Vector<Representacion>();
    Vector<Vector<Integer>> numNodosExpandidos = new Vector<Vector<Integer>>();
    numNodosExpandidos.add(new Vector<Integer>());
    numNodosExpandidos.add(new Vector<Integer>());
    numNodosExpandidos.add(new Vector<Integer>());
    
    out.write("an�lisis de los m�todos de b�squeda, n�mero de nodos expandidos\n\n" +
               "inicial -> objetivo  " + b1 + "  " + b2 + "  " + b3 +
            "\n----------------------------------------------------------------\n");
    
    for(int i = 0; i < numPruebas; i++) {
      Numero inicial = new Numero((short) Math.round(Math.random()*(999 - 100) + 100), (byte) 0);
      Numero objetivo = new Numero((short) Math.round(Math.random()*(999 - 100) + 100), (byte) 0);
      inis.add(inicial);
      objs.add(objetivo);
      heu.objetivo = objetivo;
      Problema p1 = new Problema(inicial, objetivo, b1, heu, tabues);
      Problema p2 = new Problema(inicial, objetivo, b2, tabues);
      Problema p3 = new Problema(inicial, objetivo, b3, heu, tabues);
      
      repVec.add(p1.solucionar());
      repVec.add(p2.solucionar());
      repVec.add(p3.solucionar());
      
      int j = 0;
      for(Representacion rep : repVec) {
        if(rep != null) {
          numNodosExpandidos.elementAt(j).add(rep.numNodosExpandidos);
        } else {
          numNodosExpandidos.elementAt(j).add(-1);
        }
        j++;
      }
      repVec.clear();
    }
    
    float esperanzaB1 = promedio(numNodosExpandidos.elementAt(0)),
          esperanzaB2 = promedio(numNodosExpandidos.elementAt(1)),
          esperanzaB3 = promedio(numNodosExpandidos.elementAt(2));
          
    for(int i = 0; i < inis.size(); i++) {
      out.write(inis.get(i) + " -> " + objs.get(i) + "  " +
              numNodosExpandidos.get(0).get(i) + "  " + numNodosExpandidos.get(1).get(i) +
              "  " + numNodosExpandidos.get(2).get(i) + "\n");
    }
    out.write("----------------------------------------------------------------\n" +
             "E(" + b1 + ")  " + "E(" + b2 + ")  " + "E(" + b3 + ")\n" +
             esperanzaB1 + "  " + esperanzaB2 + "  " + esperanzaB3 + "\n\n\n" +
             "            ------------ fin de an�lisis -----------");
    out.close();
  }
}
