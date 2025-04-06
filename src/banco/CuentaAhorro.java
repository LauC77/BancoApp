package banco;

public class CuentaAhorro extends Cuenta {

    private static final double TASA_RENDIMIENTO_ANUAL = 0.022; // Tasa de rendimiento anual del 2.2%

    // Constructor
    public CuentaAhorro(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);
        // No es necesario validar monto mínimo para cuenta de ahorros
    }

    // Método para aplicar el rendimiento anual (se calcula sobre el saldo)
    public void aplicarRendimientoAnual() {
        double rendimiento = getSaldo() * TASA_RENDIMIENTO_ANUAL;
        depositar(rendimiento); // Realiza el depósito del rendimiento en la cuenta
        registrarTransaccion("Rendimiento anual depositado: +$" + rendimiento);
    }

    // Método para aplicar cargos por depósitos
    public void aplicarCargoPorDeposito(double monto) {
        double cargo = 0;

        if (monto >= 500000 && monto < 2000000) {
            cargo = 3000 + (monto * 0.01); // Cargo de 3,000 más 1% sobre el monto depositado
        } else if (monto >= 2000000 && monto < 10000000) {
            cargo = 2000 + (monto * 0.005); // Cargo de 2,000 más 0.5% sobre el monto depositado
        } else if (monto >= 10000000 && monto < 100000000) {
            cargo = monto * 0.018; // Cargo de 1.8% sobre el monto depositado
        } else if (monto >= 100000000) {
            cargo = monto * 0.02; // Cargo de 2% sobre el monto depositado
        }

        // Se descuenta el cargo por depósito del saldo
        registrarTransaccion("Cargo por depósito: -$" + cargo);
        retirar(cargo); // Realiza la deducción del saldo
    }
}
