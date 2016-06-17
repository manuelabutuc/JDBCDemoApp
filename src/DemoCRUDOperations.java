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
                    demoRead(query(opt, null, null, null, null));
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
                    demoCDU(query(opt, n, t, null, null));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 3) {
                String nume = readStringConsole("Introduceti numele cautat: ");
                try {
                    demoRead(query(opt, nume, null, null, null));
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
                    demoCDU(query(opt, nume, null, numeNou, telefonNou));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (opt == 5) {
                String nume = readStringConsole("introduceti numele de sters:");

                try {
                    demoCDU(query(opt, nume, null, null, null));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } while (opt != 6);
    }

    public static String query(int n, String nume, String telefon, String numeNou, String telefonNou) {
        String query = "";
        switch (n) {
            case 1:
                query = "SELECT nume,telefon FROM agenda";
                break;
            case 2:
                query = "INSERT INTO agenda (nume, telefon) VALUES ('" + nume + "','" + telefon + "')";
                break;
            case 3:
                query = "SELECT nume,telefon FROM agenda WHERE nume='" + nume + "'";
                break;
            case 4:
                query = "UPDATE agenda SET NUME='" + numeNou + "', TELEFON='" + telefonNou + "' WHERE NUME='" + nume + "'";
                break;
            case 5:
                query = "DELETE FROM agenda WHERE nume='" + nume + "'";
                break;
        }
        return query;
    }

    private static void demoRead(String query) throws ClassNotFoundException, SQLException {
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
        ResultSet rs = st.executeQuery(query);

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

    private static void demoCDU(String query) throws ClassNotFoundException, SQLException {
        // 1. load driver
        Class.forName("org.postgresql.Driver");

        // 2. define connection params to db
        final String URL = "jdbc:postgresql://54.93.65.5/4_Manu";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        // 3. obtain a connection
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        // 4. create a query statement
        PreparedStatement pSt = conn.prepareStatement(query);

        // 5. execute a prepared statement
        int rowsInserted = pSt.executeUpdate();

        // 6. close the objects
        pSt.close();
        conn.close();
    }

}


