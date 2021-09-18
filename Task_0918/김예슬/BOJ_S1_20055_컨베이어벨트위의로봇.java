package algo0918;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * <���� ���>
 * ���� ����: ����Ǿ��� �� �� ��° �ܰ谡 ���� ���̾������� ���
 * ���� ����: �ùķ��̼�
 * <Ǯ�� ���>
 * 1. ��Ʈ�� �κ��� �Բ� �� ĭ ȸ��
 * 2. ���� ���� �ö� �κ����� �� ĭ �̵�
 *  2-1. �̵��Ϸ��� ĭ�� �κ��� �����Ѵٸ�
 *  2-1. (�κ�X && ������ >= 1)�̸� �̵� ����
 * 3. �ø��� ��ġ�� ĭ�� �������� 0�� �ƴϸ� �κ��� �ø�
 * 4. �������� 0�� ĭ�� ������ K�� �̻��̶�� ����
*/

public class BOJ_S1_20055_�����̾Ʈ���Ƿκ� {

	static int N, K;
	static ArrayList<Robot> Belt;
	
	static class Robot {
		int d;
		boolean exist;
		
		public Robot(int d, boolean exist) {
			this.d = d;
			this.exist = exist;
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		Belt = new ArrayList<>();
		
		for (int i = 0; i < 2 * N; i++) {
			Belt.add(new Robot(sc.nextInt(), false));
		}
		
		System.out.println(solve());
	}

	static int solve() {
		int step = 1;
		while (true) {
			rotate();
			move();
			insert();
			if (check() >= K) return step;
			++step;
		}
	}
	
	// 1. ��Ʈ & �κ� ȸ��
	static void rotate() {
		// �����̾��� ������ ĭ�� �ִ� ��Ʈ�� ó������ �̵� 
		Belt.add(0, Belt.remove(Belt.size() - 1));
		remove();
	}
	
	// 2. �κ� �� ĭ �̵�(i -> i+1)
	static void move() {
		for (int i = N - 2; i >= 0; i--) {
			// 2-1. iĭ�� �κ��� �������� �ʴٸ�
			if (!Belt.get(i).exist) continue;
			
			// 2-1. iĭ�� �κ��� �����Ѵٸ�
			// 2-2. (i + 1)ĭ�� �������� 0���� �۰ų�,
			// 		(i + 1)ĭ�� �κ��� �����ϸ� �̵� �Ұ���
			if (Belt.get(i + 1).exist || Belt.get(i + 1).d == 0) continue;
						
			// 2-2. (i + 1)ĭ�� �������� 0���� ũ��,
			//		(i + 1)ĭ�� �κ��� �������� �ʴٸ� �̵� ����
			--Belt.get(i + 1).d;
			Belt.get(i + 1).exist = true;
			Belt.get(i).exist = false;
		}
		remove();
	}
	
	// 3. �ø��� ��ġ�� �κ� �ø�
	static void insert() {
		// �ø��� ��ġ�� �������� 0���� �۴ٸ� �н�
		if (Belt.get(0).d == 0) return;
		
		--Belt.get(0).d;
		Belt.get(0).exist = true;;
	}
	
	// 4. �������� 0�� ĭ�� ������ ī����
	static int check() {
		int cnt = 0;
		for (Robot r : Belt) {
			if (r.d == 0) ++cnt;
		}
		return cnt;
	}
	
	// 1 or 2. �κ� ������
	static void remove() {
		Belt.get(N - 1).exist = false;
	}
}
