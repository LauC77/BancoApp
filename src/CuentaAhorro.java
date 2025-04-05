public class CuentaAhorro extends Cuenta {
    private static final double TASA_INTERES_ANUAL = 0.022;

    public CuentaAhorro(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);
    }

    @Override
    public void aplicarCargosMensuales() {
        double interesMensual = (saldo * TASA_INTERES_ANUAL) / 12;
        saldo += interesMensual;
    }

    @Override
    public void calcularComisionDeposito(double monto) {
        double comision = 0;
        if (monto >= 500000 && monto < 2000000) {
            comision = 3000 + (monto * 0.01);
        } else if (monto >= 2000000 && monto <= 10000000) {
            comision = 2000 + (monto * 0.005);
        } else if (monto > 10000000 && monto < 100000000) {
            comision = monto * 0.018;
        } else if (monto >= 100000000) {
            comision = monto * 0.02;
        }
        saldo -= comision;
    }
}
