package Metodos;

import java.util.List;

public class Conector {

    String Files = "", Format = "", Forms = "", FinalForm = "", ValidAction = "";
    int idDoc = 0, module = 12;
    boolean result = false;
    String[] DtaFormat = Format.toString().replace("]/[", "///").replace("[[", "[").replace("]]", "]").split("///");

    public boolean UpdateDocument(List dataDoc, String Files) {
        
        Forms = "[[" + module + "][" + Files + "]]";
        for (int i = 0; i < DtaFormat.length; i++) {
            if (i == DtaFormat.length - 1) {
                if (i != module) {
                    FinalForm += "[" + DtaFormat[i] + "]";
                } else {
                    FinalForm += Forms;
                }
            } else {
                if (i != module) {
                    FinalForm += "[" + DtaFormat[i] + "]/";
                } else {
                    FinalForm += Forms + "/";
                }
            }
        }
        if (ValidAction.equals("2")) {
            module++;
        }
        return result;
    }

}
