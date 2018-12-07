/**
 * Author: Keith Koinange
 **/
import java.util.List;
import javax.swing.table.AbstractTableModel;

class ProductTableModel extends AbstractTableModel {

    private static final int PROD_SKU = 0;
    private static final int PROD_DESC = 1;
    private static final int PROD_NAME = 2;
    private static final int PRICE = 3;
    private static final int DISCOUNT = 4;

    private String[] columnNames = { "ProdSKU", "ProdDesc", "ProdName", "Price", "Discount" };
    private List<Products> products;

    public ProductTableModel(List<Products> theProducts) {
        products = theProducts;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return products.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {

        Products tempProd = products.get(row);

        switch (col) {
            case PROD_SKU:
                return tempProd.getProdSKU();
            case PROD_DESC:
                return tempProd.getProdDesc();
            case PROD_NAME:
                return tempProd.getProdName();
            case PRICE:
                return tempProd.getPrice();
            case DISCOUNT:
                return tempProd.getDiscount();
            default:
                return tempProd.getProdSKU();
        }
    }@Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
}

