package BaekJoon;

import java.util.Scanner;

public class BOJ_Silver5_2941_re {
	/*
	 * 크로아티아 알파벳
	 * c 일 때, c= c- 2가지 추가.
	 * d 일 때, dz= d- 2가지 추가.
	 * l 일 때, lj 1가지 추가
	 * n 일 때, nj 1가지 추가.
	 * s 일 때, s= 1가지 추가.
	 * z 일 때, z= 1가지 추가.
	 * 
	 * 예를 잘못봐서 다시 풂.
	 * + 런타임 OutOfBounds => 2개까지 보는데 dz=는 3개를 봐야해서 예외처리 해야함.
	 */
	static String st;
	static int count;
	static char[] chArr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		st = sc.next();
		chArr=st.toCharArray();
		for(int i = 0; i < st.length(); i++) {
			if(i == chArr.length - 1) {
				count++;
				break;
			}
			
			if(chArr[i] == 'c') {
				if(chArr[i+1] == '=' || chArr[i+1] == '-') {
					count++;
					i++;
					continue;
				}else {
					count++;
					continue;
				}
			}else if(chArr[i] == 'd') {
				if(chArr[i+1] == 'z') {
					if(i+2 >= st.length()) {
						count+=2;
						break;
					}
					if(chArr[i+2] == '=') {
						count++;
						i+=2;
						continue;
					}
				}
				else if(chArr[i+1] == '-') {
					count++;
					i++;
					continue;
				}
				count++;
				continue;
			}else if(chArr[i] == 'l' || chArr[i] == 'n') {
				if(chArr[i+1] == 'j') {
					count++;
					i++;
					continue;
				}else {
					count++;
					continue;
				}
			}else if(chArr[i] == 's' || chArr[i] == 'z') {
				if(chArr[i+1] == '=') {
					count++;
					i++;
					continue;
				}else {
					count++;
					continue;
				}
			}else
				count++;
		}
		System.out.println(count);
	}
}
