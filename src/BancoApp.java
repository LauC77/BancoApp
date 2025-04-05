import java.util.Scanner;

public class BancoApp {

    private static int numCuenta=1;
    public static void main(String[] args) {
        Banco banco = new Banco("Banco Ejemplo");
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("--- MENÚ BANCO ---");
            System.out.println("1. Apertura de Cuentas");
            System.out.println("2. Transferencias");
            System.out.println("3. Retiro en Cajero");
            System.out.println("4. Cierre de Mes");
            System.out.println("5. Emitir Cheque");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                scanner.nextLine();
                System.out.println("Digite sus nombres");
                String nombres =scanner.nextLine();
                
                System.out.println("Digite sus apellidos");
                String apellidos =scanner.nextLine();
                
                System.out.println("Digite su edad");
                int edad =scanner.nextInt();
                scanner.nextLine();
                
                   String representante;
                if (edad < 18) {
                 System.out.println("Digite el nombre completo de su representante legal");
                representante = scanner.nextLine(); // Pedir el nombre del representante
                } else {
                representante = ""; // No necesita representante si es mayor de edad
                }
                    System.out.println("Ingrese el saldo inicial:");
                    double saldo = scanner.nextDouble();

                    System.out.println("Seleccione tipo de cuenta (1: Ahorro, 2: Corriente):");
                    int tipoCuenta = scanner.nextInt();

                    if (tipoCuenta == 1) {
                        banco.abrirCuenta(new CuentaAhorro(numCuenta, nombres, apellidos, edad, representante, saldo));
                        
                         System.out.println("Cuenta de ahorro creada exitosamente.");
                        
                    } else if (tipoCuenta == 2 && saldo >= 200000) {
                        banco.abrirCuenta(new CuentaCorriente(numCuenta, nombres, apellidos, edad, representante, saldo));
                       
                         System.out.println("Cuenta corriente creada exitosamente.");
                         
                    } else {
                        System.out.println("Saldo insuficiente.");
                        break;
                    }
                    System.out.println("Anote su número de cuenta: " + numCuenta );
                    numCuenta++;
                }
                case 3 -> {
                System.out.print("Ingrese el número de cuenta: ");
                int numeroCuenta = scanner.nextInt();

                System.out.print("Ingrese el monto a retirar: ");
                double monto = scanner.nextDouble();

                System.out.print("¿Está retirando en un cajero del banco? (1. Sí, 2. No): ");
                int opcionCajero = scanner.nextInt();
             

                boolean cajeroPropio = (opcionCajero == 1);
                
                Cuenta cuenta = banco.buscarCuenta(numeroCuenta); 

                if (banco.retirarCajero(numeroCuenta, monto, cajeroPropio)) {
                    double comision = cajeroPropio ? 0 : 4500;
                     cuenta.registrarTransaccion("Retiro en cajero: -$" + monto);
                    System.out.println("Se descontaron $" + comision + " de comisión.");
                    
                } else {
                    System.out.println("No se pudo realizar el retiro.");
                }
            }

                case 4 -> {
                    System.out.println("--- ESTADO DE CUENTAS ---");
                for (Cuenta cuenta : banco.getCuentas()) {
                cuenta.aplicarCargosMensuales(); // Aplica los cargos si tienes lógica en tus clases hijas
                cuenta.mostrarEstadoCuenta();    // Muestra el estado de cada cuenta
                 System.out.println("----------------------------");
    }
                }
                
                case 5 -> {
                System.out.println("¿Desea emitir un cheque? (1. Sí, 2. No)");
                int respuesta = scanner.nextInt();

                if (respuesta == 1) {
                    System.out.println("Ingrese el número de cuenta corriente:");
                    int numCuentaCheque = scanner.nextInt();

                   Cuenta cuenta = banco.buscarCuenta(numCuentaCheque); 

                    if (cuenta instanceof CuentaCorriente) { // Verifica si es cuenta corriente
                        System.out.println("Monto del cheque:");
                        double montoCheque = scanner.nextDouble();

                        if (cuenta.retirar(montoCheque + 3000)) { // Se retira el monto + comisión
                           cuenta.registrarTransaccion("Comisión emisión de Cheque: -$" + 3000);
                           cuenta.registrarTransaccion("Valor de Cheque: -$" + montoCheque);
                            System.out.println("Cheque emitido por $" + montoCheque + ". Se cobraron $3000 por comisión.");
                        } else {
                            System.out.println("Fondos insuficientes para emitir el cheque.");
                        }
                    } else {
                        System.out.println("Número de cuenta inválido o no es cuenta corriente.");
                    }
                }
                
            }
                case 6 -> System.out.println("Saliendo...");
            }
        } while (opcion != 6);

        scanner.close();
    }
}

