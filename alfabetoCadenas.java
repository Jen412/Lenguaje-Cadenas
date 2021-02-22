import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class alfabetoCadenas {
    static Scanner read = new Scanner(System.in);

    public static void main(String[] args) {
        boolean b = false;
        int n = longitudAlf();
        char[] alfabeto = generarAlfabeto(n);
        do {
            menu();
            char opc = read.next().charAt(0);
            read.nextLine();
            switch (opc) {
                case '1':
                    n = longitudAlf();
                    alfabeto = null;
                    alfabeto = generarAlfabeto(n);
                    break;
                case '2':
                    lexPos(alfabeto, n);
                    break;
                case '3':
                    posLex(alfabeto, n);
                    break;
                case '4':
                    visualizarAlf(alfabeto);
                    break;
                case '5':
                    b = true;
                    break;

                default:
                    System.out.println("Opcion no valida\n");
                    break;
            }
        } while (b == false);
    }

    public static boolean validarN(String s) {
        boolean b = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1' || s.charAt(i) == '2' || s.charAt(i) == '3' || s.charAt(i) == '4'
                    || s.charAt(i) == '5' || s.charAt(i) == '6' || s.charAt(i) == '7' || s.charAt(i) == '8'
                    || s.charAt(i) == '9') {
                b = true;
            }
        }
        return b;
    }

    public static char[] generarAlfabeto(int n) {
        boolean b = false;
        String s;
        char c[] = new char[n];
        for (int i = 0; i < n; i++) {
            do {
                System.out.println("Ingrese el Alfabeto");
                System.out.print("Alfabeto[" + (i) + "]: ");
                s = read.next();
                if (validaLon(s) == true) {
                    c[i] = s.charAt(0);
                    b = true;
                } else {
                    System.out.println("\nSolo puede ser un caracter\n");
                }
                if (validarRep(c, i) == false) {
                    System.out.println("\nNo se pueden tener valores repetidos\n");
                    b = false;
                }
            } while (b == false);
        }
        return c;
    }

    public static void menu() {
        System.out.println("Calculo de alfabetos y cadenas\n");
        System.out.println("Cambiar Alfabeto                 [1]");
        System.out.println("Buscar un Lexema por su posicion [2]");
        System.out.println("Buscar la posicion por el lexema [3]");
        System.out.println("Visualizar alfabeto              [4]");
        System.out.println("Salir                            [5]");
        System.out.print("Opcion a realizar [1-5]: ");
    }

    public static boolean validaLon(String s) {
        boolean b = true;
        if (s.length() == 0) {
            b = false;
        } else if (s.length() > 1) {
            b = false;
        }
        return b;
    }

    private static boolean validarRep(char[] c, int p) {
        boolean b = true;
        int cont = 0;
        for (int i = 0; i < c.length; i++) {
            if (c[i] == c[p]) {
                cont++;
            }
        }
        if (cont > 1) {
            b = false;
        }
        return b;
    }

    public static int longitudAlf() {
        int n = 0;
        boolean b = false;
        do {
            System.out.print("Ingrese el tamaño de el alfabeto: ");

            String val = read.next();
            if (validarN(val) == true) {
                n = Integer.parseInt(val);
                b = true;
            } else {
                System.out.println("No son validas las letras");
            }
        } while (b == false);
        return n;
    }

    public static void lexPos(char[] c, int p) {
        String s;
        boolean b = false;
        int aux[] = new int[c.length];
        int pot[] = new int[15];
        int potDis[] = new int[15];
        int n = 0;
        do {
            System.out.print("Ingrese la posición del lexema: ");
            s = read.next();
            if (validarN(s) == true) {
                n = Integer.parseInt(s);
                b = true;
            } else {
                System.out.println("No se permiten letras");
            }
        } while (b == false);
        for (int i = 0; i < c.length; i++) {
            aux[i] = i + 1;
        }
        for (int i = pot.length - 1; i >= 0; i--) {
            pot[i] = (int) Math.pow(p, i);
            // System.out.println(pot[i]);
        }
        int suma = 0;
        for (int i = potDis.length - 1; i >=0; i--) {
            suma = 0;
            for (int j = 0; j < i; j++) {
                suma += pot[j];
            }
            potDis[i] = suma;
            // System.out.println(potDis[i]);
        }
        b=false;
        int m=0;
        int comprobacion=0;
        s="";
        do {
            for (int i = pot.length-1; i>= 0; i--) {
                for (int j = aux.length-1; j >= 0; j--) {
                    comprobacion= m+(pot[i]*aux[j])+potDis[i];
                    if (comprobacion>n) {
                        comprobacion=0;
                    }       
                    else{
                        m+=pot[i]*aux[j];
                        s+=c[j];
                    }
                }
                
            }
            if(m==n){
                b=true;
            }
            
        } while (b==false);
        System.out.println("El lexema es: "+s);
    }

    public static void posLex(char[] c, int n) {
        String s;
        boolean b = false;
        int aux[] = new int[c.length];
        char aux2[];
        int pot[];
        int resultado = 0;
        do {
            System.out.print("Ingrese una cadena valida: ");
            s = read.nextLine();
            if (validCad(s, c) == false) {
                b = true;
            } else {
                System.out.println("Cadena no valida");
            }
        } while (b == false);

        pot = new int[s.length()];
        for (int i = 0; i < c.length; i++) {
            aux[i] = i + 1;
        }
        aux2 = new char[s.length()];
        int bb = s.length() - 1;
        for (int i = 0; i < pot.length; i++) {
            pot[i] = (int) Math.pow(n, i);
            aux2[bb] = s.charAt(i);
            bb--;
        }
        for (int i = 0; i < pot.length; i++) {
            for (int j = 0; j < c.length; j++) {
                if (aux2[i] == c[j]) {
                    resultado = resultado + (aux[j] * pot[i]);
                }
            }
        }
        System.out.println("El resuldato es: " + resultado);
    }

    public static boolean validCad(String s, char[] c) {
        boolean b = true;
        String x = "";
        for (int i = 0; i < c.length; i++) {
            if (i != c.length - 1) {
                x += c[i];
            } else {
                x += c[i] + " ";
            }
        }
        x = "[^ " + x + "]";
        Pattern pat = Pattern.compile(x);
        Matcher mat = pat.matcher(s);
        if (mat.find()) {
            b = true;
        } else {
            b = false;
        }
        return b;
    }

    public static void visualizarAlf(char[] c) {
        String s = "";
        s += "El Alfabeto es: [";
        for (int i = 0; i < c.length; i++) {
            if (i == c.length) {
                s += c[i] + " ";
            } else if (i == 0) {
                s += " " + c[i];
            } else {
                s += ", " + c[i];
            }
        }
        s += " ]";
        System.out.println(s);
    }
}