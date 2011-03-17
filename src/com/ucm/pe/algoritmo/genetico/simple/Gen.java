/**
 * 
 */
package com.ucm.pe.algoritmo.genetico.simple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Ivan
 *
 */
//T == Boolean
public class Gen {
	
	private int num_caracteres_gen = 0;
	private double limite_valor_minimo = 0;
	private double limite_valor_maximo = 0;
	private ArrayList<Boolean> informacion_genetica = null;
	private double fenotipo;
	
	public double getFenotipo() {
		return fenotipo;
	}

	public void setFenotipo(double fenotipo) {
		this.fenotipo = fenotipo;
	}
	
	public int getNum_caracteres_gen() {
		return num_caracteres_gen;
	}

	public void setNum_caracteres_gen(int num_caracteres_gen) {
		this.num_caracteres_gen = num_caracteres_gen;
	}
	
	public Gen() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Boolean> getInformacion_genetica() {
		return informacion_genetica;
	}


	public void setInformacion_genetica(ArrayList<Boolean> informacion_genetica) {
		this.informacion_genetica = informacion_genetica;
	}


	public Gen(double valor_rango_min, double valor_rango_max, double precision) {
		limite_valor_minimo=valor_rango_min;
		limite_valor_maximo=valor_rango_max;
		num_caracteres_gen = calcular_tamanio_gen(precision,valor_rango_min,valor_rango_max);
		informacion_genetica = new ArrayList<Boolean>(num_caracteres_gen);
		inicializar_gen(informacion_genetica,num_caracteres_gen);
		fenotipo=0;
		fenotipo = (limite_valor_minimo + 
				(limite_valor_maximo - limite_valor_minimo)
				* bin_a_dec(informacion_genetica) / (Math.pow(2,num_caracteres_gen) - 1));
	}

	private int bin_a_dec(ArrayList<Boolean> genes){
		int valor=0;
		//int lGen=getLongGen();
		int lGen=genes.size();
		String enBinario=new String();
		for (int i=0;i<lGen;i++){
			String sAux = genes.get(i)?"1":"0";
			enBinario=enBinario.concat(sAux);
		}
		valor = (int)Integer.parseInt(enBinario, 2);
		return valor;
	}

	private void inicializar_gen(ArrayList<Boolean> info_genetica,
			int num_cars_gen) {
		Random r=new Random();
		for (int i=0;i<num_cars_gen;i++)
			info_genetica.add(r.nextBoolean());
	}


	private int calcular_tamanio_gen(double precision, double valor_rango_min,
			double valor_rango_max) {
		double log10=1+((valor_rango_max-valor_rango_min)/precision);
		int res = (int) Math.ceil((Math.log(log10)/Math.log(2))) ;
		return res;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Iterator  it = informacion_genetica.iterator();
		String bits = "";
		while(it.hasNext()){
			if ((Boolean)it.next()){
				bits = bits +"1";
			}else
				bits = bits +"0";
		}
		String gen = "GEN==>"+bits;
		return gen;
	}

	public double recalculaFenotipo() {
		fenotipo = (limite_valor_minimo + 
				(limite_valor_maximo - limite_valor_minimo)
				* bin_a_dec(informacion_genetica) / (Math.pow(2,num_caracteres_gen) - 1));
		return fenotipo;
	}

	public Gen copia() {
		Gen copia = new Gen();
		copia.num_caracteres_gen = num_caracteres_gen;
		copia.limite_valor_minimo = limite_valor_minimo;
		copia.limite_valor_maximo = limite_valor_maximo;
		ArrayList<Boolean> info_genet_copy = new ArrayList<Boolean>();
		Iterator it = informacion_genetica.iterator();
		while (it.hasNext()) {
			Boolean caracter_gen = (Boolean) it.next();
			info_genet_copy.add(new Boolean(caracter_gen));
		}
		copia.informacion_genetica=info_genet_copy;
		copia.fenotipo=fenotipo;
		return copia;
	}
	
	

}
