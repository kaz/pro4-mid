package pro4.chukan.item;

public class Beer extends AlcoholicDrink {

	public Beer(String brand, int produced) {
		super(brand, produced);
	}

	@Override
	public String toString() {
		return "ビール(" + super.toString() + ")";
	}

}
