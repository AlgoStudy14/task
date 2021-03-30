package algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
//sort
public class Algo1_서울_14반_김애은 {
	static ArrayList<String> arr = new ArrayList<>(); //정렬하기 위한 arraylist
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M=sc.nextInt();//처음 비교할 수 
		int N=sc.nextInt();// 끝 비교할 수
		
		for(int i=M;i<=N;i++) {// 시작 수부터 끝 수까지
			String res="";//해당 숫자 영어 입력할 string
			if(i>=10) {//10보다 클 경우 앞자리와 뒷자리로 나눠야함. 숫자는 1~99이기 때문에
				int s=i/10;//앞자리
				int p=i%10;//뒷자리
				String tem1=check(s);//앞자리 변환
				String tem2=check(p);//뒷자리 변환
				res=tem1+"89"+tem2; //해당 숫자 숫자 사이에 89를 넣어서 split하려고 넣음
			}
			else {//한자리
				res=check(i);//변환
			}
			arr.add(res);//숫자 영어값을 arraylist에 넣음
		}
		Collections.sort(arr);//사전식 정렬
		for(int i=0;i<arr.size();i++) {//print
			System.out.print(trans(arr.get(i))+" ");//변환 함수로 가서 프린트
			if(i%10==9)//10개씩 출력
				System.out.println();//한줄
		}
	}

	private static int trans(String st) {//변환 영어에서 숫자
		if(st.contains("89")) {//두자리인지 확인
			String[] temp= st.split("89");//split
			int temp1=trans(temp[0]);//앞자리
			int temp2=trans(temp[1]);//뒷자리
			return temp1*10+temp2;//숫자
		}
		if(st.equals("zero"))return 0;//숫자
		if(st.equals("one"))return 1;//숫자
		if(st.equals("two"))return 2;//숫자
		if(st.equals("three"))return 3;//숫자
		if(st.equals("four"))return 4;//숫자
		if(st.equals("five"))return 5;//숫자
		if(st.equals("six"))return 6;//숫자
		if(st.equals("seven"))return 7;//숫자
		if(st.equals("eight"))return 8;//숫자
		if(st.equals("nine"))return 9;//숫자
		return 0;
		
	}

	private static String check(int s) {//숫자를 영어로
		if(s==0)return "zero";//영어
		if(s==1)return "one";//영어
		if(s==2)return "two";//영어
		if(s==3)return "three";//영어
		if(s==4)return "four";//영어
		if(s==5)return "five";//영어
		if(s==6)return "six";//영어
		if(s==7)return "seven";//영어
		if(s==8)return "eight";//영어
		if(s==9)return "nine";//영어
		return null;
	}
}
