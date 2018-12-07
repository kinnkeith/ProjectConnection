/**
 * Author: Keith Koinange
 **/
import java.sql.*;
import java.util.*;

public class SQLConn {
    private Connection myConn;
    private PreparedStatement sqlSearchStmt;
    private PreparedStatement sqlSearchAllStmt;
    private PreparedStatement sqlAddProd;
    private PreparedStatement sqlUpdateStmt;
    private PreparedStatement sqlDeleteProd;
    // insertStmt;

    ResultSet myRs = null;
    List<Products> list = new ArrayList<Products>();

    public List<Products> searchAllProducts() throws SQLException {
        List<Products> list = new ArrayList<Products>();
        try {
            sqlSearchAllStmt = myConn.prepareStatement("select * from finalyearproject.smarttech ");
            myRs = sqlSearchAllStmt.executeQuery();
            while (myRs.next()) {
                Products tempProd = convertRowToProducts(myRs);
                list.add(tempProd);
            }
            System.out.println("Search All Complete...");
            return list;

        }catch (Exception ex2){
            System.out.println("Error");
        }finally { close(sqlSearchAllStmt,myRs); }
        return  list;
    }

    public List<Products> searchProdID(String inProdSKU) throws SQLException {
        List<Products> list = new ArrayList<Products>();
        try {
            inProdSKU += "%";
            sqlSearchStmt = myConn.prepareStatement(("select * from finalyearproject.smarttech " +
                    "where ProdSKU like ?"));
            sqlSearchStmt.setString(1, "%"+ inProdSKU );
            myRs = sqlSearchStmt.executeQuery();

            while (myRs.next()) {
                Products tempProd = convertRowToProducts(myRs);
                list.add(tempProd);
            }
            System.out.println("Search complete..." + "\nProduct " + inProdSKU + " found!" );
            return list;

        }catch (Exception ex2){
            System.out.println(ex2);
        }finally { close(sqlSearchStmt,myRs); }
        return  list;
    }

    public List<Products> addProducts(String prodSKU,String prodDesc,String prodName,String price, String discount)
            throws SQLException {

        List<Products> list = new ArrayList<Products>();
        int result;
        try {
            sqlAddProd = myConn.prepareStatement("insert into finalyearproject.smarttech "+
                    "values (?,?,?,?,?)");
            sqlAddProd.setInt(1, Integer.parseInt(prodSKU));
            sqlAddProd.setString(2, prodDesc);
            sqlAddProd.setString(3,prodName);
            sqlAddProd.setInt(4, Integer.parseInt(price));
            sqlAddProd.setInt(5, Integer.parseInt(discount));
            result = sqlAddProd.executeUpdate();

            // if update fails, rollback and discontinue
            if ( result == 0 ) {
                myConn.rollback(); // rollback update
                return null; // update unsuccessful
            }
            System.out.println("New Product Added");
            return list;

        }catch (Exception ex2){
            System.out.println(ex2);
        }finally {
            close(sqlAddProd,myRs);
        }return  list;
    }

    public List<Products> updateProducts(String inProdSKU,String inProdDesc,String inProdName,String inPrice, String inDiscount)
            throws SQLException
    {
        List<Products> list = new ArrayList<Products>();
        int result;
        try {
            searchProdID(inProdSKU);
            sqlUpdateStmt = myConn.prepareStatement( "update smarttech " +
                    "set product = ?, ProdName = ?, Price = ?, Discount = ? " +
                    "where prodSKU = ?");
            sqlUpdateStmt.setString(1, inProdDesc );
            sqlUpdateStmt.setString(2, inProdName);
            sqlUpdateStmt.setInt(3, Integer.parseInt(inPrice));
            sqlUpdateStmt.setInt(4, Integer.parseInt(inDiscount));
            sqlUpdateStmt.setInt(5, Integer.parseInt(inProdSKU));
            System.out.println(sqlUpdateStmt);
            result = sqlUpdateStmt.executeUpdate();

            // if update fails, rollback and discontinue
            if ( result == 0 ) {
                myConn.rollback(); // rollback update
                return null; // update unsuccessful
            }
            System.out.println("Product: " +inProdSKU + " Updated");
            return list;

        }catch (Exception ex2){
            System.out.println(ex2);
        }finally {
            close(sqlSearchStmt,myRs);
        }return  list;
    }

    public List<Products> deleteProducts(String inProdSKU) throws SQLException {
        List<Products> list = new ArrayList<Products>();
        int result;
        try {
            sqlDeleteProd = myConn.prepareStatement("delete from finalyearproject.smarttech "+
                    "where ProdSKU = ?");

            sqlDeleteProd.setInt(1, Integer.parseInt(inProdSKU)  );
            System.out.println(sqlDeleteProd);
            result = sqlDeleteProd.executeUpdate();

            System.out.println("Product " + inProdSKU + " deleted!");
            return list;

        }catch (Exception ex2){
            System.out.println(ex2);
        }finally {
            close(sqlDeleteProd,myRs);
        }return  list;
    }

    public void connectToDB()
    {
        String user = "root";
        String password = "root";
        String dbUrl = "jdbc:mysql://localhost:3306/finalyearproject";
        try {
            myConn = DriverManager.getConnection(dbUrl, user, password);
            if(myConn!=null) {
                System.out.println("Connection successful to DB = " + dbUrl);
            }
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to DB failed!!!");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private Products convertRowToProducts(ResultSet myRs) throws SQLException{
        int prodSKU = myRs.getInt("ProdSKU");
        String product = myRs.getString("Product");
        String prodName = myRs.getString("ProdName");
        int price = myRs.getInt("Price");
        int discount = myRs.getInt("Discount");

        Products tempProd = new Products(prodSKU,product,prodName,price,discount);

        return tempProd;
    }

    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {
        if (myRs != null) {
            myRs.close();
        }
        if (myStmt != null) {
            myStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

}

