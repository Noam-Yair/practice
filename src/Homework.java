import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Homework {
    /*
   * Homework:
   Given a number in the range of 1 to 1,000,000,000 provide a textual representation of the number -
   For example - 1123 -> One thousand twenty three
   * */
    static Map<String, String> singles =
            Map.of( "0", "zero","1","one", "2","two","3", "three","4", "four","5", "five","6", "six","7", "seven","8", "eight","9","nine");

    static List<String> below20 =
            Arrays.asList(new String[]{"ten" ,"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen","nineteen"});
    static List<String> tens =
            Arrays.asList(new String[]{"ten", "twenty", "thirty", "forty","fifty", "sixty", "seventy", "eighty","ninety"});

    public static String textRep(int num){
        String numAsString = String.valueOf(num);
        StringBuilder numRepresentation = new StringBuilder();
        int numOfDigits = numAsString.length();
        List<String> thousands = new ArrayList<>();
        if (numOfDigits > 9){
            thousands.add("billion");
        }
        if (numOfDigits > 6){
            thousands.add("million");
        }
        if (numOfDigits > 3){
            thousands.add("thousand");
        }
        List<String> finalStringsSplited = new ArrayList<>();
        for (int i=numAsString.length()-1; i>=0; i--){
            if (numOfDigits>=3){
                if (numAsString.substring(i-2, i+1).equals("000")) {}
                else if (numAsString.substring(i-2, i).equals("00")){
                    finalStringsSplited.add(0,singles.get(numAsString.substring(i, i+1)));
                }
                else if (numAsString.substring(i-2,i-1).equals("0")){
                    finalStringsSplited.add(0, tensRep(numAsString.substring(i-1, i+1)));
                }
                else {
                    finalStringsSplited.add(0, hundredsRep(numAsString.substring(i - 2, i + 1)));
                }
                i-=2;
                numOfDigits-=3;
            }
            else if (numOfDigits==2) {
                finalStringsSplited.add(0, tensRep(numAsString.substring(i-1, i+1)));
                i-=1;
                numOfDigits-=2;
            }
            else {
                finalStringsSplited.add(0, singles.get(numAsString.substring(i,i+1)));
            }
        }
        for (int j=0; j<finalStringsSplited.size(); j++){
            numRepresentation.append(finalStringsSplited.get(j));
            if (j<thousands.size()) {
                numRepresentation.append(" ").append(thousands.get(j)).append(" ");
            }
        }
        return numRepresentation.toString();
    }

    // returning the representation of 3 digits numbers
    private static String hundredsRep (String numAsString){
        String numRepresentation ="";
        String[] digits = numAsString.split("");
        numRepresentation += singles.get(digits[0]) + " hundred ";
        if (digits[1].equals("1")){
            numRepresentation += below20.get(Integer.parseInt(digits[2]));
        }
        else if (digits[1].equals("0") && !digits[2].equals("0")){
            numRepresentation += singles.get(digits[2]);
        }
        else if (digits[1].equals("0")){
            return numRepresentation;
        }
        else {
            numRepresentation += tens.get(Integer.parseInt(digits[1])-1);
            if(!digits[2].equals("0")){
                numRepresentation+= " " + singles.get(digits[2]);
            }
        }
        return numRepresentation;
    }

    // returning the representation of 2 digits numbers
    private static String tensRep (String numAsString) {
        String numRepresentation = "";
        String[] digits = numAsString.split("");
        if (digits[0].equals("1")) return below20.get(Integer.parseInt(digits[1]));
        numRepresentation += tens.get(Integer.parseInt(digits[0])-1);
        if(!digits[1].equals("0")){
            numRepresentation+= " " + singles.get(digits[1]);
        }
        return numRepresentation;
    }

    public static void main(String[] args) {
        System.out.println(textRep(4030));
    }
}


