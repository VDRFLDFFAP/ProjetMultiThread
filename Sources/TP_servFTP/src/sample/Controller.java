package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class Controller {

    public TextField ip;
    public TextField port;
    public TextField identifier;
    public PasswordField password;
    private static final String IPADDRESS_PATTERN ="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                                                   "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static final String IPPORT_PORT = "[0-9]{1,5}";
    public TreeView myPath;

    public void connexion(ActionEvent actionEvent) {
        String s_ip = ip.getText();
        String s_port = port.getText();
        String s_identifier = identifier.getText();
        String s_password = password.getText();

        if (!s_ip.matches(IPADDRESS_PATTERN) ) {
            ip.setText("Ip invalide");
        }
        if (!s_port.matches(IPPORT_PORT)) {
            port.setText("Port Invalide");
        }

        if (s_identifier.equals("test") && s_password.equals("test")) {
            identifier.setText("Connexion réussi");
        }

    }

    public void cancel(ActionEvent actionEvent) {
    }

    public void majMyPath(ActionEvent actionEvent) {
        TreeItem test = new TreeItem();
        myPath.setRoot(test);
    }
}
