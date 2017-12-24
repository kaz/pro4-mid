package pro4.chukan;

import junit.framework.TestCase;
import pro4.chukan.item.Beer;
import pro4.chukan.item.Item;
import pro4.chukan.item.Package;
import pro4.chukan.item.Whiskey;
import pro4.chukan.item.Wine;
import pro4.chukan.shop.OokayamaLiquorShop;
import pro4.chukan.shop.Shop;

public class Test extends TestCase {

	@SuppressWarnings("unlikely-arg-type")
	public void testItemEquivalence() {
		assertTrue(new Beer("A社", 2017).equals(new Beer("A社", 2017)));
		assertFalse(new Beer("A社", 2017).equals(new Beer("B社", 2017)));
		assertFalse(new Beer("A社", 2017).equals(new Beer("A社", 2016)));
		assertFalse(new Beer("A社", 2017).equals(new Whiskey("A社", 2017)));

		Package pkg1 = new Package(new Item[] { new Beer("A社", 2017), new Wine("B社", 2016), new Whiskey("C社", 2015), });
		Package pkg2 = new Package(new Item[] { new Wine("B社", 2016), new Whiskey("C社", 2015), new Beer("A社", 2017), });
		Package pkg3 = new Package(new Item[] { new Wine("B社", 2016), new Whiskey("C社", 2015), new Whiskey("C社", 2015), new Beer("A社", 2017), });

		assertTrue(pkg1.equals(pkg2));
		assertTrue(pkg2.equals(pkg1));
		assertFalse(pkg1.equals(pkg3));
		assertFalse(pkg2.equals(pkg3));
	}

	public void testProcedure() throws Exception {
		Shop s = OokayamaLiquorShop.getInstance();
		Customer c = new Customer("TESTER");
		Tester t = new Tester();

		// 在庫がなければ予約注文に、入荷したら受け取る
		Item[] items1 = new Item[] { new Beer("A社", 2017), new Beer("A社", 2017), };
		c.order(s, items1, t::receive);
		assertNull(t.fetch());
		s.arrive();
		assertEquals(t.fetch(), new Package(items1));

		// 在庫があればすぐ受け取る
		Item[] items2 = new Item[] { new Wine("A社", 2017), new Wine("A社", 2017), };
		s.arrive();
		c.order(s, items2, t::receive);
		assertEquals(t.fetch(), new Package(items2));

		// 必要数が揃ったら受け取る
		Item[] items3 = new Item[] {
				new Wine("B社", 2017), new Wine("B社", 2017), new Wine("B社", 2017),
				new Wine("B社", 2017), new Wine("B社", 2017), new Wine("B社", 2017), };
		c.order(s, items3, t::receive);
		assertNull(t.fetch());
		s.arrive();
		assertEquals(t.fetch(), new Package(items3));

		// キャンセルしたら送られてこない
		Item[] items4 = new Item[] { new Whiskey("C社", 2017), new Whiskey("C社", 2017), };
		Order o = c.order(s, items4, t::receive);
		assertNull(t.fetch());
		c.cancel(s, o);
		s.arrive();
		assertNull(t.fetch());

		// 配送済みの注文をキャンセルしたらException
		try {
			o = c.order(s, items4, t::receive);
			assertEquals(t.fetch(), new Package(items4));
			c.cancel(s, o);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}

		// 酒類以外を注文したらException
		try {
			c.order(s, new Item[] { new Item() {} }, t::receive);
			fail();
		} catch (Exception e) {
			assertNotNull(e);
		}
	}

	private class Tester {
		private Package pack;

		public void receive(Package pack) {
			this.pack = pack;
		}

		public Package fetch() {
			Package pack = this.pack;
			this.pack = null;
			return pack;
		}
	}

}
