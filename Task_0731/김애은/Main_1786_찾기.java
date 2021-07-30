package 백준;
/*
 * kmp
 * pi로 해야된다
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_1786_찾기 {
	static String origin, pattern;
	static int pi[],cnt;
	static ArrayList<Integer> arr=new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		origin=br.readLine();
		pattern=br.readLine();
		pi=new int[pattern.length()];
		getPi();
		kmp();
		System.out.println(cnt);
		for(int a:arr)
			System.out.print(a+" ");
		
	}
	private static void kmp() {
		int j=0;
		for (int i = 0; i < origin.length(); i++) {
			while(j>0 && pattern.charAt(j)!=origin.charAt(i))
				j=pi[j-1];
			if(origin.charAt(i)==pattern.charAt(j)) {
				if(j==pattern.length()-1) {
					cnt++;
					int t=i-j+1;
					arr.add(i-j+1);
					j=pi[j]; //이부분생각을 바로 못했다...
				}else {
					++j;
				}	
			}
		}
	}
	private static void getPi() {
		int j=0;
		for (int i = 1; i < pattern.length(); i++) {
			while(j>0 && pattern.charAt(j)!=pattern.charAt(i))
				j=pi[j-1];
			if(pattern.charAt(j)==pattern.charAt(i))
				pi[i]=++j;
		}
	}

}
