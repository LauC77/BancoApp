package banco;

import java.time.LocalDate;
import java.util.ArrayList;

public class Cuenta {
    private int numeroCuenta;  // Número de cuenta del titular
    private String nombre;     // Nombre del titular
    private String apellido;   // Apellido del titular
    private int edad;          // Edad del titular
    private String representante;  // Representante en caso de que el titular sea menor de edad o esté representado
    private double saldo;      // Saldo actual de la cuenta
    private ArrayList<String> transacciones;  // Lista que guarda todas las transacciones realizadas en la cuenta
    
    // Variable para controlar si se ha aplicado el cargo mensual
    private boolean cargoMensualAplicado;  
    private LocalDate fechaUltimoCargoMensual;  // Fecha del último cargo mensual aplicado
    
    // Variable para almacenar el saldo inicial de la cuenta (al momento de crearla)
    private double saldoInicial;

    // Constructor para crear una nueva cuenta con los detalles proporcionados
    public Cuenta(int numeroCuenta, String nombre, String apellido, int edad, String representante, double saldo) {
        this.numeroCuenta = numeroCuenta;  // Asignación del número de cuenta
        this.nombre = nombre;  // Asignación del nombre
        this.apellido = apellido;  // Asignación del apellido
        this.edad = edad;  // Asignación de la edad
        this.representante = representante;  // Asignación del representante
        this.saldo = saldo;  // Asignación del saldo inicial
        this.saldoInicial = saldo;  // Guardamos el saldo inicial para referenciarlo después
        this.transacciones = new ArrayList<>();  // Inicializamos la lista de transacciones
        this.cargoMensualAplicado = false;  // Inicialmente no se ha aplicado el cargo mensual
        this.fechaUltimoCargoMensual = LocalDate.now().minusMonths(1);  // Establecemos la fecha del último cargo como hace un mes
    }

    // Método para retirar dinero de la cuenta
    public boolean retirar(double monto) {
        if (monto > 0 && saldo >= monto) {  // Verificamos que el monto sea positivo y que haya suficiente saldo
            saldo -= monto;  // Realizamos la deducción del saldo
            return true;  // Retiro exitoso
        }
        System.out.println("Fondos insuficientes o monto inválido.");  // Mensaje de error si el retiro no es posible
        return false;  // Retiro fallido
    }

    // Método para aplicar un cargo mensual por mantenimiento de la cuenta
    public void aplicarCargoMensual() {
        LocalDate fechaActual = LocalDate.now();  // Obtenemos la fecha actual

        // Verificamos si ya se ha aplicado el cargo este mes
        if (fechaActual.getMonth() != fechaUltimoCargoMensual.getMonth()) {  
            // Si no es el mismo mes, aplicamos el cargo
            double cargoMantenimientoMensual = saldo * 0.015;  // Cargo de 1.5% sobre el saldo actual
            saldo -= cargoMantenimientoMensual;  // Deduct the monthly fee from the balance

            // Registrar la transacción del cargo mensual
            registrarTransaccion("Cargo por mantenimiento mensual: -$" + cargoMantenimientoMensual);

            // Actualizamos la fecha del último cargo
            fechaUltimoCargoMensual = fechaActual;
            cargoMensualAplicado = true;  // Marcamos que ya se aplicó el cargo
        }
    }

    // Método para aplicar un cargo por emisión de cheque
    public void aplicarCargoPorCheque() {
        double cargoCheque = 3000;  // Cargo fijo por la emisión de un cheque
        saldo -= cargoCheque;  // Deduct the charge from the balance

        // Registrar la transacción con un mensaje específico
        registrarTransaccion("Comisión emisión de Cheque: -$" + cargoCheque);
    }

    // Método para depositar dinero en la cuenta
    public void depositar(double monto) {
        if (monto > 0) {  // Verificamos que el monto sea positivo
            saldo += monto;  // Sumamos el monto al saldo
            registrarTransaccion("Depósito: +$" + monto);  // Registramos la transacción
        } else {
            System.out.println("El monto a depositar debe ser mayor a 0.");  // Mensaje de error si el monto es inválido
        }
    }

    // Método para registrar una transacción en el historial de transacciones
    public void registrarTransaccion(String transaccion) {
        transacciones.add(transaccion);  // Añadimos la transacción a la lista
    }

    // Método para mostrar el estado de cuenta completo
    public void mostrarEstadoCuenta() {
        System.out.println("Cuenta: " + numeroCuenta);  // Muestra el número de cuenta
        System.out.println("Titular: " + nombre + " " + apellido);  // Muestra el nombre del titular
        System.out.println("Saldo inicial: $" + saldoInicial);  // Muestra el saldo inicial de la cuenta
        System.out.println("Saldo: $" + saldo);  // Muestra el saldo actual
        System.out.println("---- MOVIMIENTOS ----");
        for (String transaccion : transacciones) {
            System.out.println(transaccion);  // Muestra todas las transacciones registradas
        }
        System.out.println("--------------------");
    }

    // Métodos para obtener información sobre la cuenta
    public int getNumeroCuenta() {
        return numeroCuenta;  // Retorna el número de cuenta
    }

    public double getSaldo() {
        return saldo;  // Retorna el saldo actual
    }

    public String getNombre() {
        return nombre;  // Retorna el nombre del titular
    }

    public String getApellido() {
        return apellido;  // Retorna el apellido del titular
    }

    // Método setter para modificar el saldo
    public void setSaldo(double saldo) {
        this.saldo = saldo;  // Establece un nuevo valor para el saldo
    }
}


