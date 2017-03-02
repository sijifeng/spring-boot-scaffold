package com.demo;

import org.apache.commons.collections4.MapUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ApplicationTests {

	@Test
	public void collection4() {
		Map<String, Object> param = new HashMap();
		param.put("start", 10);
		param.put("username", "spring");

		System.out.println(MapUtils.getIntValue(param, "start"));
		System.out.println(MapUtils.getIntValue(param, "size", 2));
		System.out.println(MapUtils.getString(param, "username1"));
	}

	public static void main(String[] args) {
		int[] arr = {6, 3, 8, 2, 9, 1};
		System.out.println("排序前数组为：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
		for (int i = 0; i < arr.length - 1; i++) {//外层循环控制排序趟数
			for (int j = 0; j < arr.length - 1 - i; j++) {//内层循环控制每一趟排序多少次
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
		System.out.println();
		System.out.println("排序后的数组为：");
		for (int num : arr) {
			System.out.print(num + " ");
		}
	}

}