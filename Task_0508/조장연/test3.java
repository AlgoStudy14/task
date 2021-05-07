import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class test3 {
	static int[] prices;
	static int d, k;
	static int result;

	public static void main(String[] args) {
		// 문제조건 초기화
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		prices = new int[N];
		for (int i = 0; i < N; i++) {
			prices[i] = sc.nextInt();
		}
		d = sc.nextInt();
		k = sc.nextInt();

		int[] newPrices = new int[2001];
		int pLen = prices.length;
		int sPoint = 0;
		int ePoint = 2000;
		int allSum = 0;
		int answer = 0;

		
		// newPrice, sPoint, ePoint, allSum 초기화
		for (int i = 0; i < pLen; i++) {
			newPrices[prices[i]]++;
			allSum += prices[i];
		}
		while (newPrices[sPoint] == 0) {
			sPoint++;
		}
		while (newPrices[ePoint] == 0) {
			ePoint--;
		}

		
		// 조건 1
		if (ePoint - sPoint <= d) {
			answer = (allSum - ePoint - sPoint) / (pLen - 2);
			System.out.println(answer);
			return;
		}

		
		// 조건 2
		int sPoint2 = sPoint;
		int ePoint2 = ePoint;

		if (newPrices[sPoint] == 1) {
			sPoint2++;
			while (newPrices[sPoint] == 0)
				sPoint2++;
		}

		if (newPrices[ePoint] == 1) {
			ePoint2--;
			while (newPrices[ePoint] == 0)
				ePoint2--;
		}

		if (ePoint2 - sPoint2 <= d) {
			answer = (allSum - ePoint - sPoint - ePoint2 - sPoint2) / (pLen - 4);
			System.out.println(answer);
			return;
		}

		
		// 조건 3
		int min3 = sPoint;
		int max3 = sPoint;
		int sPoint3 = sPoint;
		int sum3 = 0;
		Queue<Integer> queue = new LinkedList<>();

		int[] copyNewPrices = new int[2000];
		for (int i = 0; i < copyNewPrices.length; i++) {
			copyNewPrices[i] = newPrices[i];
		}

		// 처음에 k만큼 큐에 넣기(앞에서부터 순서대로)
		for (int i = 0; i < k; i++) {
			if (max3 < sPoint3)
				max3 = sPoint3;
			queue.add(sPoint3);
			sum3 += sPoint3;
			copyNewPrices[sPoint3]--;
			while (copyNewPrices[sPoint3] == 0)
				sPoint3++;
		}

		// 앞에서 부터 순서대로 k개가 주어진 조건을 만족하는지 탐색
		// 여러개면 평균값이 가장낮은거 -> 조합을 짤필요 없이 최소인것 Sliding Window하면 됨
		while (sPoint > 2000) {
			if (max3 - min3 <= d) {
				answer = sum3 / k;
				System.out.println(answer);
				return;
			}

			if (max3 < sPoint3)
				max3 = sPoint3;

			queue.add(sPoint3);
			sum3 += sPoint3;
			copyNewPrices[sPoint3]--;
			while (copyNewPrices[sPoint3] == 0)
				sPoint3++;
			queue.poll();
			if (min3 < queue.peek())
				min3 = queue.peek();
		}

		
		// 조건 4
		int mid4 = pLen / 2;
		int sum4 = 0;
		for (int i = 0; i < newPrices.length; i++) {
			sum4 += newPrices[i];
			if (sum4 >= mid4) {
				answer = i;
				System.out.println(answer);
				return;
			}
		}

	}

}
