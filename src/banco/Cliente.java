package banco;

public class Cliente {
    // Atributos privados de la clase Cliente
    private String nombre;          // Nombre del cliente
    private String apellido;        // Apellido del cliente
    private int edad;               // Edad del cliente
    private String representante;   // Nombre del representante en caso de ser menor de edad
    private Cuenta cuenta;          // Cuenta asociada al cliente

    // Constructor que inicializa los atributos del cliente
    public Cliente(String nombre, String apellido, int edad, String representante) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.representante = representante;
    }

    // Método para asignar una cuenta al cliente
    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    // Método para obtener la cuenta asociada al cliente
    public Cuenta getCuenta() {
        return cuenta;
    }

    // Método que retorna el nombre completo del cliente
    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
