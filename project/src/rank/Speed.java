package rank;

import java.util.ArrayList;

public class Speed {// 快速排序
	public static void main(String[] args) {
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		integerList.add(7);
		integerList.add(4);
		integerList.add(8);
		integerList.add(6);
		integerList.add(9);
		integerList.add(2);
		integerList.add(1);
		int left = 0;
		int right = integerList.size() - 1;
		printList(integerList);
		// speedRank(integerList,left, right);
		// printList(integerList);
		quickRank(integerList, left, right);
		printList(quickRank(integerList, left, right));

	}

	public static ArrayList<Integer> quickRank(ArrayList<Integer> list, int left, int right) {
		if (left >= right) {
			return list;
		}
		int mid = getList(list, left, right);
		quickRank(list, left, mid);
		quickRank(list, mid + 1, right);
		return list;
	}

	public static int getList(ArrayList<Integer> list, int left, int right) {
		int base = list.get(left);
		while (left != right) {
			while (left < right && list.get(right) > base) {
				right--;
			}
			list.set(left, list.get(right));
			while (left < right && list.get(left) < base) {
				left++;
			}
			list.set(right, list.get(left));
		}
		list.set(left, base);
		return left;
	}

	public static void printList(ArrayList<Integer> list) {
		for (Integer i : list) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println();
	}

	public static ArrayList<Integer> speedRank(ArrayList<Integer> list, int left, int right) {
		if (left >= right) {
			return list;
		}
		int base = list.get(left);
		int originLeft = left;
		int originRight = right;
		int number;

		while (right != left) {
			while (left < right && list.get(right) >= base) {
				right--;// 如果大于 则不符合
			}
			while (left < right && list.get(left) <= base) {
				left++;// 大于base 不符合继续找
			}
			if (left < right) {// 位置调换
				number = list.get(left);
				list.set(left, list.get(right));
				list.set(right, number);
			}

		}
		list.set(originLeft, list.get(left));// 调换base 跟left的值
		list.set(left, base);

		System.out.println(left + "==" + right + "--" + list.size());
		if (left == 5) {
			System.out.println();
		}
		list = speedRank(list, originLeft, left);
		list = speedRank(list, left + 1, originRight);
		return list;
	}
}
