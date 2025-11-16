//Authors: Suhani Rai, Samyuktha Bodduluri 
// we did switch and ternary for the potpurri here
import java.io.FileReader;  
import java.io.FileNotFoundException; 
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

public class Cipher {
	// A final list of all the letters in the alphabet that will not be changed.
	final private static String[] ALPHABET = {"a","b", "c", "d","e", "f", "g", "h", "i", "j", "k", "l", 
								"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	// An arraylist that will contain the mixed alphabet after implementing the key.
	private static ArrayList<String> cipherAlphabet;
	// A string representing the key given by the user in the CLI.
	private static String key;
	// A string representing the file name given by the user in the CLI.
	private static String filename;
	// A string representing wether the user wants to encrypt or decrypt.
	private static String action;
	// A string contianing all the words in the file given by the user (either secretmessage or encrypted).
	private static String words;
	// A String ArrayList that contains the encrypted text.
	private static ArrayList<String> encryptedMessage = new ArrayList<String>();
	// A String ArrayList that contains the decrypted text.
	private static ArrayList<String> decryptedMessage = new ArrayList<String>();
	// The index of the letter in either ALPHABET or cipherAlphabet
	private static int index;
	/*
	The main method will assigns the filename, whether the code will encrypt or decrypt the text, and what the key is.
	It will also read the file depending on the action 

	@param    String[] args    Contains the CLI of the filename, whether the user in
	encrypting or decrypting, and the key

	pre: args length must be greater than 0.
	exceptions: if args length is less than 0, then asks user to give a valid input.
	*/
	public static void main(String[] args){
		cipherAlphabet = new ArrayList<String>();
		if (args.length == 0) {
			System.out.println("Please enter a filename, encrypting or decrpting, and key");
		}
		else {
			filename = args[0];
			action = args[1];
			key = args[2];
		}
		createCipher();
		/* Runs either the encrypting or decrypting based on whether e or d is given. If neither of the letters is given, 
		the user is told to change it*/
		if (action.equals("d")) { 
			readPlainText(filename);
			System.out.println("Go check your decrypted file!");
		}
		else if (action.equals("e")) {
			readPlainText(filename);
			System.out.println("Go check your encrypted file!");
		}
		else {
			System.out.println("Please end the code and enter either 'e' or 'd'!");
		}
	}

	/* 
	The method takes the key, puts it in cipherALphabet, and adds any letters that don't already exist in cipherAlphabet.

	Pre: the user has given a valid key in the CLI.
	Post: cipherAlphabet is shifted using the key.
	 */
	public static void createCipher() {
		for (int i = 0; i < key.length(); i++) {
			cipherAlphabet.add(key.substring(i, i + 1));
		}
		removeDoubles();
		//Iterates through ALPHABET and if it is not found in cipherAlphabet, adds it to cipherAlphabet.
		for (int j = 0; j < ALPHABET.length; j++) {
			if (cipherAlphabet.indexOf(ALPHABET[j]) < 0) {
				cipherAlphabet.add(ALPHABET[j]);
			}
		}
	}

	/*
	The method iterates through the current cipherALphabet which only contains the key and removes any letters
	that repeat.

	Pre: the key has double letters in it
	Post: double letters are removed
	*/	
	public static void removeDoubles() {
		int count = 0;
		String constant = "";
		/*Iterates through cipherAlphabet by checking if constant is equal to anything else 
		in cipherAlphabet */
		for (int j = 0; j < cipherAlphabet.size(); j++) {
			constant = cipherAlphabet.get(j);
			for (int i = 0; i < cipherAlphabet.size(); i++) {
				/*If constant is equal to a letter in cipherAlphabet then increments count
				and then removes that letter from cipherAlphabet */
				if (constant.equals(cipherAlphabet.get(i))) {
					count++;
					if (count > 1) {
						cipherAlphabet.remove(i);
						i--;
					}
				}
			}
			count = 0;
		}
	}
	
	/*
	This method iterates through the file given and adds the contents to words 

	Pre: Valid filename is given in CLI
	Post: The contents in filename gets added to the String word. 
	Exception: If file doesn't exist the user is prompted to give a valid filename.
	*/
	public static void readPlainText(String filename) {
		try {
			Scanner message = new Scanner(new FileReader(filename));
			words = message.nextLine();
			encryptOrDecrypt();
			message.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist!");
		}
	}

	/*
	Calls either encrypt or decrypt based off of the action given.  

	Pre: A valid action is given in CLI.
	*/
	public static void encryptOrDecrypt() {
		switch(action) {
			case "d": decrypt();
					break;
			case "e": encrypt();
					break;
			default: System.out.println("Enter a letter (e or d)");
					break;
		}
	}


	/*
	This method iterates through ALPHABET and checks to see where the letter given
	is found in ALPHABET and sets the index to where the letter is found.
	
	Post: Index is updated.
	*/
	public static int getIndexForEncrypt(String letter) {
		index = -1;
		for (int i = 0; i < ALPHABET.length; i++) {
			if (ALPHABET[i].equals(letter)) {
				index = i;
				break;
			}
		}
		return index;
	}

	/*
	This method iterates through cipherAlphabet and checks to see where the letter given
	is found in cipherAlphabet and sets the index to where the letter is found.
	
	Post: Index is updated.
	*/
	public static int getIndexForDecrypt(String letter) {
		index = -1;
		for (int i = 0; i < cipherAlphabet.size(); i++) {
			if (cipherAlphabet.get(i).equals(letter)) {
				index = i;
				break;
			}
		}
		return index;
	}


	/*
	This method iterates through words and changes encryptedMessage based off where 
	each letter is found from words in cipherAlphabet.
	This method then iterates through encryptedMessage and adds it to finalMessage 
	and finally updates the file with the final message.

	Pre: Words cannot be null.
	Post: encryptedMessage is changed and finalMessage is made iterating through encryptedMessage
	and file is updated with finalMessage.  
	*/
	public static void encrypt() {
		String finalMessage = "";
		//Iterates through words and changes encryptedMessage
		for (int i = 0; i < words.length(); i++) {
			/*If the index of word equals " " adds it to encryptedMessage or adds the letter based off of 
			the index it is in for cipherAlphabet */
			if (words.substring(i, i + 1).equals(" ")) {
				encryptedMessage.add(" ");
			}
			else{
				getIndexForEncrypt(words.substring(i, i + 1));
				encryptedMessage.add(cipherAlphabet.get(index));
			}
		}
		for (int j = 0; j < encryptedMessage.size(); j++) {
			finalMessage += encryptedMessage.get(j);
		}
		updateFile(finalMessage);
	}

	/*
	This method iterates through words and changes decrypedMessage based off where 
	each letter is found from words in ALPHABET.
	This method then iterates through decryptedMessage and adds it to finalMessage 
	and finally updates the file with the final message.
	
	Pre: Words cannot be null.
	Post: decryptedMessage is changed, and file is updated with finalMessage. 
	*/
	public static void decrypt() {
		String finalMessage = "";
		//Iterates through words and changes decryptedMessage
		for (int i = 0; i < words.length(); i++) {
			/*If the index of word equals " " adds it to decryptedMessage or adds the letter based off of 
			the index it is in for ALPHABET */
			if (words.substring(i, i + 1).equals(" ")) {
				decryptedMessage.add(" ");
			}
			else{
				getIndexForDecrypt(words.substring(i, i + 1));
				decryptedMessage.add(ALPHABET[index]);
			}
		}
		for (int j = 0; j < decryptedMessage.size(); j++) {
			finalMessage += decryptedMessage.get(j);
		}
		updateFile(finalMessage);
	}

	/*
	Updates either encrypted.txt or decrypted.txt based off the action
	inputted by the user. 

	Pre: A valid action is given in CLI.
	Post: Either encrypted.txt is updated with the given message
	or decrypted.txt is updated with the given message.
	Exception: If file is not found, prompts user that the file wasn't found.
	*/
	public static void updateFile(String message) {
		boolean actionValue = action.equals("e");
		String val = actionValue ? "encrypted.txt" : "decrypted.txt";
		try {
			PrintWriter pw = new PrintWriter(val);
        	pw.println(message);
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		}
	}
}