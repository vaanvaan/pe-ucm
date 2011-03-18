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
public class CromosomaF5 extends Cromosoma{
	private double evaluacion;
	private double[] fenotipo;
	
	public CromosomaF5() {
		// TODO Auto-generated constructor stub
	}
	
	public CromosomaF5(int num_genes, double[] valor_rango_min, double[] valor_rango_max , double precision) {
		super(num_genes, valor_rango_min, valor_rango_max, precision);
		evaluacion = 0;
		double x = (double) genes.get(0).getFenotipo();
		double y = (double) genes.get(1).getFenotipo();
		double parte_1 = 0;
		double parte_2 = 0;
		for (int i=1; i<=5; i++){
			parte_1 = parte_1+i*Math.cos((i+1)*x+i);
			parte_2 = parte_2+i*Math.cos((i+1)*y+i);
		}	
		evaluacion = parte_1 * parte_2;
		// parece redundancia de datos :(
		aptitud_cromosoma=evaluacion;
		fenotipo = new double[num_genes];
		fenotipo[0]=x;
		fenotipo[1]=y;
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
		double x = (double) genes.get(0).getFenotipo();
		double y = (double) genes.get(1).getFenotipo();
		double parte_1 = 0;
		double parte_2 = 0;
		for (int i=1; i<=5; i++){
			parte_1 = parte_1+i*Math.cos((i+1)*x+i);
			parte_2 = parte_2+i*Math.cos((i+1)*y+i);
		}	
		evaluacion = parte_1 * parte_2;
		aptitud_cromosoma=evaluacion;
		fenotipo[0]=x;
		fenotipo[1]=y;
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
		CromosomaF5 copia= new CromosomaF5();
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
