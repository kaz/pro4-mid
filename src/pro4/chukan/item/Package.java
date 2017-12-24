package pro4.chukan.item;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;

public class Package extends Item {

	private List<Item> items;

	public Package(Item[] items) {
		this.items = Arrays.asList(items);
		Collections.sort(this.items, (a, b) -> Integer.valueOf(a.hashCode()).compareTo(b.hashCode()));
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}

		Package p = (Package) obj;
		if (items.size() != p.items.size()) {
			return false;
		}

		for (int i = 0; i < p.items.size(); i++) {
			if (!items.get(i).equals(p.items.get(i))) {
				return false;
			}
		}

		return true;
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(items.toArray());
	}

	@Override
	public String toString() {
		return items.toString();
	}

}
