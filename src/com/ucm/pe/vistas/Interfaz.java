package com.ucm.pe.vistas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerNumberModel;

import org.math.plot.Plot2DPanel;

public class Interfaz extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labFuncion,labPoblacion,labGeneracion,
			labProbCruce,labProbMutacion,labPrecision, labFenotipo1, labFenotipo2, labValor;
	private JComboBox comboFuncion;
	private JSpinner spinPoblacion,spinGeneracion,spinProbCruce,
			spinProbMutacion,spinPrecision;
	private JButton butEjecutar;
	private ListenerGUI manejador; 
	private Plot2DPanel pGrafica1,pGrafica2;
	
	private GridBagConstraints cons;
	
	public Interfaz(){
		
		manejador=new ListenerGUI(this);
		this.add(construyeInterfaz());
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Práctica 1 de PE");

	}
	
	
	private JTabbedPane construyeInterfaz(){
		JTabbedPane panel=new JTabbedPane();
		panel.add(construyePanelDatos(),"Datos");
		panel.add(construyePanelGrafica1(),"Representación 1");
		panel.add(construyePanelGrafica2(),"Representación 2");

		panel.setBorder(BorderFactory.createEmptyBorder(10, 5, 10, 5));
		return panel;
	}
	
	
	private JPanel construyePanelDatos(){
		JPanel pDatos=new JPanel();
		GridBagLayout gbl=new GridBagLayout();
		pDatos.setLayout(gbl);
		
		cons=new GridBagConstraints();
		cons.ipadx=30;
		cons.ipady=10;
		cons.weighty = 1.0;
		cons.weightx = 1.0;
		cons.fill=GridBagConstraints.HORIZONTAL;
		labFuncion=new JLabel("Funcion:");
		rellenaConstraints(0, 0, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labFuncion,cons);
		
		
		String[] funciones={"1","2","3","4","5"};
		comboFuncion=new JComboBox(funciones);
		rellenaConstraints(2, 0, 2, 2, GridBagConstraints.EAST);
		pDatos.add(comboFuncion,cons);
		
		
		labPoblacion=new JLabel("Población inicial");
		rellenaConstraints(0, 2, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labPoblacion,cons);
		
		SpinnerNumberModel spinModel=new SpinnerNumberModel(80, 10, 200, 5);
		spinPoblacion=new JSpinner(spinModel);
		rellenaConstraints(2, 2, 2, 2, GridBagConstraints.EAST);
		pDatos.add(spinPoblacion,cons);
		
		
		labGeneracion=new JLabel("Número de generaciones");
		rellenaConstraints(0, 4, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labGeneracion,cons);
		
		spinModel=new SpinnerNumberModel(100, 20, 200, 5);
		spinGeneracion=new JSpinner(spinModel);
		rellenaConstraints(2, 4, 2, 2, GridBagConstraints.EAST);
		pDatos.add(spinGeneracion,cons);
		
		
		labPrecision=new JLabel("Precisión");
		rellenaConstraints(0, 6, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labPrecision,cons);
		
		spinModel=new SpinnerNumberModel(0.001,0.0001,1,0.001);
		spinPrecision=new JSpinner(spinModel);
		rellenaConstraints(2, 6, 2, 2, GridBagConstraints.EAST);
		pDatos.add(spinPrecision,cons);
		
		
		labProbCruce=new JLabel("Probabilidad de cruce");
		rellenaConstraints(0, 8, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labProbCruce,cons);
		
		spinModel=new SpinnerNumberModel(0.6,0,1,0.05);
		spinProbCruce=new JSpinner(spinModel);
		rellenaConstraints(2, 8, 2, 2, GridBagConstraints.EAST);
		pDatos.add(spinProbCruce,cons);
		
		
		labProbMutacion=new JLabel("Probabilidad de mutación");
		rellenaConstraints(0, 10, 2, 2, GridBagConstraints.WEST);
		pDatos.add(labProbMutacion,cons);
		
		spinModel=new SpinnerNumberModel(0.08,0,1,0.01);
		spinProbMutacion=new JSpinner(spinModel);
		rellenaConstraints(2, 10, 2, 2, GridBagConstraints.EAST);
		pDatos.add(spinProbMutacion,cons);
		
		
		butEjecutar=new JButton("Ejecutar");
		butEjecutar.setActionCommand("Ejecutar");
		butEjecutar.addActionListener(manejador);
		rellenaConstraints(0, 12, 1, 1, GridBagConstraints.CENTER);
		cons.weightx=0;
		cons.weighty=0;
		cons.ipadx=0;
		pDatos.add(butEjecutar,cons);
		
		cons.ipady=0;
		JPanel pSolucion=new JPanel();
		pSolucion.setLayout(new GridBagLayout());
		labFenotipo1=new JLabel("X: ");
		//rellenaConstraints(0, 13, 1, 1, GridBagConstraints.WEST);
		//pDatos.add(labFenotipo1,cons);
		rellenaConstraints(0, 0, 1, 1, GridBagConstraints.WEST);
		pSolucion.add(labFenotipo1,cons);
		labFenotipo2=new JLabel();
		//rellenaConstraints(2, 13, 1, 1, GridBagConstraints.WEST);
		//pDatos.add(labFenotipo2,cons);
		rellenaConstraints(1, 0, 1, 1, GridBagConstraints.WEST);
		pSolucion.add(labFenotipo2,cons);
		labFuncion=new JLabel("F: ");
		//rellenaConstraints(0, 14, 1, 1, GridBagConstraints.WEST);
		//pDatos.add(labFuncion,cons);
		rellenaConstraints(0, 1, 1, 1, GridBagConstraints.WEST);
		pSolucion.add(labFuncion,cons);
		pSolucion.setBorder(BorderFactory.createTitledBorder("Solucion"));
		
		rellenaConstraints(0, 13, 4, 1, GridBagConstraints.WEST);
		pDatos.add(pSolucion,cons);
		pDatos.setBorder(BorderFactory.createTitledBorder("Datos"));
		
		return pDatos;
	}
	
	
	private JPanel construyePanelGrafica1(){
		pGrafica1=new Plot2DPanel();
		return pGrafica1;
	}
	
	
	private JPanel construyePanelGrafica2(){
		pGrafica2=new Plot2DPanel();
		return pGrafica2;
	}
	

	private void rellenaConstraints(int x, int y, int anchoCelda, int altoCelda, int ancla){
        cons.gridx=x;
        cons.gridy=y;
        cons.gridwidth=anchoCelda;
        cons.gridheight=altoCelda;
        cons.anchor=ancla;
	}
	
	//public pob,gen,probCruce,probMut,
	public int valorPoblacion(){
		return (int)Integer.parseInt(spinPoblacion.getValue().toString());
	}
	
	public int valorGeneracion(){
		return (int)Integer.parseInt(spinGeneracion.getValue().toString());
	}
	
	public double valorProbCruce(){
		return (double)Double.parseDouble(spinProbCruce.getValue().toString());
	}
	
	public double valorProbMutacion(){
		return (double)Double.parseDouble(spinProbMutacion.getValue().toString());
	}
	
	public double valorPrecision(){
		return (double)Double.parseDouble(spinPrecision.getValue().toString());
	}
	
	//Suma 1 porque empieza en 0, y 0 representa la función 1
	public int valorFuncion(){
		return comboFuncion.getSelectedIndex()+1;
	}
	
	
	public static void main(String[] args) {
		new Interfaz();
	}


	public void representaGrafica1(double[] mejorGlobal) {
		pGrafica1.removeAllPlots();
		pGrafica1.addLinePlot("Mejor global", mejorGlobal);
		pGrafica1.addLegend("SOUTH");
	}
	
	public void representaGrafica2(double[] mejorGeneraciones, double[] mediaGeneraciones) {
		pGrafica2.removeAllPlots();
		pGrafica2.addLinePlot("Mejor por generaciones", mejorGeneraciones);
		pGrafica2.addLinePlot("Media por generaciones", mediaGeneraciones);
		pGrafica2.addLegend("SOUTH");
	}


	public void representaSolucion(double evalua, double fenotipo) {
		labFenotipo1.setText("X: "+fenotipo);
		labFuncion.setText("F: "+evalua);
	}	
	
	
}
