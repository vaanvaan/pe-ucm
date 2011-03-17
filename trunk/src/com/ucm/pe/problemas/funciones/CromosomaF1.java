/**
 * 
 */
package com.ucm.pe.problemas.funciones;

import com.ucm.pe.algoritmo.genetico.simple.Cromosoma;

/**
 * @author Ivan
 *
 */
public class CromosomaF1 extends Cromosoma{
	private double evaluacion;
	private double fenotipo;
	
	public CromosomaF1(int num_genes, double[] valor_rango_min, double[] valor_rango_max , double precision) {
		super(num_genes, valor_rango_min, valor_rango_max, precision);
		evaluacion = 0;
		fenotipo = genes.get(num_genes-1).getFenotipo();
		double e = (double)Math.E;
		double pi = (double)Math.PI;
		evaluacion = 20 + e - 20.0 * Math.pow(e,-0.2*Math.abs(fenotipo)) -Math.pow(e, Math.cos(2.0*pi*fenotipo));
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
	public double fenotipo() {
		// TODO Auto-generated method stub
		return fenotipo;
	}

	@Override
	public void evaluaRecalcula(int num_genes) {
		evaluacion = 0;
		fenotipo = genes.get(num_genes-1).getFenotipo();
		double e = (double)Math.E;
		double pi = (double)Math.PI;
		evaluacion = 20 + e - 20.0 * Math.pow(e,-0.2*Math.abs(fenotipo)) -Math.pow(e, Math.cos(2.0*pi*fenotipo));
		aptitud_cromosoma=evaluacion;
		super.fenotipo=this.fenotipo;
	}
	
	@Override
	public String toString() {
		String descrip = "Feno="+fenotipo+"||Eval="+evaluacion;
		return descrip;
	}
	
}
