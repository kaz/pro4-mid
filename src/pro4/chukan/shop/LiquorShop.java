package pro4.chukan.shop;

import pro4.chukan.Order;
import pro4.chukan.item.AlcoholicDrink;
import pro4.chukan.item.Item;

public abstract class LiquorShop extends Shop {

	public LiquorShop(String name) {
		super(name);
	}

	@Override
	public void handleOrder(Order o) throws Exception {
		for (Item i : o.getItems()) {
			if (!(i instanceof AlcoholicDrink)) {
				throw new Exception("酒類以外の注文はお受け出来ません");
			}
		}
		super.handleOrder(o);
	}

	public abstract void arrive() throws Exception;

}
