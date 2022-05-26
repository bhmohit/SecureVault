# Password Manager

This project was inspired by [KeePass](https://keepass.info/), a local password manager. Currently, this project is written in Java and is very naive with no GUI, encryption, nor a database to retreive passwords stored in previous sessions. Infact, the application is so naive it assumes that the user always inputs the correct type of input (due to the fact that Java is strongly typed). 

## My goals for this project:
-Add a database using JDBC so that users can retrieve passwords stored in previous sessions
-Implement a master password that a user must always enter before accessing data. The user must have the abilty to reset or change this password. This can be achieved by providing them with a 256-bit key which the user is provided upon the first boot of the program. This key will also change when the user changes the master password.
-Encrypt all data that will be written into the database using AES(could change)
-Implement a GUI using JavaFX 
