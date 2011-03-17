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
public class CromosomaF3 extends Cromosoma{
	private double evaluacion;
	private double[] fenotipo;
	
	public CromosomaF3() {
		// TODO Auto-generated constructor stub
	}
	
	public CromosomaF3(int num_genes, double[] valor_rango_min, double[] valor_rango_max , double precision) {
		super(num_genes, valor_rango_min, valor_rango_max, precision);
		evaluacion = 0;
		fenotipo=new double[num_genes];
		fenotipo[num_genes-1] = genes.get(num_genes-1).getFenotipo();
		double e = (double)Math.E;
		double pi = (double)Math.PI;
		evaluacion = -Math.abs(fenotipo[num_genes-1]*Math.sin(Math.sqrt(Math.abs(fenotipo[num_genes-1]))));
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
		fenotipo[num_genes-1] = genes.get(num_genes-1).recalculaFenotipo();
		double e = (double)Math.E;
		double pi = (double)Math.PI;
		evaluacion = -Math.abs(fenotipo[num_genes-1]*Math.sin(Math.sqrt(Math.abs(fenotipo[num_genes-1]))));
		aptitud_cromosoma=evaluacion;
		super.fenotipo=this.fenotipo;
	}
	
	@Override
	public String toString() {
		String descrip = "Feno="+fenotipo[numero_genes-1]+"||Eval="+evaluacion;
		return descrip;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		CromosomaF3 copia= new CromosomaF3();
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
