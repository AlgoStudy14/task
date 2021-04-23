import java.util.Scanner;
/*
 * 야구문제
 * 9명으로 이루어진 팀이 주어진 이닝 개수만큼 나타난다.
 * 나머지는 야구룰과 같음
 * 순서를 배치했을 때 가장 득점이 높은걸 구하는게 문제이다.
 * 여기서 중요한건 조건!!
 * 조건이 되게 많고 그걸 다 체크해야한다.
 * 
 * 1. 9 크기의 배열에 4번째에 처음 입력받은것을 집어넣고 모든 순열을 구한다.
 * 2. 순열이 만들어지면 해당 배열을 조건에 맞춰 돌린 뒤 합계를 구한다.
 * 3. 가장 큰합계를 보여준다.
 * 
 * 
 * feat. 동엽님
 */
public class Main_17281_야구 {
	static int N,cc;
	static int arr[][];
	static boolean check[];
	static int num[];
	static int max=Integer.MIN_VALUE;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N=sc.nextInt();
		arr=new int[N][9];
		for(int i=0;i<N;i++) {
			for(int j=0;j<9;j++) {
				arr[i][j]=sc.nextInt();
			}
		}
		num=new int[9];
		num[3]=0;
		check=new boolean[9];
		check[3]=true;
		npn(1);
		System.out.println(max);
	}
	private static void npn(int cnt) {
		if(cnt==9) {
			//System.out.println(Arrays.toString(num));
			go();
			return;
		}
	
		for(int i=0;i<=8;i++) {
			if(check[i]) continue;
			check[i]=true;
			num[i]=cnt;
			npn(cnt+1);
			check[i]=false;
		}
	}
	private static void go() {
		int j=0;
		int out=0;
		int cnt=0;
		int maru[];
		for(int i=0;i<N;i++) {
			out=0;
			maru=new int[4];//이닝마다 잇는곳
			while(true) {
				if(j==9)j=0;
				if(out==3) {
					break;
				}
				int a=arr[i][num[j]];
				if(a==0) {
					out++;
					j++;
					continue;
				}
				else if(a==1) { //안타
					if(maru[3]==1) //3마루
						cnt++;
					for(int d=3;d>1;d--) {
						maru[d]=maru[d-1];
					}
					maru[1]=1;
					
				}else if(a==2) {//2루
					if(maru[2]==1)cnt++;
					if(maru[3]==1)cnt++;
					maru[3]=maru[1];
					maru[1]=0;
					maru[2]=1;
					
				}else if(a==3) {//3루
					if(maru[1]==1)cnt++;
					maru[1]=0;
					if(maru[2]==1)cnt++;
					maru[2]=0;
					if(maru[3]==1)cnt++;
					maru[3]=1;
				}else if(a==4) {//홈런
					cnt++;
					for(int d=1;d<4;d++) {
						if(maru[d]==1) {
							cnt++;
							maru[d]=0;
						}
					}
	
				}
				j++;
			}
		}
		max=Math.max(max, cnt);
	}
}
