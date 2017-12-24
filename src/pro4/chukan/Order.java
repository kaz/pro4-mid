package pro4.chukan;

import java.util.function.Consumer;

import pro4.chukan.item.Item;
import pro4.chukan.item.Package;

public class Order {

	private Customer customer;
	private Item[] items;
	private Consumer<Package> receiver;

	public Order(Customer customer, Item[] items, Consumer<Package> receiver) {
		this.customer = customer;
		this.items = items;
		this.receiver = receiver;
	}
	
	public String getCustomerName() {
		return customer.getName();
	}
	public Item[] getItems() {
		return items;
	}

	public void resolve(Package pack) throws Exception {
		if(!pack.equals(new Package(this.items))) {
			throw new Exception("注文品と違います");
		}
		receiver.accept(pack);
	}

}
