package pro4.chukan.shop;

import pro4.chukan.item.Beer;
import pro4.chukan.item.Item;
import pro4.chukan.item.Whiskey;
import pro4.chukan.item.Wine;

public class OokayamaLiquorShop extends LiquorShop {

	private static OokayamaLiquorShop instance = new OokayamaLiquorShop();

	public static OokayamaLiquorShop getInstance() {
		return instance;
	}

	private OokayamaLiquorShop() {
		super("大岡山酒店");
	}

	public void arrive() throws Exception {
		super.arrive(arrivals[day]);
		if (++day % arrivals.length == 0) {
			day = 0;
		}
	}

	private int day = 0;
	private Item[][] arrivals = new Item[][] {
		// 日曜日
		new Item[] {
			new Beer("A社", 2017),
			new Beer("A社", 2017),
			new Beer("A社", 2017),
			new Beer("A社", 2017),
			new Beer("A社", 2017),	
			new Beer("B社", 2017),
			new Beer("B社", 2017),
			new Beer("B社", 2017),
			new Beer("B社", 2017),
			new Beer("B社", 2017),	
		},
		// 月曜日
		new Item[] {
			new Wine("A社", 2017),
			new Wine("A社", 2017),
			new Wine("A社", 2017),
			new Wine("A社", 2017),
			new Wine("A社", 2017),	
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),	
		},
		// 火曜日
		new Item[] {
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),	
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),
			new Wine("B社", 2017),	
		},
		// 水曜日
		new Item[] {
			new Whiskey("A社", 1990),
			new Whiskey("C社", 2017),
			new Whiskey("C社", 2017),
			new Whiskey("C社", 2017),
		},
		// 木曜日
		new Item[] {
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),	
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),	
		},
		// 金曜日
		new Item[] {
			new Wine("C社", 2017),
			new Wine("C社", 2017),
			new Wine("C社", 2017),
			new Wine("C社", 2017),
			new Wine("C社", 2017),	
			new Whiskey("B社", 1994),
			new Whiskey("B社", 1994),
		},
		// 土曜日
		new Item[] {
			new Beer("A社", 2017),
			new Beer("A社", 2017),
			new Beer("A社", 2017),
			new Beer("B社", 2017),	
			new Beer("B社", 2017),
			new Beer("B社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),
			new Beer("C社", 2017),	
		},
	};
}
