package org.example.actividadfinal2t10;

import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class InventarioTest {

    private Inventario inventario;
    private TextField txtId;
    private DatePicker fechaAlta;
    private ChoiceBox<TipoAtributo> choiceTipo;
    private TextField txtMarca;
    private TextField txtModelo;



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