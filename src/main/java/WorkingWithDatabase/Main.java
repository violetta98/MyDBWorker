package WorkingWithDatabase;


/**
 * Created by Violetta on 2017-08-26.
 */
public class Main {

    public static void main(String[] args) {
        DBWorker.setConnection();
        DBWorker.executeUpdate("INSERT INTO users VALUES (NULL, 'James', 24, 'jam@gmail.com')");
        DBWorker.сloseConnection();
    }

}
