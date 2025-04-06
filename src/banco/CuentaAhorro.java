package banco;

public class CuentaAhorro extends Cuenta {

    // Tasa de rendimiento anual del 2.2%. Es una constante que se aplica al saldo para calcular el rendimiento.
    private static final double TASA_RENDIMIENTO_ANUAL = 0.022; // 2.2% de rendimiento anual

    // Constructor de la clase CuentaAhorro
    public CuentaAhorro(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);  // Llamada al constructor de la clase padre (Cuenta)
        // No es necesario validar monto mínimo para cuenta de ahorros, ya que no tiene restricciones adicionales
    }

    // Método para aplicar el rendimiento anual sobre el saldo de la cuenta
    public void aplicarRendimientoAnual() {
        // Calculamos el rendimiento multiplicando el saldo por la tasa de rendimiento anual
        double rendimiento = getSaldo() * TASA_RENDIMIENTO_ANUAL;
        depositar(rendimiento);  // Depositamos el rendimiento calculado en la cuenta
        // Registramos la transacción del rendimiento anual
        registrarTransaccion("Rendimiento anual depositado: +$" + rendimiento);
    }

    // Método para aplicar cargos por depósitos, según el monto depositado
    public void aplicarCargoPorDeposito(double monto) {
        double cargo = 0;  // Variable para almacenar el cargo por depósito

        // Si el monto está entre 500,000 y 2,000,000
        if (monto >= 500000 && monto < 2000000) {
            cargo = 3000 + (monto * 0.01);  // Cargo de 3,000 más un 1% sobre el monto depositado
        } 
        // Si el monto está entre 2,000,000 y 10,000,000
        else if (monto >= 2000000 && monto < 10000000) {
            cargo = 2000 + (monto * 0.005);  // Cargo de 2,000 más un 0.5% sobre el monto depositado
        } 
        // Si el monto está entre 10,000,000 y 100,000,000
        else if (monto >= 10000000 && monto < 100000000) {
            cargo = monto * 0.018;  // Cargo del 1.8% sobre el monto depositado
        } 
        // Si el monto es mayor o igual a 100,000,000
        else if (monto >= 100000000) {
            cargo = monto * 0.02;  // Cargo del 2% sobre el monto depositado
        }

        // Registramos la transacción del cargo por depósito
        registrarTransaccion("Cargo por depósito: -$" + cargo);
        // Realizamos el retiro del cargo del saldo de la cuenta
        retirar(cargo);  // Realiza la deducción del saldo correspondiente al cargo por depósito
    }
}
