package banco;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
  private int numeroCuenta;
    private String nombre;
    private String apellido;
    private int edad;
    private String representante;
    private double saldo;
    private ArrayList<String> transacciones;
    
     // Variable para controlar si se ha aplicado el cargo mensual
    private boolean cargoMensualAplicado;
    private LocalDate fechaUltimoCargoMensual;
    
    
    // Variable para almacenar el saldo inicial de la cuenta
    private double saldoInicial;
    
         public Cuenta(int numeroCuenta, String nombre, String apellido, int edad, String representante, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.representante = representante;
        this.saldo = saldo;
        this.saldoInicial = saldo; // Guardamos el saldo inicial
        this.transacciones = new ArrayList<>();
        this.cargoMensualAplicado = false; // Inicialmente no se ha aplicado el cargo
        this.fechaUltimoCargoMensual = LocalDate.now().minusMonths(1); // Se establece como un mes antes
    }


    // Método para retirar dinero
public boolean retirar(double monto) {
    if (monto > 0 && saldo >= monto) {
            saldo -= monto;
            return true;
        }
        System.out.println("Fondos insuficientes o monto inválido.");
        return false;
}
// Método para registrar un cargo por mantenimiento mensual
    public void aplicarCargoMensual() {
        LocalDate fechaActual = LocalDate.now();

        // Verificamos si ya se ha aplicado el cargo este mes
        if (fechaActual.getMonth() != fechaUltimoCargoMensual.getMonth()) {
            // Si no es el mismo mes, aplicamos el cargo
            double cargoMantenimientoMensual = saldo * 0.015; // 1.5% de mantenimiento mensual
            saldo -= cargoMantenimientoMensual; // Realiza la deducción del saldo

            // Registrar la transacción del cargo mensual
            registrarTransaccion("Cargo por mantenimiento mensual: -$" + cargoMantenimientoMensual);

            // Actualizamos la fecha del último cargo
            fechaUltimoCargoMensual = fechaActual;
            cargoMensualAplicado = true; // Marca que ya se aplicó el cargo este mes
        }
    }

// Método para registrar un cargo por emisión de cheque
public void aplicarCargoPorCheque() {
      double cargoCheque = 3000; // Cargo fijo por uso de cheque
        saldo -= cargoCheque;
        // Registrar la transacción con un mensaje específico
        registrarTransaccion("Comisión emisión de Cheque: -$" + cargoCheque);
}

    // Método para depositar dinero
    public void depositar(double monto) {
         if (monto > 0) {
            saldo += monto;
            registrarTransaccion("Depósito: +$" + monto);
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");
        }
    }

     // Método para registrar transacciones
    public void registrarTransaccion(String transaccion) {
        transacciones.add(transaccion);
    }


    // Mostrar el estado de cuenta
     public void mostrarEstadoCuenta() {
        System.out.println("Cuenta: " + numeroCuenta);
        System.out.println("Titular: " + nombre + " " + apellido);
        System.out.println("Saldo inicial: $" + saldoInicial); // Mostrar el saldo inicial
        System.out.println("Saldo: $" + saldo);
        System.out.println("---- MOVIMIENTOS ----");
        for (String transaccion : transacciones) {
            System.out.println(transaccion);
        }
        System.out.println("--------------------");
    }

    // Obtener número de cuenta
    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    // Método setter para saldo
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
    
    
}

