package pro4.chukan.shop;

import java.util.ArrayList;
import java.util.Arrays;

import pro4.chukan.Order;
import pro4.chukan.item.Item;
import pro4.chukan.item.Package;

public abstract class Shop {
	private String name;

	private ArrayList<Item> stock = new ArrayList<>();
	private ArrayList<Order> reservation = new ArrayList<>();

	public Shop(String name) {
		System.out.println(name + "が開店しました");
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void cancelOrder(Order o) throws Exception {
		if(!reservation.remove(o)) {
			throw new Exception("そのような予約注文はありません");
		}
		System.out.println(name + ": " + o.getCustomerName() + "様からの予約注文を取り消しました");
	}
	public void handleOrder(Order o) throws Exception {
		Package pack = createPackage(o.getItems());
		if (pack == null) {
			System.out.println(name + ": " + o.getCustomerName() + "様からの注文は、在庫不足により予約として取り扱います");
			reservation.add(o);
		} else {
			System.out.println(name + ": " + o.getCustomerName() + "様へ注文品をお届けします " + pack);
			o.resolve(pack);
		}
	}

	private Package createPackage(Item[] items) {
		ArrayList<Item> pack = new ArrayList<>();
		for (Item i : items) {
			int index = stock.indexOf(i);
			if (index == -1) {
				stock.addAll(pack);
				return null;
			}
			pack.add(stock.remove(index));
		}
		return new Package(pack.toArray(new Item[0]));
	}

	public abstract void arrive() throws Exception;

	protected void arrive(Item[] items) throws Exception {
		System.out.println(name + ": 商品が入荷しました " + new Package(items));
		stock.addAll(Arrays.asList(items));

		ArrayList<Order> orders = new ArrayList<>();
		orders.addAll(reservation);
		reservation.clear();

		for (Order o : orders) {
			handleOrder(o);
		}
	}

}
