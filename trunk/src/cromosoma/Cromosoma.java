package cromosoma;
import gen.Gen;

public abstract class Cromosoma {

	protected Gen[] genes;
	protected double fenotipo;
	protected double aptitud;
	protected double puntuacion;
	protected double puntAcum;
	protected int longCrom;
	protected int numGenes;
	
	
	
	public Cromosoma(double[] xMin, double[] xMax, int nGenes, double tolerancia){
		this.numGenes=nGenes;
		this.puntuacion=0.0;
		this.puntAcum=0.0;
		this.genes=new Gen[nGenes];
		this.longCrom=0;
		//Nuevos arrays para los genes
		for (int i=0; i<nGenes; i++){
			genes[i]=new Gen(xMin[i], xMax[i], tolerancia);
			longCrom = longCrom + genes[i].getLongGen();
		}
		this.aptitud=this.evalua();
	}

	public Cromosoma(){}
	
	//Métodos abstractos, se implementan en cada tipo de cromosoma
	public abstract double evalua();
	public abstract Cromosoma copia();

	
	//Getters y setters genéricos
	/**
	 * @return the genes
	 */
	public Gen[] getGenes() {
		return genes;
	}

	/**
	 * @param genes the genes to set
	 */
	public void setGenes(Gen[] genes) {
		this.genes = genes;
	}

	/**
	 * @return the fenotipo
	 */
	public double getFenotipo() {
		return fenotipo;
	}

	/**
	 * @param fenotipo the fenotipo to set
	 */
	public void setFenotipo(double fenotipo) {
		this.fenotipo = fenotipo;
	}

	/**
	 * @return the aptitud
	 */
	public double getAptitud() {
		return aptitud;
	}

	/**
	 * @param aptitud the aptitud to set
	 */
	public void setAptitud(double aptitud) {
		this.aptitud = aptitud;
	}

	/**
	 * @return the puntuacion
	 */
	public double getPuntuacion() {
		return puntuacion;
	}

	/**
	 * @param puntuacion the puntuacion to set
	 */
	public void setPuntuacion(double puntuacion) {
		this.puntuacion = puntuacion;
	}

	/**
	 * @return the puntAcum
	 */
	public double getPuntAcum() {
		return puntAcum;
	}

	/**
	 * @param puntAcum the puntAcum to set
	 */
	public void setPuntAcum(double puntAcum) {
		this.puntAcum = puntAcum;
	}

	/**
	 * @return the longCrom
	 */
	public int getLongCrom() {
		return longCrom;
	}

	/**
	 * @param longCrom the longCrom to set
	 */
	public void setLongCrom(int longCrom) {
		this.longCrom = longCrom;
	}

	/**
	 * @return the numGenes
	 */
	public int getNumGenes() {
		return numGenes;
	}

	/**
	 * @param numGenes the numGenes to set
	 */
	public void setNumGenes(int numGenes) {
		this.numGenes = numGenes;
	}

	public void muta(int indice) {
		boolean enc = false;
		int genAct = 0;
		int pos = indice;
		while(!enc){
			if(pos<this.genes[genAct].getLongGen()){
				enc = true;
			}else{
				pos=pos - this.genes[genAct].getLongGen();
				genAct++;
			}
		}
		boolean mutado = this.genes[genAct].getGenes().get(pos);
		this.genes[genAct].getGenes().set(pos,!mutado);
	}
	
	
	
	
	
}