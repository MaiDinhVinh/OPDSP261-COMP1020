package hashtable;

public class StringHash {
    public static int naiveHash(String str){
        int hash = 0;
        char[] arr = str.toCharArray();
        for(char c: arr){
            hash += c;
        }
        return hash;
    }

    public static int foldingHash(String str){
        int hash = 0;
        int sliceIndex = 0;
        while(str.length() - sliceIndex >= 4){
            char[] arr = str.substring(sliceIndex, sliceIndex + 4).toCharArray();
            sliceIndex += 4;
            int add = 0;
            for(char c: arr){
                hash += c << add;
                add += 8;
            }
        }
        if(str.length() - sliceIndex > 0){
            char[] arr = str.substring(sliceIndex).toCharArray();
            int add = 0;
            for(char c: arr){
                hash += c << add;
                add += 8;
            }
        }
        return hash;
    }

    public static void main(String[] args) {
        System.out.println(foldingHash("lorem ipsum dolor"));
    }
}