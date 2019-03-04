package sample;

import Connectvy.ConnectionClass;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller_AddMO implements Initializable {

    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();

    @FXML
    private JFXDatePicker Date_Warranty_AddMO;
    @FXML
    private JFXDatePicker Date_StartMo_AddMO;
    @FXML
    private JFXDatePicker Date_EndMO_AddMO;
    @FXML
    private JFXTextField Txfiled_SPCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOCost_AddMO;
    @FXML
    private JFXTextField Txfiled_VAT_AddMO;
    @FXML
    private JFXTextField Txfiled_TotalCost_AddMO;
    @FXML
    private JFXTextField Txfiled_MOnum_AddMO;
    @FXML
    private JFXTextArea Txfiled_ProplemDisc_AddMO;
    @FXML
    private ComboBox<String> Selct_Techichan_AddMO;
    @FXML
    private ComboBox<String> Selct_MoStatus_AddMO;
    ObservableList<String> ListOfStatus = FXCollections.observableArrayList("created", "approve", "disapprove",
            "cannot be done", "under maintenance", "other defects has been detected", "repaired", "paid");

    ObservableList<String> ListOfTechichan = FXCollections.observableArrayList();

    @FXML
    private JFXTextField Txfiled_SpSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_SearchSP_AddMO;
    @FXML
    private JFXTextField Txfiled_CusName_AddMO;
    @FXML
    private JFXTextField Txfiled_CusMnum_AddMO;
    @FXML
    private JFXTextField Txfiled_DevSerialN_AddMO;
    @FXML
    private JFXTextField Txfiled_DevDiscription_AddMO;
    @FXML
    private JFXButton Btn_Print_AddMo;
    @FXML
    private JFXButton Btn_Cancle_AddMo;
    @FXML
    private JFXButton Btn_Delete_AddMo;
    @FXML
    private JFXButton Btn_Save_AddMo;
    @FXML
    private JFXButton Btn_Search_AddMo;
    @FXML
    private TableView<Controller_AddMO.AddSP> Table_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPdisc_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, String> Col_SPname_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Integer> Col_SPnum_AddSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.AddSP, Double> Col_SPprice_AddSP_AddMO;

    ObservableList<Controller_AddMO.AddSP> list = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> list2 = FXCollections.observableArrayList();
    ObservableList<Controller_AddMO.SelectedSP> loadlist = FXCollections.observableArrayList();
    @FXML
    private TableView<Controller_AddMO.SelectedSP> Table_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPdisc_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPname_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Integer> Col_SPnum_SelectedSP_AddMO;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, String> Col_SPSN_SelectedSP_AddMO;

    ObservableList<Controller_AddMO.SelectedSP> SPSelected2, AllSP2;
    int count = 0;
    @FXML
    private TableColumn<Controller_AddMO.SelectedSP, Double> Col_SPprice_SelectedSP_AddMO;
    @FXML
    private JFXButton Btn_ReomveSP_AddMo;
    @FXML
    private JFXButton Btn_AddSP_AddMo;
    @FXML
    private JFXTextField Txfiled_SpPrice_AddMO;

    @FXML
    private void M_Txfiled_SpSerialN_AddMO(ActionEvent event) throws SQLException {
        AllSP2 = Table_SelectedSP_AddMO.getItems();
        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();
        //  SPSelected2.get(0).SP_SNProperty("");
        //Txfiled_SpSerialN_AddMO.getText();
        double perSp_price = Double.parseDouble(Txfiled_SpPrice_AddMO.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            loadlist.add(new Controller_AddMO.SelectedSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(),
                    SPSelected2.get(i).getSP2_Description(), perSp_price, Txfiled_SpSerialN_AddMO.getText(), SPSelected2.get(i).getSP_Seq_Nber()));

            String sqlupdateAddSP = "UPDATE `require` SET `SERIAL_NUMBER` = '" + Txfiled_SpSerialN_AddMO.getText() + "', `Effective_Price` ='" + Txfiled_SpPrice_AddMO.getText() + "' WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText()
                    + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number() + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
            System.out.println(sqlupdateAddSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdateAddSP);

            SPSelected2.forEach(loadlist::remove);
            Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            calculate();
            Txfiled_SpSerialN_AddMO.setText("الرقم التسلسلي");
            Txfiled_SpPrice_AddMO.setText("السعر");
        
        }
    }

    @FXML
    private void M_MousClicked_TabelSelecSP_AddMO(MouseEvent event) {

        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        Txfiled_SpPrice_AddMO.setText(String.valueOf(SPSelected2.get(0).getSP_Price2()));

        //Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
    }

    @FXML
    private void M_Btn_ReomveSP_AddMo(ActionEvent event) throws SQLException {

        AllSP2 = Table_SelectedSP_AddMO.getItems();
        SPSelected2 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected2.size(); i++) {
            //for(int i =1;i<=1; i++){
            //list.clear();
            // list.add(new Controller_AddMO.AddSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(), SPSelected2.get(i).getSP2_Description(), 
            //SPSelected2.get(i).getSP_Price2()));
            // Table_AddSP_AddMO.setItems(list);
            String sqlDeletSP = "DELETE FROM `require` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText() + " AND SP_NBER=" + SPSelected2.get(i).getSP2_Number()
                    + " AND Seq_Nber=" + SPSelected2.get(i).getSP_Seq_Nber();
            System.out.println(sqlDeletSP);
            //SPSelected2.get(i).ge
            String sqlupdateSP = "UPDATE `spare_parts` SET `QUANTITY` = QUANTITY+1 WHERE `spare_parts`.`SP_NBER` ="+SPSelected2.get(i).getSP2_Number();
            System.out.println(sqlupdateSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlDeletSP);
            statement1.executeUpdate(sqlupdateSP);

        }
        //}
        SPSelected2.forEach(loadlist::remove);
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);

        calculate();
        //DELETE FROM `require` WHERE `require`.`MO_NBER` = 7 AND `require`.`SP_NBER` = 3;

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
    }
    //DELETE FROM `require` WHERE `SERIAL_NUMBER`=HH-2fC

    /*private void M_Txfiled_SearchSP_AddMO(ActionEvent event) {
        System.out.println(event.getEventType().toString());
        list.clear();

        String id1 = Txfiled_SearchSP_AddMO.getText();
        if (Txfiled_SearchSP_AddMO.getText().isEmpty()){
            String sql1 ="SELECT * FROM spare_parts";
            System.out.println(sql1);
            Search(sql1);
            

        }else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NBRE = '" + id1 + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NBRE LIKE '"+id1+"%';";
            System.out.println(trysql);
            Search(trysql);

        }}*/
    
     public void loadInTO(String MO_Nber, String CUS_NAME, String PROBLEM_DESC, String CUS_MOBILE_NBER, String SP_COST, String MO_COST, String DEVICE_SN, String DEVICE_DESC, String WARRANTY, String STARTING_DATE, String ENDING_DATE,
            String STATE, String EMP_NAME) throws SQLException {
        count = 2;

        Txfiled_MOnum_AddMO.setDisable(true);
        Txfiled_MOnum_AddMO.setText(MO_Nber);
        Txfiled_CusName_AddMO.setText(CUS_NAME);
        Txfiled_ProplemDisc_AddMO.setText(PROBLEM_DESC);
        Txfiled_CusMnum_AddMO.setText(CUS_MOBILE_NBER);
        Txfiled_SPCost_AddMO.setText(SP_COST);
        Txfiled_MOCost_AddMO.setText(MO_COST);
        Txfiled_DevSerialN_AddMO.setText(DEVICE_SN);
        Txfiled_DevDiscription_AddMO.setText(DEVICE_DESC);

        LocalDate WARRANTYDate = LocalDate.parse(WARRANTY);
        LocalDate STARTINGDate = LocalDate.parse(STARTING_DATE);
        LocalDate ENDINGDate = LocalDate.parse(ENDING_DATE);

        Date_Warranty_AddMO.setValue(WARRANTYDate);
        Date_StartMo_AddMO.setValue(STARTINGDate);
        Date_EndMO_AddMO.setValue(ENDINGDate);

        //List<String> State = new ArrayList<>();
        //State.add(rs.getString("STATE"));
        //Selct_MoStatus_AddMO.setItems(FXCollections.observableArrayList(State));
        Selct_MoStatus_AddMO.getSelectionModel().select(STATE);

        //List<String> Tec = new ArrayList<>();
        //Tec.add(rs.getString("EMPLOYEE_ID"));
        System.out.println("PPPPPPPPPPPPPP " + EMP_NAME);
        Selct_Techichan_AddMO.getSelectionModel().select(EMP_NAME);

        Btn_Delete_AddMo.setDisable(false);
        Btn_Save_AddMo.setDisable(false);
        Btn_Print_AddMo.setDisable(false);
        Btn_Delete_AddMo.setDisable(false);
        Txfiled_CusName_AddMO.setDisable(true);
        Btn_Cancle_AddMo.setDisable(false);
        Btn_AddSP_AddMo.setDisable(false);
        Btn_ReomveSP_AddMo.setDisable(false);
        //loadlist.clear();
        loadSpSelected();

        calculate();

    }

    public void Search(String Search, int Choose) {
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    double SP_Pri = Double.parseDouble(price);

                    list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_AddSP_AddMO.getItems().setAll(list);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {

                    Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        }

    }

    @FXML
    private void M_Btn_AddSP_AddMo(ActionEvent event) throws SQLException {

        ObservableList<Controller_AddMO.AddSP> SPSelected, AllSP;
        AllSP = Table_AddSP_AddMO.getItems();
        SPSelected = Table_AddSP_AddMO.getSelectionModel().getSelectedItems();
        // ObservableList<Controller_AddMO.SelectedSP> SPSelected3, AllSP3;
        //AllSP3 = Table_SelectedSP_AddMO.getItems();
        ///SPSelected3 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected.size(); i++) {
            System.out.println(SPSelected.size());
            //for(int i =1;i<=1; i++){

            int a = 0;
            int seqNumber = 1;
            for (SelectedSP loadlist1 : loadlist) {

                System.out.println("i==== " + a);
                System.out.println(SPSelected.get(0).getSP_Name());
                System.out.println(loadlist.get(a).getSP2_Name());
                System.out.println("SPSelected size === " + SPSelected.size());
                System.out.println("load size === " + loadlist.size());
                if (loadlist.get(a).getSP2_Name().equals(SPSelected.get(0).getSP_Name())) {
                    loadlist.get(a).getSP_Seq_Nber();
                    System.out.println("seq for a load ==  " + loadlist.get(a).getSP_Seq_Nber());
                    seqNumber = loadlist.get(a).getSP_Seq_Nber() + 1;
                    System.out.println("seqNumber== " + seqNumber);
                }
                a++;

            }

            loadlist.add(new Controller_AddMO.SelectedSP(SPSelected.get(0).getSP_Number(), SPSelected.get(0).getSP_Name(),
                    SPSelected.get(0).getSP_Description(), SPSelected.get(0).getSP_Price(), "null", seqNumber));
            //AllSP3.add()

            String sql1 = "INSERT INTO `require` VALUES(" + Txfiled_MOnum_AddMO.getText() + ",'" + SPSelected.get(0).getSP_Number() + "','"
                    + seqNumber + "','" + "Null'" + ",'" + SPSelected.get(0).getSP_Price() + "')";
            System.out.println(sql1);
            
             String sqlupdateSP = "UPDATE `spare_parts` SET `QUANTITY` = QUANTITY-1 WHERE `spare_parts`.`SP_NBER` ="+SPSelected.get(0).getSP_Number();
            System.out.println(sqlupdateSP);
            
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            statement1.executeUpdate(sqlupdateSP);

        }
        //}
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);

        calculate();

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
        //SPSelected.forEach(AllSP::remove);
        //System.out.println(SPSelected);
        //list.add(new Controller_AddMO.AddSP(aa, mid, mobile));
        //list2.add(SPSelected.get(1));
        //System.out.println(SPSelected.get(0).SP_Description);
        //Table_AddSP_AddMO.getItems().setAll(list);
        //Table_SelectedSP_AddMO.getItems().setAll(list2);
        //SPSelected.forEach(AllSP::remove);
    }

    void loadWindow(String loc, String title) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);
            stage.show();
        } catch (IOException s) {
            s.printStackTrace();
        }
    }

    @FXML
    private void M_Btn_Print_AddMo(ActionEvent event) {
        loadWindow("/sample/Print_Window.fxml", "");
    }

    @FXML
    private void M_Btn_Cancle_AddMo(ActionEvent event) {

        clear();

    }

    @FXML
    private void M_Btn_Delete_AddMo(ActionEvent event) throws SQLException {

        String sql1 = "DELETE FROM  `maintenance_operation` " + " WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
        System.out.println(sql1);
        java.sql.Statement statement1 = connection.createStatement();
        statement1.executeUpdate(sql1);
        clear();

    }

    public void clear() {
        Txfiled_MOnum_AddMO.setDisable(false);

        Txfiled_ProplemDisc_AddMO.clear();
        Txfiled_CusMnum_AddMO.clear();
        Txfiled_SPCost_AddMO.setText("0.00");
        Txfiled_MOCost_AddMO.setText("0.00");
        Txfiled_DevSerialN_AddMO.clear();
        Txfiled_DevDiscription_AddMO.clear();
        Txfiled_SpSerialN_AddMO.clear();
        Txfiled_TotalCost_AddMO.setText("0.00");
        Txfiled_VAT_AddMO.setText("0.00");
        Txfiled_MOnum_AddMO.clear();
        Txfiled_SearchSP_AddMO.clear();
        Txfiled_CusName_AddMO.clear();

        Selct_Techichan_AddMO.getSelectionModel().clearSelection();
        Selct_MoStatus_AddMO.getSelectionModel().clearSelection();
        Date_Warranty_AddMO.setValue(null);
        Date_StartMo_AddMO.setValue(null);
        Date_EndMO_AddMO.setValue(null);
        Table_SelectedSP_AddMO.getItems().clear();
        //loadlist.clear();

    }

    @FXML
    private void M_Btn_Save_AddMo(ActionEvent event) throws SQLException {
        
            if (Txfiled_MOnum_AddMO.getText().isEmpty() ||Txfiled_ProplemDisc_AddMO.getText().isEmpty() ||Txfiled_CusMnum_AddMO.getText().isEmpty()  ||
                    Txfiled_DevSerialN_AddMO.getText().isEmpty() ||Txfiled_DevDiscription_AddMO.getText().isEmpty() ||Date_EndMO_AddMO.getValue() == null ||
                    Date_StartMo_AddMO.getValue() == null   ||Date_Warranty_AddMO.getValue() == null  ||Selct_MoStatus_AddMO.getValue().isEmpty() ||
                    Selct_Techichan_AddMO.getValue().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `employee`");
            ResultSet rs2 = st2.getResultSet();
            //SELECT * FROM `employee` 
             int IndexOFTech = 0;
        for (int i = 0; i < ListOfTechichan.size(); i++) {
            
            while (rs2.next()) {

                //ListOfTechichan.add(rs2.getString("EMP_NAME"));

            
            
            //البحث ب رقم العميل من الداتابيس
            if (Selct_Techichan_AddMO.getValue().equals(rs2.getString("EMP_NAME"))) {
                
                IndexOFTech = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));

            }}
        }//IndexOFTech++;
            System.out.println("INDEX== " + IndexOFTech);
        if (count == 1) {
            System.out.println("Equal  insert");

            String sql1 = "INSERT INTO `maintenance_operation` VALUES(" + monumber + "," + "'" + Selct_MoStatus_AddMO.getValue() + "'" + "," + "'" + Txfiled_MOCost_AddMO.getText()
                    + "'" + "," + "'" + Txfiled_SPCost_AddMO.getText() + "'" + "," + "'" + Date_StartMo_AddMO.getValue() + "'" + "," + "'" + Date_EndMO_AddMO.getValue() + "'" + "," + "'"
                    + Date_Warranty_AddMO.getValue() + "'" + "," + "'" + Txfiled_ProplemDisc_AddMO.getText() + "'" + "," + "'" + Txfiled_DevSerialN_AddMO.getText() + "'" + "," + "'" + Txfiled_DevDiscription_AddMO.getText()
                    + "'" + "," + "'" + IndexOFTech + "'" + "," + "'" + Txfiled_CusMnum_AddMO.getText() + "', NULL ,NULL" + ")";
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);

        } else if (count == 2) {
            System.out.println("Equal  update");
            System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `maintenance_operation` SET STATE='" + Selct_MoStatus_AddMO.getValue() + "',MO_COST='" + Txfiled_MOCost_AddMO.getText() + "',SP_COST='" + Txfiled_SPCost_AddMO.getText()
                    + "',STARTING_DATE='" + Date_StartMo_AddMO.getValue()+ "',ENDING_DATE='" + Date_EndMO_AddMO.getValue()+ "',WARRANTY='" + Date_Warranty_AddMO.getValue()+ "',PROBLEM_DESC='" + Txfiled_ProplemDisc_AddMO.getText()
                    + "',DEVICE_SN='" + Txfiled_DevSerialN_AddMO.getText()+ "',DEVICE_DESC='" + Txfiled_DevDiscription_AddMO.getText()+ "',EMPLOYEE_ID='" +IndexOFTech+ "',CUS_MOBILE_NBER='" + Txfiled_CusMnum_AddMO.getText()
                    + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
            System.out.println(Selct_MoStatus_AddMO.getValue().equalsIgnoreCase(sql1));

            if (Selct_MoStatus_AddMO.getValue().equalsIgnoreCase("paid")) {

                System.out.println("PAAAAAAAAAID");
                String inv_num_date = "UPDATE  `maintenance_operation` SET INVOICE_DATE='" +/*المفروض  يكون التاريخ الحالي للنظام*/ Date_Warranty_AddMO.getValue() + "',INVOICE_NBER='" +/*المفروض تكون فارغه وتنشى سيكونس*/ 4 + "' WHERE MO_NBER= '" + Txfiled_MOnum_AddMO.getText() + "'";
                java.sql.Statement statement2 = connection.createStatement();
                statement2.executeUpdate(inv_num_date);

                //System.out.println(Txfiled_MOnum_AddMO.getText());
            }
            //else if (mo state == problem){send mail}
        }
        count = 2;

    }
    public int monumber = 0;

    @FXML
    private void M_Btn_Search_AddMo(ActionEvent event) throws SQLException, ParseException {
                if (Txfiled_MOnum_AddMO.getText().isEmpty() ) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + Txfiled_MOnum_AddMO.getText());

        ResultSet rs = st.getResultSet();
        //st = connection.prepareCall(sql);

        if (rs.first()) {

            System.out.println(Txfiled_MOnum_AddMO.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("MO_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_MOnum_AddMO.getText());

            if (rs.getString("MO_NBER").equals(Txfiled_MOnum_AddMO.getText())) {

                count = 2;

                Txfiled_MOnum_AddMO.setDisable(true);
                Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
                Txfiled_ProplemDisc_AddMO.setText(rs.getString("PROBLEM_DESC"));
                Txfiled_CusMnum_AddMO.setText(rs.getString("CUS_MOBILE_NBER"));
                Txfiled_SPCost_AddMO.setText(rs.getString("SP_COST"));
                Txfiled_MOCost_AddMO.setText(rs.getString("MO_COST"));
                Txfiled_DevSerialN_AddMO.setText(rs.getString("DEVICE_SN"));
                Txfiled_DevDiscription_AddMO.setText(rs.getString("DEVICE_DESC"));

                LocalDate WARRANTYDate = LocalDate.parse(rs.getString("WARRANTY"));
                LocalDate STARTINGDate = LocalDate.parse(rs.getString("STARTING_DATE"));
                LocalDate ENDINGDate = LocalDate.parse(rs.getString("ENDING_DATE"));

                Date_Warranty_AddMO.setValue(WARRANTYDate);
                Date_StartMo_AddMO.setValue(STARTINGDate);
                Date_EndMO_AddMO.setValue(ENDINGDate);

                //List<String> State = new ArrayList<>();
                //State.add(rs.getString("STATE"));
                //Selct_MoStatus_AddMO.setItems(FXCollections.observableArrayList(State));
                Selct_MoStatus_AddMO.getSelectionModel().select(rs.getString("STATE"));

                //List<String> Tec = new ArrayList<>();
                //Tec.add(rs.getString("EMPLOYEE_ID"));
                System.out.println("PPPPPPPPPPPPPP "+rs.getString("EMP_NAME"));
                Selct_Techichan_AddMO.getSelectionModel().select(rs.getString("EMP_NAME"));

                Btn_Delete_AddMo.setDisable(false);
                Btn_Save_AddMo.setDisable(false);
                Btn_Print_AddMo.setDisable(false);
                Btn_Delete_AddMo.setDisable(false);
                Txfiled_CusName_AddMO.setDisable(true);
                Btn_Cancle_AddMo.setDisable(false);
                Btn_AddSP_AddMo.setDisable(false);
                Btn_ReomveSP_AddMo.setDisable(false);
                //loadlist.clear();
                loadSpSelected();

                calculate();

            }

            //java.sql.Statement statement1 = connection.createStatement();
            //statement1.executeQuery(sql);
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `maintenance_operation` ORDER BY `MO_NBER` DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {
                
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();
                count = 1;
                monumber = Integer.parseInt(rs2.getString("MO_NBER"));
                monumber++;
                System.out.println(monumber);
                Txfiled_MOnum_AddMO.setText(String.valueOf(monumber));
                Txfiled_MOnum_AddMO.setDisable(true);
                //Txfiled_MOnum_AddMO.clear();
                Btn_Delete_AddMo.setDisable(true);
                Btn_Cancle_AddMo.setDisable(false);
                Btn_Save_AddMo.setDisable(false);
                Btn_Print_AddMo.setDisable(false);
                Txfiled_CusName_AddMO.setDisable(false);
            }
        }
    }//}

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //ConnectionClass connectionClass =new ConnectionClass();
        // we call conneClass  that we make it up
        connectionClass.connectDB();
        //Connection connection= connectionClass.getConnection();
        intilCol();
        loadData();
        loadTech();
        System.out.println("Byee");
        Selct_Techichan_AddMO.setItems(ListOfTechichan);
        Selct_MoStatus_AddMO.setItems(ListOfStatus);

    }

    public void loadTech() {
        String query = "SELECT EMP_NAME FROM employee";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfTechichan.add(rs.getString("EMP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    private void intilCol() {

        Col_SPnum_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPdisc_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description"));
        Col_SPprice_AddSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price"));

        Col_SPnum_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Number2"));
        Col_SPname_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Name2"));
        Col_SPdisc_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Description2"));
        Col_SPSN_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_SN"));
        Col_SPprice_SelectedSP_AddMO.setCellValueFactory(new PropertyValueFactory<>("SP_Price2"));

    }

    private void loadData() {

        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                String mname = rs.getString("SP_NBER");
                String mid = rs.getString("SP_NAME");
                String mobile = rs.getString("DESCRIPTION");
                String price = rs.getString("PRICE");

                int SP_num = Integer.parseInt(mname);
                double SP_Pri = Double.parseDouble(price);

                list.add(new Controller_AddMO.AddSP(SP_num, mid, mobile, SP_Pri));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_AddSP_AddMO.getItems().setAll(list);

    }

    private void loadSpSelected() throws SQLException {
        loadlist.clear();
        System.out.println("i am in loadSpSelected ");

        String SQLqq = "SELECT *\n"
                + "FROM   spare_parts s\n"
                + "JOIN   `require` r ON s.SP_NBER = r.SP_NBER WHERE MO_NBER= "+Txfiled_MOnum_AddMO.getText();
        System.out.println(SQLqq);
        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Seq = Integer.parseInt(rs.getString("Seq_Nber"));

                    double SP_Pri = Double.parseDouble(rs.getString("PRICE"));
                    System.out.println(rs.getString("SP_NBER"));

                    double SP_Pri2 = Double.parseDouble(rs.getString("Effective_Price"));

                    //Txfiled_SPCost_AddMO.setText(String.valueOf(a));
                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"),SP_Seq));
                    if (rs.getString("MO_NBER").equals(Txfiled_MOnum_AddMO.getText())) {
                        a += SP_Pri2;

                        System.out.println("Total AA price =  " + a);
                        System.out.println("EQUALLLLLLL");
                        Txfiled_SPCost_AddMO.setText(String.valueOf(a));
                        //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                        loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"), SP_Seq));
                    }

                }
            //System.out.println(loadlist.get(0).getSP2_Name());
            Table_SelectedSP_AddMO.getItems().setAll(loadlist);
            }


            rs.close();

        } catch (SQLException ex) {
        }

        /*String query = "SELECT * FROM `require` WHERE MO_NBER= " + Txfiled_MOnum_AddMO.getText();
        System.out.println(query);
        ResultSet rs = connectionClass.execQuery(query);

        String query2 = "SELECT * FROM spare_parts";
        ResultSet rs2 = connectionClass.execQuery(query2);
        try {
            while (rs2.next()) {
                String mname = rs2.getString("SP_NBRE");
                String mid = rs2.getString("SP_NAME");
                String mobile = rs2.getString("DESCRIPTION");
                String price = rs2.getString("PRICE");

                int SP_num = Integer.parseInt(mname);
                double SP_Pri = Double.parseDouble(price);
                System.out.println(rs2.getString("SP_NBRE"));
        while (rs.next()) {
    //System.out.println(rs.getString("SP_NBRE"));
            System.out.println(rs.getString("MO_NBER"));
            System.out.println(rs.getString("SERIAL_NUMBER"));

                
                System.out.println(rs.getString("MO_NBER"));
                System.out.println(rs.getString("SERIAL_NUMBER"));
                System.out.println("dddddddddddddd");
                                double SP_Pri2 = Double.parseDouble(rs.getString("Effective_Price"));

                loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri2, rs.getString("SERIAL_NUMBER")));
                if (rs.getString("MO_NBER") == Txfiled_MOnum_AddMO.getText()) {

                    loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                }
                }
            }
            rs2.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_SelectedSP_AddMO.getItems().setAll(loadlist);
         */
    }

    public void calculate() {
        double spcost = 0;
        //System.out.println("size=  " + list2.size());

        if (!loadlist.isEmpty()) {

            for (int i = 0; i < loadlist.size(); i++) {
                double[] mypriceArray = new double[loadlist.size()];
                mypriceArray[i] = loadlist.get(i).getSP_Price2();
                System.out.println(mypriceArray[i]);
                spcost += mypriceArray[i];
                System.out.println("pr= " + i + "  " + mypriceArray[i]);
                Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
            }
        }

        double costofmaint = Double.parseDouble(Txfiled_MOCost_AddMO.getText());
        double costofSP = spcost;
        double total = costofmaint + costofSP;
        double vat = total * 0.05;
        System.out.println("VAT== " + vat);
        double Total = total + vat;
        Txfiled_VAT_AddMO.setText(String.valueOf(vat));
        Txfiled_TotalCost_AddMO.setText(String.valueOf(Total));

    }

    @FXML
    private void M_Txfiled_SPCost_AddMO(ActionEvent event) {

        calculate();
    }

    String id3 = "";
    public int Choose = 0;

    @FXML
    private void M_Txfiled_CusMnum_AddMO(KeyEvent event) {
        Choose = 1;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        id3 += event.getText();
        if (event.getText().equals("")) {
            id3 = id3.substring(0, id3.length() - 1);

        }

        System.out.println("__________  " + id3);

        String sql1 = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` = '" + id3 + "'";
        String trysql = "SELECT * FROM `customer` WHERE `CUS_MOBILE_NBER` LIKE '" + id3 + "%';";
        System.out.println(sql1);
        Search(sql1, Choose);

    }

    String id2 = "";

    @FXML
    private void M_Txfiled_SearchSP_AddMO(KeyEvent event) {
        Choose = 2;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());
        list.clear();
        id2 += event.getText();
        if (event.getText().equals("")) {
            id2 = id2.substring(0, id2.length() - 1);

        }

        String id1 = Txfiled_SearchSP_AddMO.getText();
        System.out.println("__________  " + id2);
        System.out.println("__________  " + id1);
        System.out.println("__________  " + Txfiled_SearchSP_AddMO.getText());

        if (id2.isEmpty()) {
            String sql1 = "SELECT * FROM spare_parts";
            System.out.println(sql1);
            Search(sql1, Choose);

        } else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + id2 + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + id2 + "%';";
            System.out.println(trysql);
            Search(trysql, Choose);

        }
    }

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleStringProperty SP_Description;
        private final SimpleDoubleProperty SP_Price;

        AddSP(int SP_Number, String SP_Name, String SP_Description, double SP_Price) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Description = new SimpleStringProperty(SP_Description);
            this.SP_Price = new SimpleDoubleProperty(SP_Price);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public SimpleIntegerProperty SP_NumberProperty() {
            return SP_Number;
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public SimpleStringProperty SP_NameProperty() {
            return SP_Name;
        }

        public String getSP_Description() {
            return SP_Description.get();
        }

        public SimpleStringProperty SP_DescriptionProperty() {
            return SP_Description;
        }

        public double getSP_Price() {
            return SP_Price.get();
        }

        public SimpleDoubleProperty SP_PriceProperty() {
            return SP_Price;
        }

    }

    public static class SelectedSP {

        private final SimpleIntegerProperty SP_Number2;
        private final SimpleStringProperty SP_Name2;
        private final SimpleStringProperty SP_Description2;
        private final SimpleDoubleProperty SP_Price2;
        private final SimpleStringProperty SP_SN;
        private final SimpleIntegerProperty SP_Seq_Nber;

        SelectedSP(Integer SP_Number2, String SP_Name2, String SP_Description2, double SP_Price2, String SP_SN, Integer SP_Seq_Nber) {
            this.SP_Number2 = new SimpleIntegerProperty(SP_Number2);
            this.SP_Name2 = new SimpleStringProperty(SP_Name2);
            this.SP_Description2 = new SimpleStringProperty(SP_Description2);
            this.SP_Price2 = new SimpleDoubleProperty(SP_Price2);
            this.SP_SN = new SimpleStringProperty(SP_SN);
            this.SP_Seq_Nber = new SimpleIntegerProperty(SP_Seq_Nber);

        }

        public Integer getSP2_Number() {
            return SP_Number2.get();
        }

        public SimpleIntegerProperty SP_Number2Property() {
            return SP_Number2;
        }

        public String getSP2_Name() {
            return SP_Name2.get();
        }

        public SimpleStringProperty SP_Name2Property() {
            return SP_Name2;
        }

        public String getSP2_Description() {
            return SP_Description2.get();
        }

        public SimpleStringProperty SP_Description2Property() {
            return SP_Description2;
        }

        public double getSP_Price2() {
            return SP_Price2.get();
        }

        public SimpleDoubleProperty SP_Price2Property() {
            return SP_Price2;
        }

        public String getSP_SN() {
            return SP_SN.get();
        }

        public SimpleStringProperty SP_SNProperty() {
            return SP_SN;
        }

        public Integer getSP_Seq_Nber() {
            return SP_Seq_Nber.get();
        }

        public SimpleIntegerProperty SP_Seq_NberProperty() {
            return SP_Seq_Nber;
        }

    }
}
