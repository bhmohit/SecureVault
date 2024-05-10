# Secure Vault :lock:

This project was inspired by [KeePass](https://keepass.info/), a local password manager. 


## Restarting this project to have it use the SpringFramework ❗





## My goals for this project :white_check_mark:
- Add a database using JDBC so that users can retrieve passwords stored in previous sessions
- Implement a master password that a user must always enter before accessing data. The user must have the ability to reset or change this password. This can be achieved by providing them with a 256-bit key which the user stores safely. This key will also change when the user changes the master password.
- Encrypt all data that will be written into the database using AES
- Implement a GUI using JavaFX 
- Try to comply with [NIST](https://www.auditboard.com/blog/nist-password-guidelines/#:~:text=NIST%20requires%20an%208%2Dcharacter%20minimum%20for%20passwords.) standards for password storage.

## What I could do differently :repeat_one: 
- Use Hibernate: An ORM would significantly change the ease of creating the application since I would not have to deal with JDBC. I decided against using Hibernate to re-learn JDBC concepts.

## Problems with the current version :thinking: 
- Design: The UI is for demonstration purposes only, it simply gets the job done. In the future, I would work on a better, more interactive UI. 
- Application is available locally only: From a security standpoint, it is *NOT* as safe to host the application on the web. However, the reward of multi-platform usability outweighs the security concerns (which can be dealt with). 



