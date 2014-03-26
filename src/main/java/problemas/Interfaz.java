package problemas;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashSet;

public class Interfaz extends java.awt.Frame {
  private Controlador controlador;
  private short inicial, objetivo;
  private HashSet<Short> tabues;
  private javax.swing.JFileChooser fileChooser;
  
  public Interfaz() {
    initComponents();
    fileChooser = new javax.swing.JFileChooser();
    tabues = new HashSet<Short>();
    controlador = new Controlador(this);
  }
  
  public int mostrarMensaje() {
    return javax.swing.JOptionPane.showConfirmDialog(this, 
                "Results window will be cleared, do you want to continue?",
                "Choose", javax.swing.JOptionPane.YES_NO_OPTION);
  }
  public short getInicial() {
    return inicial;
  }
  
  public short getObjetivo() {
    return objetivo;
  }
  
  public HashSet<Short> getTabues() {
    if(jCheckBox1.isSelected()) {
      return tabues;
    }
    return new HashSet<Short>();
  }
  
  public int getHeuristica() {
    if(jComboBox1.isEnabled()) {
      return jComboBox1.getSelectedIndex();
    }
    return -1;
  }
  
  public int getBusqueda() {
    return buttonGroup1.getSelection().getMnemonic();
  }
  
  private void initComponents() {
    buttonGroup1 = new javax.swing.ButtonGroup();
    jPanel1 = new javax.swing.JPanel();
    jRadioButton1 = new javax.swing.JRadioButton();
    jRadioButton2 = new javax.swing.JRadioButton();
    jRadioButton3 = new javax.swing.JRadioButton();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();
    jCheckBox1 = new javax.swing.JCheckBox();
    jButton1 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jFormattedTextField3 = new javax.swing.JFormattedTextField(createFormatter("###"));
    jPanel3 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jFormattedTextField1 = new javax.swing.JFormattedTextField(createFormatter("###"));
    jFormattedTextField2 = new javax.swing.JFormattedTextField(createFormatter("###"));
    jButton2 = new javax.swing.JButton();
    String [] str = {"Manhattan Distance", "Maximum Manhattan Distance"};
    jComboBox1 = new javax.swing.JComboBox(str);

    buttonGroup1.add(jRadioButton1);
    buttonGroup1.add(jRadioButton2);
    buttonGroup1.add(jRadioButton3);

    setResizable(false);
    setTitle("Path finding algorithms");
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent evt) {
        exitForm(evt);
      }
    });

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Method"));
    jPanel1.setDoubleBuffered(false);
    
    jRadioButton1.setSelected(true);
    jRadioButton1.setText("A*");
    jRadioButton1.setMnemonic(1);
    jRadioButton1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jRadioButton1.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton1ActionPerformed(evt);
      }
    });

    jRadioButton2.setText("Breadth-First Search");
    jRadioButton2.setMnemonic(2);
    jRadioButton2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jRadioButton2.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton2ActionPerformed(evt);
      }
    });

    jRadioButton3.setText("Hill CLimb");
    jRadioButton3.setMnemonic(3);
    jRadioButton3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jRadioButton3.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jRadioButton3ActionPerformed(evt);
      }
    });
    
    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .add(29, 29, 29)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jRadioButton1)
          .add(jRadioButton2)
          .add(jRadioButton3)
          .add(jPanel1Layout.createSequentialGroup()
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 157, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .addContainerGap(52, Short.MAX_VALUE))
    );

    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .add(19, 19, 19)
        .add(jRadioButton1)
        .add(5, 5, 5)
        .add(jRadioButton2)
        .add(5, 5, 5)
        .add(jRadioButton3)
        .add(21, 21, 21)
        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(31, Short.MAX_VALUE))
    );

    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Impossibles"));
    jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
    jList1.setEnabled(false);
    jList1.setValueIsAdjusting(true);
    jScrollPane1.setViewportView(jList1);

    jCheckBox1.setText("Activate impossibles");
    jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
    jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
    jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        activarLista(evt);
      }
    });

    jButton1.setText("Load");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    jButton3.setText("Add");
    jButton3.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton3ActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel2Layout.createSequentialGroup()
        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel2Layout.createSequentialGroup()
            .add(119, 119, 119)
            .add(jButton1))
          .add(jPanel2Layout.createSequentialGroup()
            .addContainerGap()
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jPanel2Layout.createSequentialGroup()
                .add(25, 25, 25)
                .add(jButton3))
              .add(jPanel2Layout.createSequentialGroup()
                .add(44, 44, 44)
                .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 38, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
          .add(jPanel2Layout.createSequentialGroup()
            .add(28, 28, 28)
            .add(jCheckBox1)))
        .addContainerGap(28, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel2Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel2Layout.createSequentialGroup()
            .add(jFormattedTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jButton3))
          .add(org.jdesktop.layout.GroupLayout.LEADING, jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 195, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jCheckBox1)
        .add(18, 18, 18)
        .add(jButton1)
        .addContainerGap())
    );

    jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Problem"));
    jLabel1.setText("Origin");

    jLabel2.setText("Dest. ");

    jLabel3.setText("-->");

    org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jFormattedTextField1)
          .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .add(39, 39, 39)
        .add(jLabel3)
        .add(43, 43, 43)
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jFormattedTextField2)
          .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .add(15, 15, 15))
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel3Layout.createSequentialGroup()
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel3Layout.createSequentialGroup()
            .add(jLabel1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jFormattedTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(jPanel3Layout.createSequentialGroup()
            .add(jLabel2)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
              .add(jLabel3)
              .add(jFormattedTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
        .addContainerGap(34, Short.MAX_VALUE))
    );

    jButton2.setText("Start");
    jButton2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton2ActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(35, 35, 35)
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
          .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
          .add(layout.createSequentialGroup()
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jButton2)
            .add(64, 64, 64))
          .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(layout.createSequentialGroup()
            .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(19, 19, 19)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(17, 17, 17)
            .add(jButton2)))
        .addContainerGap(29, Short.MAX_VALUE))
    );
    pack();
  }// </editor-fold>                        
  private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    jComboBox1.setEnabled(true);
  }
  
  private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
    jComboBox1.setEnabled(false);
  }
  
  private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {
    jComboBox1.setEnabled(true);
  }
  
  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
    fileChooser.setDialogTitle("Load impossibles...");
    
    if(fileChooser.showOpenDialog(jPanel2) == JFileChooser.APPROVE_OPTION) {
      try {
        String s;
        File file = fileChooser.getSelectedFile();
        BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
        s = in.readLine();
        while(s != null) { // mientras no sea fin de fichero, seguimos leyendo
          short n = new Short(s);
          if(n < 100 || n > 999) {
            throw new Exception("Parse error: Number out of bounds");
          } else {
            tabues.add(n);
          }
          s = in.readLine();
        }
        in.close();
        jList1.setListData(tabues.toArray());
        
      } catch(Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, 
                "Parse error", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
      }
    }
  }//GEN-LAST:event_jButton1ActionPerformed

  private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    if(jFormattedTextField3.getText().length() == 3) {
      try { 
        Short s = new Short(jFormattedTextField3.getText());
        if(s >= 100) {
          tabues.add(s);
          jList1.setListData(tabues.toArray());
        }
      } catch(NumberFormatException exc) {
          exc.printStackTrace();
      }
    } 
  }//GEN-LAST:event_jButton3ActionPerformed

  private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
      if(jFormattedTextField1.getText().length() == 3 &&
              jFormattedTextField2.getText().length() == 3) {
        Short s1 = new Short(jFormattedTextField1.getText());
        Short s2 = new Short(jFormattedTextField2.getText());
        if(s1 >= 100 && s2 >= 100) {
          this.inicial = s1;
          this.objetivo = s2;
          controlador.iniciar();
        } else {
          javax.swing.JOptionPane.showMessageDialog(this, 
                "Defined problem numbers out of bounds", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }
      }
    } catch(NumberFormatException exc) {
      javax.swing.JOptionPane.showMessageDialog(this, 
                "Malformed problem", "error", javax.swing.JOptionPane.ERROR_MESSAGE);
    } catch(Exception exc) {
      javax.swing.JOptionPane.showMessageDialog(this, 
                exc.toString(), "error", javax.swing.JOptionPane.ERROR_MESSAGE);
    }
  }//GEN-LAST:event_jButton2ActionPerformed

  private void activarLista(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarLista
    jList1.setEnabled(jCheckBox1.isSelected());
  }//GEN-LAST:event_activarLista
  
  private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
    System.exit(0);
  }//GEN-LAST:event_exitForm
  
  protected MaskFormatter createFormatter(String s) {
    MaskFormatter formatter = null;
    try {
      formatter = new MaskFormatter(s);
    } catch (java.text.ParseException exc) {
      System.err.println("Bad formatted: " + exc.getMessage());
      System.exit(-1);
    }
    return formatter;
  }
  
  public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Interfaz().setVisible(true);
      }
    });
  }
  
  private javax.swing.ButtonGroup buttonGroup1;
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JFormattedTextField jFormattedTextField1;
  private javax.swing.JFormattedTextField jFormattedTextField2;
  private javax.swing.JFormattedTextField jFormattedTextField3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JList jList1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JRadioButton jRadioButton1;
  private javax.swing.JRadioButton jRadioButton2;
  private javax.swing.JRadioButton jRadioButton3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JComboBox jComboBox1;
}
