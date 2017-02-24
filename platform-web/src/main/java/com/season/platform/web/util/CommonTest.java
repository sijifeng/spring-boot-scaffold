package com.season.platform.web.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiyc on 2017/2/24.
 */
public class CommonTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");

		for(String temp : list){
			if("1".equals(temp)){
				list.remove(temp);
			}
		}

		System.out.println(list);
	}
}
