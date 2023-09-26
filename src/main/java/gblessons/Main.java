package gblessons;



public class Main {

    public static void main(String[] args) {

        HashMap<String, String> hashMap = new HashMap<>(4);
        String oldvalue = hashMap.put("+79001112233", "AAAAAA");
        oldvalue = hashMap.put("+79001112231", "BBBBBB");
        oldvalue = hashMap.put("+79001112232", "CCCCCCC");
        oldvalue = hashMap.put("+79001112233", "DDDDDDD");
        oldvalue = hashMap.put("+79001112234", "EEEEEEE");
        oldvalue = hashMap.put("+79001112235", "MMMMM");
        oldvalue = hashMap.put("+79001112236", "FFFFF");
        oldvalue = hashMap.put("+79001112237", "ggggggg");
        oldvalue = hashMap.put("+79001112238", "GGGGGGG1");
        oldvalue = hashMap.put("+79001112239", "GGGGGGG2");
        oldvalue = hashMap.put("+79001112230", "GGGGGGG3");

        System.out.println(hashMap);
        }
    }


