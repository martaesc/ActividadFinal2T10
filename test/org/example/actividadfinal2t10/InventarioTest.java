package org.example.actividadfinal2t10;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * Clase de prueba para la clase Inventario.
 * Esta clase contiene pruebas para los métodos de la clase Inventario.
 */
class InventarioTest {

    private Inventario inventario;
    private TextField txtId;
    private DatePicker fechaAlta;
    private ChoiceBox<TipoAtributo> choiceTipo;
    private TextField txtMarca;
    private TextField txtModelo;


    /**
     * Prueba para el método altaDispositivo de la clase Inventario.
     * Esta prueba verifica que un dispositivo se añade correctamente al inventario y que sus detalles son correctos.
     */
 @Test
void testAltaDispositivo() {
    Inventario inventario = new Inventario();

    String id = "123";
    LocalDate fechaCompraLocalDate = LocalDate.now();
    TipoAtributo tipo = TipoAtributo.Impresora;
    String marca = "Brand";
    String modelo = "Model";

    inventario.altaDispositivo(id, fechaCompraLocalDate, tipo, marca, modelo);

    ObservableList<Dispositivo> dispositivosParaMostrar = inventario.getDispositivosParaMostrar();

    assertEquals(1, dispositivosParaMostrar.size());
    Dispositivo dispositivo = dispositivosParaMostrar.get(0);
    assertEquals(id, dispositivo.getIdentificador());
    assertEquals(fechaCompraLocalDate, dispositivo.getFechaCompra());
    assertEquals(tipo, dispositivo.getTipoAtributo());
    assertEquals(marca, dispositivo.getMarca());
    assertEquals(modelo, dispositivo.getModelo());
}

    /**
     * Prueba para el método ordenarDispositivosFecha de la clase Inventario con orden ascendente.
     * Esta prueba verifica que los dispositivos se ordenan correctamente por fecha en orden ascendente.
     */
    @Test
    void testOrdenarDispositivosFechaAscendente() {
        Inventario inventario = new Inventario();
        Dispositivo dispositivo1 = new Dispositivo("1", LocalDate.of(2022, 1, 1), TipoAtributo.Impresora, "Marca1", "Modelo1");
        Dispositivo dispositivo2 = new Dispositivo("2", LocalDate.of(2022, 1, 2), TipoAtributo.Impresora, "Marca2", "Modelo2");
        inventario.getDispositivos().add(dispositivo2);
        inventario.getDispositivos().add(dispositivo1);

        inventario.ordenarDispositivosFecha(1);

        assertTrue(inventario.getDispositivos().get(0).getFechaCompra().isBefore(inventario.getDispositivos().get(1).getFechaCompra()));
    }

    /**
     * Prueba para el método ordenarDispositivosFecha de la clase Inventario con orden descendente.
     * Esta prueba verifica que los dispositivos se ordenan correctamente por fecha en orden descendente.
     */
    @Test
    void testOrdenarDispositivosFechaDescendente() {
        Inventario inventario = new Inventario();
        Dispositivo dispositivo1 = new Dispositivo("1", LocalDate.of(2022, 1, 1), TipoAtributo.Impresora, "Marca1", "Modelo1");
        Dispositivo dispositivo2 = new Dispositivo("2", LocalDate.of(2022, 1, 2), TipoAtributo.Impresora, "Marca2", "Modelo2");
        inventario.getDispositivos().add(dispositivo1);
        inventario.getDispositivos().add(dispositivo2);

        inventario.ordenarDispositivosFecha(2);

        assertTrue(inventario.getDispositivos().get(0).getFechaCompra().isAfter(inventario.getDispositivos().get(1).getFechaCompra()));
    }


}