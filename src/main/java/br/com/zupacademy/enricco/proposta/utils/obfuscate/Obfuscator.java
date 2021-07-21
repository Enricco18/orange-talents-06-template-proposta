package br.com.zupacademy.enricco.proposta.utils.obfuscate;

import javax.validation.constraints.Email;
import java.math.BigDecimal;

public class Obfuscator {
    public static String obfuscateEmail(@Email String email){
        return  email.replaceAll("(^[^@]|(?!^)\\G)[^@]", "$1*");
    }
    public static String obfuscateName( String name) {
        String [] names = name.split("\\s");
        String maskedName = "";
        for (String unmaskedName: names
             ) {
            try {
                maskedName += maskString(unmaskedName,1,unmaskedName.length(),'*')+ " ";
            }catch (Exception e){
                maskedName += " ";
            }

        }
        return  maskedName.trim();
    }

    public static String obfuscateDocument( String document){
        String maskedDocument = document.replaceAll("(\\..{3})",".***");
        maskedDocument = maskedDocument.replaceAll("(/.{4})","/***");
        return maskedDocument;
    }

    public static String obfuscateSalary( BigDecimal salary){
        return  salary.toString().replaceAll("([0-9])","*");
    }
    public static String obfuscateAddress( String address){

        return  address.replaceAll("([^0-9|,|\\s|\\.])","*");
    }

    private static String maskString(String strText, int start, int end, char maskChar)
            throws Exception{

        if(strText == null || strText.equals(""))
            return "";

        if(start < 0)
            start = 0;

        if( end > strText.length() )
            end = strText.length();

        if(start > end)
            throw new Exception("End index cannot be greater than start index");

        int maskLength = end - start;

        if(maskLength == 0)
            return strText;

        StringBuilder sbMaskString = new StringBuilder(maskLength);

        for(int i = 0; i < maskLength; i++){
            sbMaskString.append(maskChar);
        }

        return strText.substring(0, start)
                + sbMaskString.toString()
                + strText.substring(start + maskLength);
    }
}

