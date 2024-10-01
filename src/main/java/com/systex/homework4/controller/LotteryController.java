package com.systex.homework4.controller;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.systex.homework4.model.LotteryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LotteryController {
	
	@Autowired
	LotteryService lotteryService;
	
	@GetMapping("/lottery/reTry")
	private String reTry() {
		return "lottery/lottery";
	}
	
	@PostMapping("/lottery/exLottery")
	private String lottery(HttpServletRequest request, HttpSession session) {
		return exLottery(request, session);
	}
	
	private String exLottery(HttpServletRequest request, HttpSession session) {
		LinkedList<String> errorMsgs = new LinkedList<>();
		String groupString = (String)request.getParameter("groups");
		String filterString = (String)request.getParameter("filterNum");
		session.setAttribute("errors", errorMsgs);
		if (groupString.isEmpty()) {
			errorMsgs.add("請輸入需要數組的數量");
		}
		session.setAttribute("groups", groupString);
		session.setAttribute("filterNum", filterString);
		if(!errorMsgs.isEmpty()) {
			return "lottery/lottery";
		}
		
		try {
			int groups = Integer.parseInt(groupString);
			Set<Integer> filterSet = new HashSet<>();
			if (!filterString.isEmpty()) {
				String[] filterArray = filterString.split(" ");
				for (String filterNum : filterArray) {
					if (filterNum == "") {
						continue;
					}
					filterSet.add(Integer.parseInt(filterNum));
				}
			}
			Set[] resultSets = lotteryService.getNumbers(groups, filterSet);
			session.setAttribute("resultLottery", resultSets);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			errorMsgs.add("請輸入整數");
			return "lottery/lottery";
		} catch (Exception e) {
			e.printStackTrace();
			errorMsgs.add(e.getMessage());
			return "lottery/lottery";
		}
		
		return "lottery/result";
	}
}
