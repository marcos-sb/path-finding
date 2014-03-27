package problemas;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.io.File;
import java.util.Vector;

public class Window extends javax.swing.JFrame {
  private Controlador controlador;
  private boolean estadoPrevio;  // estado de los botones para poder retornar a �l
  // cuando se desactive la casilla de generaci�n
  // de informe de rendimiento
  
  public Window(Controlador cntrl) {
    initComponents();
    controlador = cntrl;
    estadoPrevio = true;
  }
  
  public void iniciarVistas(Nodo inicial) {
    String [] strArray = {"Open node list"};
    jList1.setListData(strArray);
    TreeModel arbolVacio = new DefaultTreeModel(inicial);
    jTree1.setModel(arbolVacio);
    jTree1.setScrollsOnExpand(true);
    jTree2.setModel(arbolVacio);
    jButton1.setEnabled(true);
    jButton2.setEnabled(true);
    estadoPrevio = true;
  }
  
  public void solAlcanzada(boolean solAlcanzada, Representacion rep, 
          boolean hayRepetidos) {
    if(solAlcanzada) {
      if(!hayRepetidos) {
        javax.swing.JOptionPane.showMessageDialog(this, "Path to destination found\n\n" +
              "Path cost: " + rep.getCosteCamino() + "\n\n" +
              "Opened node count: " + rep.numNodosExpandidos + "\n" +
              " (non-repeated)\n\n" + "Generated node count: " + rep.numNodosGenerados +
              "\n (non-repeated)                 \n\n", "Processing finished",
              javax.swing.JOptionPane.INFORMATION_MESSAGE);
      } else {
        javax.swing.JOptionPane.showMessageDialog(this, "Path to destination found\n\n" +
              "Path cost: " + rep.getCosteCamino() + "\n\n" +
              "Opened node count: " + rep.numNodosExpandidos + "\n" +
              "\nGenerated node count: " + rep.numNodosGenerados +
              "\n                                \n", "Processing finished",
              javax.swing.JOptionPane.INFORMATION_MESSAGE);
      }
    } else {
      javax.swing.JOptionPane.showMessageDialog(this, "No path to destination",
              "Ooops!", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }
  public void setArbolExp(DefaultTreeModel arbolExp, TreeNode meta) {
    jTree1.setModel(arbolExp);
    jTree1.setSelectionPath(new TreePath(arbolExp.getPathToRoot(meta)));
  }
  
  public void setListaAbiertos(Vector<? extends Nodo> abiertos) {
    jList1.setListData(abiertos);
    jList1.setSelectedIndex(0);
  }
  
  public void setExpandidos(DefaultTreeModel arbolExp) {
    jTree2.setModel(arbolExp);
  }
  
  private void initComponents() {
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jTree1 = new javax.swing.JTree();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    jTree2 = new javax.swing.JTree();
    jCheckBox1 = new javax.swing.JCheckBox();
    jScrollPane3 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    
    setResizable(false);
    setTitle("Results");
    setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Node Tree"));
    jTree1.setEditable(true);
    jScrollPane1.setViewportView(jTree1);
    
    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addContainerGap())
            );
    jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
            .addContainerGap()
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
            .addContainerGap())
            );
    
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Most recently opened node | Open node list"));
    jScrollPane2.setViewportView(jTree2);
    
    jCheckBox1.setText("Generate performance evaluation");
    jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jCheckBox1ActionPerformed(evt);
      }
    });
    
    jList1.setModel(new javax.swing.AbstractListModel() {
      String[] strings = { "Open node list" };
      public int getSize() { return strings.length; }
      public Object getElementAt(int i) { return strings[i]; }
    });
    jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
    jScrollPane3.setViewportView(jList1);
    
    org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 225, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jScrollPane3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 218, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jPanel2Layout.createSequentialGroup()
            .add(135, 135, 135)
            .add(jCheckBox1)))
            .addContainerGap(20, Short.MAX_VALUE))
            );
    jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
            .add(jScrollPane3)
            .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
            .add(jCheckBox1)
            .addContainerGap())
            );
    
    jButton1.setText("Step");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });
    
    jButton2.setText("Fast-Forward");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });
    
    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
            .add(40, 40, 40)
            .add(jButton1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 330, Short.MAX_VALUE)
            .add(jButton2)
            .add(32, 32, 32))
            .add(layout.createSequentialGroup()
            .add(27, 27, 27)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())))
            );
    layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 29, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jButton2)
            .add(jButton1))
            .add(53, 53, 53))))
            );
    pack();
  }
  
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    controlador.paso();
  }
  
  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    if(jCheckBox1.isSelected()) {
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
      fileChooser.setDialogTitle("Save as...");
      
      if(fileChooser.showSaveDialog(jPanel2) == JFileChooser.APPROVE_OPTION) {
        try {
          File file = fileChooser.getSelectedFile();
          this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.WAIT_CURSOR));
          controlador.rendimiento(file, 40);
          this.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.DEFAULT_CURSOR));
          jCheckBox1.setSelected(false);
          jButton1.setEnabled(estadoPrevio);
          jTree1.setEnabled(true);
          jTree2.setEnabled(true);
          jList1.setEnabled(true);
          jButton2.setEnabled(estadoPrevio);
          jButton2.setText(">>");
      
          javax.swing.JOptionPane.showMessageDialog(this, "Analysis completed",
                  "Information", javax.swing.JOptionPane.INFORMATION_MESSAGE);
          
          
        } catch(Exception e) {
          javax.swing.JOptionPane.showMessageDialog(this,
                  "An error has occurred: " + e.toString(), "error",
                  javax.swing.JOptionPane.ERROR_MESSAGE);
        }
      }
    } else {
      controlador.solucionar();
    }
  }
  
  private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {
    if(jCheckBox1.isSelected()) {
      jButton1.setEnabled(false);
      jTree1.setEnabled(false);
      jTree2.setEnabled(false);
      jList1.setEnabled(false);
      jButton2.setEnabled(true);
      jButton2.setText("Start");
    } else {
      jButton1.setEnabled(estadoPrevio);
      jTree1.setEnabled(true);
      jTree2.setEnabled(true);
      jList1.setEnabled(true);
      jButton2.setEnabled(estadoPrevio);
      jButton2.setText(">>");
    }
  }
  
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JList jList1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JTree jTree1;
  private javax.swing.JTree jTree2;
  
}

