package com.cielo.clients_pre_register.utils;

public class Util {

    public static String cpfFormat(String cpf) {

        int maxLength = 11;
        String originalCpf = cpf.replaceAll("[.-]", "");

        int lengthDiff = maxLength - originalCpf.length();

        StringBuilder formattedString = new StringBuilder();
        if (lengthDiff > 0) {
            for (int i = 0; i < lengthDiff; i++) {
                formattedString.append('0');
            }
        }

        formattedString.append(originalCpf);
        return formattedString.toString();
    }


    public static String cnpjFormat(String cnpj) {

        int maxLength = 14;
        String originalCnpj = cnpj.replaceAll("[./-]", "");

        int lengthDiff = maxLength - originalCnpj.length();

        StringBuilder formattedString = new StringBuilder();
        if (lengthDiff > 0) {
            for (int i = 0; i < lengthDiff; i++) {
                formattedString.append('0');
            }
        }

        formattedString.append(originalCnpj);
        return formattedString.toString();
    }


}
