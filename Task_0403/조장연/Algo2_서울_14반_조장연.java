import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Algo2_서울_14반_조장연 {

	static ArrayList<Integer> weightList;
	static int cNum; // 추의 갯수
	static int[] cArr; // 추의 무게 저장 배열
	static int rNum; // 구슬의 갯수
	static int[] rArr; // 구슬무게 저장 배열
	static char[] answer; // 결과값 저장 배열

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		cNum = sc.nextInt();
		cArr = new int[cNum];
		for (int i = 0; i < cNum; i++) {
			cArr[i] = sc.nextInt();
		}
		rNum = sc.nextInt();
		rArr = new int[rNum];
		answer = new char[rNum];
		for (int i = 0; i < rNum; i++) {
			rArr[i] = sc.nextInt();
		}
		weightList = new ArrayList<>();

		weightList.add(cArr[0]); // ArrayList에 처음 추의 크기 넣음

		for (int i = 1; i < cArr.length; i++) {
			int weightListSize = weightList.size();
			for (int j = 0; j < weightListSize; j++) { // 추를 하나씩 추가하면서 만들 수 있는 무게를 weightList에 저장
				if (!weightList.contains(Math.abs(weightList.get(j) - cArr[i]))) {
					weightList.add(Math.abs(weightList.get(j) - cArr[i]));
				}
				if (!weightList.contains(weightList.get(j) + cArr[i])) {
					weightList.add(weightList.get(j) + cArr[i]);
				}
				if (!weightList.contains(cArr[i]))
					weightList.add(cArr[i]);
			}
		}

		for (int i = 0; i < rNum; i++) { // weightList에 있으면 만들 수 있는 무게임을 사용해서 구슬 각각의 결과값을 answer배열에 저장
			if (weightList.contains(rArr[i]))
				answer[i] = 'Y';
			else
				answer[i] = 'N';
		}

		for (int i = 0; i < answer.length; i++) { // 결과값 출력
			System.out.print(answer[i] + " ");
		}
		
	}

}
