public class Enigma{

    private String rotorInit[] = {"#GNUAHOVBIPWCJQXDKRYELSZFMT",
        "#EJOTYCHMRWAFKPUZDINSXBGLQV",
        "#BDFHJLNPRTVXZACEGIKMOQSUWY",
        "#NWDKHGXZVRIFJBLMAOPSCYUTQE",
        "#TGOWHLIFMCSZYRVXQABUPEJKND"};


    private Rotor rotors[];
        
    public Enigma(int id1, int id2, int id3, String start){

        rotors = new Rotor[3];
        rotors[0] = new Rotor(rotorInit[id1-1], start.charAt(0));
        rotors[1] = new Rotor(rotorInit[id2-1], start.charAt(1));
        rotors[2] = new Rotor(rotorInit[id3-1], start.charAt(2));
        
    }


    public String decrypt(String message){        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            
            // Reverse process: find character on outer rotor
            int outerIndex = rotors[2].indexOf(c);
            
            // Get aligned character on middle rotor (same index)
            char middleChar = rotors[1].charAt(outerIndex);
            
            // Find that character on outer rotor again
            int secondOuterIndex = rotors[2].indexOf(middleChar);
            
            // Output character aligned with it on inner rotor
            char decryptedChar = rotors[0].charAt(secondOuterIndex);
            result.append(decryptedChar);
            
            // Rotate rotors after decryption
            rotate();
        }
        
        return result.toString();
    }


    
    public String encrypt(String message){
        return "";
    }

    
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
