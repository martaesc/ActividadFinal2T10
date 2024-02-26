package org.example.actividadfinal2t10;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;

import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class Inventario {

    @FXML
    private Button btnAlta;

    @FXML
    private Button btnImprimir;

    @FXML
    private Button btnModificar;

    @FXML
    private Button btnOrdenar;

    @FXML
    private Button btnBaja;

    @FXML
    private ListView<Dispositivo> listaDispositivos;

    @FXML
    TextField txtId;

    @FXML
    TextField txtMarca;

    @FXML
    TextField txtModelo;

    @FXML
    DatePicker fechaAlta;

    @FXML
    ChoiceBox<TipoAtributo> choiceTipo;

    private List<Dispositivo> dispositivos;

    private int ordenacion;

    private String rutaImpresion;

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }

    public Inventario() {
        this.dispositivos = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        TipoAtributo[] tipos = TipoAtributo.values();
        ObservableList<TipoAtributo> tiposList = FXCollections.observableArrayList(tipos);
        choiceTipo.setItems(tiposList);

        actualizarListaDispositivos();
    }

  public ObservableList<Dispositivo> getDispositivosParaMostrar() {
    return FXCollections.observableArrayList(dispositivos);
}

public void actualizarListaDispositivos() {
    listaDispositivos.setItems(getDispositivosParaMostrar());
}

@FXML
public void btnOrdenar() {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Ordenar dispositivos por fecha de compra");
    alert.setHeaderText("Seleccione el orden de clasificación");
    ButtonType btnAscendente = new ButtonType("Ascendente");
    ButtonType btnDescendente = new ButtonType("Descendente");
    alert.getButtonTypes().setAll(btnAscendente, btnDescendente);

    var result = alert.showAndWait();
    if (result.isPresent() && result.get() == btnAscendente) {
        ordenarDispositivosFecha(1); // 1 for ascending order
    } else if (result.isPresent() && result.get() == btnDescendente) {
        ordenarDispositivosFecha(2); // 2 for descending order
    }

    actualizarListaDispositivos();
}

public void ordenarDispositivosFecha(int orden) {
    if (orden == 1) {
        dispositivos.sort(Comparator.comparing(Dispositivo::getFechaCompra));
    } else if (orden == 2) {
        dispositivos.sort(Comparator.comparing(Dispositivo::getFechaCompra).reversed());
    }
}

    @FXML
    public void btnImprimir() {
        // Prompt the user to enter the file path
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Imprimir dispositivos");
        dialog.setHeaderText("Introduzca la ruta absoluta del archivo");
        dialog.setContentText("Ruta:");

        var result = dialog.showAndWait();
        if (result.isPresent()) {
            rutaImpresion = result.get();
            imprimirDispositivos();
        }
}

public void altaDispositivo(String id, LocalDate fechaCompraLocalDate, TipoAtributo tipo, String marca, String modelo) {
    if (id == null || id.isEmpty() || fechaCompraLocalDate == null || tipo == null || marca == null || marca.isEmpty() || modelo == null || modelo.isEmpty()) {
        return;
    }

    Dispositivo nuevoDispositivo = new Dispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);
    dispositivos.add(nuevoDispositivo);
}

@FXML
public void handleBtnAltaClick() {
    String id = txtId.getText();
    LocalDate fechaCompraLocalDate = fechaAlta.getValue();
    TipoAtributo tipo = choiceTipo.getValue();
    String marca = txtMarca.getText();
    String modelo = txtModelo.getText();

    altaDispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);
}



    @FXML
public void btnBaja() {
    Dispositivo selectedDispositivo = listaDispositivos.getSelectionModel().getSelectedItem();
    if (selectedDispositivo != null) {
        dispositivos.remove(selectedDispositivo);
        actualizarListaDispositivos();
    }
}


    @FXML
    public void btnModificar() throws IOException {
        Dispositivo selectedDispositivo = listaDispositivos.getSelectionModel().getSelectedItem();
        if (selectedDispositivo != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/actividadfinal2t10/dispositivo-view.fxml"));
            Parent root = loader.load();
            Dispositivo controller = loader.getController();
            controller.modificarDispositivo(selectedDispositivo);
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
            actualizarListaDispositivos();
        }


    }


public void imprimirDispositivos() {
    try (PrintWriter writer = new PrintWriter(rutaImpresion)) {
        for (Dispositivo dispositivo : dispositivos) {
            writer.println("[" + dispositivo.getIdentificador() + ", " +  dispositivo.getFechaCompra()  + ", " + dispositivo.getTipoAtributo() + ", " +  dispositivo.getMarca() + ", " + dispositivo.getModelo() + "]");
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmación de impresión");
        alert.setHeaderText(null);
        alert.setContentText("Los dispositivos se han impreso correctamente en el archivo.");
        alert.showAndWait();

    } catch (FileNotFoundException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

    public void limpiarCampos() {
        txtId.clear();
        fechaAlta.setValue(null);
        choiceTipo.setValue(null);
        txtMarca.clear();
        txtModelo.clear();

    }

}