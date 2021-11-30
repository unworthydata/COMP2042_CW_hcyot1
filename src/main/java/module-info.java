module com.game.comp2042_cw_hcyot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.game.comp2042_cw_hcyot1 to javafx.fxml;
    exports com.game.comp2042_cw_hcyot1;
}