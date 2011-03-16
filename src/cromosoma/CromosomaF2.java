package cromosoma;

import gen.Gen;

public class CromosomaF2 extends Cromosoma {

	public CromosomaF2(double[] min, double[] max, int genes, double tolerancia) {
		super(min, max, genes, tolerancia);
		// TODO Auto-generated constructor stub
	}

	public CromosomaF2() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Cromosoma copia() {
		Cromosoma copia= new CromosomaF2();
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
		double f=0.0;
		double x = this.getGenes()[0].fenotipo();
		double y = this.getGenes()[1].fenotipo();
		double pi =(double)Math.PI;
		f = 21.5 + x*Math.sin(4.0*pi*x)+y*Math.sin(20.0*pi*y);
		return f;
	}

}
