module com.game.comp2042_cw_hcyot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;
    requires commons.csv;

    exports com.game.comp2042_cw_hcyot1;
    opens com.game.comp2042_cw_hcyot1 to javafx.fxml;

    exports com.game.comp2042_cw_hcyot1.ball;
    opens com.game.comp2042_cw_hcyot1.ball to javafx.fxml;

    exports com.game.comp2042_cw_hcyot1.brick;
    opens com.game.comp2042_cw_hcyot1.brick to javafx.fxml;

    exports com.game.comp2042_cw_hcyot1.game;
    opens com.game.comp2042_cw_hcyot1.game to javafx.fxml;
}