package edu.monash.fit2099.demo.mars.items;

import edu.monash.fit2099.engine.weapons.WeaponItem;

public class Stick extends WeaponItem {

	public Stick() {
		super("stick", '/', 10, "pokes", 100);
	}

	public int getPurchasePrice() {
		return 0;
	}

	public int getSellPrice() {
		return 0;
	}
}


