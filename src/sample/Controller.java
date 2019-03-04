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
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Controller implements Initializable {

    ConnectionClass connectionClass = new ConnectionClass();
    // we call conneClass  that we make it up
    Connection connection = connectionClass.getConnection();

    int count = 0;
    public ListView listv;

    public ObservableList<MO> CurrnetList = FXCollections.observableArrayList();
    public ObservableList<MO> PendingList = FXCollections.observableArrayList();
    public ObservableList<MO> FinshedList = FXCollections.observableArrayList();
    public ObservableList<MO> PriveousList = FXCollections.observableArrayList();

    ObservableList<String> ListOfSuppliers = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSelectedSP = FXCollections.observableArrayList();
    ObservableList<AddSP> ListOFSP = FXCollections.observableArrayList();

    @FXML
    public TableView<MO> Table_CurrentMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_Cost_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Current_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Current_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Current_MngMO;

    @FXML
    private TableView<MO> Table_FinshedMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Finshed_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Finshed_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Finshed_MngMO;

    @FXML
    private TableView<MO> Table_PreviousMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Previous_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Previous_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Previous_MngMO;

    @FXML
    private TableView<MO> Table_pendingMO_MngMO;
    @FXML
    private TableColumn<MO, Double> Col_MOCost_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_CusMobile_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_CusName_Pending_MngMO;
    @FXML
    private TableColumn<MO, Integer> Col_MOnum_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOtechin_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOStatus_Pending_MngMO;
    @FXML
    private TableColumn<MO, String> Col_MOEndDate_Pending_MngMO;
    @FXML
    private JFXButton Btn_Edit_MangeCurrentMO;
    @FXML
    private JFXTextField Txfiled_Search_MangeCurrentMO;
    @FXML
    public AnchorPane rootPage;
    @FXML
    private JFXDatePicker Date_REQdate_ReqSP;
    @FXML
    private JFXTextField Txfiled_REQnum_ReqSP;
    @FXML
    private JFXButton Btn_Print_ReqSP;
    @FXML
    private JFXButton Btn_Cancle_ReqSP;
    @FXML
    private JFXButton Btn_Delete_ReqSP;
    @FXML
    private JFXButton Btn_Save_ReqSP;
    @FXML
    private JFXButton Btn_Search_ReqSP;

    @FXML
    private TableView<AddSP> Table_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_SelectedSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_SelectedSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_QuanitiySP_ReqSP;
    @FXML
    private JFXButton Btn_RemoveSP_ReqSP;

    @FXML
    private TableView<AddSP> Table_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPQuantity_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, String> Col_SPname_AddSP_ReqSP;
    @FXML
    private TableColumn<AddSP, Integer> Col_SPnum_AddSP_ReqSP;
    @FXML
    private JFXTextField Txfiled_SearchSP_ReqSP;
    @FXML
    private JFXButton Btn_AddSP_ReqSP;
    @FXML
    private ComboBox<String> Selct_Supplier_ReqSP;
    @FXML
    private Label SP_aboutTObeOUT_MainWindow;
    @FXML
    private Label PendingMO_MainWindow;
    @FXML
    private Label FinhedMO_MainWindow;
    @FXML
    private Label CurrentMO_MainWindow;

    @FXML
    private Tab Main_Tab;
    @FXML
    private ImageView icMaonMove;
    @FXML
    private Label MainLable1;
    @FXML
    private JFXTextField Txfiled_Name_Customer;
    @FXML
    private JFXTextField Txfiled_Address_Customer;
    @FXML
    private JFXTextField Txfiled_MNum_Customer;
    @FXML
    private JFXTextField Txfiled_Email_Customer;
    @FXML
    private JFXButton Btn_ChangeMN_Customer;
    @FXML
    private JFXButton Btn_Save_Customer;
    @FXML
    private JFXButton Btn_Cancle_Customer;

    @FXML
    private JFXButton Btn_Delete_Customer;
    @FXML
    private JFXButton Btn_Search_Customer;
    @FXML
    private Tab Mangment_MO_Tab;
    @FXML
    private JFXTextField Txfiled_Name_SP;
    @FXML
    private JFXTextField Txfiled_Quantity_SP;
    @FXML
    private JFXTextField Txfiled_SPNum_SP;
    @FXML
    private JFXTextField Txfiled_Price_SP;
    @FXML
    private JFXTextArea Txfiled_Discription_SP;
    @FXML
    private JFXButton Btn_Cancle_SP;
    @FXML
    private JFXButton Btn_Delete_SP;
    @FXML
    private JFXButton Btn_Save_SP;
    @FXML
    private JFXButton Btn_Search_SP;

    @FXML
    private ToggleGroup ReportsDate;
    @FXML
    private Label MainLable;
    @FXML
    private JFXButton Btn_Cancel_Employee;

    @FXML
    private JFXButton Btn_Delete_Employee;

    @FXML
    private JFXButton Btn_Save_Employee;

    @FXML
    private JFXButton Btn_Search_Employee;

    @FXML
    private JFXTextField Txfiled_Name_Supplier;

    @FXML
    private JFXTextField Txfiled_Address_Supplier;

    @FXML
    private JFXTextField Txfiled_Num_Supplier;

    @FXML
    private JFXTextField Txfiled_MNum_Supplier;

    @FXML
    private JFXTextField Txfiled_Email_Supplier;

    @FXML
    private JFXButton Btn_Cancle_Supplier;

    @FXML
    private JFXButton Btn_Delete_Supplier;

    @FXML
    private JFXButton Btn_Save_Supplier;

    @FXML
    private JFXButton Btn_Search_Supplier;
    @FXML
    private JFXTextField Txfiled_Name_Employee;

    @FXML
    private JFXTextField Txfiled_MNum_Employee;

    @FXML
    private ComboBox<String> Selct_Sex_Employee;

    @FXML
    private ComboBox<String> Selct_JType_Employee;
    ObservableList<String> ListOfJType = FXCollections.observableArrayList("Administrator", "ReceptionDesk", "Technician");
    ObservableList<String> ListOfSex = FXCollections.observableArrayList("Male", "Female");

    @FXML
    private JFXTextField Txfiled_Num_Employee;

    @FXML
    private JFXTextField Txfiled_Email_Employee;

    @FXML
    private JFXTextField Txfiled_Address_Employee;

    @FXML
    private JFXTextField Txfiled_Password_Employee;

    //int count = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connectionClass.connectDB();
        listv.getItems().add("- عمليات الصيانة الحالية");
        listv.getItems().add("- عمليات الصيانة المنتهية");
        listv.getItems().add("- عمليات الصيانة السابقة");
        listv.getItems().add("- تقدير مالي عن عملية صيانة");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالعملاء");
        listv.getItems().add("- قائمة عمليات الصيانة لعميل");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالموظفين");
        listv.getItems().add("- قائمة عمليات الصيانة لموظف");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالمزودين");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قطع على وشك النفاذ");
        listv.getItems().add("- قطع الغيار التي نفذت كميتها");

        Selct_JType_Employee.setItems(ListOfJType);
        Selct_Sex_Employee.setItems(ListOfSex);

        intilCol();
        loadAllMO();
        //loadAllSP();
        loadSuppliers();

    }

    private void intilCol() {

        Col_MOnum_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_Cost_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Current_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Finshed_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Previous_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        Col_MOnum_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Number"));
        Col_CusName_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Name"));
        Col_CusMobile_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("Cus_Mobile"));
        Col_MOtechin_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_technician"));
        Col_MOEndDate_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_EndDate"));
        Col_MOCost_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_TotalCost"));
        Col_MOStatus_Pending_MngMO.setCellValueFactory(new PropertyValueFactory<>("MO_Status"));

        //______________________________________________________
        Col_SPnum_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_AddSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

        Col_SPnum_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Number"));
        Col_SPname_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Name"));
        Col_SPQuantity_SelectedSP_ReqSP.setCellValueFactory(new PropertyValueFactory<>("SP_Quantity"));

    }

    public void loadAllMO() {

        String query = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                System.out.println("HEREEEEE");
                //System.out.println("rs.getString(\"MO_Number\")  ="+rs.getString("MO_Number"));

                String MONber = rs.getString("MO_NBER");
                String mobile = rs.getString("CUS_MOBILE_NBER");
                String priceSP = rs.getString("SP_COST");
                String priceMO = rs.getString("MO_COST");
                int MO_num = Integer.parseInt(MONber);
                int CusMobile = Integer.parseInt(mobile);

                double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);

                if (rs.getString("STATE").equalsIgnoreCase("cannot be done") || rs.getString("STATE").equalsIgnoreCase("other defects has been detected")
                        || rs.getString("STATE").equalsIgnoreCase("created")) {
                    //Pending

                    PendingList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                } else if (rs.getString("STATE").equalsIgnoreCase("approve") || rs.getString("STATE").equalsIgnoreCase("under maintenance")) {
                    //Current
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                } else if (rs.getString("STATE").equalsIgnoreCase("repaired")) {
                    //Finshed
                    FinshedList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                } else if (rs.getString("STATE").equalsIgnoreCase("paid") || rs.getString("STATE").equalsIgnoreCase("disapprove")) {
                    //Priveous
                    PriveousList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            }
            rs.close();

            String SPqury = "SELECT * FROM spare_parts WHERE `SP_QUANTITY` < 5";
            ResultSet rs2 = connectionClass.execQuery(SPqury);
            int rowcount = 0;
            if (rs2.last()) {
                rowcount = rs2.getRow();
            }

            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);
            Table_FinshedMO_MngMO.getItems().setAll(FinshedList);
            Table_PreviousMO_MngMO.getItems().setAll(PriveousList);
            Table_pendingMO_MngMO.getItems().setAll(PendingList);
            PendingMO_MainWindow.setText(String.valueOf(PendingList.size()));
            CurrentMO_MainWindow.setText(String.valueOf(CurrnetList.size()));
            FinhedMO_MainWindow.setText(String.valueOf(FinshedList.size()));
            SP_aboutTObeOUT_MainWindow.setText(String.valueOf(rowcount));

        } catch (SQLException ex) {
            ex.printStackTrace();

        }

    }

    private void loadAllSP() {
        ListOFSP.clear();
        String query = "SELECT * FROM spare_parts";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {
                String SPnumber = rs.getString("SP_NBER");

                String SPquantity = rs.getString("SP_QUANTITY");

                int SP_num = Integer.parseInt(SPnumber);
                int SP_quantity = Integer.parseInt(SPquantity);

                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

    }

    private void loadSpecifecSP() throws SQLException {
        ListOFSP.clear();
        String query = "SELECT * FROM `spare_parts`";
        ResultSet rs = connectionClass.execQuery(query);
        if (rs.isBeforeFirst() == false) {
            System.out.println("NULL RRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRRr");

            loadAllSP();
            System.out.println("NULL KKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
        } else {

            while (rs.next()) {
                System.out.println("NOOOOT NULL EEEEEEEEEEEEEE");
                String SPnumber = rs.getString("SP_NBER");

                String SPquantity = rs.getString("SP_QUANTITY");

                int SP_num = Integer.parseInt(SPnumber);
                int SP_quantity = Integer.parseInt(SPquantity);

                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

            }

            rs.close();
            for (int i = 0; i < ListOFSP.size(); i++) {
                for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                    if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                        System.out.println(ListOFSP.get(i).getSP_Number() + "-----------" + ListOFSelectedSP.get(j).getSP_Number());
                        System.out.println("i==" + i + "j==" + j);
                        ListOFSP.remove(i);
                        System.out.println("Size==" + ListOFSP.size());
                        System.out.println("NOOOOT NULL OOOOOOOOOOOOOOOOOO");

                    }
                }

            }


            /*   try {
                while (rs.next()) {
                    System.out.println("?????????????????????????????????????????????");

                    String SPnumber = rs.getString("SP_NBER");

                    String SPquantity = rs.getString("SP_QUANTITY");

                    int SP_num = Integer.parseInt(SPnumber);
                    int SP_quantity = Integer.parseInt(SPquantity);
                    System.out.println("");
                    if (ListOFSP.isEmpty()) {
                        System.out.println("Equal 00000000000");
                        ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

                    } else {
                        for (int i = 0; i < ListOFSP.size(); i++) {
                            if (ListOFSP.get(i).getSP_Number().equals(SP_num)) {
                                System.out.println("How are youuuu!!!" + ListOFSP.get(i).getSP_Number() + "====" + SP_num);
                                rs.next();
                                SPnumber = rs.getString("SP_NBER");
                                 SP_num = Integer.parseInt(SPnumber);
i=1000;
                                
                            } else if (!ListOFSP.get(i).getSP_Number().equals(SP_num)) {

                                ListOFSP.add(new AddSP(SP_num, rs.getString("SP_NAME"), SP_quantity));

                                System.out.println("i am fine!!!");
                              
                            }
                        }
                    }
                }
                rs.close();

            } catch (SQLException ex) {
                ex.printStackTrace();

            }*/
            // ListOFSelectedSP.forEach(ListOFSP::remove);
            Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

        }
    }

    private void loadSpSelected() throws SQLException {
        ListOFSelectedSP.clear();
        System.out.println("i am in loadSpSelected ");
        String SQLqq = "SELECT * FROM `spare_parts` s JOIN `attach` r ON s.SP_NBER = r.SP_NBER WHERE r.REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

        System.out.println(SQLqq);
        ResultSet rs = connectionClass.execQuery(SQLqq);

        try {
            double a = 0.00;
            if (rs.isBeforeFirst()) {
                while (rs.next()) {

                    int SP_num = Integer.parseInt(rs.getString("SP_NBER"));
                    int SP_Quant = Integer.parseInt(rs.getString("Req_QUANTITY"));

                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, rs.getString("SP_NAME"), rs.getString("DESCRIPTION"), SP_Pri2, rs.getString("SERIAL_NUMBER"),SP_Seq));
                    // if (rs.getString("REQUEST_NBER").equals(Txfiled_REQnum_ReqSP.getText())) {
                    //loadlist.add(new Controller_AddMO.SelectedSP(SP_num, mid, mobile, SP_Pri, rs.getString("SERIAL_NUMBER")));
                    ListOFSelectedSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));                // }

                }
                //System.out.println(loadlist.get(0).getSP2_Name());
                Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
            }

            rs.close();

        } catch (SQLException ex) {
        }
    }

    void loadWindow(String loc, String title) {
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);

            stage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Btn_AddMO_MangeMO(ActionEvent event) {
        loadWindow("/sample/AddMo_AR.fxml", "");

    }

    @FXML
    private void M_Txfiled_MNum_Customer(ActionEvent event) {
    }

    @FXML
    private void M_Btn_ChangeMN_Customer(ActionEvent event) {
    }

    @FXML
    private void M_Btn_Cancle_Customer(ActionEvent event) {
        Txfiled_MNum_Customer.clear();
        Txfiled_Name_Customer.clear();
        Txfiled_Email_Customer.clear();
        Txfiled_Address_Customer.clear();

        Txfiled_MNum_Customer.setDisable(false);
    }

    @FXML
    private void Btn_Edit_MangeFinshedMO(ActionEvent event) throws SQLException {
        openEdit(Table_FinshedMO_MngMO);

    }

    @FXML
    private void Txfiled_Search_MangeFinshedMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangePreviousMO(ActionEvent event) throws SQLException {
        openEdit(Table_PreviousMO_MngMO);

    }

    @FXML
    private void Txfiled_Search_MangePreviousMO(ActionEvent event) {
    }

    public void openEdit(TableView TableName) throws SQLException {

        ObservableList<MO> SPSelected, AllSP;
        AllSP = TableName.getItems();
        SPSelected = TableName.getSelectionModel().getSelectedItems();

        System.out.println("Cus name   " + SPSelected.get(0).getCus_Name());
        System.out.println("MO_NUMBER=  " + SPSelected.get(0).getMO_Number());

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/AddMo_AR.fxml"));
        try {
            loader.load();

        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Connection connection = connectionClass.getConnection();

        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER  = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE MO_NBER = " + SPSelected.get(0).getMO_Number());

        ResultSet rs = st.getResultSet();

        if (rs.first()) {

            Controller_AddMO controller_AddMO = loader.getController();

            controller_AddMO.loadInTO(rs.getString("MO_NBER"), rs.getString("CUS_NAME"), rs.getString("PROBLEM_DESC"), rs.getString("CUS_MOBILE_NBER"), rs.getString("SP_COST"), rs.getString("MO_COST"),
                    rs.getString("DEVICE_SN"), rs.getString("DEVICE_DESC"), rs.getString("WARRANTY"), rs.getString("STARTING_DATE"), rs.getString("ENDING_DATE"), rs.getString("STATE"), rs.getString("EMP_NAME"));

        }

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.showAndWait();
        CurrnetList.clear();
        PendingList.clear();
        FinshedList.clear();
        PriveousList.clear();

        loadAllMO();
    }

    @FXML
    private void Btn_Edit_MangePendingMO(ActionEvent event) throws SQLException {
        openEdit(Table_pendingMO_MngMO);

    }

    @FXML
    private void Txfiled_Search_MangePendingMO(ActionEvent event) {
    }

    @FXML
    private void M_Btn_Delete_Customer(ActionEvent event) throws SQLException {

        String sql1 = "DELETE FROM  `customer`  WHERE CUS_MOBILE_NBER= " + Txfiled_MNum_Customer.getText();
        System.out.println(sql1);
        java.sql.Statement statement1 = connection.createStatement();

        clear();
        try {
            statement1.executeUpdate(sql1);

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Deleted Successfully");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" Cannot delete or update a parent row: a foreign key constraint fails (`mo_db`.`maintenance_operation`, CONSTRAINT `maintenance_operation_ibfk_2` FOREIGN KEY (`CUS_MOBILE_NBER`) REFERENCES `customer` (`CUS_MOBILE_NBER`) ON DELETE NO ACTION)");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void M_Btn_Save_Customer(ActionEvent event) throws SQLException {
        if (Txfiled_MNum_Customer.getText().isEmpty() || Txfiled_Name_Customer.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = ("INSERT INTO customer (CUS_MOBILE_NBER,CUS_NAME,CUS_EMAIL,CUS_ADDRESS) VALUES('" + Txfiled_MNum_Customer.getText() + "','" + Txfiled_Name_Customer.getText() + "','" + Txfiled_Email_Customer.getText() + "','" + Txfiled_Address_Customer.getText() + "')");
            //"INSERT INTO customer (CUS_MOBILE_NBER ,'CUS_NAME','CUS_EMAIL',' CUS_ADDRESS') VALUES ("+Txfiled_MNum_Customer.getText()+ ""+","+"" +   Txfiled_Name_Customer.getText() + "" + ","+"" +    Txfiled_Email_Customer.getText()+ "" + ","+"" +  Txfiled_Address_Customer.getText()+")"; 
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.prepareStatement(sqll);

            statement1.executeUpdate(sqll);

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `customer` SET CUS_NAME='" + Txfiled_Name_Customer.getText() + "',CUS_EMAIL='" + Txfiled_Email_Customer.getText() + "',CUS_ADDRESS='" + Txfiled_Address_Customer.getText()
                    + "' WHERE CUS_MOBILE_NBER= '" + Txfiled_MNum_Customer.getText() + "'";
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
        } else if (count == 3) {
            System.out.println("Equal  update mobile number");
            String sqll = "UPDATE customer SET CUS_MOBILE_NBER='" + Txfiled_MNum_Customer.getText() + "' WHERE CUS_NAME= '" + Txfiled_Name_Customer.getText() + "'";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqll);

        }
        //count = 2;

    }
    public int number = 0;

    @FXML
    private void M_Btn_Search_Customer(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `customer`  WHERE CUS_MOBILE_NBER = " + Txfiled_MNum_Customer.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_MNum_Customer.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("CUS_MOBILE_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_MNum_Customer.getText());

            if (rs.getString("CUS_MOBILE_NBER").equals(Txfiled_MNum_Customer.getText())) {

                count = 2;

                Txfiled_Name_Customer.setText(rs.getString("CUS_NAME"));
                Txfiled_Email_Customer.setText(rs.getString("CUS_EMAIL"));
                Txfiled_Address_Customer.setText(rs.getString("CUS_ADDRESS"));
                Txfiled_MNum_Customer.setDisable(true);
                Btn_Delete_Customer.setDisable(false);
                Btn_Cancle_Customer.setDisable(false);
                Btn_ChangeMN_Customer.setDisable(false);

            }
        } else {
            System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
            count = 1;
            Txfiled_MNum_Customer.setDisable(true);
            Txfiled_MNum_Customer.clear();
            Btn_Cancle_Customer.setDisable(false);
            Btn_Save_Customer.setDisable(false);
            Btn_Delete_Customer.setDisable(false);
            Btn_ChangeMN_Customer.setDisable(false);

        }

    }

    @FXML
    void M_Btn_Cancel_Employee(ActionEvent event) {
        Txfiled_Num_Employee.setDisable(false);
        Txfiled_Name_Employee.clear();
        Txfiled_Email_Employee.clear();
        Txfiled_Address_Employee.clear();
        Txfiled_MNum_Employee.clear();
        Selct_JType_Employee.getSelectionModel().clearSelection();
        Selct_Sex_Employee.getSelectionModel().clearSelection();
        Txfiled_Password_Employee.clear();

    }

    @FXML
    void M_Btn_Delete_Employee(ActionEvent event) throws SQLException {
        String sql1 = "DELETE FROM  `employee`  WHERE EMPLOYEE_ID= " + Txfiled_Num_Employee.getText();
        System.out.println(sql1);
        java.sql.Statement statement1 = connection.createStatement();

        Txfiled_Num_Employee.setDisable(false);
        Txfiled_Name_Employee.clear();
        Txfiled_Email_Employee.clear();
        Txfiled_Address_Employee.clear();
        Txfiled_MNum_Employee.clear();
        Selct_JType_Employee.getSelectionModel().clearSelection();
        Selct_Sex_Employee.getSelectionModel().clearSelection();
        Txfiled_Password_Employee.clear();
        try {
            statement1.executeUpdate(sql1);

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Deleted Successfully");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(" Cannot delete or update a parent row: a foreign key constraint fails (`mo_db`.`maintenance_operation`, CONSTRAINT `maintenance_operation_ibfk_1` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `employee` (`EMPLOYEE_ID`) ON DELETE NO ACTION)");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void M_Btn_Save_Employee(ActionEvent event) throws SQLException {

        if (Txfiled_Num_Employee.getText().isEmpty() || Txfiled_Name_Employee.getText().isEmpty() || Txfiled_Email_Employee.getText().isEmpty()
                || Txfiled_Address_Employee.getText().isEmpty() || Txfiled_MNum_Employee.getText().isEmpty() || Selct_JType_Employee.getValue().isEmpty() || Selct_Sex_Employee.getValue().isEmpty() || Txfiled_Password_Employee.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = "INSERT INTO employee (EMPLOYEE_ID, EMP_NAME, EMP_EMAIL,EMP_ADDRESS,EMP_MOBILE_NBER,JOP_TYPE,SEX,PASSWORD) VALUES (" + number + "," + "'" + Txfiled_Name_Employee.getText() + "'" + "," + "'" + Txfiled_Email_Employee.getText()
                    + "'" + "," + "'" + Txfiled_Address_Employee.getText() + "'" + "," + "'" + Txfiled_MNum_Employee.getText() + "'" + "," + "'" + Selct_JType_Employee.getValue() + "'" + "," + "'" + Selct_Sex_Employee.getValue()
                    + "'" + "," + "'" + Txfiled_Password_Employee.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.prepareStatement(sqll);

            statement1.executeUpdate(sqll);

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `employee` SET   EMP_NAME='" + Txfiled_Name_Employee.getText() + "',EMP_EMAIL='" + Txfiled_Email_Employee.getText() + "',EMP_ADDRESS='" + Txfiled_Address_Employee.getText() + "',EMP_MOBILE_NBER='" + Txfiled_MNum_Employee.getText()
                    + "',JOP_TYPE='" + Selct_JType_Employee.getValue() + "',SEX='" + Selct_Sex_Employee.getValue() + "',PASSWORD='" + Txfiled_Password_Employee.getText()
                    + " 'WHERE EMPLOYEE_ID=' " + Txfiled_Num_Employee.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
        }
        count = 2;

    }

    @FXML
    private void M_Btn_Search_Employee(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `employee`  WHERE EMPLOYEE_ID = " + Txfiled_Num_Employee.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_Num_Employee.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("EMPLOYEE_ID"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_Num_Employee.getText());

            if (rs.getString("EMPLOYEE_ID").equals(Txfiled_Num_Employee.getText())) {

                count = 2;

                Txfiled_Name_Employee.setText(rs.getString("EMP_NAME"));
                Txfiled_Email_Employee.setText(rs.getString("EMP_EMAIL"));
                Txfiled_Address_Employee.setText(rs.getString("EMP_ADDRESS"));
                Txfiled_MNum_Employee.setText(rs.getString("EMP_MOBILE_NBER"));
                Selct_JType_Employee.getSelectionModel().select(rs.getString("JOP_TYPE"));
                Selct_Sex_Employee.getSelectionModel().select(rs.getString("SEX"));
                Txfiled_Password_Employee.setText(rs.getString("PASSWORD"));

                Txfiled_Num_Employee.setDisable(true);
                Btn_Save_Employee.setDisable(false);
                Btn_Delete_Employee.setDisable(false);
                Btn_Cancel_Employee.setDisable(false);

            } else {

                Statement st2 = connection.createStatement();
                st2.executeQuery("SELECT * FROM employee ORDER BY EMPLOYEE_ID DESC LIMIT 1");
                ResultSet rs2 = st2.getResultSet();
                //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
                if (rs2.first()) {

                    System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                    //System.out.println();

                    count = 1;
                    number = Integer.parseInt(rs2.getString("EMPLOYEE_ID"));
                    number++;
                    System.out.println(number);
                    Txfiled_Num_Employee.setText(String.valueOf(number));
                    Txfiled_Num_Employee.setDisable(true);
                    Btn_Save_Employee.setDisable(false);
                    Btn_Delete_Employee.setDisable(false);
                    Btn_Cancel_Employee.setDisable(false);

                }
            }
        }

    }

    @FXML
    private void M_Txfiled_Name_SP(ActionEvent event) {
    }

    @FXML
    void M_Btn_Search_SP(ActionEvent event) throws SQLException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `spare_parts`  WHERE SP_NBER = " + Txfiled_SPNum_SP.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_SPNum_SP.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("SP_NBER"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_SPNum_SP.getText());

            if (rs.getString("SP_NBER").equals(Txfiled_SPNum_SP.getText())) {

                count = 2;

                Txfiled_Name_SP.setText(rs.getString("SP_NAME"));
                Txfiled_Price_SP.setText(rs.getString("PRICE"));
                Txfiled_Quantity_SP.setText(rs.getString("SP_QUANTITY"));
                Txfiled_Discription_SP.setText(rs.getString("DESCRIPTION"));

                Txfiled_SPNum_SP.setDisable(true);
                Btn_Save_SP.setDisable(false);
                Btn_Delete_SP.setDisable(false);
                Btn_Cancle_SP.setDisable(false);

            }
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM spare_parts ORDER BY SP_NBER DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();

                count = 1;
                number = Integer.parseInt(rs2.getString("SP_NBER"));
                number++;
                System.out.println(number);
                Txfiled_SPNum_SP.setText(String.valueOf(number));
                Txfiled_SPNum_SP.setDisable(true);
                Btn_Save_SP.setDisable(false);
                Btn_Delete_SP.setDisable(false);
                Btn_Cancle_SP.setDisable(false);

            }
        }
    }

    @FXML
    private void M_Btn_Cancle_SP(ActionEvent event) {
        Txfiled_SPNum_SP.setDisable(false);
        Txfiled_Name_SP.clear();
        Txfiled_Price_SP.clear();
        Txfiled_Quantity_SP.clear();
        Txfiled_Discription_SP.clear();
    }

    @FXML
    private void M_Btn_Delete_SP(ActionEvent event) throws SQLException {
        String sql1 = "DELETE FROM  `spare_parts`  WHERE SP_NBER= " + Txfiled_SPNum_SP.getText();
        System.out.println(sql1);
        java.sql.Statement statement1 = connection.createStatement();

        Txfiled_SPNum_SP.setDisable(false);
        Txfiled_Name_SP.clear();
        Txfiled_Price_SP.clear();
        Txfiled_Quantity_SP.clear();
        Txfiled_Discription_SP.clear();
        try {
            statement1.executeUpdate(sql1);

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Deleted Successfully");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Cannot delete or update a parent row: a foreign key constraint fails (`mo_db`.`require`, CONSTRAINT `require_ibfk_2` FOREIGN KEY (`SP_NBER`) REFERENCES `spare_parts` (`SP_NBER`)) or Cannot delete or update a parent row: a foreign key constraint fails (`mo_db`.`attach`, CONSTRAINT `attach_ibfk_1` FOREIGN KEY (`SP_NBER`) REFERENCES `spare_parts` (`SP_NBER`))");
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void M_Btn_Save_SP(ActionEvent event) throws SQLException {
        if (Txfiled_SPNum_SP.getText().isEmpty() || Txfiled_Name_SP.getText().isEmpty() || Txfiled_Price_SP.getText().isEmpty()
                || Txfiled_Quantity_SP.getText().isEmpty() || Txfiled_Discription_SP.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = "INSERT INTO spare_parts (SP_NBER, SP_NAME, PRICE,SP_QUANTITY ,DESCRIPTION) VALUES (" + number + "," + "'" + Txfiled_Name_SP.getText() + "'" + "," + "'" + Txfiled_Price_SP.getText()
                    + "'" + "," + "'" + Txfiled_Quantity_SP.getText() + "'" + "," + "'" + Txfiled_Discription_SP.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.prepareStatement(sqll);

            statement1.executeUpdate(sqll);

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `spare_parts` SET  SP_NAME='" + Txfiled_Name_SP.getText() + "',PRICE='" + Txfiled_Price_SP.getText() + "',SP_QUANTITY='" + Txfiled_Quantity_SP.getText() + "',DESCRIPTION='" + Txfiled_Discription_SP.getText()
                    + " 'WHERE SP_NBER=' " + Txfiled_SPNum_SP.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
        }
        // count = 2;

    }

    @FXML
    private void M_Btn_Save_Supplier(ActionEvent event) throws SQLException {
        if (Txfiled_Num_Supplier.getText().isEmpty() || Txfiled_MNum_Supplier.getText().isEmpty() || Txfiled_Email_Supplier.getText().isEmpty()
                || Txfiled_Name_Supplier.getText().isEmpty() || Txfiled_Address_Supplier.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }

        if (count == 1) {
            System.out.println("Equal  insert");
            String sqll = "INSERT INTO supplier (SUPPLIER_NBER, SUP_MOBILE_NBER, SUP_EMAIL,SUP_NAME ,SUP_ADDRESS) VALUES (" + number + "," + "'" + Txfiled_MNum_Supplier.getText() + "'" + "," + "'" + Txfiled_Email_Supplier.getText()
                    + "'" + "," + "'" + Txfiled_Name_Supplier.getText() + "'" + "," + "'" + Txfiled_Address_Supplier.getText() + "')";
            System.out.println(sqll);
            java.sql.Statement statement1 = connection.prepareStatement(sqll);

            statement1.executeUpdate(sqll);

        } else if (count == 2) {
            System.out.println("Equal  update");
            //System.out.println(Selct_MoStatus_AddMO.getValue());
            String sql1 = "UPDATE  `supplier` SET  SUP_MOBILE_NBER='" + Txfiled_MNum_Supplier.getText() + "',SUP_EMAIL='" + Txfiled_Email_Supplier.getText() + "',SUP_NAME='" + Txfiled_Name_Supplier.getText() + "',SUP_ADDRESS='" + Txfiled_Address_Supplier.getText()
                    + "' WHERE SUPPLIER_NBER= '" + Txfiled_Num_Supplier.getText() + "'";

            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sql1);
        }
        count = 2;

    }

    @FXML
    private void M_Btn_Search_Supplier(ActionEvent event) throws SQLException, ParseException {

        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `supplier`  WHERE SUPPLIER_NBER = " + Txfiled_Num_Supplier.getText());
        ResultSet rs = st.getResultSet();
        if (rs.first()) {

            System.out.println(Txfiled_Num_Supplier.getText());

            System.out.println("THIS NU NUMBER IN DB== " + rs.getString("SUPPLIER_NBER"));
            System.out.println("THIS NU NUMBER IN FILED== " + Txfiled_Num_Supplier.getText());

            if (rs.getString("SUPPLIER_NBER").equals(Txfiled_Num_Supplier.getText())) {

                count = 2;

                Txfiled_MNum_Supplier.setText(rs.getString("SUP_MOBILE_NBER"));
                Txfiled_Email_Supplier.setText(rs.getString("SUP_EMAIL"));
                Txfiled_Name_Supplier.setText(rs.getString("SUP_NAME"));
                Txfiled_Address_Supplier.setText(rs.getString("SUP_ADDRESS"));

                Txfiled_Num_Supplier.setDisable(true);
                Btn_Save_Supplier.setDisable(false);
                Btn_Delete_Supplier.setDisable(false);
                Btn_Cancle_Supplier.setDisable(false);

            }
        } else {
            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM supplier ORDER BY SUPPLIER_NBER DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {

                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();

                count = 1;
                number = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));
                number++;
                System.out.println(number);
                Txfiled_Num_Supplier.setText(String.valueOf(number));
                Txfiled_Num_Supplier.setDisable(true);
                Btn_Save_Supplier.setDisable(false);
                Btn_Delete_Supplier.setDisable(false);
                Btn_Cancle_Supplier.setDisable(false);

            }
        }
    }

    @FXML
    private void M_Btn_Delete_Supplier(ActionEvent event) throws SQLException {
        String sql1 = "DELETE FROM  `supplier`  WHERE SUPPLIER_NBER= " + Txfiled_Num_Supplier.getText();
        System.out.println(sql1);
        java.sql.Statement statement1 = connection.createStatement();

        clearSUP();
        try {
            statement1.executeUpdate(sql1);

            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Deleted Successfully");
                alert.showAndWait();
                return;
            }
        } catch (SQLException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            return;
        }

    }

    @FXML
    private void M_Btn_Cancle_Supplier(ActionEvent event) {

        clearSUP();
    }

    public void clearSUP() {
        Txfiled_Num_Supplier.setDisable(false);
        Txfiled_Num_Supplier.clear();
        Txfiled_MNum_Supplier.clear();
        Txfiled_Email_Supplier.clear();
        Txfiled_Name_Supplier.clear();
        Txfiled_Address_Supplier.clear();

    }

    @FXML
    private void prbuttonReports(ActionEvent event) {
    }

    @FXML
    public void Mangment_MO_tab_selected(Event event) {

        MainLable.setText("ادارة عمليات الصيانة");

    }

    @FXML
    public void Mangment_Customer_tab_selected(Event event) {
        MainLable.setText("ادارة العملاء");

    }

    @FXML
    public void Mangment_supliers_tab_selected(Event event) {
        MainLable.setText("ادارة المزودين");
    }

    @FXML
    public void Mangment_Staff_tab_selected(Event event) {
        MainLable.setText("ادارة الموظفين");
    }

    @FXML
    public void RequstSpearPart_tab_selected(Event event) {
        MainLable.setText("طلب قطع غيار");
    }

    @FXML
    public void Mangment_SpearParts_tab_selected(Event event) {
        MainLable.setText("ادارة قطع الغيار");
    }

    @FXML
    public void Mangment_Reports_tab_selected(Event event) {
        MainLable.setText("ادارة التقارير");
    }

    @FXML
    private void Main_Tab(Event event) {
        MainLable.setText(" ");
    }

    private void Mangment_Tools_tab_selected(Event event) {
        MainLable.setText("الأدوات");

    }
    ObservableList<AddSP> SPSelected2, AllSP2;

    @FXML
    private void M_Btn_RemoveSP_ReqSP(ActionEvent event) throws SQLException {

        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        //Txfiled_SpSerialN_AddMO.setText(SPSelected2.get(0).getSP_SN());
        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected2.size(); i++) {
            //for(int i =1;i<=1; i++){
            //list.clear();
            // list.add(new Controller_AddMO.AddSP(SPSelected2.get(i).getSP2_Number(), SPSelected2.get(i).getSP2_Name(), SPSelected2.get(i).getSP2_Description(), 
            //SPSelected2.get(i).getSP_Price2()));
            // Table_AddSP_AddMO.setItems(list);
            String sqlDeletSP = "DELETE FROM `attach` " + " WHERE SP_NBER= " + SPSelected2.get(i).getSP_Number() + " AND REQUEST_NBER=" + Txfiled_REQnum_ReqSP.getText();

            System.out.println(sqlDeletSP);
            //SPSelected2.get(i).ge

            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlDeletSP);

        }
        //}
        SPSelected2.forEach(ListOFSelectedSP::remove);
        Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
        loadSpecifecSP();

        //calculate();
        //DELETE FROM `require` WHERE `require`.`MO_NBER` = 7 AND `require`.`SP_NBER` = 3;
        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
    }

    @FXML
    private void M_Btn_AddSP_ReqSP(ActionEvent event) throws SQLException {
        ObservableList<AddSP> SPSelected, AllSP;
        AllSP = Table_AddSP_ReqSP.getItems();
        SPSelected = Table_AddSP_ReqSP.getSelectionModel().getSelectedItems();
        // ObservableList<Controller_AddMO.SelectedSP> SPSelected3, AllSP3;
        //AllSP3 = Table_SelectedSP_AddMO.getItems();
        ///SPSelected3 = Table_SelectedSP_AddMO.getSelectionModel().getSelectedItems();

        //_____________________
        //for (AddSP addSP : Table_AddSP_AddMO.getSelectionModel().getSelectedItems()) {
        for (int i = 0; i < SPSelected.size(); i++) {
            System.out.println(SPSelected.size());
            //for(int i =1;i<=1; i++){

            ListOFSelectedSP.add(new AddSP(SPSelected.get(i).getSP_Number(), SPSelected.get(i).getSP_Name(), 1));
            //AllSP3.add()

            String sql1 = "INSERT INTO `attach` VALUES(" + SPSelected.get(0).getSP_Number() + ",'" + Txfiled_REQnum_ReqSP.getText() + "','"
                    + 1 + "')";
            java.sql.Statement statement1 = connection.createStatement();
            System.out.println(sql1);
            statement1.executeUpdate(sql1);
        }
        //}
        Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);

        // Txfiled_SPCost_AddMO.setText(String.valueOf(spcost));
        //SPSelected.forEach(AllSP::remove);
        //System.out.println(SPSelected);
        //list.add(new Controller_AddMO.AddSP(aa, mid, mobile));
        //list2.add(SPSelected.get(1));
        //System.out.println(SPSelected.get(0).SP_Description);
        //Table_AddSP_AddMO.getItems().setAll(list);
        //Table_SelectedSP_AddMO.getItems().setAll(list2);
        SPSelected.forEach(AllSP::remove);
    }

    @FXML
    private void M_Btn_Print_ReqSP(ActionEvent event) {
    }

    @FXML
    private void M_Btn_Cancle_ReqSP(ActionEvent event) {
        clear();
    }

    @FXML
    private void M_Btn_Delete_ReqSP(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            String deletSP = "DELETE FROM  `attach` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();
            String sql1 = "DELETE FROM  `requested_spare_parts` " + " WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText();
            System.out.println(deletSP);
            System.out.println(sql1);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(deletSP);
            statement1.executeUpdate(sql1);
            clear();
        } else {

        }
    }

    @FXML
    private void M_Btn_Save_ReqSP(ActionEvent event) throws SQLException {

        if (Txfiled_REQnum_ReqSP.getText().isEmpty() || Date_REQdate_ReqSP.getValue() == null || Selct_Supplier_ReqSP.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
        Statement st2 = connection.createStatement();
        st2.executeQuery("SELECT * FROM `supplier`");
        ResultSet rs2 = st2.getResultSet();
        //SELECT * FROM `employee` 
        int IndexOFTech = 0;
        for (int i = 0; i < ListOfSuppliers.size(); i++) {

            while (rs2.next()) {

                //ListOfTechichan.add(rs2.getString("EMP_NAME"));
                //البحث ب رقم العميل من الداتابيس
                if (Selct_Supplier_ReqSP.getValue().equals(rs2.getString("SUP_NAME"))) {

                    IndexOFTech = Integer.parseInt(rs2.getString("SUPPLIER_NBER"));

                }
            }
        }//IndexOFTech++;

        System.out.println("INDEX== " + IndexOFTech);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Look, a Confirmation Dialog");
        alert.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            if (count == 1) {
                System.out.println("Equal  insert");

                String sql1 = "INSERT INTO `requested_spare_parts` VALUES(" + monumber + "," + "'" + Date_REQdate_ReqSP.getValue() + "'" + "," + "'" + IndexOFTech + "')";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);

            } else if (count == 2) {
                System.out.println("Equal  update");
                //System.out.println(Selct_MoStatus_AddMO.getValue());
                String sql1 = "UPDATE  `requested_spare_parts` SET REQUEST_DATE='" + Date_REQdate_ReqSP.getValue() + "',SUPPLIER_NBER='" + IndexOFTech
                        + "' WHERE REQUEST_NBER= '" + Txfiled_REQnum_ReqSP.getText() + "'";
                System.out.println(sql1);
                java.sql.Statement statement1 = connection.createStatement();
                statement1.executeUpdate(sql1);
                //System.out.println(Selct_MoStatus_AddMO.getValue().equalsIgnoreCase(sql1));

            }
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Information Dialog");
            alert2.setHeaderText(null);
            alert2.setContentText("I have a great message for you!");

            alert2.showAndWait();
            clear();
            count = 2;

        } else {

        }
    }

    public void clear() {

        Txfiled_REQnum_ReqSP.setDisable(false);
        Txfiled_REQnum_ReqSP.clear();
        Selct_Supplier_ReqSP.getSelectionModel().clearSelection();
        Date_REQdate_ReqSP.setValue(null);
        Table_SelectedSP_ReqSP.getItems().clear();
        Table_AddSP_ReqSP.getItems().clear();
        ListOFSelectedSP.clear();
        ListOFSP.clear();
        Txfiled_QuanitiySP_ReqSP.setText("الكمية");
        Txfiled_SearchSP_ReqSP.setText("بحث");

    }

    public void loadSuppliers() {
        String query = "SELECT SUP_NAME FROM supplier";
        ResultSet rs = connectionClass.execQuery(query);
        try {
            while (rs.next()) {

                ListOfSuppliers.add(rs.getString("SUP_NAME"));

            }
            rs.close();

        } catch (SQLException ex) {
            ex.printStackTrace();

        }
        Selct_Supplier_ReqSP.setItems(ListOfSuppliers);

    }

    public int monumber = 0;

    @FXML
    private void M_Btn_Search_ReqSP(ActionEvent event) throws SQLException {
        if (Txfiled_REQnum_ReqSP.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Pleas enter the value");
            alert.showAndWait();
            return;

        }
        Connection connection = connectionClass.getConnection();
        Statement st = connection.createStatement();
        st.executeQuery("SELECT * FROM `requested_spare_parts` r JOIN `supplier` s ON r.SUPPLIER_NBER  = s.SUPPLIER_NBER  WHERE REQUEST_NBER = " + Txfiled_REQnum_ReqSP.getText());
//REQUEST_NBER
        ResultSet rs = st.getResultSet();
        //st = connection.prepareCall(sql);

        if (rs.first()) {

            System.out.println(Txfiled_REQnum_ReqSP.getText());

            System.out.println("THIS MO NUMBER IN DB== " + rs.getString("REQUEST_NBER"));
            System.out.println("THIS MO NUMBER IN FILED== " + Txfiled_REQnum_ReqSP.getText());

            if (rs.getString("REQUEST_NBER").equals(Txfiled_REQnum_ReqSP.getText())) {

                count = 2;

                Txfiled_REQnum_ReqSP.setDisable(true);

                LocalDate REQUEST_DATE = LocalDate.parse(rs.getString("REQUEST_DATE"));

                Date_REQdate_ReqSP.setValue(REQUEST_DATE);

                System.out.println("PPPPPPPPPPPPPP " + rs.getString("SUP_NAME"));
                Selct_Supplier_ReqSP.getSelectionModel().select(rs.getString("SUP_NAME"));

                Btn_Delete_ReqSP.setDisable(false);
                Btn_Save_ReqSP.setDisable(false);
                Btn_Print_ReqSP.setDisable(false);
                Btn_Delete_ReqSP.setDisable(false);

                Btn_Cancle_ReqSP.setDisable(false);
                Btn_AddSP_ReqSP.setDisable(false);
                Btn_RemoveSP_ReqSP.setDisable(false);
                //loadlist.clear();
                loadSpSelected();
                loadSpecifecSP();

                //calculate();
            }

            //java.sql.Statement statement1 = connection.createStatement();
            //statement1.executeQuery(sql);
        } else {

            Statement st2 = connection.createStatement();
            st2.executeQuery("SELECT * FROM `requested_spare_parts` ORDER BY `REQUEST_NBER` DESC LIMIT 1");
            ResultSet rs2 = st2.getResultSet();
            //System.out.println("FFFFFFFFFFFFFFFFF"+rs2.getString("MO_NBER"));
            if (rs2.first()) {
                System.out.println("DDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
                //System.out.println();
                count = 1;
                monumber = Integer.parseInt(rs2.getString("REQUEST_NBER"));
                monumber++;
                System.out.println(monumber);
                Txfiled_REQnum_ReqSP.setText(String.valueOf(monumber));
                Txfiled_REQnum_ReqSP.setDisable(true);
                //Txfiled_MOnum_AddMO.clear();
                Btn_Delete_ReqSP.setDisable(true);
                Btn_Cancle_ReqSP.setDisable(false);
                Btn_Save_ReqSP.setDisable(false);
                Btn_Print_ReqSP.setDisable(false);
            }
        }
    }//}

    @FXML
    private void M_MousClicked_TabelSelecSP_ReqSP(MouseEvent event) {
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();

        Txfiled_QuanitiySP_ReqSP.setText(String.valueOf(SPSelected2.get(0).getSP_Quantity()));

    }

    @FXML
    private void M_Txfiled_QuanitiySP_ReqSP(ActionEvent event) throws SQLException {
        AllSP2 = Table_SelectedSP_ReqSP.getItems();
        SPSelected2 = Table_SelectedSP_ReqSP.getSelectionModel().getSelectedItems();
        //  SPSelected2.get(0).SP_SNProperty("");
        //Txfiled_SpSerialN_AddMO.getText();
        int perSp_Quant = Integer.parseInt(Txfiled_QuanitiySP_ReqSP.getText());

        for (int i = 0; i < SPSelected2.size(); i++) {
            ListOFSelectedSP.add(new AddSP(SPSelected2.get(i).getSP_Number(), SPSelected2.get(i).getSP_Name(), perSp_Quant));                // }

            String sqlupdateAddSP = "UPDATE `attach` SET `Req_QUANTITY` = '" + Txfiled_QuanitiySP_ReqSP.getText() + "' WHERE REQUEST_NBER= " + Txfiled_REQnum_ReqSP.getText()
                    + " AND SP_NBER=" + SPSelected2.get(i).getSP_Number();
            System.out.println(sqlupdateAddSP);
            java.sql.Statement statement1 = connection.createStatement();
            statement1.executeUpdate(sqlupdateAddSP);

            SPSelected2.forEach(ListOFSelectedSP::remove);
            Table_SelectedSP_ReqSP.getItems().setAll(ListOFSelectedSP);
            //calculate();
            Txfiled_QuanitiySP_ReqSP.setText("الكمية");
        }
    }
    String id2 = "";

    @FXML
    private void M_Txfiled_SearchSP_ReqSP(KeyEvent event) throws SQLException {
        Choose = 2;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());
        ListOFSP.clear();
        id2 += event.getText();
        if (event.getText().equals("")) {
            id2 = id2.substring(0, id2.length() - 1);

        }

        String id1 = Txfiled_SearchSP_ReqSP.getText();
        System.out.println("__________  " + id2);
        System.out.println("__________  " + id1);
        System.out.println("__________  " + Txfiled_SearchSP_ReqSP.getText());

        if (id2.isEmpty()) {
            loadSpecifecSP();

        } else {
            String sql1 = "SELECT * FROM spare_parts WHERE SP_NAME = '" + id2 + "'";
            String trysql = "SELECT * FROM spare_parts WHERE SP_NAME LIKE '" + id2 + "%';";
            System.out.println(trysql);
            Search(trysql, Choose);

        }
    }
 String id3 = "";
    @FXML
    private void M_Txfiled_Search_MangeCurrentMO(KeyEvent event) throws SQLException {
          Choose = 3;
        System.out.println(event.getEventType().toString());
        System.out.println(event.getText());

        id3 += event.getText();
        if (event.getText().equals("")) {
            id3 = id3.substring(0, id3.length() - 1);

        }

        System.out.println("__________  " + id3);

        String sql1 = "SELECT * FROM `maintenance_operation` WHERE `MO_NBER` = '" + id3 + "'";
        String trysql = "SELECT * FROM `maintenance_operation` m JOIN `customer` r ON m.CUS_MOBILE_NBER = r.CUS_MOBILE_NBER JOIN employee e ON m.EMPLOYEE_ID = e.EMPLOYEE_ID WHERE `MO_NBER` LIKE '" + id3 + "%' AND STATE ='approve' OR STATE ='under maintenance' OR `CUS_NAME` LIKE '" + id3 + "%' AND STATE ='approve' OR STATE ='under maintenance';";
        System.out.println(trysql);
        Search(trysql, Choose);
        


    }

    public static class AddSP {

        private final SimpleIntegerProperty SP_Number;
        private final SimpleStringProperty SP_Name;
        private final SimpleIntegerProperty SP_Quantity;

        AddSP(int SP_Number, String SP_Name, int SP_Quantity) {
            this.SP_Number = new SimpleIntegerProperty(SP_Number);
            this.SP_Name = new SimpleStringProperty(SP_Name);
            this.SP_Quantity = new SimpleIntegerProperty(SP_Quantity);

        }

        public Integer getSP_Number() {
            return SP_Number.get();
        }

        public String getSP_Name() {
            return SP_Name.get();
        }

        public Integer getSP_Quantity() {
            return SP_Quantity.get();
        }

    }

    public static class MO {

        private final SimpleIntegerProperty MO_Number;
        private final SimpleStringProperty Cus_Name;
        private final SimpleIntegerProperty Cus_Mobile;
        private final SimpleStringProperty MO_technician;
        private final SimpleStringProperty MO_EndDate;
        private final SimpleDoubleProperty MO_TotalCost;
        private final SimpleStringProperty MO_Status;

        MO(Integer MO_Number, String Cus_Name, Integer Cus_Mobile, String MO_technician, String MO_EndDate, double MO_TotalCost, String MO_Status) {
            this.MO_Number = new SimpleIntegerProperty(MO_Number);
            this.Cus_Name = new SimpleStringProperty(Cus_Name);
            this.Cus_Mobile = new SimpleIntegerProperty(Cus_Mobile);
            this.MO_technician = new SimpleStringProperty(MO_technician);
            this.MO_EndDate = new SimpleStringProperty(MO_EndDate);
            this.MO_TotalCost = new SimpleDoubleProperty(MO_TotalCost);
            this.MO_Status = new SimpleStringProperty(MO_Status);

        }

        public Integer getMO_Number() {
            return MO_Number.get();
        }

        public String getCus_Name() {
            return Cus_Name.get();
        }

        public Integer getCus_Mobile() {
            return Cus_Mobile.get();
        }

        public String getMO_technician() {
            return MO_technician.get();
        }

        public String getMO_EndDate() {
            return MO_EndDate.get();
        }

        public Double getMO_TotalCost() {
            return MO_TotalCost.get();
        }

        public String getMO_Status() {
            return MO_Status.get();
        }
    }

    @FXML
    private void M_Btn_Edit_MangeCurrentMO(ActionEvent event) throws SQLException {

        openEdit(Table_CurrentMO_MngMO);
    }
    int Choose = 0;

    public void Search(String Search, int Choose) throws SQLException {
        if (Choose == 2) {
            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {
                    String mname = rs.getString("SP_NBER");
                    String mid = rs.getString("SP_NAME");
                    String mobile = rs.getString("DESCRIPTION");
                    String price = rs.getString("PRICE");

                    int SP_num = Integer.parseInt(mname);
                    int SP_Quant = Integer.parseInt(rs.getString("SP_QUANTITY"));

                    ListOFSP.add(new AddSP(SP_num, rs.getString("SP_Name"), SP_Quant));
                }

                for (int i = 0; i < ListOFSP.size(); i++) {
                    for (int j = 0; j < ListOFSelectedSP.size(); j++) {
                        if (ListOFSP.get(i).getSP_Number().equals(ListOFSelectedSP.get(j).getSP_Number())) {
                            System.out.println(ListOFSP.get(i).getSP_Number() + "-----------" + ListOFSelectedSP.get(j).getSP_Number());
                            System.out.println("i==" + i + "j==" + j);
                            ListOFSP.remove(i);
                            System.out.println("Size==" + ListOFSP.size());
                            System.out.println("NOOOOT NULL OOOOOOOOOOOOOOOOOO");

                        }

                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_AddSP_ReqSP.getItems().setAll(ListOFSP);

        } else if (Choose == 1) {

            ResultSet rs = connectionClass.execQuery(Search);
            try {
                while (rs.next()) {

                    //Txfiled_CusName_AddMO.setText(rs.getString("CUS_NAME"));
                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }

        } else if (Choose == 3) {

            ResultSet rs = connectionClass.execQuery(Search);
            CurrnetList.clear();
            try {
                while (rs.next()) {
                    

                    String MONber = rs.getString("MO_NBER");
                    String mobile = rs.getString("CUS_MOBILE_NBER");
                    String priceSP = rs.getString("SP_COST");
                    String priceMO = rs.getString("MO_COST");
                    int MO_num = Integer.parseInt(MONber);
                    int CusMobile = Integer.parseInt(mobile);
                    double TotalCost = Double.parseDouble(priceSP) + Double.parseDouble(priceMO);
                    CurrnetList.add(new MO(MO_num, rs.getString("CUS_NAME"), CusMobile, rs.getString("EMP_NAME"), rs.getString("ENDING_DATE"), TotalCost, rs.getString("STATE")));

                }

            } catch (SQLException ex) {
                ex.printStackTrace();

            }
            Table_CurrentMO_MngMO.getItems().setAll(CurrnetList);

        }

    }
}

/*
package sample;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    public Tab Mangment_MO_Tab;
    public Label MainLable;

    public TreeTableView TableViewReports;
    public TreeTableColumn Table_ColomnViewReports;
   public Label RepLable;
    public Tab Main_Tab;
    public ImageView icMaonMove;
    public AnchorPane kk;
    public JFXButton prbutton;
    public ListView listv;
    public JFXButton ShowMoWindowbuttn;



    @FXML
    private Label MainLable1;
    @FXML
    private ToggleGroup ReportsDate;


    @FXML
    public void Mangment_MO_tab_selected(Event event) {

      //  MainLable.setText("ادارة عمليات الصيانة");

    }

    @FXML
    public void Mangment_Customer_tab_selected(Event event) {
//        MainLable.setText("ادارة العملاء");

    }

    @FXML
    public void Mangment_supliers_tab_selected(Event event) {
  //      MainLable.setText("ادارة المزودين");
    }

    @FXML
    public void Mangment_Staff_tab_selected(Event event) {
    //    MainLable.setText("ادارة الموظفين");
    }

    @FXML
    public void RequstSpearPart_tab_selected(Event event) {
    //    MainLable.setText("طلب قطع غيار");
    }

    @FXML
    public void Mangment_SpearParts_tab_selected(Event event) {
        //MainLable.setText("ادارة قطع الغيار");
    }

    @FXML
    public void Mangment_Reports_tab_selected(Event event) {
        //MainLable.setText("ادارة التقارير");
    }



    @FXML
    public void Main_Tab(Event event) {
       
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listv.getItems().add("- عمليات الصيانة الحالية");
        listv.getItems().add("- عمليات الصيانة المنتهية");
        listv.getItems().add("- عمليات الصيانة السابقة");
        listv.getItems().add("- تقدير مالي عن عملية صيانة");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالعملاء");
        listv.getItems().add("- قائمة عمليات الصيانة لعميل");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالموظفين");
        listv.getItems().add("- قائمة عمليات الصيانة لموظف");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قائمة بالمزودين");
        listv.getItems().add("---------------------------");
        listv.getItems().add("- قطع على وشك النفاذ");
        listv.getItems().add("- قطع الغيار التي نفذت كميتها");


       


    }

    @FXML
    public void prbuttonReports(ActionEvent actionEvent) {
        //ReportTable.getItems().setAll(studentsModels1);
        //ReportTable.setItems(studentsModels1);
    }

    @FXML
    public void ShowMoWindow(ActionEvent actionEvent) {
        loadWindow("/sample/AddMoNeww.fxml" ,"" );
    }


    void loadWindow(String loc , String title){
        try {

            Parent parent = FXMLLoader.load(getClass().getResource(loc));
            Stage stage = new Stage(StageStyle.DECORATED);
            stage.setScene(new Scene(parent));
            stage.setTitle(title);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }}

    @FXML
    private void Btn_ChangeMN_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Cancle_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Delete_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Save_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Search_Customer(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangeCurrentMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangeCurrentMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangeFinshedMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangeFinshedMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangePreviousMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangePreviousMO(ActionEvent event) {
    }

    @FXML
    private void Btn_Edit_MangePendingMO(ActionEvent event) {
    }

    @FXML
    private void Txfiled_Search_MangePendingMO(ActionEvent event) {
    }


    class StudentsModel {

//  private SimpleStringProperty Name;
  private  SimpleStringProperty Name;



        public StudentsModel( String reports) {
   this.Name = new SimpleStringProperty(reports);

  }

  public String getName() {
   return Name.get();
  }

  public void setName(String reportTableCol) {
   this.Name = new SimpleStringProperty(reportTableCol);
  }

 }
}
 */
