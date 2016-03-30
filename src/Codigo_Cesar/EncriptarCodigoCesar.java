package Codigo_Cesar;

import java.util.Scanner;

public class EncriptarCodigoCesar {

    public static void main (String[] args)  {

        Scanner teclat = new Scanner(System.in);	// Teclado

        System.out.println("Introduce una cadena: ");
        String cadena = teclat.nextLine();  	// Leemos el String

        cadena = cadena.toLowerCase();	// Lo paso a minusculas

        String alfabeto = "abcdefghijklmnopqrstuvwxyz"; // String del alfabeto
        String palabraFinal = "";   // String que usaremos para construir la palabra final

        for(int iterador = 0; iterador < cadena.length(); iterador++){  // este for recorrera toda la palabra introducida

            int posicionEnAlfabeto = 0;

            char letraExtraida = cadena.charAt(iterador);	// Sacara la letra en char

            for(int iterador1 = 0; iterador1 < alfabeto.length(); iterador1++){ // la buscara dentro del alfabeto y encontrara su posicion
                if(letraExtraida == alfabeto.charAt(iterador1)){   // Si la letra es igual a la letra en la posicion de nuestro iterador
                    posicionEnAlfabeto = iterador1; // Guardamos la posicion en la que se encuentra
                    break;  // Una vez encontrada cortamos el bucle
                }
            }

            // Estos dos if's son para evitar problemas con las dos ultimas letras "y" & "z"
            // Las ultima y penultima letra seran igual a la pimera y segunda respectivamente

            if(posicionEnAlfabeto == alfabeto.length() - 2){
                posicionEnAlfabeto = -2;
            }
            else if(posicionEnAlfabeto == alfabeto.length() - 1){
                posicionEnAlfabeto = -1;
            }

            // Una vez encontrada la posicion extraemos la letra que este dos posiciones por delante en el alfabeto

            char letraCifrada = alfabeto.charAt(posicionEnAlfabeto + 2);

            palabraFinal = palabraFinal + letraCifrada; // Y la añadimos a nuestra palabra

            // Este proceso se repite con cada letra de la palabra introducida
        }

        System.out.println("La cadena cifrada : " + palabraFinal);  // La mostramos en pantalla
        teclat.close();
    }
}