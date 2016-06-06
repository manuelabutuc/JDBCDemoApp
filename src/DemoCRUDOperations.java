import java.sql.*;

public class DemoCRUDOperations extends SkeletonJava {


    public static void main(String[] args) {

        int opt = 0;

        do {
            printConsole("Meniul principal:");
            printConsole("1 - Afisarea sirului de nume / 2 - Creare nume / 3 - Cautare nume / 4 - Modificare nume / 5 - Stergere nume");
            printConsole("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

            opt = readIntConsole("Alegeti optiunea dorita:");
            System.out.println();

            if (opt == 1) {
                try {
                    demoRead();
                    System.out.println();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 2) {
                String n = readStringConsole("Introduceti numele: ");
                String t = readStringConsole("Introduceti numarul de telefon: ");
                try {
                    demoCreate(n, t);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 3) {
                String nume = readStringConsole("Introduceti numele cautat: ");
                try {
                    demoSearch(nume);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 4) {
                String nume = readStringConsole("Introduceti numele de modificat:");
                String numeNou = readStringConsole("Introduceti numele dorit:");
                String telefonNou = readStringConsole("Introduceti numarul de telefon:");


                try {
                    demoUpdate(nume, numeNou, telefonNou);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 5) {
                String nume = readStringConsole("introduceti numele de sters:");

                try {
                    demoDelete(nume);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } while (opt != 6);
    }


    private static void demoCreate(String nume, String telefon) throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("INSERT INTO agenda (nume, telefon) VALUES (?,?)");
        pSt.setString(1, nume);
        pSt.setString(2, telefon);

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

    private static void demoRead() throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT nume,telefon FROM agenda");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("nume").trim());
            System.out.print("---");
            System.out.println(rs.getString("telefon").trim());
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoSearch(String num) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        Statement st = conn.createStatement();

        // 5. execute a query
        ResultSet rs = st.executeQuery("SELECT nume,telefon FROM agenda WHERE nume='" + num + "'");

        // 6. iterate the result set and print the values
        while (rs.next()) {
            System.out.print(rs.getString("nume").trim());
            System.out.print("---");
            System.out.println(rs.getString("telefon").trim());
        }

        // 7. close the objects
        rs.close();
        st.close();
        conn.close();
    }

    private static void demoUpdate(String num, String numeNou, String telefonNou) throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("UPDATE agenda SET NUME=?, TELEFON=? WHERE NUME='" + num + "'");
        pSt.setString(1, numeNou);
        pSt.setString(2, telefonNou);


        // 5. execute a prepared statement
        int rowsUpdated = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }


    private static void demoDelete(String nume) throws ClassNotFoundException, SQLException {

        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement("DELETE FROM agenda WHERE nume='" + nume + "'");


        // 5. execute a prepared statement
        int rowsDeleted = pSt.executeUpdate();
        System.out.println(rowsDeleted + " rows were deleted.");
        // 6. close the objects
        pSt.close();
        conn.close();
    }


}
