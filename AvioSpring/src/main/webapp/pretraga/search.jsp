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
 	<title>Pretraga</title>
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
        <!-- <li class="news"><form action="/Aero/auth/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
        <input type="submit" value="ODJAVI SE"/>
        </form></li>-->
        </security:authorize>
        
      </ul>
      </div>
      </header>

      <form action="/Aero/rezervacija/pronadjiLetove" method="get">
      <div class="search">
              <ul>
              <li>
		      Od:
		      <input type="text" class="polje" style="font-size:16px" name="from"  />
	          </li>
	          <li>
			  Do:
			  <input type="text" class="polje" style="font-size:16px"  name="to" />
	          </li>
	          <li>
	          	Datum polaska:
	          	<input type="date" style="font-size:16px" class="polje" name="datumPolaska">
	          </li>
              <li>
    		  <!--<input type="submit" class="submit" name="submit" value="Pretraži" style="font-size: 0.9em;font-family: 'Oswald';" />-->
    		  <button type="submit" class="searchDugme1">Pretraži</button>
    		  </li>
    		 
			  </ul>
     

    </div>
    </form>
     <c:if test="${!empty uspjehRez }">  
    <p class="porukaUspjehLet">Uspješno ste rezervisali let!</p>
    </c:if>
  
</body>
</html>
