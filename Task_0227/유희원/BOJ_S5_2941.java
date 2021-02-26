package imprepare.problems;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2941_크로아티아알파벳 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		char arr[] = new char[s.length()];
		arr = s.toCharArray();
		int ans = 0;
    //뒤에서부터 글자수 여러개가 한글자인거 걸러줌
		for (int i = s.length()-1; i >=1; i--) {
			if(arr[i]=='-') arr[i] = '0';
			if(arr[i]=='=') {
				arr[i] = '0';
				if(i>=2&&arr[i-1] == 'z'&&arr[i-2]=='d') arr[i-1] = '0';
			}
			//첨에 && || 를 괄호 안묶어주고 내서 틀림.. 
			if(arr[i]=='j') {
				if(i>=1&&(arr[i-1] == 'l'||arr[i-1]=='n')) arr[i] = '0';
			}
		}
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != '0') ans++;
		}
		System.out.println(ans);

	}

}
