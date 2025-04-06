
public class Cliente {
    private String nombre;
    private String apellido;
    private int edad;
    private String representante;
    private Cuenta cuenta;

    public Cliente(String nombre, String apellido, int edad, String representante) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.representante = representante;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }
}
