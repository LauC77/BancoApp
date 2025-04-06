package banco;

public class Cliente {
    // Atributos privados de la clase Cliente
    private String nombre;          // Nombre del cliente, almacena el primer nombre
    private String apellido;        // Apellido del cliente, almacena el apellido
    private int edad;               // Edad del cliente, almacena la edad del cliente
    private String representante;   // Nombre del representante, se usa solo si el cliente es menor de edad
    private Cuenta cuenta;          // Cuenta asociada al cliente, se asigna la cuenta bancaria del cliente

    // Constructor que inicializa los atributos del cliente
    // Este constructor recibe los parámetros necesarios para crear un cliente con nombre, apellido, edad y representante
    public Cliente(String nombre, String apellido, int edad, String representante) {
        this.nombre = nombre;          // Inicializa el nombre del cliente
        this.apellido = apellido;      // Inicializa el apellido del cliente
        this.edad = edad;              // Inicializa la edad del cliente
        this.representante = representante; // Inicializa el nombre del representante si es menor de edad
    }

    // Método para asignar una cuenta al cliente
    // Este método recibe un objeto de tipo Cuenta y lo asigna al cliente
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;  // Asigna la cuenta al cliente
    }

    // Método para obtener la cuenta asociada al cliente
    // Este método devuelve la cuenta bancaria asociada al cliente
    public Cuenta getCuenta() {
        return cuenta;  // Retorna la cuenta asociada al cliente
    }

    // Método que retorna el nombre completo del cliente
    // Este método combina el nombre y apellido del cliente y lo devuelve como una cadena
    public String getNombreCompleto() {
        return nombre + " " + apellido;  // Retorna el nombre completo del cliente (nombre + apellido)
    }
}
