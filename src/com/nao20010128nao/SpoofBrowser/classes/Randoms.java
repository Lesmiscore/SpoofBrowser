package com.nao20010128nao.SpoofBrowser.classes;
import java.util.*;

public class Randoms{
	public static int getInt(){
		Random a=new Random();
		Random b=new Random();
		return com.nao20010128nao.ToolBox.Math.XOR(a.nextInt(),b.nextInt());
	}
	public static int getInt(int max){
		Random a=new Random();
		Random b=new Random();
		return com.nao20010128nao.ToolBox.Math.XOR(a.nextInt(max),b.nextInt(max));
	}
}
