/**
 * 
 */
package com.ucm.pe.algoritmo.genetico.simple;

import java.util.ArrayList;
import java.util.Random;

import com.ucm.pe.problemas.funciones.CromosomaF1;


/**
 * @author Ivan
 *
 */
public class AlgoritmoGeneticoSimple {

	
	private int generacion_actual;
	private int tamanio_poblacion;
	private int num_max_generaciones;
	private double probabilidad_cruce;
	private double probabilidad_mutacion;
	private double tolerancia;
	private boolean maximizacion;
	ArrayList<Cromosoma> poblacion=null;
	private Cromosoma solucion=null;
	private int tipo_metodo_seleccion=0;

	// vars de las estadísticas que tenemos que recoger
	private double[] mejor_generacion_actual;
	private double[] media_generacion_actual;
	private double[] mejor_sobre_generacional;
	
	public AlgoritmoGeneticoSimple() {
		// TODO Auto-generated constructor stub
	}

	public void lanzarAlgoritmo() {
		try {
			obtener_parametros();
			inicializaPoblacion();
			evaluarPoblacion();
			while(!terminado()){
				generacion_actual++;
				seleccion(tipo_metodo_seleccion);
				reproduccion();
				mutacion();
				evaluarPoblacion();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	private void mutacion() {
		// TODO Auto-generated method stub
		boolean mutado;
		double probabilidad;
		Random r=new Random();
		int num_genes = poblacion.get(0).numero_genes;
		for(int i=0; i<tamanio_poblacion; i++){
			mutado = false;
			for(int j=0; j<poblacion.get(i).numero_genes; j++){
				probabilidad = r.nextDouble();
				if(probabilidad < probabilidad_mutacion){
					//el 0 indica el gen en el que muta hay que poder soportar diferentes genes
					ArrayList<Boolean> info_genetica = poblacion.get(i).genes.get(0).getInformacion_genetica();
					info_genetica.set(j, !info_genetica.get(j)); // true --> false y al revés
					mutado = true;
				}
			}
			if(mutado){
				poblacion.get(i).evaluaRecalcula(num_genes);
			}
		}
	}

	private void reproduccion() {
		// TODO Auto-generated method stub

		int[] indices_cruce = new int[tamanio_poblacion];
		int cont_cruces = 0;
		double probabilidad;
		Random r=new Random();
		for(int i=0; i<tamanio_poblacion; i++){
			probabilidad=r.nextDouble();
			if(probabilidad<probabilidad_cruce){
				indices_cruce[cont_cruces] = i;
				cont_cruces++;
			}
		}
		//si son impares, los hacemos pares quitando el último
		if((cont_cruces % 2)==1){
			cont_cruces--;
		}
		
		System.out.println("INDICES DE CRUCE =====> "+indices_cruce.toString());
		int longitud_cromosoma=poblacion.get(0).longitud_cromosoma;
		int punto_cruce = r.nextInt(longitud_cromosoma);
		for(int i=0; i<cont_cruces; i+=2){
			realizaCruce(indices_cruce[i], indices_cruce[i+1], punto_cruce,longitud_cromosoma);	
		}
	
	}

	private void realizaCruce(int indice_padre, int indice_madre, int punto_cruce, int longitud_crmosoma) {
		// TODO Auto-generated method stub
		try{
			Cromosoma padre = poblacion.get(indice_padre);
			Cromosoma madre = poblacion.get(indice_madre);
			Cromosoma hijo_1= (Cromosoma) padre.clone();
			Cromosoma hijo_2= (Cromosoma) madre.clone();
			System.out.println("PADRE===>"+hijo_1.toString()+"||"+hijo_1.genes.get(0).toString());
			System.out.println("MADRE===>"+hijo_2.toString()+"||"+hijo_2.genes.get(0).toString());
			//de momento se hace el cruce en cada uno de los genes del individuo
			for (int j=0; j < hijo_1.numero_genes;j++){
				// cadena booleana del hijo parte padre
				Gen gen_h1= hijo_1.genes.get(j);
				ArrayList<Boolean> info_genetica_hijo = gen_h1.getInformacion_genetica();
				//cadena booleana de la madre
				Gen gen_madre = madre.genes.get(j);
				ArrayList<Boolean> info_genetica_madre = gen_madre.getInformacion_genetica();
				// cadena booleana del hijo parte madre
				Gen gen_h2= hijo_2.genes.get(j);
				ArrayList<Boolean> info_genetica_hijo2 = gen_h2.getInformacion_genetica();
				//cadena booleana de la padre
				Gen gen_padre = padre.genes.get(j);
				ArrayList<Boolean> info_genetica_padre = gen_padre.getInformacion_genetica();
				//intercambiamos la info
				for (int i=punto_cruce; i<longitud_crmosoma; i++){
					info_genetica_hijo.set(i, info_genetica_madre.get(i));
					info_genetica_hijo2.set(i, info_genetica_padre.get(i));
				}
				hijo_1.genes.get(j).setInformacion_genetica(info_genetica_hijo);
				hijo_2.genes.get(j).setInformacion_genetica(info_genetica_hijo2);
			}
			hijo_1.evaluaRecalcula(hijo_1.numero_genes);
			hijo_2.evaluaRecalcula(hijo_2.numero_genes);
			//sustituimos los padres por los hijos
			/*
			 * TODO aqui se pueden meter nuevas estrategias de sustitución
			 */
			System.out.println("PADRE===>"+padre.toString()+"||"+padre.genes.get(0).toString());
			System.out.println("MADRE===>"+madre.toString()+"||"+madre.genes.get(0).toString());
			System.out.println("HIJO MEZCLADO 1 ======>"+hijo_1.toString()+"||"+hijo_1.genes.get(0).toString());
			System.out.println("HIJO MEZCLADO -2- ======>"+hijo_2.toString()+"||"+hijo_2.genes.get(0).toString());
			poblacion.set(indice_padre, hijo_1);
			poblacion.set(indice_madre, hijo_2);
		}catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void seleccion(int tipoSeleccion) {
		// TODO Auto-generated method stub
		switch (tipoSeleccion) {
		case 0: seleccionRuleta(tamanio_poblacion);
				break;
		default: break;
		}
	}
	
	private void seleccionRuleta(int tam_poblacion){
		ArrayList<Cromosoma>  nueva_pob = new ArrayList<Cromosoma>(tam_poblacion);
		double probSeleccion;
		int pos_superviviente;
		Random r=new Random();
		try {
			for (int i=0;i<tam_poblacion;i++){
				probSeleccion=r.nextDouble();
				pos_superviviente=0;
				while ((probSeleccion>poblacion.get(pos_superviviente).puntuacion_acumulada) &&
						pos_superviviente <tam_poblacion){
					pos_superviviente++;
				}
				nueva_pob.add((Cromosoma) poblacion.get(pos_superviviente).clone());
				System.out.println("SELECCION====> nodo seleccionado====>"+poblacion.get(pos_superviviente).toString());
			}
			poblacion=nueva_pob;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean terminado() {
		// TODO Auto-generated method stub
		boolean terminado=false;
		if (generacion_actual>=num_max_generaciones-1) //TODO Falta convergencia
			terminado=true;
		System.out.println("NO HEMOS ACABADO");
		System.out.println("EL MEJOR POR AHORA ES" + solucion.toString());
		return terminado;
	}

	private void evaluarPoblacion() {
		// TODO Auto-generated method stub
		double punt_acumulada = 0.0;
		double aptitud_mejor = 0.0;
		double suma_aptitud = 0.0;
		int posicion_mejor=-1;
		//..
		//si es maximización o minimización
		//
		for(int i=0; i<tamanio_poblacion; i++){
			Cromosoma individuo = poblacion.get(i);
			if(individuo.aptitud_cromosoma > aptitud_mejor){
				posicion_mejor=i;
				aptitud_mejor = individuo.evalua();
			}
			suma_aptitud = suma_aptitud + individuo.evalua();
		}
		//actualizamos las variables de muestreo
		mejor_generacion_actual[generacion_actual] = aptitud_mejor;
		media_generacion_actual[generacion_actual] = suma_aptitud/tamanio_poblacion;
		
		//Asignación de la puntucaión y pontuación acumulada por individuo
		for (int i=0; i<tamanio_poblacion; i++){
			Cromosoma individuo = poblacion.get(i);
			double puntuacion = individuo.evalua()/suma_aptitud;
			punt_acumulada = punt_acumulada + puntuacion;
			individuo.puntuacion_individuo=puntuacion;
			individuo.puntuacion_acumulada=punt_acumulada;
		}
		if(solucion==null ){ // si es el primero se asigna
			solucion=poblacion.get(0);
			posicion_mejor=0;
		}
//		//comprobamos si supera el que llevamos para la solución.
//		if(solucion.aptitud_cromosoma< poblacion.get(posicion_mejor).aptitud_cromosoma){
//			solucion=poblacion.get(posicion_mejor);
//		}
//		//tenemos que ver si hay que actualizar el global
//		if(generacion_actual>0){
//			mejor_sobre_generacional[generacion_actual] = solucion.evalua();
//		}
		if (solucion==null || aptitud_mejor>solucion.aptitud_cromosoma){
			try {
				solucion=(Cromosoma) poblacion.get(posicion_mejor).clone();
				System.out.println("EL MEJOR POR AHORA ES:"+solucion.toString());
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		if()
	}

	private void inicializaPoblacion() {
		// TODO Auto-generated method stub
		mejor_generacion_actual= new double[num_max_generaciones];
		media_generacion_actual= new double[num_max_generaciones];
		mejor_sobre_generacional= new double[num_max_generaciones];
		generacion_actual=0;
		poblacion = new ArrayList<Cromosoma>(tamanio_poblacion);
		//para la función 1
		for (int i = 0; i < tamanio_poblacion; i++) {
			double[] min = {0};
			double[] max = {32};
			poblacion.add(new CromosomaF1(1,min,max,tolerancia));
			System.out.println("Inicializacion===>"+poblacion.get(i).toString());
		}
		maximizacion=true;
		//fin para la primera funcion
	}

	private void obtener_parametros() {
		tamanio_poblacion=10;
		probabilidad_cruce=0.7;
		num_max_generaciones=10;
		probabilidad_mutacion=0.01;
		tolerancia=0.0001;
	}
}
