package Compiladores;

import java.util.*;

public class Scanner {

    private final String source;

    private final List<Token> tokens = new ArrayList<>();

    public int linea = 1;

    private static final Map<String, TipoToken> palabrasReservadas;
    static {
        palabrasReservadas = new HashMap<>();
        palabrasReservadas.put("and", TipoToken.AND);
        palabrasReservadas.put("class", TipoToken.CLASS);
        palabrasReservadas.put("else", TipoToken.ELSE);
        palabrasReservadas.put("false",TipoToken.FALSE );
        palabrasReservadas.put("for", TipoToken.FOR);
        palabrasReservadas.put("func",TipoToken.FUNC ); //definir funciones
        palabrasReservadas.put("if", TipoToken.IF);
        palabrasReservadas.put("null",TipoToken.NULL );
        palabrasReservadas.put("or", TipoToken.OR);
        palabrasReservadas.put("print", TipoToken.PRINT);
        palabrasReservadas.put("return",TipoToken.RETURN);
        palabrasReservadas.put("super",TipoToken.SUPER);
        palabrasReservadas.put("this", TipoToken.THIS);
        palabrasReservadas.put("true", TipoToken.TRUE);
        palabrasReservadas.put("var", TipoToken.VAR); //definir variables
        palabrasReservadas.put("while", TipoToken.WHILE);
    }

    Scanner(String source){
        this.source = source;
    }

    List<Token> scanTokens(){
        //Aquí va el corazón del scanner.

        /*
        Analizar el texto de entrada para extraer todos los tokens
        y al final agregar el token de fin de archivo
         */

        String readChain = source;
        String subChain;
        String concatChain = "";
        Double numberChain = 0.0;

        int large;
        large = readChain.length();
        System.out.println(large);

        int state = 0;

        for (int i = 0; i<large; i+=1){

            String c = readChain.substring(i,i+1);
            System.out.println(c);

            switch (state) {
                case 0:
                    switch (c) {
                        case "(" -> tokens.add(new Token(TipoToken.LEFT_PARANTHESIS, "(", null, linea));
                        case ")" -> tokens.add(new Token(TipoToken.RIGHT_PARANTHESIS, ")", null, linea));
                        case "{" -> tokens.add(new Token(TipoToken.LEFT_BRACKET, "{", null, linea));
                        case "}" -> tokens.add(new Token(TipoToken.RIGHT_BRACKET, "}", null, linea));
                        case "," -> tokens.add(new Token(TipoToken.COMMA, ",", null, linea));
                        case "." -> tokens.add(new Token(TipoToken.POINT, ".", null, linea));
                        case "/" -> tokens.add(new Token(TipoToken.SLASH, "/", null, linea));
                        case "-" -> tokens.add(new Token(TipoToken.MINUS_SIGN, "-", null, linea));
                        case "+" -> tokens.add(new Token(TipoToken.PLUS_SIGN, "+", null, linea));
                        case "*" -> tokens.add(new Token(TipoToken.MULTIPLICATION_SIGN, "*", null, linea));
                        case ";" -> tokens.add(new Token(TipoToken.SEMICOLON, ";", null, linea));
                        case "!" -> {
                            if (readChain.charAt(i + 1) == '=') {
                                tokens.add(new Token(TipoToken.NOT_EQUALS_OPERATOR, "!=", null, linea));
                                i++;
                            } else
                                tokens.add(new Token(TipoToken.NEGATE_OPERATOR, "!", null, linea));
                        }
                        case "=" -> {
                            if (readChain.charAt(i + 1) == '=') {
                                tokens.add(new Token(TipoToken.EQUALS_OPERATOR, "==", null, linea));
                                i++;
                            } else
                                tokens.add(new Token(TipoToken.EQUALS_SIGN, "=", null, linea));
                        }
                        case "<" -> {
                            if (readChain.charAt(i + 1) == '=') {
                                tokens.add(new Token(TipoToken.LESS_THAN_EQUALS, "<=", null, linea));
                                i++;
                            } else
                                tokens.add(new Token(TipoToken.LESS_THAN, "<", null, linea));
                        }
                        case ">" -> {
                            if (readChain.charAt(i + 1) == '=') {
                                tokens.add(new Token(TipoToken.GREATHER_THAN_EQUALS, ">=", null, linea));
                                i++;
                            } else
                                tokens.add(new Token(TipoToken.GREATER_THAN, ">", null, linea));
                        }

                        default -> {
                            if (c.matches("[A-Za-z0-9]")){
                                state = 10;
                                i--;
                            }
                        }
                    }
                    break;
                case 10:
                    if (c.matches("[A-Za-z0-9.]")) {
                            subChain = readChain.substring(i,i+1);
                            concatChain = concatChain.concat(subChain);
                    } else {
                        if (concatChain.matches("[0-9.]+")){
                            try {
                                numberChain = Double.parseDouble(concatChain);
                            }catch (NumberFormatException ex){
                                ex.printStackTrace();
                            }
                            tokens.add(new Token(TipoToken.NUMBER, "Number",numberChain , linea));
                        } else{
                            tokens.add(new Token(TipoToken.TEXT, "Text",concatChain , linea));
                        }

                        state = 0;
                        i--;
                    }
                    // generar el token
                    // retroceder i en 1
                    //comparar el lexema con las palabras reservadas
                    //Si coincide, creas el token correspondiente para la palabra reservada
                    // caso contrario creas el token de identificador

                    break;

            }


        }
        tokens.add(new Token(TipoToken.EOF, "", null, linea));
        return tokens;
    }

    /*
Signos o símbolos del lenguaje:
( - LEFT_PARANTHESIS
) - RIGHT_PARANTHESIS
{ - LEFT_BRACKET
} - RIGTH_BRACKET
, - COMMA
. - POINT
; - SEMICOLON
- - MINUS
+ - PLUS
* - MULTIPLICATION
/ - SLASH
! - NEGATE_OPERATOR
!= - NOT_EQUALS_OPERATOR
= - EQUALS_SIGN
==  EQUALS_OPERATOR
< - LESS_THAN
<= - LESS_THAN_EQUALS
> - GREATHER_THAN
>= - GREATHER_THAN_EQUALS
// -> comentarios (no se genera token)
/* ... * / -> comentarios (no se genera token)
Identificador,
Cadena
Numero
Cada palabra reservada tiene su nombre de token

 */

}
