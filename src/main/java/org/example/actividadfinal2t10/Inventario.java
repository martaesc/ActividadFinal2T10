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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


/**
 * Clase Inventario que gestiona una lista de dispositivos.
 */
public class Inventario {


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

    private String rutaImpresion;

    public List<Dispositivo> getDispositivos() {
        return dispositivos;
    }


    /**
     * Constructor de la clase Inventario.
     * Inicializa la lista de dispositivos como un ArrayList vacío.
     */
    public Inventario() {
        this.dispositivos = new ArrayList<>();
    }


    /**
     * Método que se llama automáticamente después de que se ha cargado el archivo FXML.
     * Inicializa los elementos de la interfaz de usuario.
     */
    @FXML
    public void initialize() {
        TipoAtributo[] tipos = TipoAtributo.values();
        ObservableList<TipoAtributo> tiposList = FXCollections.observableArrayList(tipos);
        choiceTipo.setItems(tiposList);

        actualizarListaDispositivos();
    }


    /**
     * Devuelve una lista observable de los dispositivos para mostrar en la interfaz de usuario.
     *
     * @return una lista observable de dispositivos
     */
    public ObservableList<Dispositivo> getDispositivosParaMostrar() {
        return FXCollections.observableArrayList(dispositivos);
    }


    /**
     * Actualiza la lista de dispositivos en la interfaz de usuario.
     */
    public void actualizarListaDispositivos() {
        listaDispositivos.setItems(getDispositivosParaMostrar());
    }


    /**
     * Método asociado al botón de ordenar en la interfaz de usuario.
     * <p>
     * Muestra un cuadro de diálogo de confirmación para que el usuario seleccione el orden de clasificación.
     * Luego, ordena los dispositivos por fecha de compra en el orden seleccionado y actualiza la lista de dispositivos en la interfaz de usuario.
     */
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


    /**
     * Ordena la lista de dispositivos por fecha de compra.
     *
     * @param orden un entero que determina el orden de clasificación.
     *              Si es 1, los dispositivos se ordenan en orden ascendente.
     *              Si es 2, los dispositivos se ordenan en orden descendente.
     */
    public void ordenarDispositivosFecha(int orden) {
        if (orden == 1) {
            dispositivos.sort(Comparator.comparing(Dispositivo::getFechaCompra));
        } else if (orden == 2) {
            dispositivos.sort(Comparator.comparing(Dispositivo::getFechaCompra).reversed());
        }
    }


    /**
     * Método asociado al botón de imprimir en la interfaz de usuario.
     * <p>
     * Muestra un cuadro de diálogo para que el usuario introduzca la ruta absoluta del archivo donde se imprimirán los dispositivos.
     * Si el usuario introduce una ruta, llama al método imprimirDispositivos() para imprimir los dispositivos en el archivo.
     */
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


    /**
     * Añade un nuevo dispositivo a la lista de dispositivos.
     *  <p>
     * Si alguno de los parámetros es nulo o vacío, el método retorna sin hacer nada.
     * Si todos los parámetros son válidos, crea un nuevo dispositivo, lo añade a la lista,
     *  actualiza la lista de dispositivos en la interfaz de usuario y limpia los campos de entrada
     *
     * @param id el identificador del dispositivo
     * @param fechaCompraLocalDate la fecha de compra del dispositivo
     * @param tipo el tipo de atributo del dispositivo
     * @param marca la marca del dispositivo
     * @param modelo el modelo del dispositivo
     *.
     */
    public void altaDispositivo(String id, LocalDate fechaCompraLocalDate, TipoAtributo tipo, String marca, String modelo) {
        if (id == null || id.isEmpty() || fechaCompraLocalDate == null || tipo == null || marca == null || marca.isEmpty() || modelo == null || modelo.isEmpty()) {
            return;
        }

        Dispositivo nuevoDispositivo = new Dispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);
        dispositivos.add(nuevoDispositivo);
        actualizarListaDispositivos();
        limpiarCampos();
    }


    /**
     * Método asociado al botón de alta en la interfaz de usuario.
     * <p>
     * Recoge los valores introducidos por el usuario en los campos de texto y el selector de fecha y tipo.
     * Luego, llama al método altaDispositivo() para añadir un nuevo dispositivo con los valores recogidos.
     */
    @FXML
    public void handleBtnAltaClick() {
        String id = txtId.getText();
        LocalDate fechaCompraLocalDate = fechaAlta.getValue();
        TipoAtributo tipo = choiceTipo.getValue();
        String marca = txtMarca.getText();
        String modelo = txtModelo.getText();

        altaDispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);
    }


    /**
     * Método asociado al botón de baja en la interfaz de usuario.
     * <p>
     * Obtiene el dispositivo seleccionado en la lista de dispositivos.
     * Si hay un dispositivo seleccionado, lo elimina de la lista de dispositivos y actualiza la lista en la interfaz de usuario.
     */
    @FXML
    public void btnBaja() {
        Dispositivo selectedDispositivo = listaDispositivos.getSelectionModel().getSelectedItem();
        if (selectedDispositivo != null) {
            dispositivos.remove(selectedDispositivo);
            actualizarListaDispositivos();
        }
    }


    /**
     * Método asociado al botón de modificar en la interfaz de usuario.
     * <p>
     * Obtiene el dispositivo seleccionado en la lista de dispositivos.
     * Si hay un dispositivo seleccionado, carga la vista de dispositivo, obtiene su controlador y llama al método modificarDispositivo() con el dispositivo seleccionado.
     * Luego, muestra la vista en una nueva ventana modal y espera hasta que se cierre.
     * Finalmente, actualiza la lista de dispositivos en la interfaz de usuario.
     *
     * @throws IOException si ocurre un error al cargar la vista de dispositivo.
     */
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


    /**
     * Imprime la lista de dispositivos en un archivo.
     *
     * Este método recorre la lista de dispositivos y para cada dispositivo,
     * imprime sus detalles en el archivo especificado por la ruta de impresión.
     * Los detalles del dispositivo se imprimen en el formato: [identificador, fecha de compra, tipo de atributo, marca, modelo].
     *
     * Después de imprimir todos los dispositivos, muestra una alerta de información para confirmar que los dispositivos se han impreso correctamente.
     *
     * Si ocurre un error al intentar abrir el archivo para escribir, se imprime el mensaje de error en la consola.
     *
     * @throws FileNotFoundException si el archivo especificado por la ruta de impresión no se puede abrir para escribir.
     */
    public void imprimirDispositivos() {
        try (PrintWriter writer = new PrintWriter(rutaImpresion)) {
            for (Dispositivo dispositivo : dispositivos) {
                writer.println("[" + dispositivo.getIdentificador() + ", " + dispositivo.getFechaCompra() + ", " + dispositivo.getTipoAtributo() + ", " + dispositivo.getMarca() + ", " + dispositivo.getModelo() + "]");
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


    /**
     * Limpia los campos de entrada en la interfaz de usuario.
     */
    public void limpiarCampos() {
        txtId.clear();
        fechaAlta.setValue(null);
        choiceTipo.setValue(null);
        txtMarca.clear();
        txtModelo.clear();

    }

}