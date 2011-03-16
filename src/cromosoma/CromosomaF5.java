package cromosoma;

import gen.Gen;

public class CromosomaF5 extends Cromosoma {

	public CromosomaF5(double[] min, double[] max, int genes, double tolerancia) {
		super(min, max, genes, tolerancia);
		// TODO Auto-generated constructor stub
	}

	public CromosomaF5() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cromosoma copia() {
		Cromosoma copia= new CromosomaF5();
		copia.setNumGenes(this.numGenes);
		copia.setAptitud(this.aptitud);
		copia.setFenotipo(this.fenotipo);
		copia.setLongCrom(this.longCrom);
		//copia.setGenes(this.genes);
		Gen[] genes=new Gen[this.numGenes];
		for (int i=0;i<this.numGenes;i++)
			genes[i]=this.genes[i].copia();
		copia.setGenes(genes);
		copia.setPuntAcum(this.puntAcum);
		copia.setPuntuacion(this.puntuacion);
		return copia;
	}

	@Override
	public double evalua() {
		double valor1=0.0;
		double valor2=0.0;
		double x = this.getGenes()[0].fenotipo();
		double y = this.getGenes()[1].fenotipo();
		for (int i=1; i<=5; i++){
			valor1=valor1+(i*Math.cos((i+1)*x+i));
			valor2=valor2+(i*Math.cos((i+1)*y+i));
		}		
		return valor1*valor2;
	}

}
