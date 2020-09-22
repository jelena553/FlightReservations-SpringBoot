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
    <title>Registracija</title>
 	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
 	<meta charset="UTF-8">
 	<style>
         @import url('https://fonts.googleapis.com/css?family=Oswald:300&display=swap');
         @import url('https://fonts.googleapis.com/css?family=Raleway&display=swap');
         @import url('https://fonts.googleapis.com/css?family=Fira+Sans&display=swap');
    </style> 
 	</head>
<body class="news">
	
	


  <header>
    <div class="nav">
      <ul>
        <li ><a href="/Aero/auth/pocetna">POČETNA</a></li>
        <li ><a class="active" href="/Aero/rezervacija/pretraga">REZERVACIJA LETOVA</a></li>
        <security:authorize access="isAuthenticated()">
        <li ><a href="/Aero/rezervacija/pregledRezervacija">PREGLED REZERVACIJA</a></li>
        <security:authorize access="hasRole('ZAPOSLENI')">
        <li ><a href="/Aero/zaposleni/pocetnaZaposleni.jsp">NOVI UNOSI</a></li>
        </security:authorize>
        </security:authorize>
        <security:authorize access="isAnonymous()">
        <li ><a href="/Aero/auth/metod1">REGISTRACIJA</a></li>
        <li ><a href="/Aero/registracija/login.jsp">PRIJAVI SE</a></li>
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
      <c:if test="${not empty duplikat }">
      	<p class="korisnickoImePostoji">Korisničko ime postoji!Pokušajte ponovo</p>
      </c:if>
      
      <sf:form action="/Aero/auth/registruj" method="post" modelAttribute="korisnik">
        <div class="registracija">

          <p class="naslovi">Unesite vase podatke</p>

          <sf:input type="text" path="ime" placeholder="Ime" class="inn"/>
          <sf:input type="text" path="prezime" placeholder="Prezime" class="inn"/>
          <sf:input type="text" path="email" placeholder="Email" class="inn"/>
          <sf:errors class="errors" path="email"/>
          <p class="naslovi">Nalog</p>
          <sf:input type="text" path="korisnickoIme" placeholder="Korisnicko ime"/>
          <sf:input type="password" path="lozinka" placeholder="Lozinka" class="passReg"/>
          <sf:errors class="errors" path="lozinka"/>
          
          <div class="datRodj">
          <p class="naslovi">Datum rodjenja</p> 
          <sf:input type="date" path="datumRodj" placeholder="Datum" class="dd"/> 
          <sf:errors classs="errors" path="datumRodj"/>
          
          </div>
           <!--
           <p class="naslovi">Pol</p>
           <input type="radio" name="pol" value="m">M
           <input type="radio" name="pol" value="z">Z

           <p class="naslovi">Unesite broj kreditne/debitne kartice</p>
           <input type="text" name="ccv" placeholder="Broj kartice">-->
           
           <p class="naslovi">Tip naloga</p>
           <sf:select path="roles" items="${uloge }" itemValue="idRole" itemLabel="naziv"/>

           <button type="submit" class="regBtn">Registruj se</button>
          
        </div>
      </sf:form>
      
      

      
     </body>
     </html>
