# Password Manager

This project was inspired by [KeePass](https://keepass.info/), a local password manager. 

## My goals for this project:
-Add a database using JDBC so that users can retrieve passwords stored in previous sessions<br>
-Implement a master password that a user must always enter before accessing data. The user must have the abilty to reset or change this password. This can be achieved by providing them with a 256-bit key which the user is provided upon the first boot of the program. This key will also change when the user changes the master password.<br>
-Encrypt all data that will be written into the database using AES(subject to change)<br>
-Implement a GUI using JavaFX 

##Updates in recent commits: 
-Added JDBC so that data can be stored, retrieved, and removed from a MySQL database<br>
-Added master password and forgot master password functionality. The master password and the key for reseting the master password is encrypted with SHA-256 and stored in the same database in the master table. <br>

##Problems with the current version:
-Passwords are not encrypted in the database. Solution: Encrypting the passwords with AES and salt or some other pollutant. <br>
-InputMismatchExceptions that arise due to Java being strongly typed. Solution: Add try catch statements to ensure correct input type. However, when a GUI is implemented this problem will not arise. <br>

##Future Add-ons:
-Adding the GUI using JavaFX.


