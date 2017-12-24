package pro4.chukan.item;

public abstract class Item {
	@Override
	public boolean equals(Object obj) {
		return getClass().equals(obj.getClass());
	}
}
