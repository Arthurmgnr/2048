package main;

import main.gui.*;

import javax.swing.SwingUtilities;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws URISyntaxException {
        System.out.println(DBConnection.getConnection());
        DBConnection.openConnection();
        System.out.println();

        SwingUtilities.invokeLater(() -> {
            Home frame = new Home();
//            Register frame = new Register();
//            Login frame = new Login();
//            ProfileGame frame = new ProfileGame("TestUser", true, true);
//            Profile frame = new Profile("TestUser");
//            EditProfile frame = new EditProfile("TestUser");
//            Game frame = new Game("TestUser");
            frame.setVisible(true);
        });

        Runtime.getRuntime().addShutdownHook(new Thread(DBConnection::closeConnection));
    }
}