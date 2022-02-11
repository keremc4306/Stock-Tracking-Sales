package stockCrudR2;

public class Computer {
	private int itemNo;
	private String brand;
	private String processor;
	private int ram;
	private int ssd;
	private int price;
	private int numOfStock;
	
	public Computer(int itemNo, String brand, String processor, 
			int ram, int ssd, int price, int numOfStock) {
		this.itemNo = itemNo;
		this.brand = brand;
		this.processor = processor;
		this.ram = ram;
		this.ssd = ssd;
		this.price = price;
		this.numOfStock = numOfStock;
	}

	public int getItemNo() {
		return itemNo;
	}

	public void setItemNo(int itemNo) {
		this.itemNo = itemNo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getProcessor() {
		return processor;
	}

	public void setProcessor(String processor) {
		this.processor = processor;
	}

	public int getRam() {
		return ram;
	}

	public void setRam(int ram) {
		this.ram = ram;
	}

	public int getSsd() {
		return ssd;
	}

	public void setSsd(int ssd) {
		this.ssd = ssd;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getNumOfStock() {
		return numOfStock;
	}

	public void setNumOfStock(int numOfStock) {
		this.numOfStock = numOfStock;
	}
}
