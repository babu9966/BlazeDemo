import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;

public class AllVovelsRemove {

    public static void main(String[] args) {

        String str = "Maheshbabbhu";
        String vovels = "aeiouAEIOU";
        String output = "";
        for (char c : str.toCharArray()) {
            if (vovels.indexOf(c) == -1){

            output = output + c;
        }
    }
        System.out.println(output);
    }

}

