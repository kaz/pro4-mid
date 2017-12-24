package pro4.chukan.item;

public class Wine extends AlcoholicDrink {

	public Wine(String brand, int produced) {
		super(brand, produced);
	}

	@Override
	public String toString() {
		return "ワイン(" + super.toString() + ")";
	}

}
