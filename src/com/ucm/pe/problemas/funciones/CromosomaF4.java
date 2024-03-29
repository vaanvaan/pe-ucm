/**
 * 
 */
package com.ucm.pe.problemas.funciones;

import java.util.ArrayList;

import com.ucm.pe.algoritmo.genetico.simple.Cromosoma;
import com.ucm.pe.algoritmo.genetico.simple.Gen;


/**
 * @author Ivan
 *
 */
public class CromosomaF4 extends Cromosoma{
	private double evaluacion;
	private double[] fenotipo;
	
	public CromosomaF4() {
		// TODO Auto-generated constructor stub
	}
	
	public CromosomaF4(int num_genes, double[] valor_rango_min, double[] valor_rango_max , double precision) {
		super(num_genes, valor_rango_min, valor_rango_max, precision);
		evaluacion = 0;
		double x = 0;
		fenotipo = new double[num_genes];
		for (int i=0; i<numero_genes; i++){
			x=genes.get(i).getFenotipo();
			fenotipo[i]=x;
			evaluacion=evaluacion +(-x*Math.sin(Math.sqrt(Math.abs(x))));
		}
		// parece redundancia de datos :(
		aptitud_cromosoma=evaluacion;
		super.fenotipo=this.fenotipo;
	}

	@Override
	public double evalua() {
		// TODO Auto-generated method stub
		return evaluacion;
	}

	@Override
	public double[] fenotipo() {
		// TODO Auto-generated method stub
		return fenotipo;
	}

	@Override
	public void evaluaRecalcula(int num_genes) {
		evaluacion = 0;
		double x = 0;
		fenotipo = new double[num_genes];
		for (int i=0; i<numero_genes; i++){
			x=genes.get(i).getFenotipo();
			fenotipo[i]=x;
			evaluacion=evaluacion +(-x*Math.sin(Math.sqrt(Math.abs(x))));
		}
		aptitud_cromosoma=evaluacion;
		super.fenotipo=this.fenotipo;
	}
	
	@Override
	public String toString() {
		String descrip = "Feno=";
		for (int i = 0; i < numero_genes; i++) {
			descrip= descrip+fenotipo[i]+"||";
		}
		descrip=descrip+"||Eval="+evaluacion;
		return descrip;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		CromosomaF4 copia= new CromosomaF4();
		copia.aptitud_cromosoma=aptitud_cromosoma;
		copia.evaluacion=evaluacion;
		copia.fenotipo=fenotipo;
		ArrayList<Gen> copygenes = new ArrayList<Gen>();
		for (int i = 0; i < numero_genes; i++) {
			Gen gen = genes.get(i).copia();
			copygenes.add(gen);
		}
		copia.genes=copygenes;
		copia.numero_genes = numero_genes;
		copia.puntuacion_individuo = puntuacion_individuo;
		copia.puntuacion_acumulada = puntuacion_acumulada;
		copia.longitud_cromosoma = longitud_cromosoma;
		return copia;
	}
	
}
