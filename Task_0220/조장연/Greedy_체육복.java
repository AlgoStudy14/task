import java.util.ArrayList;

/*
 * <문제 요약>
 * 추가 체육복을 앞뒤 학생에게만 빌려줄 수 있을 때 체육복을 입을 수 있는 최대 학생수
 * 주의 : 여벌 체육복을 가지고 온 학생이 도난당했을 수 있음!
 * 
 * <풀이법 요약>
 * 앞뒤사람 번호가 있는지를 찾을때, 추가 체육복이 있는 학생이 잃어버린 경우등의 경우를 생각하여 ArrayList로 전처리해줌
 * 무조건 앞사람부터 빌려줌(복잡하게 예외 상황을 생각할 필요가 없음)
 */

public class Greedy_체육복 {

	public static void main(String[] args) {
		Greedy_체육복 pm = new Greedy_체육복();
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		System.out.println(pm.solution(n, lost, reserve));
	}

	public int solution(int n, int[] lost, int[] reserve) {
		int answer = n;
		ArrayList<Integer> lList = new ArrayList<>(); // 잃어버린 체육복 ArrayList
		ArrayList<Integer> rList = new ArrayList<>(); // 추가 체육복 ArrayList

		for (int i = 0; i < lost.length; i++) { // lList 전처리과정
			lList.add(lost[i]);
		}

		for (int i = 0; i < reserve.length; i++) { // rList 전처리과정(이때 여벌옷이 있는 학생이 도난당한 경우도 고려해서 전처리 해줌)
			if (!lList.contains(reserve[i]))
				rList.add(reserve[i]);
			else
				lList.remove((Integer) reserve[i]);

		}

		for (int i = 0; i < rList.size(); i++) {
			if (lList.contains(rList.get(i) - 1)) {
				lList.remove((Integer) (rList.get(i) - 1));
				continue;
			}

			if (lList.contains(rList.get(i) + 1)) {
				lList.remove((Integer) (rList.get(i) + 1));
			}
		}
		return answer - lList.size();
	}

}
