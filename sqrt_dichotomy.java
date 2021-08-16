
public class sqrt_dichotomy{
	public static void main(String[] args){
		System.out.println("Method start....");
            	double result = sqrtBydichotomy(2,1e-10);
		System.out.println("Result is : " + result);
	}
	
	public static double sqrtBydichotomy(int baseNum, double accuracy) {
		double higher = baseNum;
		double lower = 0;
		double mid = (lower + higher) / 2;
		int count = 0;
		while( (higher - lower) > accuracy) {
			count ++;
			if( mid * mid > baseNum ) {
				higher = mid;
			} else {
				lower = mid;
			}
		//	String str = String.format("Higher is %g, lower is %g", higher, lower);
		//	System.out.print(str);
			mid = ( lower + higher) / 2;
			System.out.println(count);
		}
		return mid;
	}
}
