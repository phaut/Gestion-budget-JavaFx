package modele;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Persona {

	private StringProperty nombres;
    private StringProperty apellidos;
    private IntegerProperty id_cliente;
    private ObjectProperty <LocalDate>fechacliente;

    public Persona (String nombres, String apellidos, Integer id_cliente, LocalDate fechacliente) {
        this.nombres=  new SimpleStringProperty (nombres);
        this.apellidos= new SimpleStringProperty ( apellidos);
        this.id_cliente=new SimpleIntegerProperty (id_cliente);
        this.fechacliente= new SimpleObjectProperty<>(fechacliente);
    }
    public String getNombres() {
        return nombres.get();
    }

    public  void  setNombres(String nombres) {
        this.nombres=new SimpleStringProperty (nombres);
    }


    public String getApellidos() {
        return apellidos.get();
    }

    public  void  setApellidos(String apellidos) {
        this.apellidos=new SimpleStringProperty ( apellidos);
    }


    public Integer getId_cliente() {
        return id_cliente.get();
    }

    public  void  setid_cliente(Integer id_cliente) {
        this.id_cliente=new SimpleIntegerProperty (id_cliente);
    }
    public LocalDate getFechaCliente() {
        return fechacliente.get();
    }
    public void setFechaCliente(LocalDate fechacliente) {
        this.fechacliente = new SimpleObjectProperty<>(fechacliente);
    }
    public ObjectProperty<LocalDate> fechaClienteProperty() {
        return fechacliente;
    }

}

/* public void initialize(URL arg0, ResourceBundle arg1) {
            clienteid.setCellValueFactory(new PropertyValueFactory <Persona, Integer>("id_cliente"));
            nombrescol.setCellValueFactory(new PropertyValueFactory <Persona, String>("nombres"));
             apellidoscol.setCellValueFactory(new PropertyValueFactory <Persona, String>("apellidos"));
             fechacli.setCellValueFactory(cellData -> cellData.getValue().fechaClienteProperty());

             DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

             fechacli.setCellFactory(column -> {
                 return new TableCell<Persona, LocalDate>() {
                     @Override
                     protected void updateItem(LocalDate item, boolean empty) {
                         super.updateItem(item, empty);

                         if (item == null || empty) {
                             setText(null);
                         } else {
                             setText(formatter.format(item));

                         }
                     }
                 };
             });

             seleccionaregistros();
             seleccionanombre();
             seleccionapellido();
        }   */
