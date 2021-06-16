package algo210619;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Jungol_1309_팩토리얼 {
	
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		n = Integer.parseInt(in.readLine());
		
		factorial(n, 1);
	}
	
	private static void factorial(int num, long mult) {
		
		if(num == 1) {
			System.out.println("1! = 1");
			System.out.println(mult);
			return;
		}
		
		System.out.println(num+"! = " + num+" * "+(num-1)+"!");
		factorial(num-1, (long)mult*num);
	}
}
