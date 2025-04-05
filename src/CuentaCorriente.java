public class CuentaCorriente extends Cuenta {
    private static final double TASA_MANTENIMIENTO = 0.015;

    public CuentaCorriente(int numeroCuenta, String titular, String apellido, int edad, String representante, double saldo) {
        super(numeroCuenta, titular, apellido, edad, representante, saldo);
    }

    @Override
    public void aplicarCargosMensuales() {
        double cargo = saldo * TASA_MANTENIMIENTO;
        saldo -= cargo;
    }

    @Override
    public void calcularComisionDeposito(double monto) {
        double comision = 0;
        if (monto < 500000) {
            comision = 7000;
        } else if (monto >= 500000 && monto < 2000000) {
            comision = 5000 + (monto * 0.02);
        } else if (monto >= 2000000 && monto <= 10000000) {
            comision = 4000 + (monto * 0.02);
        } else if (monto > 10000000) {
            comision = monto * 0.033;
        }
        saldo -= comision;
    }
    
    public void emitirCheque(double monto) {
    double total = monto + 3000; // Monto del cheque + cargo por emisión
    if (saldo >= total) {
        saldo -= total;
        System.out.println("Cheque emitido por $" + monto + ". Se cobraron $3000 por comisión.");
    } else {
        System.out.println("Saldo insuficiente para emitir cheque.");
    }
}

}
