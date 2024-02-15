module org.example.actividadfinal2t10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.actividadfinal2t10 to javafx.fxml;
    exports org.example.actividadfinal2t10;
}