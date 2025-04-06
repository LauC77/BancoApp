package banco;

import java.time.LocalDate;

public class CuentaCorriente extends Cuenta {

    private static final double TASA_MANTENIMIENTO = 0.015; // 1.5% mensual
    private static final double CARGO_USO_CHEQUE = 3000; // Cargo fijo por uso de cheque
    private boolean cargoMensualAplicado; // Controla si ya se aplicó el cargo mensual
    private LocalDate fechaUltimoCargo; // Guarda la fecha del último cargo aplicado

    public CuentaCorriente(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);

        // Verificar que el saldo cumple con el monto mínimo de apertura (200,000)
        if (saldo < 200000) {
            throw new IllegalArgumentException("El monto de apertura debe ser al menos 200,000 pesos.");
        }

        this.cargoMensualAplicado = false; // Inicialmente no se ha aplicado el cargo
        this.fechaUltimoCargo = LocalDate.now(); // Se establece la fecha actual
    }

    // Método para aplicar cargos mensuales
    @Override
    public void aplicarCargoMensual() {
        // Verificar si ya se ha aplicado el cargo este mes
        LocalDate fechaActual = LocalDate.now();
        
        // Solo aplica el cargo si es un nuevo mes o nunca se aplicó
        if (!cargoMensualAplicado || fechaActual.getMonth() != fechaUltimoCargo.getMonth()) {
            // Aplica la tasa de mantenimiento mensual del 1.5% (sobre el saldo)
            double cargoMantenimientoMensual = getSaldo() * TASA_MANTENIMIENTO;
            registrarTransaccionSinRetiro("Cargo por mantenimiento mensual: -$" + cargoMantenimientoMensual);
            setSaldo(getSaldo() - cargoMantenimientoMensual); // Actualiza el saldo

            // Actualizar la fecha del último cargo
            fechaUltimoCargo = fechaActual;
            cargoMensualAplicado = true; // Marcar que ya se aplicó el cargo
        }
    }

    // Método para aplicar cargos por uso de cheques
    public void aplicarCargoPorUsoCheque() {
        registrarTransaccionSinRetiro("Cargo por uso de cheque: -$" + CARGO_USO_CHEQUE);
        setSaldo(getSaldo() - CARGO_USO_CHEQUE); // Actualiza el saldo
    }

    // Método para aplicar cargos por depósitos
    public void aplicarCargoPorDeposito(double monto) {
        double cargo = 0;

        if (monto < 500000) {
            cargo = 7000;
        } else if (monto >= 500000 && monto < 2000000) {
            cargo = 5000 + (monto * 0.02);
        } else if (monto >= 2000000 && monto <= 10000000) {
            cargo = 4000 + (monto * 0.02);
        } else if (monto > 10000000) {
            cargo = monto * 0.033;
        }

        // Se descuenta el cargo por depósito del saldo
        registrarTransaccion("Cargo por depósito: -$" + cargo);
        setSaldo(getSaldo() - cargo); // Actualiza el saldo
    }

    // Método adicional para registrar transacciones sin "retiro" (para cargos como mantenimiento o cheques)
    private void registrarTransaccionSinRetiro(String transaccion) {
        // Llama al método de registrar transacción con un mensaje que no es un retiro
        System.out.println(transaccion); // En un sistema real, aquí se registraría en una base de datos o archivo.
    }
}
