package com.whg.ijvm.ch04.util;

public class Pair<L, R> {

	public final L left;
	public final R right;
	
	public static<L, R> Pair<L, R> of(L left, R right){
		return new Pair<>(left, right);
	}
	
	private Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}
	
}
