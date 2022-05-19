package com.company;

import java.io.Serializable;
import java.util.HashMap;

/**
 * this class save request information
 */
public class RequestR implements Serializable {

   // private int number;
   private String method;
   private String url;
   private HashMap<String, String> headers = new HashMap<>();
   private Boolean checkiArgoman;
   private Boolean checkoutput;
   private String nameoffileout;
   private Boolean checksave;
   private Boolean dashd;
   private HashMap<String, String> datas = new HashMap<>();

   /**
    * @param method        is method of request
    * @param url           is url of request
    * @param headers       is headers of request
    * @param checkiArgoman is check -i Argoman Boolean of request
    * @param checkoutput   is check -o Argoman Boolean of request
    * @param nameoffileout is name of file for create this for save response body;
    * @param checksave     is check -s Argoman Boolean of request
    * @param dashd         is check -d Argoman Boolean of request
    * @param datas         is dataArray of request
    */
   public RequestR(String method, String url, HashMap<String, String> headers, Boolean checkiArgoman,
         Boolean checkoutput,
         String nameoffileout, Boolean checksave, Boolean dashd, HashMap<String, String> datas) {
      this.url = url;
      this.headers = headers;
      this.checkiArgoman = checkiArgoman;
      this.checkoutput = checkoutput;
      this.nameoffileout = nameoffileout;
      this.checksave = false;
      this.dashd = dashd;
      this.datas = datas;
      this.method = method;

   }

   /**
    * @return return the method of request
    */
   public String getMethod() {
      return method;
   }

   /**
    * @return return the url of request
    */
   public String getUrl() {
      return url;
   }

   /**
    * @return return the name of file out of request
    */
   public String getNameoffileout() {
      return nameoffileout;
   }

   /**
    * @return return the checkoutput of request
    */

   public Boolean getCheckoutput() {
      return checkoutput;
   }

   /**
    * @return return the checksave of request
    */
   public Boolean getChecksave() {
      return checksave;
   }

   /**
    * @return return the dashd of request
    */
   public Boolean getDashd() {
      return dashd;
   }

   /**
    * @return return the checkiArgoman of request
    */
   public Boolean getCheckiArgoman() {
      return checkiArgoman;
   }

   /**
    * @return return the headers of request
    */

   public HashMap<String, String> getHeaders() {
      return headers;
   }

   /**
    * @return return the datas of request
    */
   public HashMap<String, String> getDatas() {
      return datas;
   }

   /**
    * @return the list of request
    */
   public String namayeshRequest() {
      // 1 . url: google.com | method: GET | headers: Accept: text/html …
      String result = " url: " + url + " | method: " + method;
      if (headers != null || headers.size() != 0) {
         result = result + " | headers: ";
         for (String key : headers.keySet()) {
            result = result + key + ": " + headers.get(key) + " , ";
         }
      }
      if (datas != null || datas.size() != 0) {
         result = result + " | data : ";
         for (String key : datas.keySet()) {
            result = result + key + ": " + datas.get(key) + " , ";
         }
      }
      return result;

   }

}
