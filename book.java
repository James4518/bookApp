package book;

public class book {
    private int id;
    private String name;
    private double price;

	public book(int id,String name,double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String toString(){
        return "序号:" + this.id + "、书名:" + this.name+ "、价格:" + this.price;
    }
    public int getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
} 
