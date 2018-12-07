/**
 * Author: Keith Koinange
**/
public class Products {
    private int prodSKU;
    private String prodDesc;
    private String prodName;
    private int price;
    private int discount;

    public Products(int prodSKU,String prodDesc,String prodName,int price, int discount){
        super();//why??
        this.prodSKU = prodSKU;
        this.prodDesc = prodDesc;
        this.prodName = prodName;
        this.price = price;
        this.discount = discount;
    }

    public int getProdSKU() { return prodSKU; }
    public String getProdDesc() { return prodDesc; }
    public String getProdName() {
        return prodName;
    }
    public int getPrice() {
        return price;
    }
    public int getDiscount() {
        return discount;
    }

    public void setProdSKU(int prodSKU) {
        this.prodSKU = prodSKU;
    }
    public void setProdDesc(String productDesc) {
        this.prodDesc = productDesc;
    }
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String printProdString(){
        return String.format("Product [prodSKU=%s, prodDesc=%s, prodName=%s, price=%s, discount=%s]",
                prodSKU,prodDesc,prodName,price,discount);
    }
}
