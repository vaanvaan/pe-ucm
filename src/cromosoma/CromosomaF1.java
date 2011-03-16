package cromosoma;

import gen.Gen;

public class CromosomaF1 extends Cromosoma {

	
	public CromosomaF1(double[] xMin, double[] xMax, int nGenes,
			double tolerancia) {
		super(xMin, xMax, nGenes, tolerancia);
		// TODO Auto-generated constructor stub
	}

	public CromosomaF1() {}

	@Override
	public Cromosoma copia() {
		// TODO Auto-generated method stub
		Cromosoma copia= new CromosomaF1();
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
			double e = (double)Math.E;
			double pi = (double)Math.PI;
			f = 20 + e - 20.0 * Math.pow(e,-0.2*Math.abs(x)) -Math.pow(e, Math.cos(2.0*pi*x));
			return f;
	}

}
