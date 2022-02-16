package com.whg.ijvm.ch03;

public class Pair<T> {

	public final T left;
	public final T right;
	
	public static<T> Pair<T> of(T left, T right){
		return new Pair<T>(left, right);
	}
	
	private Pair(T left, T right) {
		this.left = left;
		this.right = right;
	}
	
}
