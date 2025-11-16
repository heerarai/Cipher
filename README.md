# Cipher 

Authors: Suhani Rai, Samyuktha Bodduluri

## Overview

This Java program implements a simple substitution cipher for encrypting and decrypting text files. Users can provide a custom key, and the 
program generates a unique cipher alphabet based on that key. The program reads input from a file, performs the specified action (encrypt or decrypt), 
and outputs the result to a new file.

___

## Features

- Custom Key Input: Users can provide any key to create a unique cipher alphabet.
- Encryption & Decryption: Supports both encrypting and decrypting messages.
- File Handling: Reads text from a file and writes the output to encrypted.txt or decrypted.txt.
- Space Preservation: Spaces in the original text are preserved in the output.
- Duplicate Key Handling: Automatically removes repeated characters from the key.

___

## How It Works

- User Input: The program takes three command-line arguments: the filename, the action (e for encrypt or d for decrypt), and the cipher key.
- Cipher Alphabet Creation:
  - The key is added to an initially empty cipher alphabet.
  - Any duplicate letters in the key are removed.
  - Remaining letters from the standard alphabet that aren’t in the key are appended to complete the cipher alphabet.
- File Reading: The program reads the contents of the input file line by line.
- Encrypt or Decrypt:
  - Encryption: Each letter is replaced by its corresponding letter in the cipher alphabet.
  - Decryption: Each letter is replaced by its corresponding letter in the original alphabet.
- Output File: The transformed text is saved to encrypted.txt or decrypted.txt depending on the chosen action.
