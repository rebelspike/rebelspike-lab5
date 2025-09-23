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
            
            // Reverse process of encryption
            // Find which index on outer rotor (rotors[2]) gives us this character
            int outerIndexForResult = rotors[2].indexOf(c);
            
            // That index corresponds to a character on middle rotor (rotors[1])
            char middleChar = rotors[1].charAt(outerIndexForResult);
            
            // Find where that middle character appears on outer rotor (rotors[2])
            int outerIndexForMiddle = rotors[2].indexOf(middleChar);
            
            // That index corresponds to the original character on inner rotor (rotors[0])
            char decryptedChar = rotors[0].charAt(outerIndexForMiddle);
            result.append(decryptedChar);
            
            // Rotate rotors after decryption
            rotate();
        }
        
        return result.toString();
    }
    
    public String encrypt(String message){
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            
            // Find character on inner rotor (rotors[0])
            int innerIndex = rotors[0].indexOf(c);
            
            // Note character aligned with it on outer rotor (rotors[2] at same index)
            char alignedOnOuter = rotors[2].charAt(innerIndex);
            
            // Find that character on middle rotor (rotors[1])
            int middleIndex = rotors[1].indexOf(alignedOnOuter);
            
            // Output character aligned with it on outer rotor (rotors[2] at same index)
            char encryptedChar = rotors[2].charAt(middleIndex);
            result.append(encryptedChar);
            
            // Rotate rotors after encryption
            rotate();
        }
        
        return result.toString();
    }

    
    private void rotate(){
        if(rotors[0].rotate()){
            if(rotors[1].rotate()){
                rotors[2].rotate();
            }
        }
    }
    
}
