/**
 * 
 */
package com.ucm.pe.algoritmo.genetico.simple;

import java.util.ArrayList;

/**
 * @author Ivan
 *
 */
public abstract class Cromosoma implements Cloneable{
	public int numero_genes = 0;
	public ArrayList<Gen> genes;
	public double aptitud_cromosoma = 0;
	public double puntuacion_individuo =0;
	public double puntuacion_acumulada = 0;
	public int longitud_cromosoma = 0;
	public double[] fenotipo;
	
	public Cromosoma() {
		// TODO Auto-generated constructor stub
	}
	
	public Cromosoma(int num_genes, double[] valor_rango_min, double[] valor_rango_max , double precision){
		numero_genes= num_genes;
		puntuacion_acumulada=0;
		puntuacion_individuo=0;
		longitud_cromosoma=0;
		genes= new ArrayList<Gen>(numero_genes);
		for (int i = 0; i < numero_genes; i++) {
			genes.add(new Gen(valor_rango_min[i], valor_rango_max[i],precision));
			
		}
		longitud_cromosoma=genes.get(0).getNum_caracteres_gen();
		aptitud_cromosoma=evalua();
		fenotipo=fenotipo();
	}
	

	@Override
	protected abstract Object clone() throws CloneNotSupportedException;
	public abstract double evalua();
	public abstract double[] fenotipo();
	public abstract void evaluaRecalcula(int num_genes);
	
}
