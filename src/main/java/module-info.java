module com.game.comp2042_cw_hcyot1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.jfree.fxgraphics2d;
    requires javafx.swing;

    exports com.game.comp2042_cw_hcyot1;
    exports com.game.comp2042_cw_hcyot1.ball;
    opens com.game.comp2042_cw_hcyot1.ball to javafx.fxml;
    exports com.game.comp2042_cw_hcyot1.brick;
    opens com.game.comp2042_cw_hcyot1.brick to javafx.fxml;
    exports com.game.comp2042_cw_hcyot1.debug;
    opens com.game.comp2042_cw_hcyot1.debug to javafx.fxml;

    exports com.game.comp2042_cw_hcyot1.fxMenus;
    opens com.game.comp2042_cw_hcyot1.fxMenus to javafx.fxml;
}