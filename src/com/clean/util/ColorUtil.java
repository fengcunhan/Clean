package com.clean.util;

import com.clean.database.LogUtil;

import android.graphics.Color;

public class ColorUtil {
	
	public static int getBlueByOrder(int order){
		int green=order*12+127;
		if(green>255){
			green=0;
		}
		int blue=Color.argb(17, 15, 127, 255);
		LogUtil.e("ColorUtil", blue+"");
		return blue;
	}
	
}
