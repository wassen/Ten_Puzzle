package com.gmail.asayamailver312;

import java.util.ArrayDeque;
import java.util.Deque;

public class calculateString {

	Object calc(String formula) {

		// toArray
		char[] fCharArray = formula.toCharArray();
		Deque<Character> fList = new ArrayDeque<Character>();
		for (int i = 0; i < fCharArray.length; i++) {
			fList.add(new Character(fCharArray[i]));
		}

		// mold
		// 正規表現つかう？空白だめ、括弧の数あってるか確認と、括弧の種類

		// revPol
		fList = toReversePolish(fList);

		// calc
		// calc(formula);

		System.out.println(fList);
		return "";
	}

	Deque<Character> toReversePolish(Deque<Character> fList) {
		// 小数だめ、複数桁だめ、複数括弧だめ(複数括弧の実装は？再帰的にtorevpol呼ぶか)(やってます) 、優先順序
		Deque<Character> main = new ArrayDeque<Character>();
		Deque<Character> sub = new ArrayDeque<Character>();

		while (!fList.isEmpty()) {
			Character next = fList.removeFirst();

			if (Character.isDigit(next)) {
				main.addLast(next);
			}

			else if (next.compareTo('(') == 0) {
				Deque<Character> perList = new ArrayDeque<Character>();

				while (!(next.compareTo(')') == 0)) {
					perList.add(next);
					next = fList.removeFirst();
				}
				perList.removeFirst();
				main.addAll(toReversePolish(perList));
			}

			else if (next.compareTo('+') == 0) {
				if (!sub.isEmpty()) {
					main.add(sub.removeFirst());
				}
				sub.add(next);
			} else if (next.compareTo('-') == 0) {
				if (!sub.isEmpty()) {
					main.add(sub.removeFirst());
				}
				sub.add(next);
			} else if (next.compareTo('*') == 0) {
				if (!sub.isEmpty()) {
					main.add(sub.removeFirst());
				}
				sub.add(next);
			} else if (next.compareTo('/') == 0) {
				if (!sub.isEmpty()) {
					main.add(sub.removeFirst());
				}
				sub.add(next);
			}

		}

		for (int i = 0; i < sub.size(); i++) {
			main.addLast(sub.removeLast());
		}
		return main;
	}
}
