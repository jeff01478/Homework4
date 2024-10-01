package com.systex.homework4.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class LotteryService {
	public TreeSet[] getNumbers(int groups, Set<Integer> excludes) {
		TreeSet[] resultSetArray = new TreeSet[groups];
		for (int i = 0; i<groups; i++) {
			TreeSet<Integer> resultTreeSet = new TreeSet<>();
			while(resultTreeSet.size() != 6) {
				int num = (int)(Math.random()*49 + 1);
				if (excludes.contains(num)) {
					continue;
				}
				resultTreeSet.add(num);
			}
			resultSetArray[i] = resultTreeSet;
		}
		
		return resultSetArray;
	}
}
