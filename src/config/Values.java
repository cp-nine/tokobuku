package config;

import java.util.HashMap;
import java.util.Stack;

public class Values {

    public static boolean isNumeric(String strNum) {
        return strNum.matches("-?\\d+(\\.\\d+)?");
    }

    public static boolean checkString(String s){

        // mapping brackets apa saja yang akan di jadikan acuan validasi
        HashMap<Character, Character> map = new HashMap<Character, Character>();
        map.put('(', ')');
        map.put('[', ']');
        map.put('{', '}');

        // membuat stack karakter baru
        Stack<Character> stack = new Stack<Character>();
        // string di konversi ke array char
        char[] charArray = s.toCharArray();
        // looping char sesuai panjang array
        // untuk di cocokan dengan  map char yang sudah di tentukan
        for (Character c: charArray) {
            // jika key/index map mempunyai karakter yang sama dengan karakter
            // dimasukan ke dalam stack
            if(map.keySet().contains(c)){
                stack.push(c); //memasukan char ke dalam stack
            }
            // jika char tidak memiliki kecocokan dengan key map
            else {
                // di cek apakan nilai map / map value memiliki karakter yang sama dengan char
                if(map.values().contains(c)){
                    // jika pengecekan benar
                    // kemudian cek apakah stack != kosong / isi
                    // dan apakah map value sama dengan char
                    // jika sama maka nilai pada stack diambil
                    if (!stack.isEmpty() && map.get(stack.peek())== c){
                        stack.pop(); // ambil / hapus nilai pada stack
                    }
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
