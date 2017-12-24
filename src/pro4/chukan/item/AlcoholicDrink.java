package pro4.chukan.item;

import java.util.Objects;

public abstract class AlcoholicDrink extends Item {

	private String brand;
	private int produced;

	public AlcoholicDrink(String brand, int produced) {
		this.brand = brand;
		this.produced = produced;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj)) {
			return false;
		}

		AlcoholicDrink drink = (AlcoholicDrink) obj;
		return brand.equals(drink.brand) && produced == drink.produced;
	}

	@Override
	public int hashCode() {
		return Objects.hash(getClass(), brand, produced);
	}

	@Override
	public String toString() {
		return produced + "年 " + brand + "製";
	}

}
