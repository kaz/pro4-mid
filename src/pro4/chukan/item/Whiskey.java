package pro4.chukan.item;

public class Whiskey extends AlcoholicDrink {

	public Whiskey(String brand, int produced) {
		super(brand, produced);
	}

	@Override
	public String toString() {
		return "ウイスキー(" + super.toString() + ")";
	}

}
