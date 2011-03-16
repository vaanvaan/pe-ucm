package algortimo;

import java.util.Random;
import java.util.Scanner;

import cromosoma.Cromosoma;
import cromosoma.CromosomaF1;

public class AlgoritmoGenetico {

	private Cromosoma[] poblacion;
	private int tam_poblacion;
	private int num_max_generaciones;
	private Cromosoma cromosomaMejor;
	private int pos_mejor;
	private double prob_cruce;
	private double prob_mutacion;
	private double tolerancia;
	private int numGenAct;
	
	public void obtener_parametros(){
//		Scanner scan = new Scanner(System.in);
//		System.out.println("Indique tamaño de poblacion");
//		tam_poblacion= scan.nextInt();
		tam_poblacion=100;
//		System.out.println("Indique probabilidad de cruce");
//		prob_cruce = scan.nextFloat();
		prob_cruce=0.7;
//		System.out.println("Indique el numero de generaciones");
//		num_max_generaciones= scan.nextInt();
		num_max_generaciones=100;
//		System.out.println("Indique la probabilidad de mutacion");
//		prob_mutacion = scan.nextFloat();
		prob_mutacion=0.01;
		//System.out.println("Tolerancia?");
		//tolerancia= scan.nextFloat();
		tolerancia=0.0001;
	}
	
	
	public AlgoritmoGenetico(){
		obtener_parametros();
		System.out.println("obtener params");
		inicializaPoblacion();
		System.out.println("inicializa pob");
		this.evaluarPoblacion();
		System.out.println("evalua poblac");
		while(!this.terminado()){
			System.out.println("no terminado");
			this.numGenAct++;
			System.out.println("generacion="+numGenAct);
 			this.seleccion(0,this.tam_poblacion);
 			System.out.println("seleccion");
			this.reproduccion(this.tam_poblacion);
			System.out.println("reproduccion");
			this.mutacion(this.tam_poblacion);
			System.out.println("mutacion");
			this.evaluarPoblacion();
			System.out.println("evaluacion");
			System.out.println("EL MEJOR ES= "+cromosomaMejor.getAptitud());
		}
		System.out.println("FIN");
	}
public static void main(String[] args) {
	AlgoritmoGenetico ag = new AlgoritmoGenetico();
}
	private void evaluarPoblacion() {
		double puntuacion_acumul = 0;
		double aptitud_mejor = 0;
		double sumaaptitud=0;
		for (int i = 0; i < this.tam_poblacion; i++) {
			sumaaptitud = sumaaptitud + poblacion[i].getAptitud();
			if (poblacion[i].getAptitud()>aptitud_mejor) {
				pos_mejor=i;
				aptitud_mejor= poblacion[i].getAptitud();
			}
		}
		//revisamos los contadores de aptitud relativa y punt acumulada
		for (int i = 0; i < tam_poblacion; i++) {
			poblacion[i].setPuntuacion(poblacion[i].getAptitud()/sumaaptitud);
			poblacion[i].setPuntAcum(poblacion[i].getPuntuacion()+puntuacion_acumul);
			puntuacion_acumul = puntuacion_acumul+poblacion[i].getPuntuacion();
		}
		//actualizamos el mejor
		if (cromosomaMejor==null){
			cromosomaMejor=poblacion[pos_mejor].copia();
		}
		if (aptitud_mejor>cromosomaMejor.getAptitud()) {
			cromosomaMejor=poblacion[pos_mejor];
		}
	}


	private void reproduccion(int tam_poblacion) {
		int[] indexCruce = new int[tam_poblacion];
		int contCruce = 0;
		double probabilidad;
		
		for(int i=0; i<tam_poblacion; i++){
			probabilidad = Math.random();
			if(probabilidad<prob_cruce){
				indexCruce[contCruce] = i;
				contCruce++;
			}
		}
		// en caso de un numero de cruces impar, le quitamos 1 al contador
		// por lo que se saltaría el último individuo
		if((contCruce % 2)!=0){
			contCruce--;
		}
		Random r = new Random();
		int l=this.poblacion[0].getLongCrom();
		int puntoCruce = r.nextInt(l);
		for(int i=0; i<contCruce; i+=2){
			realizarCruce(indexCruce[i], indexCruce[i+1], puntoCruce);	
		}
	}


	public void mutacion(int tam_pob){
		boolean mutado;
		double prob;
		for(int i=0; i<tam_pob; i++){
			mutado = false;
			for(int j=0; j<this.poblacion[i].getLongCrom(); j++){
				prob = Math.random();
				if(prob < this.prob_mutacion){
					this.poblacion[i].muta(j);
					mutado = true;
				}
			}
			if(mutado){
				this.poblacion[i].setAptitud(this.poblacion[i].evalua());
			}
		}
	}

	private void realizarCruce(int madre, int padre, int puntoCruce) {
		Cromosoma hijo1=poblacion[madre].copia();
		Cromosoma hijo2=poblacion[padre].copia();
		//ahora copiamos a los hijos los genes cruzados
		for (int i = puntoCruce; i < poblacion[padre].getLongCrom(); i++) {
			hijo1.getGenes()[0].getGenes().set(i, poblacion[padre].getGenes()[0].getGenes().get(i));
			hijo2.getGenes()[0].getGenes().set(i, poblacion[madre].getGenes()[0].getGenes().get(i));
//			hijo1.getGenes()[i]=poblacion[padre].getGenes()[i];
//			hijo2.getGenes()[i]=poblacion[madre].getGenes()[i];
		}
		hijo1.setAptitud(hijo1.evalua());
		hijo2.setAptitud(hijo2.evalua());
		//TODO sustituimos los padres por los hijos OR por los peores
		poblacion[padre]=hijo1;
		poblacion[madre]=hijo2;
	}


	public void inicializaPoblacion() {
		numGenAct=0;
		poblacion = new Cromosoma[tam_poblacion];
		for (int i = 0; i < tam_poblacion; i++) {
			double[] min={0};
			double[] max={32};
			CromosomaF1 crom= new CromosomaF1(min,max,1,tolerancia);
			//crom.inicializaCromosomas();
			crom.setAptitud(crom.evalua());
			poblacion[i]=crom;
		}
	}
	
	public boolean terminado(){
		boolean terminado=false;
			if (numGenAct>=num_max_generaciones) //TODO Falta convergencia
				terminado=true;
		return terminado;
	}

	//Selección por ruleta
	private void seleccion(int tipoSeleccion,int tamPoblacion) {
		switch (tipoSeleccion) {
		case 0: seleccionRuleta(tamPoblacion);
				break;
		default: break;
		}
		
	}
	
	
	private void seleccionRuleta(int tam_poblacion){
		Cromosoma[] nueva_pob=new Cromosoma[tam_poblacion];
		//int[] seleccionados_sobrevivir=new int[tam_poblacion];
		double probSeleccion;
		int pos_superviviente;
		Random r=new Random();
		for (int i=0;i<tam_poblacion;i++){
			probSeleccion=r.nextDouble();
			pos_superviviente=0;
			while ((probSeleccion>poblacion[pos_superviviente].getPuntAcum()) &&
					pos_superviviente <tam_poblacion){
				pos_superviviente++;
			}
				
			//seleccionados_sobrevivir[i]=pos_superviviente;
			nueva_pob[i]=poblacion[pos_superviviente].copia();
		}
	}
	
}
