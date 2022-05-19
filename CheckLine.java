package com.company;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * in this class we checked the line for httpclient
 */
public class CheckLine {
    private Boolean checkUrl = false;
    private String url = null;
    private Boolean checkMethod = false;
    private String method = null;
    private Boolean checkIargoman = false;
    private Boolean checkHelp = false;
    private Boolean checkSave = false;
    private Boolean checkOutput = false;
    private Boolean checkNameFileOutput = false;
    private String nameOfFileOutput = null;
    private Boolean checkHeader = false;
    private HashMap<String, String> headers = new HashMap<>();
    private Boolean checkDashd = false;
    private HashMap<String, String> datas = new HashMap<>();
    private Boolean checklist = false;
    private Boolean checkFire = false;
    public ArrayList<Integer> numbersOfFire = new ArrayList<>();

    /**
     * @return gets the array of fires number
     */
    public ArrayList<Integer> getfire() {
        return numbersOfFire;
    }

    /**
     * @return gets the array of headers
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, String> getDatas() {
        return datas;
    }

    public String getMethod() {
        return method;
    }

    public String getNameOfFileOutput() {
        return nameOfFileOutput;
    }

    public String getUrl() {
        return url;
    }

    /**
     * @param url check url is current or not
     * @return true if the url is valid .
     */
    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    // private static String URI = "http://www.google.com";

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public Boolean checkUrl(ArrayList<String> line) {
        if (line.contains("--url")) {
            int indexofurl = line.indexOf("--url");
            if (indexofurl < line.size() - 1) {
                indexofurl++;
                if (isValid(line.get(indexofurl))) {
                    checkUrl = true;
                    url = line.get(indexofurl);

                }
            }
        }
        if (checkUrl.equals(false)) {
            // System.out.println("the url is not correct :(");
        }
        return checkUrl;

    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */

    public Boolean checkMethod(ArrayList<String> line) {
        for (int i = 0; i < line.size(); i++) {
            System.out.println(line.get(i));
        }

        if (line.contains("-M") || line.contains("--method")) {
            int indexofmethod = 0;
            if (line.contains("-M")) {
                indexofmethod = line.indexOf("-M");

            } else if (line.contains("--method")) {
                indexofmethod = line.indexOf("--method");

            }
            if (indexofmethod < line.size() - 1) {
                indexofmethod++;
                if (line.get(indexofmethod).equals("GET")) {

                    checkMethod = true;
                    method = "GET";

                } else if (line.get(indexofmethod).equals("POST")) {

                    checkMethod = true;
                    method = "POST";

                } else if (line.get(indexofmethod).equals("PUT")) {

                    checkMethod = true;
                    method = "PUT";

                } else if (line.get(indexofmethod).equals("DELETE")) {

                    checkMethod = true;
                    method = "DELETE";

                }
            }
        } else if (!(line.contains("-M")) && !(line.contains("--method"))) {
            if (!(line.contains("PUT")) && !(line.contains("POST")) && !(line.contains("GET"))
                    && !(line.contains("DELETE"))) {
                checkMethod = true;
                method = "GET";
            }
        }
        if (checkMethod.equals(false)) {
            System.out.println("Error in Method Name :(");
        }

        return checkMethod;
    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public Boolean checkiArgoman(ArrayList<String> line) {
        if (line.contains("-i")) {
            checkIargoman = true;
        }
        return checkIargoman;
    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public Boolean checkHelp(ArrayList<String> line) {
        if (line.contains("-h") || line.contains("--help")) {
            checkHelp = true;
        }
        return checkHelp;
    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public Boolean checkSave(ArrayList<String> line) {
        if (line.contains("-S") || line.contains("--save")) {
            checkSave = true;
        }
        return checkSave;
    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public Boolean checkOutput(ArrayList<String> line) {
        Boolean check = false;
        if (line.contains("-O") || line.contains("--output")) {
            checkOutput = true;
            int indexofoutput = 0;
            if (line.contains("-O")) {
                indexofoutput = line.indexOf("-O");
            }
            if (line.contains("--output")) {
                indexofoutput = line.indexOf("--output");

            }
            if (indexofoutput == (line.size() - 1)) {
                nameOfFileOutput = "output_[CurrentDate]";
                checkNameFileOutput = false;
                System.out.println("no name");
            }
            if (indexofoutput < (line.size() - 1)) {
                int indexofname = indexofoutput + 1;
                if (line.get(indexofname).equals("-i") || line.get(indexofname).equals("--url") ||
                        line.get(indexofname).equals("-M") || line.get(indexofname).equals("--method") ||
                        line.get(indexofname).equals("-h") || line.get(indexofname).equals("--help") ||
                        line.get(indexofname).equals("-H") || line.get(indexofname).equals("--headers") ||
                        line.get(indexofname).equals("-S") || line.get(indexofname).equals("--save") ||
                        line.get(indexofname).equals("-d") || line.get(indexofname).equals("--data")) {
                    nameOfFileOutput = "output_[CurrentDate]";
                    checkNameFileOutput = false;
                    check = true;
                    System.out.println("no name");

                } else if (check.equals(false)) {

                    nameOfFileOutput = line.get(indexofname);
                    checkNameFileOutput = true;
                    System.out.println("yes name");
                }
            }

        }
        // System.out.println(nameOfFileOutput);
        return checkOutput;
    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public boolean checkHeaders(ArrayList<String> line) {
        for (int i = 0; i < line.size(); i++) {
            System.out.println(line.get(i));
        }

        if (line.contains("-H") || line.contains("--headers")) {
            int indexofheader = 0;
            if (line.contains(("-H"))) {
                indexofheader = line.lastIndexOf("-H");
            }
            if (line.contains("--headers")) {
                indexofheader = line.lastIndexOf("--headers");

            }
            if (indexofheader < (line.size() - 1)) {
                int indexofheaderbody = indexofheader + 1;
                String header = line.get(indexofheaderbody);
                System.out.println(header);
                Boolean check1 = false;
                for (int i = 0; i < header.length(); i++) {
                    if (header.charAt(i) == ':') {
                        check1 = true;
                        break;

                    }
                }
                if (header.charAt(0) == '"' && header.charAt(header.length() - 1) == '"' && check1.equals(true)) {
                    checkHeader = true;
                    header = header.substring(1, header.length() - 1);
                    String[] arr2 = header.split(";");
                    for (String s3 : arr2) {
                        if (s3.contains(":")) {
                            String[] arr3 = s3.split(":");
                            headers.put(arr3[0], arr3[1]);
                        }
                    }

                }

            }

        }

        return checkHeader;

    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */

    public boolean checkData(ArrayList<String> line) {
        if (line.contains("-d") || line.contains("--data")) {
            int indexofdata = 0;
            if (line.contains(("-d"))) {
                indexofdata = line.lastIndexOf("-d");
            }
            if (line.contains("--data")) {
                indexofdata = line.lastIndexOf("--data");

            }
            if (indexofdata < (line.size() - 1)) {
                int indexofdatabody = indexofdata + 1;
                String data = line.get(indexofdatabody);
                Boolean check1 = false;
                for (int i = 0; i < data.length(); i++) {
                    if (data.charAt(i) == '=') {
                        check1 = true;
                        break;

                    }
                }
                if (data.charAt(0) == '"' && data.charAt(data.length() - 1) == '"' && check1.equals(true)) {
                    checkDashd = true;
                    data = data.substring(1, data.length() - 1);
                    String[] arr2 = data.split("&");
                    for (String s3 : arr2) {
                        if (s3.contains("=")) {
                            String[] arr3 = s3.split("=");
                            datas.put(arr3[0], arr3[1]);
                        }
                    }

                }

            }

        }

        return checkDashd;

    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public boolean checklist(ArrayList<String> line) {
        boolean check2 = false;
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).equals("-i") || line.get(i).equals("--url") ||
                    line.get(i).equals("-M") || line.get(i).equals("--method") ||
                    line.get(i).equals("-h") || line.get(i).equals("--help") ||
                    line.get(i).equals("-H") || line.get(i).equals("--headers") ||
                    line.get(i).equals("-S") || line.get(i).equals("--save") ||
                    line.get(i).equals("-d") || line.get(i).equals("--data")) {
                check2 = true;
                break;
            }
        }
        if (line.contains("list") && line.size() == 1 && check2 == false) {
            checklist = true;
        }
        return checklist;

    }

    /**
     * @param line is array of string in the line
     * @return true or false
     */
    public boolean checkFire(ArrayList<String> line) {
        boolean check3 = false;
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i).equals("-i") || line.get(i).equals("--url") ||
                    line.get(i).equals("-M") || line.get(i).equals("--method") ||
                    line.get(i).equals("-h") || line.get(i).equals("--help") ||
                    line.get(i).equals("-H") || line.get(i).equals("--headers") ||
                    line.get(i).equals("-S") || line.get(i).equals("--save") ||
                    line.get(i).equals("-d") || line.get(i).equals("--data")) {
                check3 = true;
                break;
            }

        }
        if (line.contains("fire") && line.size() > 1 && check3 == false) {

            checkFire = true;
            int indexoffire = line.indexOf("fire");
            try {
                for (int i = indexoffire + 1; i < line.size(); i++) {
                    Integer number = Integer.parseInt(line.get(i));
                    numbersOfFire.add(number);

                }

            } catch (NumberFormatException e) {
                System.out.println("please enter the current number");
            }

        }
        return checkFire;

    }

}
