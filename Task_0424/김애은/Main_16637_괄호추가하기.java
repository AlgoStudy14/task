package bfsdfs;
/*
 * dfs 순서 잘못했다......................
 * dfs로 기존꺼 다 돌고 그 뒤부터 하나씩 괄호 해서 확인한다.
 * 괄호 하고 안하고 하고 안하고~
9
3+8*7-9*2
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main_16637_괄호추가하기 {
	static int N;
	static ArrayList<Integer> num = new ArrayList<>();
	static ArrayList<Character>op = new ArrayList<>();
	static int answer=Integer.MIN_VALUE;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		N=Integer.parseInt(br.readLine());
		String arr=br.readLine();
		for(int i=0;i<arr.length();i++) {
			if(arr.charAt(i)>='0' && arr.charAt(i)<='9') 
				num.add(arr.charAt(i)-'0');
			else
				op.add(arr.charAt(i));
		}
		
		dfs(0,num.get(0));
		System.out.println(answer);
	}
	private static void dfs(int index, int sum) {
		if(index>=N/2) {
			answer=Math.max(answer, sum);
			return;
		}
		int res1=cal(sum,num.get(index+1),op.get(index)); //앞에 연산
		dfs(index+1,res1); //다음 연산
		if(index+1<N/2) { //두개 합친 연산
			int res2=cal(num.get(index+1),num.get(index+2),op.get(index+1));
			dfs(index+2,cal(sum,res2,op.get(index)));
		}
	}
	private static int cal(int num1, int num2, char ope) {
		if(ope=='+')
			return num1+num2;
		else if(ope=='-')
			return num1-num2;
		else
			return num1*num2;
	}
}
