package com.company;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * this class create the http request
 */
public class HttpClient {
    // C:\Users\Lenovo\IdeaProjects\testdefult
    private static final String NOTES_PATH = "./notes/";
    private static final String OUT_PATH = "./body/";

    // It's a static initializer. It's executed when the class is loaded.
    // It's similar to constructor
    static {
        boolean isSuccessful = new File(NOTES_PATH).mkdirs();
        System.out.println("Creating " + NOTES_PATH + " directory is successful: " + isSuccessful);
        boolean isSuccessful2 = new File(OUT_PATH).mkdirs();
        System.out.println("Creating " + OUT_PATH + " directory is successful: " + isSuccessful);

    }

    /**
     * @param url           is url of request
     * @param headers       is headers of request
     * @param checkiArgoman is check -i Argoman Boolean of request
     * @param checkoutput   is check -o Argoman Boolean of request
     * @param nameoffileout is name of file for create this for save response body;
     * @param checksave     is check -s Argoman Boolean of request
     * @param dashd         is check -d Argoman Boolean of request
     * @param datas         is dataArray of request
     * @throws IOException is exception of create request
     */
    public static void getMethod(String url, HashMap<String, String> headers, Boolean checkiArgoman,
            Boolean checkoutput,
            String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) throws IOException {
        // ArrayList<Request>requests=new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet request = new HttpGet(url);

            if (headers.size() != 0) {
                for (String key : headers.keySet()) {
                    request.addHeader(key, headers.get(key));
                }
            }
            CloseableHttpResponse response = httpClient.execute(request);
            CloseableHttpResponse response2 = httpClient.execute(request);

            try {
                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion()); // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode()); // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                // HttpEntity entity2 = response.getEntity();

                if (entity != null) {
                    // return it as a String

                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
                if (checkiArgoman.equals(true)) {
                    Header[] headers2 = response.getAllHeaders();
                    for (Header header : headers2) {
                        System.out.println("Key : " + header.getName()
                                + " ,Value : " + header.getValue());
                    }

                }
                if (checkoutput.equals(true)) {

                    // String responseBody = EntityUtils.toString(response.getEntity());
                    //
                    saveOut(response2, nameoffileout);

                }

                if (checksave.equals(true)) {
                    writeRequest("GET", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd,
                            datas);
                    /// save request body in text file
                }

                if (dashd.equals(true)) {
                    // formData(datas,url,"GET");
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("please check your request");
            } finally {
                response.close();
            }

        } catch (ClientProtocolException e) {
            System.out.println("Target host is not specified");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("please check your request");
        } finally {
            httpClient.close();
        }

    }

    /**
     * @param url           is url of request
     * @param headers       is headers of request
     * @param checkiArgoman is check -i Argoman Boolean of request
     * @param checkoutput   is check -o Argoman Boolean of request
     * @param nameoffileout is name of file for create this for save response body;
     * @param checksave     is check -s Argoman Boolean of request
     * @param dashd         is check -d Argoman Boolean of request
     * @param datas         is dataArray of request
     * @throws IOException is exception of create request
     */

    public static void postMethod(String url, HashMap<String, String> headers, Boolean checkiArgoman,
            Boolean checkoutput,
            String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) throws IOException {
        // ArrayList<Request>requests=new ArrayList<>();

        if (dashd.equals(true)) {
            formData(datas, url, "POST", checkiArgoman, checkoutput, nameoffileout, headers);
            if (checksave.equals(true)) {
                writeRequest("POST", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd, datas);

                /// save request body in text file
            }

        } else {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpPost request = new HttpPost(url);

                if (headers.size() != 0) {
                    for (String key : headers.keySet()) {
                        request.addHeader(key, headers.get(key));
                    }
                }
                CloseableHttpResponse response = httpClient.execute(request);
                CloseableHttpResponse response2 = httpClient.execute(request);

                try {
                    // Get HttpResponse Status
                    System.out.println(response.getProtocolVersion()); // HTTP/1.1
                    System.out.println(response.getStatusLine().getStatusCode()); // 200
                    System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                    System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK

                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        String result = EntityUtils.toString(entity);
                        System.out.println(result);
                    }
                    if (checkiArgoman.equals(true)) {
                        Header[] headers2 = response.getAllHeaders();
                        for (Header header : headers2) {
                            System.out.println("Key : " + header.getName()
                                    + " ,Value : " + header.getValue());
                        }

                    }

                    if (checksave.equals(true)) {
                        writeRequest("POST", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd,
                                datas);

                        /// save request body in text file
                    }

                    // if (dashd.equals(true)) {
                    // formData(datas, url, "POST");
                    // }
                    if (checkoutput.equals(true)) {

                        // String responseBody = EntityUtils.toString(response.getEntity());
                        //
                        saveOut(response2, nameoffileout);

                    }

                } catch (Exception e) {
                    System.out.println("please check your request");
                } finally {
                    response.close();
                }

            } catch (Exception e) {
                System.out.println("please check your request");
            } finally {
                httpClient.close();
            }

        }

    }

    /**
     * @param url           is url of request
     * @param headers       is headers of request
     * @param checkiArgoman is check -i Argoman Boolean of request
     * @param checkoutput   is check -o Argoman Boolean of request
     * @param nameoffileout is name of file for create this for save response body;
     * @param checksave     is check -s Argoman Boolean of request
     * @param dashd         is check -d Argoman Boolean of request
     * @param datas         is dataArray of request
     * @throws IOException is exception of create request
     */
    public static void putMethod(String url, HashMap<String, String> headers, Boolean checkiArgoman,
            Boolean checkoutput,
            String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) throws IOException {
        // ArrayList<Request>requests=new ArrayList<>();

        if (dashd.equals(true)) {
            formData(datas, url, "PUT", checkiArgoman, checkoutput, nameoffileout, headers);
            if (checksave.equals(true)) {
                writeRequest("PUT", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd, datas);

                /// save request body in text file
            }
        } else {

            CloseableHttpClient httpClient = HttpClients.createDefault();
            try {
                HttpPut request = new HttpPut(url);

                if (headers.size() != 0) {
                    for (String key : headers.keySet()) {
                        request.addHeader(key, headers.get(key));
                    }
                }
                CloseableHttpResponse response = httpClient.execute(request);
                CloseableHttpResponse response2 = httpClient.execute(request);

                try {
                    // Get HttpResponse Status
                    System.out.println(response.getProtocolVersion()); // HTTP/1.1
                    System.out.println(response.getStatusLine().getStatusCode()); // 200
                    System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                    System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK

                    HttpEntity entity = response.getEntity();
                    if (entity != null) {
                        // return it as a String
                        String result = EntityUtils.toString(entity);
                        System.out.println(result);
                    }
                    if (checkiArgoman.equals(true)) {
                        Header[] headers2 = response.getAllHeaders();
                        for (Header header : headers2) {
                            System.out.println("Key : " + header.getName()
                                    + " ,Value : " + header.getValue());
                        }

                    }

                    if (checksave.equals(true)) {
                        writeRequest("PUT", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd,
                                datas);

                        /// save request body in text file
                    }

                    // if (dashd.equals(true)) {
                    // formData(datas, url, "PUT");
                    // }
                    if (checkoutput.equals(true)) {

                        // String responseBody = EntityUtils.toString(response.getEntity());
                        //
                        saveOut(response2, nameoffileout);

                    }

                } catch (Exception e) {
                    System.out.println("please check your request");
                } finally {
                    response.close();
                }

            } catch (Exception e) {
                System.out.println("please check your request");
            } finally {
                httpClient.close();

            }
        }

    }

    /**
     * @param url           is url of request
     * @param headers       is headers of request
     * @param checkiArgoman is check -i Argoman Boolean of request
     * @param checkoutput   is check -o Argoman Boolean of request
     * @param nameoffileout is name of file for create this for save response body;
     * @param checksave     is check -s Argoman Boolean of request
     * @param dashd         is check -d Argoman Boolean of request
     * @param datas         is dataArray of request
     * @throws IOException is exception of create request
     */
    public static void deleteMethod(String url, HashMap<String, String> headers, Boolean checkiArgoman,
            Boolean checkoutput,
            String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) throws IOException {
        // ArrayList<Request>requests=new ArrayList<>();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpDelete request = new HttpDelete(url);

            if (headers.size() != 0) {
                for (String key : headers.keySet()) {
                    request.addHeader(key, headers.get(key));
                }
            }
            CloseableHttpResponse response = httpClient.execute(request);
            CloseableHttpResponse response2 = httpClient.execute(request);

            try {
                // Get HttpResponse Status
                System.out.println(response.getProtocolVersion()); // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode()); // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
                if (checkiArgoman.equals(true)) {
                    Header[] headers2 = response.getAllHeaders();
                    for (Header header : headers2) {
                        System.out.println("Key : " + header.getName()
                                + " ,Value : " + header.getValue());
                    }

                }
                if (checkoutput.equals(true)) {

                    // String responseBody = EntityUtils.toString(response.getEntity());
                    //
                    saveOut(response2, nameoffileout);

                }

                if (checksave.equals(true)) {
                    writeRequest("DELETE", url, headers, checkiArgoman, checkoutput, nameoffileout, checksave, dashd,
                            datas);

                    /// save request body in text file
                }

                if (dashd.equals(true)) {
                    // formData(datas,url,"DELETE");
                }

            } catch (Exception e) {
                System.out.println("please check your request");
            } finally {
                response.close();
            }

        } catch (Exception e) {
            System.out.println("please check your request");
        } finally {
            httpClient.close();
        }

    }

    // public static void saveOut2(CloseableHttpResponse response, String
    // nameOfFile) throws IOException {
    // String responseBody;
    // ByteArrayOutputStream bous=new ByteArrayOutputStream();
    // if(response.getEntity() ==null){
    // responseBody="";
    // }
    // else {
    // InputStream in= response.getEntity().getContent();
    // int r;
    // byte[]buffer=new byte[1024];
    // while ((r=in.read(buffer,0,buffer.length)) !=-1){
    //
    // }
    // bous.flush();
    // responseBody=bous.toString();
    //
    // in.close();
    // }
    //
    //
    // }

    public static void saveOut2(HttpURLConnection connection, String nameOfFile) throws IOException {
        byte[] responseBody;

        boolean check = false;
        if (nameOfFile.equals("output_[CurrentDate]")) {
            check = true;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddS");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            // nameOfFile.concat("output_[" +date +"]");
            nameOfFile = ("output_" + date);
        }

        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            File output;
            output = new File(OUT_PATH + nameOfFile);
            InputStream in = connection.getInputStream();
            FileOutputStream out = new FileOutputStream(output);
            int read = 0;
            while ((read = in.read()) != -1) {
                byteOutput.write(read);
            }
            responseBody = byteOutput.toByteArray();
            out.write(responseBody);
            in.close();
            out.close();
            byteOutput.close();

        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);

        }

    }

    /**
     * @param response   is response of request
     * @param nameOfFile is name of file to save responseBody
     * @throws IOException is exception of create file
     */

    public static void saveOut(CloseableHttpResponse response, String nameOfFile) throws IOException {
        boolean check = false;
        if (nameOfFile.equals("output_[CurrentDate]")) {
            check = true;
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMddS");
            LocalDateTime now = LocalDateTime.now();
            String date = dtf.format(now);
            // nameOfFile.concat("output_[" +date +"]");
            nameOfFile = ("output_" + date);
        }

        // String content=EntityUtils.toString(response.getEntity(),
        // StandardCharsets.UTF_8);
        // String content=response.getEntity().toString();11111
        //
        // byte[] bytes = content.getBytes();
        //
        //
        // Path filepath = Paths.get(OUT_PATH+nameOfFile);
        //
        // try ( OutputStream out = Files.newOutputStream(filepath)) {
        //
        // System.out.println("current");
        // out.write(bytes);
        //
        // } catch (IOException e) {
        // e.printStackTrace();
        // }1111111

        // writeOutPut(responseBody,nameOfFile);

        // if(check==true){
        // File file=new File(NOTES_PATH + nameOfFile);
        // try {
        // file.createNewFile();
        // nameOfFile=file.getName();
        //
        // }catch (IOException e){
        // e.printStackTrace();
        // }
        //
        // }

        try {

            // for(Header header : response.getAllHeaders()){
            // if(header.getName().equals("file")){
            // nameOfFile=nameOfFile+".png";
            //
            // }
            //
            // }

            FileOutputStream out = new FileOutputStream(OUT_PATH + nameOfFile);
            try (InputStream is = response.getEntity().getContent()) {
                int len = 0;
                byte[] buffer = new byte[4096];
                while ((len = is.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                out.flush();
                out.close();
                is.close();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("the file not found :(");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("cant save out put in file please try again :(");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("cant save out put in file please try again :(");
        }

    }

    /**
     * @param url           is url of request
     * @param headers       is headers of request
     * @param checkiArgoman is check -i Argoman Boolean of request
     * @param checkoutput   is check -o Argoman Boolean of request
     * @param nameoffileout is name of file for create this for save response body;
     * @param checksave     is check -s Argoman Boolean of request
     * @param dashd         is check -d Argoman Boolean of request
     * @param datas         is dataArray of request
     * @throws IOException is exception of create request
     */

    public static void writeRequest(String method, String url, HashMap<String, String> headers, Boolean checkiArgoman,
            Boolean checkoutput,
            String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) {
        String fileName = getProperFileName();

        RequestR request = new RequestR(method, url, headers, checkiArgoman, checkoutput,
                nameoffileout, checksave, dashd, datas);
        try (FileOutputStream output = new FileOutputStream(NOTES_PATH + fileName)) {

            ObjectOutputStream os = new ObjectOutputStream(output);
            os.writeObject(request);
            // System.out.println(note);
        } catch (FileNotFoundException e) {
            System.out.println("file not found :(");
            // e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException error in save request :(");

            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception error in save request :(");

        }

    }

    public static String fileReader(File file) {

        String content = null;

        try (FileInputStream input = new FileInputStream(NOTES_PATH + file.getName())) {

            ObjectInputStream os = new ObjectInputStream(input);
            RequestR note = (RequestR) os.readObject();
            content = note.namayeshRequest();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // catch (ClassNotFoundException e){
        // e.printStackTrace();
        // }

        return content;
    }

    /**
     * @param file is file of request
     */
    public static void fileReaderforfire(File file) {

        try (FileInputStream input = new FileInputStream(NOTES_PATH + file.getName())) {

            ObjectInputStream os = new ObjectInputStream(input);
            RequestR note = (RequestR) os.readObject();
            String method = note.getMethod();
            String url2 = note.getUrl();
            String nameoffileout2 = note.getNameoffileout();
            Boolean checkiArgoman2 = note.getCheckiArgoman();
            Boolean checkOutput2 = note.getCheckoutput();
            Boolean checkSave2 = note.getChecksave();
            Boolean dashd2 = note.getDashd();
            HashMap<String, String> headers2 = note.getHeaders();
            HashMap<String, String> datas2 = note.getDatas();
            // private String method;
            // private String url;
            // private HashMap<String,String>headers=new HashMap<>();
            // private Boolean checkiArgoman;
            // private Boolean checkoutput;
            // private String nameoffileout;
            // private Boolean checksave;
            // private Boolean dashd;
            // private HashMap<String,String>datas=new HashMap<>();
            if (method.equals("GET")) {
                getMethod(url2, headers2, checkiArgoman2, checkOutput2, nameoffileout2, checkSave2, dashd2, datas2);

            } else if (method.equals("POST")) {
                postMethod(url2, headers2, checkiArgoman2, checkOutput2, nameoffileout2, checkSave2, dashd2, datas2);

            } else if (method.equals("PUT")) {
                putMethod(url2, headers2, checkiArgoman2, checkOutput2, nameoffileout2, checkSave2, dashd2, datas2);

            } else if (method.equals("DELETE")) {
                deleteMethod(url2, headers2, checkiArgoman2, checkOutput2, nameoffileout2, checkSave2, dashd2, datas2);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // catch (ClassNotFoundException e){
        // e.printStackTrace();
        // }

    }

    /**
     * @param number is array of fire files
     */
    public void fire(ArrayList<Integer> number) {
        File[] files = getFilesInDirectory();
        for (Integer i = 0; i < number.size(); i++) {
            try {
                fileReaderforfire(files[number.get(i) - 1]);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("index is not current :(");
            }

        }

    }

    /**
     * @return return the list of file requests
     */
    public static File[] getFilesInDirectory() {

        return new File(NOTES_PATH).listFiles();

    }

    /**
     * this method print request saved in console
     */
    public void listRequest() {

        File[] files = getFilesInDirectory();
        String result = null;
        int number = 1;
        for (File file : files) {
            System.out.println(number + " . ");

            result = result + number + " . ";
            result = fileReader(file);
            number++;
            System.out.println(result);

        }
        // System.out.println(files.length);

    }

    /**
     * @return create return the current name of file
     */
    private static String getProperFileName() {

        return System.currentTimeMillis() + "_new file.txt";
    }

    // public void writeOutPut(String responseBody ,String nameOfFile){
    // if(nameOfFile.equals("output_[CurrentDate]")){
    // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    // LocalDateTime now = LocalDateTime.now();
    // String date =dtf.format(now);
    //// nameOfFile.concat("output_[" +date +"]");
    // nameOfFile=("output_[" +date+"]");
    // }
    //
    //
    //
    // try {
    // FileWriter fileWriter=new FileWriter(nameOfFile);
    // fileWriter.write(responseBody);
    //
    // fileWriter.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    //
    //
    // }
    //
    // /**
    // * @return check boolean
    // */
    // public String readfromoutfile(File file){
    // String content=null;
    // BufferedReader objReader = null;
    // try {
    // String strCurrentLine;
    //
    // objReader = new BufferedReader(new FileReader(file.getName()));
    //
    // while ((strCurrentLine = objReader.readLine()) != null) {
    //
    // content = strCurrentLine;
    // }
    //
    // } catch (IOException e) {
    //
    // e.printStackTrace();
    //
    // } finally {
    //
    // try {
    // if (objReader != null)
    // objReader.close();
    // } catch (IOException ex) {
    // ex.printStackTrace();
    // }
    // }
    //
    //
    // return content;
    //
    //
    //
    // }
    //
    //
    //
    // public void get() throws IOException {
    // CloseableHttpClient httpClient = HttpClients.createDefault();
    //
    // try {
    //
    // HttpGet request = new HttpGet("sajam");
    //
    //
    // // add request headers
    // request.addHeader("custom-key", "mkyong");
    // request.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
    //
    // CloseableHttpResponse response = httpClient.execute(request);
    //
    // try {
    //
    // // Get HttpResponse Status
    // System.out.println(response.getProtocolVersion()); // HTTP/1.1
    // System.out.println(response.getStatusLine().getStatusCode()); // 200
    // System.out.println(response.getStatusLine().getReasonPhrase()); // OK
    // System.out.println(response.getStatusLine().toString()); // HTTP/1.1 200 OK
    //
    // HttpEntity entity = response.getEntity();
    //// Header[] headers = response.getAllHeaders();
    //// for (Header header : headers) {
    //// System.out.println("Key : " + header.getName()
    //// + " ,Value : " + header.getValue());
    //// }
    //
    //// String responseBody = EntityUtils.toString(response.getEntity());
    //
    // if (entity != null) {
    // // return it as a String
    // String result = EntityUtils.toString(entity);
    // System.out.println(result);
    // }
    //
    // } catch (Exception e) {
    // System.out.println("please check your request");
    // } finally {
    // response.close();
    // }
    //
    // }catch (Exception e) {
    // System.out.println("please check your request");
    // }
    // finally {
    // httpClient.close();
    // }
    //
    //
    // }
    //

    /**
     * @param body                 is array of data
     * @param boundary             is boundary
     * @param bufferedOutputStream is bufferedOutputStream for request
     * @throws IOException
     */
    public static void bufferOutFormData(HashMap<String, String> body, String boundary,
            BufferedOutputStream bufferedOutputStream) throws IOException {
        for (String key : body.keySet()) {
            bufferedOutputStream.write(("--" + boundary + "\r\n").getBytes());
            if (key.contains("file")) {
                bufferedOutputStream.write(("Content-Disposition: form-data; filename=\""
                        + (new File(body.get(key))).getName() + "\"\r\nContent-Type: Auto\r\n\r\n").getBytes());
                try {
                    BufferedInputStream tempBufferedInputStream = new BufferedInputStream(
                            new FileInputStream(new File(body.get(key))));
                    byte[] filesBytes = tempBufferedInputStream.readAllBytes();
                    bufferedOutputStream.write(filesBytes);
                    bufferedOutputStream.write("\r\n".getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("error in set data in formData");

                    // e.printStackTrace();
                }
            } else {

                bufferedOutputStream.write(("Content-Disposition: form-data; name=\"" + key + "\"\r\n\r\n").getBytes());
                bufferedOutputStream.write((body.get(key) + "\r\n").getBytes());
            }
        }
        bufferedOutputStream.write(("--" + boundary + "--\r\n").getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    /**
     * @param fooBody is array of data
     * @param url1    is url of request
     * @param method  is methodName of request
     */
    public static void formData(HashMap<String, String> fooBody, String url1, String method, Boolean checkiArgoman,
            Boolean checkOutPot, String nameOfFile, HashMap<String, String> headers) {
        try {
            URL url = new URL(url1);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String boundary = System.currentTimeMillis() + "";
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            if (headers.size() != 0) {
                for (String key : headers.keySet()) {
                    connection.setRequestProperty(key, headers.get(key));
                }
            }
            // connection.setRequestProperty("Content-Type", "multipart/form-data;
            // boundary=" + boundary);
            BufferedOutputStream request = new BufferedOutputStream(connection.getOutputStream());
            bufferOutFormData(fooBody, boundary, request);
            if (connection.getResponseCode() == 400) {
                BufferedInputStream bis = new BufferedInputStream(connection.getErrorStream());
                ByteArrayOutputStream buf = new ByteArrayOutputStream();
                int result = bis.read();
                while (result != -1) {
                    buf.write((byte) result);
                    result = bis.read();
                }
                // StandardCharsets.UTF_8.name() > JDK 7
                System.out.println("ERROR:" + buf.toString("UTF-8"));
                // return buf.toString("UTF-8");
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(connection.getInputStream());

            System.out.println(new String(bufferedInputStream.readAllBytes()));
            System.out.println(connection.getResponseCode());

            if (checkiArgoman.equals(true)) {
                System.out.println(connection.getHeaderFields());
            }
            if (checkOutPot.equals(true)) {
                saveOut2(connection, nameOfFile);
                /// save body request
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("error in set data in formData");

        }

    }

}
