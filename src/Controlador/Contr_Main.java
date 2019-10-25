/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Funciones.Semantico;
import Funciones.Sintactico;
import Vista.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;

/**
 *
 * @author jorge
 */
public class Contr_Main {
   private javax.swing.JFrame jFrame1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane numeracion;
    private javax.swing.JButton jButtonEjecutar;
    private javax.swing.JTable jTableVar;
    private javax.swing.JTable jTableFunc;

    private Semantico semantico;


    private int nfila;
    
    public Contr_Main(Main ventana){
        jFrame1=ventana.getjFrame1();
        jPanel1=ventana.getjPanel1();
        jScrollPane1=ventana.getjScrollPane1();
        jScrollPane2=ventana.getjScrollPane2();
        jTabbedPane1=ventana.getjTabbedPane1();
        jTextPane1=ventana.getjTextPane1();
        jTextPane2=ventana.getjTextPane2();
        numeracion=new JTextPane();
        nfila=1;
        numeracion.setEditable(false);
        numeracion.setBackground(new Color(244,244,244));
        numeracion.setForeground(new Color(0,128,192));
        Font font=new Font("Arial",Font.PLAIN,18);
        numeracion.setFont(font);
        jTextPane1.setFont(font);
        jTextPane2.setFont(font);
//        numeracion.setEnabled(false);
        numeracion.setText("1");
        jButtonEjecutar=ventana.getjButtonEjecutar();
        jTableVar=ventana.getjTable1();
        jTableFunc=ventana.getjTable2();
        semantico=new Semantico(jScrollPane2,jTableVar,jTableFunc);
        jTextPane2=ventana.getjTextPane2();

        Document doc = jTextPane1.getDocument();
        doc.addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                if(nfila!=doc.getDefaultRootElement().getElementCount()){
                    String cad=numeracion.getText();
                    if(nfila<doc.getDefaultRootElement().getElementCount()){
                        while(nfila<doc.getDefaultRootElement().getElementCount()){
                            nfila++;
                            cad=cad+"\n"+nfila;
                        }
                        numeracion.setText(cad);
                    }else{
                        int dif=(nfila-doc.getDefaultRootElement().getElementCount());
                        int cont=0,i=cad.length()-1;
                        while(cont<dif){
                            if(cad.charAt(i)==10){
                                 cont++;
                            }
                            i--;
                        }
                        numeracion.setText(numeracion.getText().substring(0,i+1));
                        nfila=doc.getDefaultRootElement().getElementCount();
                    }
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                if(nfila!=doc.getDefaultRootElement().getElementCount()){
                    int dif=(nfila-doc.getDefaultRootElement().getElementCount());
                    String cad=numeracion.getText();
                    int cont=0,i=cad.length()-1;
                    while(cont<dif){
                        if(cad.charAt(i)==10){
                             cont++;
                        }
                        i--;
                    }
                    numeracion.setText(numeracion.getText().substring(0,i+1));
                    nfila=doc.getDefaultRootElement().getElementCount();
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        jScrollPane1.setViewportView(jTextPane1);
        jScrollPane1.setRowHeaderView(numeracion);
        jButtonEjecutar.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String cadena=jTextPane1.getText();
                semantico.run(cadena);
                jTextPane2.setText(semantico.getOutput());
            }
        });
        
    }
}
