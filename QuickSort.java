package gwu.quick_sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class QuickSort {
	
	public static void main(String[] args) {
		Integer temp;
		boolean flag = true;
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number you want to sort:(enter 0 to exit)");
		while(flag){
			try {
				temp=scan.nextInt();
				if(temp==0){
					flag = false;
				}else{
					arr.add(temp);
				}
			} catch (Exception e) {
				System.out.println("Please enter an integer!");
				scan.next();
			}
		}
		System.out.print("The outcome of non_recursive_quick_sort is:");
		long start1 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			non_recursive_quick_sort(arr, 0, arr.size());
		}
		
		long end1 = System.currentTimeMillis();
		long time1 = end1-start1;
		for(int i=0; i<arr.size();i++){
			System.out.print("\t"+arr.get(i));
		}
		System.out.println();
		System.out.println("The time of non_recursive method is:"+time1);
		long start2 = System.currentTimeMillis();
		for(int i=0;i<10000;i++){
			recursive_quick_sort(arr, 0, arr.size());
		}
		
		long end2 = System.currentTimeMillis();
		long time2 = end2-start2;
		System.out.print("The outcome of recursive_quick_sort is:\t");
		for(int i=0; i<arr.size();i++){
			System.out.print("\t"+arr.get(i));
		}
		System.out.println();
		System.out.println("The time of recursive method is:"+time2);
				
	}
	public static int pritation(List<Integer> list, int left, int right){
		if(list.isEmpty()||list==null||left>=right)
			return -1;
		if(list.size()==1)
			return 0;
		int i = left, j = right-1, key = list.get(i), temp = 0;
		if(i<=j){
			do{
				while(key>=list.get(i)&&i<right-1){
					i++;
				}
				while(key<=list.get(j)&&j>left){
					j--;
				}
				if(i<j){
					temp = list.get(i);
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}while(i<j);
			if(i>=j&&j>0){
				temp = list.get(left);
				list.set(left, list.get(j));
				list.set(j, temp);
				return j;
			}
		}
		return 0;
	
	}
	public static void non_recursive_quick_sort(List<Integer> list, int left, int right){
		if(list==null||left<0||right<=0||left>right)
			return;
		int position = 0, i = 0, j = 0;
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(right);
		stack.push(left);
		while(!stack.isEmpty()){
			i = stack.pop();
			j = stack.pop();
			if(i<=j){
				position = pritation(list, i, j);
			}
			if(position>=0){
				if(position>i){
					stack.push(position);
					stack.push(i);
				}
				if(position<j-1){
					stack.push(j);
					stack.push(position+1);
				}
			}
			
		}
	}
	public static void recursive_quick_sort(List<Integer> list, int left, int right){
		if(list.isEmpty()||list==null||list.size()==1||left>=right)
			return;
		int max = pritation(list, left, right);
		recursive_quick_sort(list, left, max);
		recursive_quick_sort(list, max+1, right);
	}
}
