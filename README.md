# springboot-security
This project contains the code for secuirty of springboot application which generally using authencation relam.

1.RealamProvider class contain the code where you can get the user details from database and return the user details after matched.
if usser name and password doest not matched you can throw your own security exception.

2. "/", "/home", "/login" this type of url are authorized, if any other web resource are requested it will go to the authencation.
if it successfully authenticated it will redirected to /dasboard resource else /login page.

3. For logout you have to call /logout1 from your page in that case user get logout and rendered to /login page.
