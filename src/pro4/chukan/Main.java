package pro4.chukan;

import java.util.ArrayList;
import java.util.Scanner;

import pro4.chukan.item.Item;
import pro4.chukan.item.Package;
import pro4.chukan.shop.OokayamaLiquorShop;
import pro4.chukan.shop.Shop;

public class Main {

	private static ArrayList<Order> orders = new ArrayList<>();
	private static Package received;

	private static void receive(Package pack) {
		received = pack;
		for (Order o : orders) {
			if (pack.equals(new Package(o.getItems()))) {
				orders.remove(o);
				return;
			}
		}
	}

	public static void main(String[] args) throws Exception {

		Scanner s = new Scanner(System.in);
		System.out.print("名前を入力してください: ");

		Customer customer = new Customer(s.nextLine());
		Shop shop = OokayamaLiquorShop.getInstance();

		while (true) {
			System.out.println("---------------------");
			System.out.println("1: 次の日へ");
			System.out.println("2: 注文する");
			System.out.println("3: キャンセルする");
			System.out.println("4: 終了");
			System.out.println("---------------------");
			System.out.print("選択: ");

			switch (s.nextInt()) {
			default:
				s.close();
				return;
			case 1:
				shop.arrive();
				break;
			case 2:
				ArrayList<Item> order = new ArrayList<>();
				while (true) {
					System.out.print("種類(Beer/Wine/Whiskey): ");
					String type = s.next();
					System.out.print("製造元: ");
					String brand = s.next();
					System.out.print("製造年: ");
					int produced = s.nextInt();

					Item i = (Item) Class.forName("pro4.chukan.item." + type).getConstructor(String.class, int.class)
							.newInstance(brand, produced);
					order.add(i);

					System.out.println("---------------------");
					System.out.println("1: 注文品を追加");
					System.out.println("2: 注文を実行");
					System.out.println("---------------------");
					System.out.print("選択: ");

					if (s.nextInt() != 1) {
						break;
					}
				}

				received = null;
				Order o = customer.order(shop, order.toArray(new Item[0]), Main::receive);
				if (received == null) {
					orders.add(o);
				}

				break;
			case 3:
				System.out.println("---------------------");
				for (int i = 0; i < orders.size(); i++) {
					System.out.println(i + ": " + new Package(orders.get(i).getItems()));
				}
				System.out.println(orders.size() + ": 戻る");
				System.out.println("---------------------");
				System.out.print("選択: ");

				int index = s.nextInt();
				if (0 <= index && index < orders.size()) {
					customer.cancel(shop, orders.remove(index));
				}

				break;
			}
		}
	}

}