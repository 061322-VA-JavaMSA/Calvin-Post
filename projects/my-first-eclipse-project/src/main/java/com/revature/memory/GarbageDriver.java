package com.revature.memory;

import java.util.ArrayList;

public class GarbageDriver {

	public int id;
	
	public GarbageDriver(int id) {
		
		this.id = id;
	}
	public static void main(String[] args) {
		//ArrayList<GarbageDriver> gb = new ArrayList<GarbageDriver>();
		for(int i = 0; i < 1000; i++) {
			//gb.add(new GarbageDriver(i));
			GarbageDriver gd = new GarbageDriver(i);
			System.gc();
		}
	}

	@Override
	protected void finalize() {
		System.out.println("id: " + id + " has been garbage collected.");
	}
}
