package pro4.chukan;

import java.util.function.Consumer;

import pro4.chukan.item.Item;
import pro4.chukan.item.Package;
import pro4.chukan.shop.Shop;

public class Customer {

	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Order order(Shop shop, Item[] items, Consumer<Package> receiver) throws Exception {
		System.out.println(name + ": " + shop.getName() + "に注文を送ります " + new Package(items));

		Order o = new Order(this, items, pack -> {
			System.out.println(name + ": 注文品が届きました" + pack);
			receiver.accept(pack);
		});
		shop.handleOrder(o);

		return o;
	}

	public void cancel(Shop shop, Order order) throws Exception {
		System.out.println(name + ": " + shop.getName() + "への注文をキャンセルします " + new Package(order.getItems()));
		shop.cancelOrder(order);
	}

}
