package com.company;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * in this class we test the code
 */
public class Main {

    /**
     * in this method we print the help text in console
     */
    public static void help() {
        // the -h/-help command
        System.out.println("\033[0;32m" + "Usage: curl [options...] <url>\n" + "\033[0m");
        // //-a
        // System.out.println("\033[0;32m"+"-a, --auth"+"\033[0m"+" <data> auth header
        // of bearer token kind\n" +
        // " ex: curl <url> -a/--auth token123");
        // -d
        System.out.println("\033[0;32m" + "-d, --data" + "\033[0m" + "         <data>   HTTP POST data\n" +
                "\033[0;32m" + "--data-urlencoded" + "\033[0m  <data> HTTP POST data url encoded\n" +
                "                    ex: curl <url> -M POST -d \"data1=value1&data2=value2\"\n" +
                "                    ex: curl <url> -M POST --data-urlencode \"data1=value1&data2=value2\"\n");
        // //-f
        // System.out.println("\033[0;32m"+"-f, --follow "+"\033[0m follow
        // redirection");
        // fire
        System.out.println("\033[0;32m" + "fire"
                + "\033[0m                 use to trigger saved request by their index of use. Use \"curl list\" to see the indexes");
        // -h
        System.out.println("\033[0;32m" + "-h, --help    " + "\033[0m       This help text\n");
        // -H
        System.out.println(
                "\033[0;32m" + "-H, --headers" + "\033[0m        <header/@file> Pass custom header(s) to server\n");
        System.out.println("                    ex: \"header1=value1;header2=value2\"");
        // -i
        System.out.println(
                "\033[0;32m" + "-i, --include   " + "\033[0m     Include protocol response headers in the output\n");
        // //-j
        // System.out.println("\033[0;32m"+"-j, --json "+"\033[0m used to set the the
        // message body format ");
        // System.out.println(" ex:curl <url> -M POST -j/--json
        // {\"key1\":\"value1\",\"key2\":\"value2\"} ");
        // -M
        System.out.println("\033[0;32m" + "-M, --method   " + "\033[0m     specify method request");
        // list
        System.out.println("\033[0;32m" + "list     " + "\033[0m           list the saved requests");
        // -O
        System.out.println("\033[0;32m" + "-O, --output" + "\033[0m        <file> Write to file instead of stdout");
        // //-q
        // System.out.println("\033[0;32m" + "-q, --query "+"\033[0m set the query
        // params of the request\n" +
        // " ex: curl <url> -q/--query \"queryParam1=value1&queryParam2=value2\" -with
        // no space in between-\"");
        // //remove
        // System.out.println("\033[0;32m" + "remove "+"\033[0m remove request from the
        // list of saved requests");
        // -S
        System.out.println("\033[0;32m" + "-S, --save " + "\033[0m          save the request in the system ");
        // //--upload
        // System.out.println("\033[0;32m"+"--upload "+"\033[0m <absolute path> -upload
        // existing file using its absolute path-");

    }

    /**
     * @param args
     * @throws IOException
     *                     in this method we read line from console with scanner
     */
    public static void main(String[] args) throws IOException {
        // HttpClient httpClient=new HttpClient();
        // formData();
        // CheckLine checkLine=new CheckLine();
        // ArrayList<String>line=new ArrayList<>();
        // line.add("fire");
        // line.add("1569784");
        // line.add("2");
        // checkLine.checkFire(line);
        // ArrayList<Integer>arr=checkLine.getfire();
        // for(int i=0;i<arr.size();i++){
        // System.out.println(arr.get(i));
        // }

        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            HttpClient myClient = new HttpClient();
            CheckLine checkLine = new CheckLine();
            String[] arr = in.nextLine().split(" ");
            ArrayList<String> line = new ArrayList<>();
            for (String s : arr) {
                line.add(s);

            }
            Boolean checknextline = false;
            ///
            Boolean checkHelp = checkLine.checkHelp(line);
            boolean checklinehelp = false;
            if (checkHelp.equals(true)) {
                help();
                checklinehelp = true;
            }
            if (checklinehelp == false) {
                Boolean checklist = checkLine.checklist(line);
                boolean checklinelist = false;
                if (checklist.equals(true)) {
                    /// sedazadan tabe list
                    myClient.listRequest();
                    checklinelist = true;
                    return;

                }

                if (checklinelist == false) {
                    Boolean checkFire = checkLine.checkFire(line);
                    Boolean checklinefire2 = false;
                    if (checkFire.equals(true)) {
                        /// seda zadan tabe fire

                        myClient.fire(checkLine.getfire());
                        checklinefire2 = true;

                    }

                    if (checklinefire2.equals(false)) {

                        Boolean checkurl = checkLine.checkUrl(line);
                        Boolean checkHeaders = checkLine.checkHeaders(line);
                        Boolean checkData = checkLine.checkData(line);
                        Boolean checkMethod = checkLine.checkMethod(line);
                        Boolean checkiArgoman = checkLine.checkiArgoman(line);
                        Boolean checkOutput = checkLine.checkOutput(line);
                        Boolean checkSave = checkLine.checkSave(line);

                        if (checkurl.equals(false) || checkMethod.equals(false) ||
                                ((line.contains("-H") || line.contains("--headers")) && checkHeaders.equals(false)) ||
                                ((line.contains("-h") || line.contains("--help")) && checkHelp.equals(false)) ||
                                ((line.contains("-d") || line.contains("--data")) && checkData.equals(false)) ||
                                ((line.contains("-i") && checkiArgoman.equals(false))) ||
                                ((line.contains("-O") || line.contains("--output")) && checkOutput.equals(false)) ||
                                ((line.contains("-S") || line.contains("--save")) && checkSave.equals(false)) ||
                                ((line.contains("list") && checklist.equals(false))) ||
                                ((line.contains("fire") && checkFire.equals(false)))) {
                            /// chap peygham khata
                            System.out.println("this line is not current please try again");
                            checknextline = true;
                            System.out.println(
                                    (line.contains("-H") || line.contains("--headers")) && checkHeaders.equals(false));

                        }
                        if (checknextline.equals(false)) {
                            String url2 = checkLine.getUrl();
                            String method2 = checkLine.getMethod();
                            String nameoffileoutput2 = checkLine.getNameOfFileOutput();
                            HashMap<String, String> headers2 = checkLine.getHeaders();
                            HashMap<String, String> datas2 = checkLine.getDatas();
                            ArrayList<Integer> fire2 = checkLine.getfire();
                            System.out.println(url2);
                            System.out.println(method2);
                            System.out.println(nameoffileoutput2);
                            if (method2.equals("GET")) {
                                myClient.getMethod(url2, headers2, checkiArgoman, checkOutput, nameoffileoutput2,
                                        checkSave, checkData, datas2);

                            } else if (method2.equals("POST")) {
                                myClient.postMethod(url2, headers2, checkiArgoman, checkOutput, nameoffileoutput2,
                                        checkSave, checkData, datas2);

                            } else if (method2.equals("PUT")) {
                                myClient.putMethod(url2, headers2, checkiArgoman, checkOutput, nameoffileoutput2,
                                        checkSave, checkData, datas2);

                            } else if (method2.equals("DELETE")) {
                                myClient.deleteMethod(url2, headers2, checkiArgoman, checkOutput, nameoffileoutput2,
                                        checkSave, checkData, datas2);

                            }

                        }

                    }

                }

            }

        }

    }
}
