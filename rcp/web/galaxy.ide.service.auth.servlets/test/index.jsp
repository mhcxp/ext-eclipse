 <HTML> 
 <HEAD> 
 <TITLE>My App Home</TITLE> 
 <META http-equiv=Content-Typecontent="text/html; charset=utf-8"> 
 <% 
 String javaVersion = System.getProperty("java.version"); 
 %> 
 </HEAD> 
 <BODY> 
 <p>Current JRE version: <font color="#FF0000"><%=javaVersion%></font></p> 
 <p> 
 <a href="/authHttpServlet?name=user&pass=pass&service=test"> 
 Click to test servlet 
 </a> 
 </BODY> 
 </HTML> 
