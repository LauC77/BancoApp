package banco;
import java.util.ArrayList;

public class Banco {
    private String nombre;
    private ArrayList<Cuenta> cuentas;


    public Banco(String nombre) {
        this.nombre = nombre;
        this.cuentas = new ArrayList<>();
    }

    public void abrirCuenta(Cuenta cuenta) {
        cuentas.add(cuenta);
  
    }

    public boolean transferir(int origen, int destino, double monto) {
        Cuenta cuentaOrigen = buscarCuenta(origen);
        Cuenta cuentaDestino = buscarCuenta(destino);

        if (cuentaOrigen != null && cuentaDestino != null) {
            if (cuentaOrigen.retirar(monto)) {
                cuentaDestino.depositar(monto);
                System.out.println("Transferencia realizada.");
                return true;
            }
        }
        System.out.println("Error en la transferencia.");
        return false;
    }

public boolean retirarCajero(int numeroCuenta, double monto, boolean cajeroPropio) {
    Cuenta cuenta = buscarCuenta(numeroCuenta);
    if (cuenta != null) {
        double montoFinal = cajeroPropio ? monto : (monto + 4500);
        if (cuenta.retirar(montoFinal)) {
            if (!cajeroPropio) {
                cuenta.registrarTransaccion("Comisión por retiro en cajero externo: -$4500");
            }
            System.out.println("Retiro exitoso.");
            return true;
        }
    }
    System.out.println("Fondos insuficientes.");
    return false;
}



    public void cierreDeMes() {
        for (Cuenta cuenta : cuentas) {
            cuenta.aplicarCargoMensual();
            cuenta.mostrarEstadoCuenta();
        }
    }

    Cuenta buscarCuenta(int numeroCuenta) {
        for (Cuenta cuenta : cuentas) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                return cuenta;
            }
        }
        return null;
    }

     public ArrayList<Cuenta> getCuentas() {  // Método corregido
        return cuentas;
    }
    }

