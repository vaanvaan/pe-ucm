package gen;

import java.util.ArrayList;
import java.util.Random;

public class Gen {

	private int longGen;
	private double xMin;
	private double xMax;
	//private double tolerancia;
	private ArrayList<Boolean> genes;
	
	
	public Gen(double xMin, double xMax, double tolerancia){
		this.xMax=xMax;
		this.xMin=xMin;
		this.calculaLCrom(tolerancia);
		genes = new ArrayList<Boolean>(this.longGen);
		iniciaGenes();
	}


	public Gen() {
		// TODO Auto-generated constructor stub
	}


	private void iniciaGenes() {
		Random r=new Random();
		for (int i=0;i<this.longGen;i++)
			genes.add(r.nextBoolean());
	}
	
	public double getxMin() {
		return xMin;
	}
	public double getxMax() {
		return xMax;
	}
	public void setLongGen(int longGen) {
		this.longGen = longGen;
	}
	public int getLongGen() {
		return longGen;
	}
	public ArrayList<Boolean> getGenes() {
		return genes;
	}

	public void calculaLCrom(double tolerancia){
		int xmin=(int)getxMin();
		int xmax=(int)getxMax();
		double log10;
		log10=1+((xmax-xmin)/tolerancia);
		setLongGen( (int) Math.ceil( (Math.log(log10)/Math.log(2)) ) );
	}
	
	public double fenotipo() {
		double valor=0;
		int xmin=(int)getxMin();
		int xmax=(int)getxMax();
		double lCrom=getLongGen();
		valor=  (xmin + (xmax - xmin) * bin_a_dec(getGenes())
				/ (Math.pow(2,lCrom) - 1));
		return valor;
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
	

	//Prueba
	public static void main(String[] args) {
		ArrayList<Boolean> g=new ArrayList<Boolean>();
		g.add(true);
		g.add(false);
		g.add(true);
		Gen gn=new Gen(0, 10, 1);
		int dec=gn.bin_a_dec(g);
		System.out.println(dec);	
	}


	public Gen copia() {
		Gen nuevo=new Gen();
		nuevo.longGen=this.longGen;
		nuevo.xMin=this.xMin;
		nuevo.xMax=this.xMax;
		ArrayList<Boolean> genes=new ArrayList<Boolean>();
		for (int i=0;i<longGen;i++)
			genes.add(this.getGenes().get(i));
		nuevo.genes=genes;
		return nuevo;
	}

	
}
