package com.ccolor.web.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PassUtil {
	protected String prefix(String str) {
		StringBuffer tmp = new StringBuffer();
		StringBuffer tmp_prefix = new StringBuffer();
		char add_ch = 0;
		for (int i = 0, j = 0, k = 0, t = 0; i < str.length(); i += k) {
			tmp.append(str.charAt(i));
			if (t % 4 == 3) {
				k = 1;
			} else {
				k = 5;
			}
			t++;
		}
		for (int i = (tmp.toString().length() - 1); i >= 0; i--) {
			tmp_prefix.append(tmp.toString().charAt(i));
		}
		return tmp_prefix.toString();
	}

	protected String trans(String str) {

		StringBuffer tmp = new StringBuffer();
		int j = 0;
		char add_ch = 0;
		for (int i = (str.length() - 1); i >= 0; i--) {
			j = (j == 4 ? 0 : j);
			for (int k = 0; k <= 3; k++) {
				if (j == k) {
					tmp.append(str.charAt(i));
				} else {
					switch ((int) (Math.random() * 2.9)) {
					case 0:
						add_ch = (char) ((int) ((Math.random() * 10) + 48));
						break;
					case 1:
						add_ch = (char) ((int) (((Math.random() * 26) + 65)));
						break;
					case 2:
						add_ch = (char) ((int) ((Math.random() * 26) + 97));
						break;
					}
					int mad = (int) (Math.random() * 2);
					tmp.append(add_ch);
				}
			}
			// System.out.println(str.charAt(i));
			j++;
		}

		return tmp.toString();
	}
}
