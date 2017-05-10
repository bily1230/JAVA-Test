package TestMain;

import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by ning on 2017/4/10.
 */
public class Test1 {


    public static void main(String[] args) {



        String jsonStr = "{\"id\":\"1111\",\"name\":\"wangsan\"}";

      // JSONObject jsonObject = new JSONObject();
      //  String id = (String)jsonObject.get("id");
       // System.out.print(jsonObject);
        // Object sister = jsonObject.get("sister");
       /* if(sister instanceof JSONArray){
            JSONArray jsonArray = (JSONArray) sister;
            for(Object json:jsonArray){
                if(json instanceof  JSONObject){
                    JSONObject sister1 = (JSONObject)json;
                    System.out.print(sister1.get("id"));
                }
            }

        }*/

    }
    public static void printName(List<Person> list){
        for(Person person : list){
            System.out.println( "person--" + person.getName());
        }
    }

    public static void printNameAndage(List<? extends Person> list){
        for(Person person : list){
            System.out.println( "person--" + person.getName());
        }
    }

}
