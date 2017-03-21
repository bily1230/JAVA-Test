package enums;

/**
 * Created by ning on 2017/2/23.
 */
public enum Enum1 implements EnumTest {
    RED("红色"),YELLOW("黄色"),GREEN("绿色");
    private static Enum1 enum1;
    private String  color;
     Enum1(String color){
        this.color = color;
    }

    @Override
    public String value() {
        return color;
    }

    public String getColor(){
         return color;
    }
    public static void main(String[] args){
        try{
            Enum1 enum1 = Enum1.valueOf("RED");
            System.out.println(enum1.value());
        }catch(Exception e){
            e.printStackTrace();
        }

       if(enum1!=null){

       }

    }
}
