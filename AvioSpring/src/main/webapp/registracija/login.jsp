<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
 <head>
 	<title></title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
 	<meta charset="UTF-8">
 </head>

<body class="news">
	
	<style>
         @import url('https://fonts.googleapis.com/css?family=Oswald:300&display=swap');
         @import url('https://fonts.googleapis.com/css?family=Raleway&display=swap');
         @import url('https://fonts.googleapis.com/css?family=Fira+Sans&display=swap');
    </style> 


  <header>
    <div class="nav">
      <ul>
        <li class=><a href="/Aero/auth/pocetna">POČETNA</a></li>
        <li class=><a class="active" href="/Aero/rezervacija/pretraga">REZERVACIJA LETOVA</a></li>
        <security:authorize access="isAuthenticated()">
        <li class=><a href="/Aero/rezervacija/pregledRezervacija">PREGLED REZERVACIJA</a></li>
        <security:authorize access="hasRole('ZAPOSLENI')">
        <li class=><a href="/Aero/zaposleni/pocetnaZaposleni.jsp">NOVI UNOSI</a></li>
        </security:authorize>
        </security:authorize>
        <security:authorize access="isAnonymous()">
        <li class=><a href="/Aero/auth/metod1">REGISTRACIJA</a></li>
        <li class=><a href="/Aero/registracija/login.jsp">PRIJAVI SE</a></li>
        </security:authorize>
        <security:authorize access="isAuthenticated()">
        <li><a href="/Aero/auth/logout">ODJAVI SE</a></li>
        <!--<li class="news"><form action="/Aero/auth/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
        <input type="submit" value="ODJAVI SE"/>
        </form></li>-->
        </security:authorize>
        
      </ul>
      </div>
      </header>


     <c:url var="loginUrl" value="/login" />  
	<c:if test="${not empty param.error}">
		<div class="alert-danger">
			<p class="invalidCredentials">Netačni korisnički podaci, pokušajte ponovo!</p>
			</div>
			</c:if>
			
			
	<c:if test="${not empty korisnik }">
		<p class="invalidCredentials2">Uspješno ste se registrovali!</p>
	</c:if>
      
    


        
        <form class="loginPolja" action="${loginUrl }" method="post">
          <p>
            <label for="ki">Korisničko ime:</label>
            <input type="text" name="username" id="ki" required>
          </p>
        
           <p>
            <label for="loz">Lozinka:</label> 
            <input type="password" name="password" id="loz" required>
          </p>
          <p>
          	<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
          </p>

        <div class="dugmePrijava">
          <input type="submit" class="submit" value="Prijavi se">
          
        </div>

        <div class="logRegi">
            <p>Nemate nalog? <a href="/Aero/auth/metod1">Registracija</a></p>
          
        </div>
        </form>



      

  </body>
  </html>
        