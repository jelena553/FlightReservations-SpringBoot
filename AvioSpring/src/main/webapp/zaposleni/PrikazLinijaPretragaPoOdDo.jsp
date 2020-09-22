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

      <div class="izborUnosLeta">
        <ul>
            <li><a href="/Aero/radnik/ucitajAerodrome">Unesite novu liniju</a></li>
            <li><a href="/Aero/radnik/ucitajAvione">Unesite novi let</a></li>
            
        </ul>
      </div>
     
      <c:if test="${empty linije }">
      <p class="porukaNemaTakvihLinija">Nema takvih linija!</p>
      </c:if>
      
      
      
      <c:if test="${!empty linije }">
      
      
      
      
       <div class="tabl">
        <form action="/Aero/radnik/preusmjeri" method="get">
       
       

            <table class="tabela4" cellspacing="0">
                 <tr>
                     <th>POLAZISTE</th><th>DESTINACIJA</th><th>VRIJEME POLASKA</th><th>VRIJEME DOLASKA</th><th></th>


                 </tr>
                 <c:forEach items="${linije }" var="l">
                 <tr>
                 <td>${l.polaziste}</td> <td>${l.odrediste}</td> <td><fmt:formatDate pattern="HH:mm" value="${l.vrijemePolaska}"/></td> <td><fmt:formatDate pattern="HH:mm" value="${l.vrijemeDolaska}"/></td><td style="background-color: #ccc;"class="dugme3"><button type="submit" class="unesiLetDugme3" name="idLinija" value="${l.idLinija}">Unesite let</button>
                 </td>
                 </tr>
                 </c:forEach>

                 
          
                </table>
        </form>
         
   </div>
   
   </c:if>



     
	</body>
</html>
