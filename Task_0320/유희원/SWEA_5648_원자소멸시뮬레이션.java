package sw_a_prac;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

//구글링했는데 이해 잘 안감..
public class SWEA_5648_원자소멸시뮬레이션 {

	static int T, N, x,y,d,K;
	static PriorityQueue<double[]> pq;
	static ArrayList<int[]> list;
	static int[] arr, a,b;
	static double[] check,temp;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int t = 1; t <=T; t++) {
			list = new ArrayList<>();
			pq = new PriorityQueue<>(new Comparator<double[]>() {

				@Override
				public int compare(double[] o1, double[] o2) {
					if(o1[0] < o2[0]) return -1;
					else if(o1[0] > o2[0])	return 1;
                    else if(o1[1] != o2[1])	return (int)(o1[1]-o2[1]);
                    else if(o1[2] != o2[2])	return (int)(o1[2]-o2[2]);
                    return 0;
				}
			});
			N = sc.nextInt();
			for (int i = 1; i <=N; i++) {
				x = 2000 + sc.nextInt();
				y = 2000 - sc.nextInt();
				d = sc.nextInt();
				K = sc.nextInt();
				list.add(new int[] {x,y,d,K});
			}
			for (int i = 0; i <N-1; i++) {
				a = list.get(i);
				if(a[2] == 0) {
					for (int j = i+1; j <N; j++) {
						b = list.get(j);
						//a[2] = 상
						if(a[0] > b[0]) {
							if(b[2] == 1 && a[1] == b[1]) {
								//하
								pq.add(new double[] {(a[0]-b[0])/2.0,i,j});
							}else if(b[2] == 2 && a[0] - b[0] == b[1] - a[1]){
                				//좌                     
                				pq.add(new double[]{a[0] - b[0], i, j});
                			}else if(b[2] == 3 && a[0] - b[0] == a[1] - b[1]){
                				//우
                				pq.add(new double[]{a[0] - b[0], i, j});
                			}
						}
					}
				}else if(a[2] == 1) {
                	for(int j = i + 1; j < N; ++j){
                        b = list.get(j);
                        //a[2] = 하
                        if(a[0] < b[0]){
                            if(b[2] == 0 && b[1] == a[1]){
                                //상                                             
                                pq.add(new double[]{(b[0] - a[0]) / 2.0, i, j});
                            }else if(b[2] == 2 && b[0] - a[0] == b[1] - a[1]){
                                //좌                     
                                pq.add(new double[]{b[0] - a[0], i, j});
                            }else if(b[2] == 3 && b[0] - a[0] == a[1] - b[1]){
                                //우
                                pq.add(new double[]{b[0] - a[0], i, j});
                            }
                        }
                    }
                }else if(a[2] == 2) {
                	for(int j = i + 1; j < N; ++j){
                        b = list.get(j);
                        //a[2] = 좌
                        if(a[1] > b[1]){
                            if(b[2] == 3 && a[0] == b[0]){
                                //우                                                     
                                pq.add(new double[]{(a[1] - b[1]) / 2.0, i, j});
                            }else if(b[2] == 0 && b[0] - a[0] == a[1] - b[1]){
                                //상                     
                                pq.add(new double[]{a[1] - b[1], i, j});
                            }else if(b[2] == 1 && a[0] - b[0] == a[1] - b[1]){
                                //하
                                pq.add(new double[]{a[1] - b[1], i, j});
                            }
                        }
                    }
                }else if(a[2] == 3) {
                	for(int j = i + 1; j < N; ++j){
                        b = list.get(j);
                        //a[2] = 우
                        if(a[1] < b[1]){
                            if(b[2] == 2 && a[0] == b[0]){
                                //좌                                                     
                                pq.add(new double[]{(b[1] - a[1]) / 2.0, i, j});
                            }else if(b[2] == 0 && b[0] - a[0] == b[1] - a[1]){
                                //상                     
                                pq.add(new double[]{b[1] - a[1], i, j});
                            }else if(b[2] == 1 && a[0] - b[0] == b[1] - a[1]){
                                //하
                                pq.add(new double[]{b[1] - a[1], i, j});
                            }
                        }
                    }
                }
			}
			check = new double[N];
			while(!pq.isEmpty()) {
				temp = pq.poll();
				if(check[(int)temp[1]]==0&&check[(int)temp[2]]==0) {
					check[(int)temp[1]]= temp[0];
					check[(int)temp[2]]= temp[0];
				}else if(check[(int)temp[1]]==temp[0]) check[(int)temp[2]]=temp[0];
			}
			int total = 0;
			for (int j = 0; j <N; j++) {
				if(check[j]>0) total += list.get(j)[3];
			}
			System.out.println("#"+t+" "+total);
		}
	}

}
