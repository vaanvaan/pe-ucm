package cromosoma;

import gen.Gen;

public class CromosomaF3 extends Cromosoma {

	public CromosomaF3(double[] min, double[] max, int genes, double tolerancia) {
		super(min, max, genes, tolerancia);
		// TODO Auto-generated constructor stub
	}

	public CromosomaF3() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cromosoma copia() {
		Cromosoma copia= new CromosomaF3();
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
		double valor=0.0;
		double x = this.getGenes()[0].fenotipo();
		valor = -Math.abs(x*Math.sin(Math.sqrt(Math.abs(x))));
		return valor;
	}

}