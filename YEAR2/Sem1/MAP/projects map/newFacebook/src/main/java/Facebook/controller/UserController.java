package Facebook.controller;

import Facebook.domain.User;
import Facebook.service.Service;
import Facebook.utils.events.UserEntityChangeEvent;
import Facebook.utils.observer.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class UserController implements Observer<UserEntityChangeEvent> {
    Service service;
    ObservableList<User> model = FXCollections.observableArrayList();

    //pagina login
    @FXML
    TextField userNameField;
    @FXML
    TextField passwordField;
    @FXML
    private Button LogInButton;



    //a 2 a pag
    @FXML
    TableView<User> tableView;
    @FXML
    TableColumn<User,String> tableColumnFirstName;
    @FXML
    TableColumn<User,String> tableColumnLastName;


    public void setUtilizatorService(Service service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @FXML
    public void initialize() {
//        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstName"));
//        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        tableView.setItems(model);

    }

    @FXML
    protected void onLogInButtonClick() {
        boolean found=false;
        boolean valid=false;
        String username=userNameField.getText();
        String password=passwordField.getText();
        if(!(username.isEmpty() || username.isEmpty())){
            valid=true;
            List<User> users=service.getListUsers();
            for(User u:users){
                if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                    //deschid fereastra noua cu user

                    found=true;
                }
            }
        }
        if(!valid || !found){
            System.out.println(0);
            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "Invalid username or password!");

            //mesaj de eroare logare
        }
    }

    private void initModel() {
        Iterable<User> messages = service.getListUsers();
        List<User> users = StreamSupport.stream(messages.spliterator(), false)
                .collect(Collectors.toList());
        model.setAll(users);
    }



//    public void handleAddUtilizator(ActionEvent actionEvent) {
//        User user = new User("1");
//        try{
//            User saved = service.addUser(user);
//        } catch (ValidationException e){
//            MessageAlert.showErrorMessage(null, e.getMessage());
//            return;
//        }
//        MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User adaugat cu succes!");
////        if(service.addUtilizator(user) == null){
//            MessageAlert.showMessage(null, Alert.AlertType.INFORMATION, "Info", "User adaugat cu succes!");
//        } else{
//            MessageAlert.showErrorMessage(null, "Failed adding user");
//        }
//    }

    /*public void handleDeleteUtilizator(ActionEvent actionEvent) {
        User user=(User) tableView.getSelectionModel().getSelectedItem();
        if (user!=null) {
            service.removeUser(user);
        }
    }*/

    @Override
    public void update(UserEntityChangeEvent userEntityChangeEvent) {
        initModel();
    }


}
