import Garage.Mysql;

public class RateAlgo{
	private Mysql mysql = new Mysql();
	private double rateValue;
	private double[][] a;


	public RateAlgo(){

	}

	public void rate(){
		double sum = 0.0;
		for(int i=0;i<this.a.length-1;i++){
			sum += (this.a[i+1][0] + this.a[i][0]) / (this.a[i+1][1] - this.a[i][1]);
		}
		this.rateValue = sum/this.a.length; 
	}

	public void isIncreasing(){
		int i=0;
		boolean switch = false;
		while(i<a.length||!(switch)){
			if(Math.abs())
			i++;
		}
	}


	
}