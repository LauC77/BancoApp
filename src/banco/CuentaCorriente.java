package banco;

import java.time.LocalDate;

public class CuentaCorriente extends Cuenta {

    // Tasa de mantenimiento mensual del 1.5%. Se aplica al saldo cada mes.
    private static final double TASA_MANTENIMIENTO = 0.015; // 1.5% mensual
    // Cargo fijo por el uso de cheques.
    private static final double CARGO_USO_CHEQUE = 3000; // Cargo fijo por uso de cheque
    // Controla si ya se aplicó el cargo mensual, para evitar duplicados.
    private boolean cargoMensualAplicado; 
    // Fecha en que se aplicó el último cargo mensual.
    private LocalDate fechaUltimoCargo; 

    // Constructor de la clase CuentaCorriente
    public CuentaCorriente(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);  // Llamada al constructor de la clase padre (Cuenta)

        // Verifica que el saldo de apertura sea al menos 200,000. Si no es así, lanza un error.
        if (saldo < 200000) {
            throw new IllegalArgumentException("El monto de apertura debe ser al menos 200,000 pesos.");
        }

        // Inicializa las variables relacionadas con el cargo mensual
        this.cargoMensualAplicado = false;  // Inicialmente, no se ha aplicado el cargo mensual
        this.fechaUltimoCargo = LocalDate.now();  // Se establece la fecha del último cargo como la fecha actual
    }

    // Método para aplicar el cargo mensual por mantenimiento
    @Override
    public void aplicarCargoMensual() {
        // Obtener la fecha actual para comprobar si es un nuevo mes
        LocalDate fechaActual = LocalDate.now();
        
        // Aplica el cargo solo si no se ha aplicado en este mes o nunca se aplicó
        if (!cargoMensualAplicado || fechaActual.getMonth() != fechaUltimoCargo.getMonth()) {
            // Calcula el cargo de mantenimiento mensual basado en el saldo
            double cargoMantenimientoMensual = getSaldo() * TASA_MANTENIMIENTO;
            // Registrar la transacción de mantenimiento sin retirar el dinero todavía
            registrarTransaccionSinRetiro("Cargo por mantenimiento mensual: -$" + cargoMantenimientoMensual);
            // Actualiza el saldo, descontando el cargo mensual
            setSaldo(getSaldo() - cargoMantenimientoMensual);

            // Actualiza la fecha del último cargo y marca que ya se aplicó el cargo mensual
            fechaUltimoCargo = fechaActual;
            cargoMensualAplicado = true; 
        }
    }

    // Método para aplicar cargos por uso de cheques
    public void aplicarCargoPorUsoCheque() {
        // Registrar la transacción del cargo por cheque sin retirar dinero todavía
        registrarTransaccionSinRetiro("Cargo por uso de cheque: -$" + CARGO_USO_CHEQUE);
        // Actualiza el saldo descontando el cargo por uso de cheque
        setSaldo(getSaldo() - CARGO_USO_CHEQUE); 
    }

    // Método para aplicar cargos por depósitos
    public void aplicarCargoPorDeposito(double monto) {
        double cargo = 0; // Variable para almacenar el cargo calculado

        // Establece diferentes cargos según el monto depositado
        if (monto < 500000) {
            cargo = 7000;  // Cargo fijo de 7,000 si el monto es menor a 500,000
        } else if (monto >= 500000 && monto < 2000000) {
            cargo = 5000 + (monto * 0.02);  // Cargo de 5,000 más un 2% sobre el monto si está entre 500,000 y 2,000,000
        } else if (monto >= 2000000 && monto <= 10000000) {
            cargo = 4000 + (monto * 0.02);  // Cargo de 4,000 más un 2% sobre el monto si está entre 2,000,000 y 10,000,000
        } else if (monto > 10000000) {
            cargo = monto * 0.033;  // Cargo del 3.3% si el monto es mayor a 10,000,000
        }

        // Registrar la transacción del cargo por depósito
        registrarTransaccion("Cargo por depósito: -$" + cargo);
        // Actualiza el saldo descontando el cargo por el depósito
        setSaldo(getSaldo() - cargo); 
    }

    // Método adicional para registrar transacciones sin "retiro" (para cargos como mantenimiento o cheques)
    private void registrarTransaccionSinRetiro(String transaccion) {
        // En un sistema real, aquí se registraría la transacción en una base de datos o archivo.
        System.out.println(transaccion);  // Imprime el mensaje de la transacción en la consola
    }
}
