import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_Gold2_16437 {
	/*
	 * <양 구출작전>
	 * 양들을 1번섬으로 보내고 싶음.
	 * 각 섬들에 연결된 곳으로 보내며, 늑대가 있는 섬에서는 잡아먹힘.
	 * 총 탈출한 마리수는?
	 * 
	 * <풀이>
	 * 1504번 문제처럼 ArrayList 한 칸당 arrayList 또 넣어서 처리하기
	 * 제일 깊숙한? 곳까지(dfs?) 연결된 섬부터 시작하여 연결된 곳으로 양일 경우 보냄.
	 * 보냈는데 늑대가 있는섬이라면 차를 구해야함.
	 * 양이라면 수를 더해서 그곳에서 또 연결된 곳에 보냄.
	 * 
	 * 다른 index의 arrayList 반복
	 * 
	 * 	메모리 : 253928KB	시간 : 1800ms
	 * => 배열 3개가 아니라 클래스로 넣엇으면 나앗으려나?
	 */
	
	static int N;
	static long ans; 		//int 는 안된다고해서.
	static ArrayList<Integer>[] arr;
	static char[] animal;
	static int[] num;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N + 1];
		animal = new char[N + 1];
		arr = new ArrayList[N + 1];
		for(int i = 1; i <= N; i++)
			arr[i] = new ArrayList<Integer>();
		//1번섬은 탈출할 섬. 클래스로?
		for(int i = 2; i < N + 1; i++) {
			animal[i] = sc.next().charAt(0);
			num[i] = sc.nextInt();
			arr[sc.nextInt()].add(i);
		}
		//ans = move(1);
		System.out.println(move(1));
	}
	
	static long move(int index) {
		long sum = 0;
		//가장 깊숙한 섬으로 가기.
		for(int i : arr[index])
			sum += move(i);
		
		if(animal[index] == 'W')
			return (sum - num[index]) < 0 ? 0 : sum - num[index];
		else
			return sum + num[index];
	}
}
