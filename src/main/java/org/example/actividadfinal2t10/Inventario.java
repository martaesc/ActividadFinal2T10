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
    private TextField txtId;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private DatePicker fechaAlta;

    @FXML
    private ChoiceBox<TipoAtributo> choiceTipo;

    private List<Dispositivo> dispositivos;

    private int ordenacion;

    private String rutaImpresion;


    public Inventario() {
        this.dispositivos = new ArrayList<>();
    }

    @FXML
    public void initialize() {
        TipoAtributo[] tipos = TipoAtributo.values();
        ObservableList<TipoAtributo> tiposList = FXCollections.observableArrayList(tipos);
        choiceTipo.setItems(tiposList);

        mostrarDispositivos();
    }

    public void mostrarDispositivos() {
        ObservableList<Dispositivo> dispositivosObservable = FXCollections.observableArrayList(dispositivos);
        listaDispositivos.setItems(dispositivosObservable);
    }

    @FXML
    public void btnOrdenar() {
        ordenarDispositivosFecha(1); // 1 para ordenación ascendente, 2 para descendente
        mostrarDispositivos(); // Actualizar la vista después de ordenar
    }

    public void ordenarDispositivosFecha(int tipoOrdenacion) {
        Comparator<Dispositivo> comparator = Comparator.comparing(Dispositivo::getFechaCompra);
        if (tipoOrdenacion == 2) {
            comparator = comparator.reversed();
        }
        dispositivos.sort(comparator);
    }

    @FXML
    public void btnAlta() {
        String id = txtId.getText();
        LocalDate fechaCompraLocalDate = fechaAlta.getValue();
        TipoAtributo tipo = choiceTipo.getValue(); // Aquí recogemos el tipo seleccionado en el ChoiceBox
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        Dispositivo nuevoDispositivo = new Dispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);
        dispositivos.add(nuevoDispositivo);
        mostrarDispositivos();

        limpiarCampos();
    }


    public void imprimirDispositivos() {
        // Código para imprimir dispositivos a un archivo txt
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
            mostrarDispositivos();
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