package com.ucm.pe.vistas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.ucm.pe.algoritmo.genetico.simple.AlgoritmoGeneticoSimple;

public class ListenerGUI implements ActionListener {

	private Interfaz interfaz;
	public ListenerGUI(Interfaz interfaz) {
		this.interfaz=interfaz;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Ejecutar")){
            //interfaz.ejecuta();
			int poblacion=interfaz.valorPoblacion();
			int numGeneraciones=interfaz.valorGeneracion();
			double probCruce=interfaz.valorProbCruce();
			double probMutacion=interfaz.valorProbMutacion();
			double precision=interfaz.valorPrecision();
			int funcion=interfaz.valorFuncion();
			
			AlgoritmoGeneticoSimple ag = new AlgoritmoGeneticoSimple();
			ag.lanzarAlgoritmo(poblacion,numGeneraciones,funcion,probCruce,probMutacion,precision);
			
			double[] mejor_generaciones=ag.getMejor_generacion_actual();
			double[] media_generaciones=ag.getMedia_generacion_actual();
			double[] mejor_global=ag.getMejor_sobre_generacional();
			interfaz.representaGrafica1(mejor_global);
			interfaz.representaGrafica2(mejor_generaciones,media_generaciones);
			interfaz.representaSolucion(ag.getSolucion().evalua(),ag.getSolucion().fenotipo()[ag.getSolucion().numero_genes-1]);
    }


	}

}
