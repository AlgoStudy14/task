package Jungol;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class JUNGOL_1205 {
	/*
	 * 조커
	 * 스트레이트 최대길이
	 * 조커는 아무 수로도 변환가능
	 * 
	 * 정렬해서 조커 개수 세고 개수 센만큼 수를 True 시켜서 수세면될듯?
	 */
	static int T, J, count_J, count_S, same, answer;
	static ArrayList<Integer> ans;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		int num = -1;
		ans = new ArrayList<>();
		//입력. 만약 0을 받으면 조커 개수 ++
		for(int t = 0; t < T; t++) {
			num = sc.nextInt();
			ans.add(num);
			if(num == 0)
				count_J++;
		}
		//정렬 및 연속되지 않는 수라도 count는 1
		Collections.sort(ans);
		count_S = 1;
		J = count_J;
		//0 0 0 0 0 0 0 0 0 0 0 0 일때 예외처리
		if(J == T) {
			answer = count_J;
		}else {
			for(int a = J; a < T; a++) {
				for(int i = a; i < T; i++) {
					//마지막 항이 아니고, 다음 항이 현재항보다 1 큰 수 일때 count++
					if( (i + 1 != T) && (ans.get(i+1) - ans.get(i)) == 1)
						count_S++;
					else {
						//마지막 항이 아니고, 다음 항이 현재 항과 수가 같을 땐 pass
						if( (i + 1 != T) && (ans.get(i) - ans.get(i+1) == 0 ))
							continue;
						//마지막 항이 아니고, 조커의 남은 개수가 0개가 아니며, 앞 뒤의 차가 남은 조커의 개수보다 적거나 같을 때. + 남은 조커가 0이면 굳이 볼 필요없이 현재 count가 전부.
						else if( (i + 1 != T)  && (ans.get(i+1) - ans.get(i) - 1 <= count_J)) {
							count_J -= ans.get(i+1) - ans.get(i) - 1;
							count_S += ans.get(i+1) - ans.get(i);
							continue;
						}
						//나머지의 경우에서 조커의 남은 개수가 있다면 남은 개수만큼 count에 더해줘야함.
						if(count_J != 0)
							count_S += count_J;
						//무엇이 더 큰지 판단하고, 답을 집어 넣은 이후엔 count와 남은 조커 개수 초기화.
						answer = Math.max(answer, count_S);
						count_S = 1;
						count_J = J;
					}
				}
			}
		}
		System.out.println(answer);
	}
}