package org.example.actividadfinal2t10;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.Date;

public class Dispositivo {

    @FXML
    private Button btnAceptar;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtFechaCompra;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtMarca;

    @FXML
    private TextField txtModelo;

    @FXML
    private ChoiceBox<TipoAtributo> choiceTipo;
    private String identificador;
    private LocalDate fechaCompra;
    private double precio;
    private TipoAtributo tipoAtributo;
    private String marca;
    private String modelo;

    private Dispositivo dispositivo;

    public Dispositivo() {
    }

    public Dispositivo(String identificador, LocalDate fechaCompra, TipoAtributo tipoAtributo, String marca, String modelo) {
        this.identificador = identificador;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.tipoAtributo = tipoAtributo;
        this.marca = marca;
        this.modelo = modelo;
    }



    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public TipoAtributo getTipoAtributo() {
        return tipoAtributo;
    }

    public void setTipoAtributo(TipoAtributo tipoAtributo) {
        this.tipoAtributo = tipoAtributo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @FXML
    public void initialize() {
        TipoAtributo[] tipos = TipoAtributo.values();
        ObservableList<TipoAtributo> tiposList = FXCollections.observableArrayList(tipos);
        choiceTipo.setItems(tiposList);
    }


    public void modificarDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
        txtId.setText(dispositivo.getIdentificador());
        txtFechaCompra.setText(dispositivo.getFechaCompra().toString());
        txtMarca.setText(dispositivo.getMarca());
        txtModelo.setText(dispositivo.getModelo());
        choiceTipo.setValue(dispositivo.getTipoAtributo());
    }

    @FXML
    public void btnAceptar() {
        dispositivo.setIdentificador(txtId.getText());
        dispositivo.setFechaCompra(LocalDate.parse(txtFechaCompra.getText()));
        dispositivo.setMarca(txtMarca.getText());
        dispositivo.setModelo(txtModelo.getText());
        dispositivo.setTipoAtributo(choiceTipo.getValue());
        Stage stage = (Stage) btnAceptar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void btnCancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    @Override
    public String toString() {
        return "Dispositivo{" +
                "id ='" + identificador + '\'' +
                ", fecha compra =" + fechaCompra +
                ", tipo =" + tipoAtributo +
                ", marca ='" + marca + '\'' +
                ", modelo ='" + modelo + '\'' +
                '}';
    }

}
