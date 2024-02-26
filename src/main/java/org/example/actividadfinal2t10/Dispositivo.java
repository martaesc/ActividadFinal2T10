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


    /**
     * Constructor de la clase Dispositivo
     * @param identificador
     * @param fechaCompra
     * @param tipoAtributo
     * @param marca
     * @param modelo
     */
    public Dispositivo(String identificador, LocalDate fechaCompra, TipoAtributo tipoAtributo, String marca, String modelo) {
        this.identificador = identificador;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.tipoAtributo = tipoAtributo;
        this.marca = marca;
        this.modelo = modelo;
    }


    /**
     * Getters y Setters
     */
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


    /**
     * Método que se llama automáticamente después de que se ha cargado el archivo FXML.
     * Inicializa el ChoiceBox de tipos de atributos con todos los valores posibles de TipoAtributo.
     */
    @FXML
    public void initialize() {
        TipoAtributo[] tipos = TipoAtributo.values();
        ObservableList<TipoAtributo> tiposList = FXCollections.observableArrayList(tipos);
        choiceTipo.setItems(tiposList);
    }


    /**
     * Modifica los detalles del dispositivo seleccionado.
     *<p>
     * Este método se utiliza para actualizar los detalles del dispositivo seleccionado en la interfaz de usuario.
     * Toma un objeto Dispositivo como parámetro y establece los campos de texto y el selector de tipo en la interfaz de usuario con los detalles del dispositivo.
     *
     * @param dispositivo el dispositivo seleccionado cuyos detalles se van a modificar.
     */
    public void modificarDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
        txtId.setText(dispositivo.getIdentificador());
        txtFechaCompra.setText(dispositivo.getFechaCompra().toString());
        txtMarca.setText(dispositivo.getMarca());
        txtModelo.setText(dispositivo.getModelo());
        choiceTipo.setValue(dispositivo.getTipoAtributo());
    }


    /**
     * Método asociado al botón de aceptar en la interfaz de usuario.
     * <p>
     * Este método recoge los valores introducidos por el usuario en los campos de texto y el selector de tipo,
     * y los establece en el dispositivo actual.
     * Luego, cierra la ventana actual.
     */
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


    /**
     * Método asociado al botón de cancelar en la interfaz de usuario.
     * <p>
     * Este método obtiene la ventana actual y la cierra, efectivamente cancelando la operación actual.
     */
    @FXML
    public void btnCancelar() {
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }


    /**
     * Sobrescribe el método toString para la clase Dispositivo.
     *<p>
     * Este método devuelve una representación en cadena del objeto Dispositivo,
     * incluyendo su identificador, fechaCompra, tipoAtributo, marca y modelo.
     *
     * @return una representación en cadena del objeto Dispositivo
     */
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
