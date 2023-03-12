package Compiladores;

public enum TipoToken {

    // Crear un tipoToken por palabra reservada
    // Crear un tipoToken: identificador, una cadena y numero
    // Crear un tipoToken por cada "Signo del lenguaje" (ver clase Scanner)


    // Palabras clave:
    AND, CLASS, ELSE, FALSE, FOR, FUNC, IF, NULL, OR, PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

    // Indentificadores
    NUMBER, TEXT,

    // Separadores
    LEFT_BRACKET, RIGHT_BRACKET, COMMA, LEFT_PARANTHESIS, RIGHT_PARANTHESIS, POINT, SLASH, SEMICOLON,

    // Operadores
    MINUS_SIGN, MULTIPLICATION_SIGN, PLUS_SIGN, LESS_THAN, GREATER_THAN, EQUALS_SIGN, EQUALS_OPERATOR, NOT_EQUALS_OPERATOR,
    GREATHER_THAN_EQUALS, LESS_THAN_EQUALS, NEGATE_OPERATOR,

    // Final de cadena
    EOF

}
