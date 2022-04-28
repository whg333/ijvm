package com.whg.compile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokenizer {

    List<Token> parseTokens(String input){
        List<Token> tokens = new ArrayList<>();
        int current = 0;
        int length = input.length();

        while(current < length){
            char c = input.charAt(current);
            if(isSpace(c)){
                ++current;
            }else if(isParen(c)){
                tokens.add(new Token(Token.TokenType.paren, String.valueOf(c)));
                ++current;
            }else if(isDigit(c)){
                StringBuilder value = new StringBuilder();
                value.append(c);
                c = input.charAt(++current); // next char
                while(isDigit(c)){
                    value.append(c);
                    c = input.charAt(++current);
                }
                tokens.add(new Token(Token.TokenType.number, value.toString()));
            }else if(isLetter(c)){
                StringBuilder value = new StringBuilder();
                value.append(c);
                c = input.charAt(++current); // next char
                while(isLetter(c)){
                    value.append(c);
                    c = input.charAt(++current);
                }
                tokens.add(new Token(Token.TokenType.name, value.toString()));
            }else{
                throw new IllegalArgumentException("Unknown character: "+c);
            }
        }

        System.out.println(Arrays.toString(tokens.toArray(new Tokenizer.Token[0])));
        return tokens;
    }

    private boolean isParen(char c){
        return c == '(' || c == ')';
    }

    private boolean isDigit(char c){
        return Character.isDigit(c);
    }

    private boolean isLetter(char c){
        return Character.isLetter(c);
    }

    private boolean isSpace(char c){
        return Character.isSpaceChar(c) || Character.isWhitespace(c);
    }

    public static void main(String[] args) {
        System.out.println(Character.isSpaceChar(' '));
        System.out.println(Character.isWhitespace(' '));
        System.out.println(Character.isSpaceChar('\t'));
        System.out.println(Character.isWhitespace('\t'));
    }

    public static class Token {

        enum TokenType {

            paren, name, number,

        }

        final TokenType type;
        final String value;

        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "type: " + type +
                    ", value: '" + value + '\'' +
                    '}';
        }
    }

}
