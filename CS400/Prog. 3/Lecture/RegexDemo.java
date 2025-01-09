import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
    public static void main(String[] args){
        String s = "According to some, the quick brown fox jumps over the lazy dog. But according to the dog, the lazy fox jumps over the quick brown dog";

        // regex can be used by a number of mathods accross the java api:
        //s.split("");

        // These classes provide an interface to find and replace regex
        // patterns within a given String. Here's an example that only
        // prints out the matches of a single capture group
        Pattern pattern = Pattern.compile("(\\w)+\\s(fox)"); // order of capture groups is left -> right where capt. groups is text inside "()"
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()){
            System.out.println(matcher.group(0)); // group is the capture group (0 = whole match)
        }
    }
}

// use grep [regex] [file.name] to print out file text with regex highlighted 
// to handle bash characters in the regex, surround with single quotations ''

// use 'quotes' to bash control chars
// use -P for more regex syntax (Perl)
// -n for showing line numbers
// -o for matching occurrences within a line rather than a full line
// -i for case insensitive matches 
// -v for inverting matches

//curl takes url to make request to and 