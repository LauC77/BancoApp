import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
    protected int numeroCuenta;
    protected String titular;
    protected String apellido;
    protected int edad;
    protected String representante;
    protected double saldo;
    
    // Nueva lista para registrar los movimientos
    protected List<String> transacciones = new ArrayList<>();

    public Cuenta(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.apellido = apellido;
        this.edad = edad;
        this.representante = representante;
        this.saldo = saldo;
        transacciones.add("Cuenta creada con saldo inicial: $" + saldo);
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double monto) {
        saldo += monto;
        transacciones.add("Depósito: +$" + monto);
    }

    public boolean retirar(double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            transacciones.add("Retiro: -$" + monto);
            return true;
        }
        return false;
    }

    public void registrarTransaccion(String detalle) {
        transacciones.add(detalle);
    }

    public abstract void aplicarCargosMensuales();
    public abstract void calcularComisionDeposito(double monto);

    public void mostrarEstadoCuenta() {
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + titular + " " + apellido);
        if (edad < 18) {
            System.out.println("Representante: " + representante);
        }
        System.out.println("Saldo: $" + saldo);
        System.out.println("---- MOVIMIENTOS ----");
        for (String mov : transacciones) {
            System.out.println(mov);
        }
        System.out.println("----------------------");
    }
}
