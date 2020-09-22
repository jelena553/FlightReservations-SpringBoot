<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Unos leta</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
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

      <div class="izborUnosLeta">
        <ul>
            <li><a href="/Aero/radnik/ucitajAerodrome">Unesite novu liniju</a></li>
            <li><a href="/Aero/radnik/ucitajAvione">Unesite novi let</a></li>
            
        </ul>
      </div>
        <div class="containerDatum">
        <sf:form action="/Aero/radnik/unesiLet" method="post" modelAttribute="let">
        	<ul class="unosLetaLista">
        	
            
                <li>
                <label for="datumPolaska">Datum polaska </label>
                </li>

                <li>
                <sf:input type="date" path="datumPolaska" required="required"></sf:input>
                </li>
                
                <li>
                <label for="avion">Avion </label>
                </li>

                <li>
                <sf:select  path="avion" items="${avioni }" itemValue="idAvion" itemLabel="naziv" cssClass="avionInput"></sf:select>
                </li>
             
            
        	

        	<li>
                <button type="submit" class="unesiLetDugme3">Unesi let</button>
            </li>
            
        </ul>
        </sf:form>
        </div>
        
        <c:if test="${!empty letS }" >
        <p class="porukaUspjehLet">Uspješno ste dodali let!</p>
        </c:if>
        
        <c:if test="${!empty greskaSaveLet }" >
        <p class="porukaUspjehLet">Došlo je do greške, pokušajte ponovo</p>
        </c:if>
        
        <c:if test="${pogresanDatum }" >
        <p class="porukaUspjehLet2">Datum polaska leta ne može biti u prošlosti!</p>
        </c:if>
        
        


</body>
</html>