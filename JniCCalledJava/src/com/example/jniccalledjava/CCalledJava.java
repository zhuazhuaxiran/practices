package com.example.jniccalledjava;

public class CCalledJava {
	
	public String mName;
//	public CCalledJava(String name){
//		this.mName=name;
//	}
	public static String helloJni(String name){
		return name;
	}
	
	public String getName(){
		return mName;
	}
	
	public static native int wrap_main();
}
