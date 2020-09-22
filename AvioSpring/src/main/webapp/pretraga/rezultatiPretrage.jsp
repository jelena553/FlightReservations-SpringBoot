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
		      <input type="text" class="polje" style="font-size:16px" name="from" value="" />
	          </li>
	          <li>
			  Do:
			  <input type="text" class="polje" style="font-size:16px"  name="to" value="" />
	          </li>
	          <li>
	          	Datum polaska:
	          	<input type="date" style="font-size:16px" class="polje" name="datumPolaska">
	          </li>
              <li>
    		  <!--<input type="submit" class="submit" name="submit" value="Pretrazi" style="font-size: 0.9em;font-family: 'Oswald';" />-->
    		  <button type="submit" class="searchDugme1">Pretraži</button>
    		  </li> 
    		 
			  </ul>
			  
			  </div>
			
			 </form>
     
     
     <c:choose >
     	<c:when test="${greskaDatumKorisnik }">
     	<p class="datumKorisnikGreska">Ne možete unijeti datum u prošlosti!
     	</p>
     	</c:when>
     	<c:when test="${empty letovi }">
     		<p class="prazniLetoviPoruka">
     		Ne postoje letovi sa traženim parametrima!
     		</p>
     	</c:when>
     
     <c:otherwise>

    
    
       
       
       
       <div class="tekst">
          <p>Dostupni letovi za datum  <fmt:formatDate pattern="dd.MM.yyyy" value="${datum }"/> na relaciji  ${from } --> ${to }</p>
        </div>
     

    
        
       
       <div class="lista">
       <ul class=>

          <li>   <table class="tabela" cellspacing="0">
                 <tr>
                     <th>VRIJEME POLASKA</th><th>VRIJEME DOLASKA</th><th>ECONOMY</th><th>BUSINESS</th><th></th>
                 </tr>
                 
                 <c:forEach items="${letovi }" var="l">
                 

                 <tr>
                 <form action="/Aero/rezervacija/detaljiRezervacije" method="get">
                     <td><fmt:formatDate pattern="HH:mm" value="${l.linija.vrijemePolaska }"/>(${l.linija.aerodrom1.kod}) </td> <td><fmt:formatDate pattern="HH:mm" value="${l.linija.vrijemeDolaska }"/>(${l.linija.aerodrom2.kod })</td>
                    <td>
                    	<c:choose>
                        <c:when test="${l.avion.brSjedistaEconomy-l.popunjenostEconomy==0 }">
                           rasprodato
                        </c:when>
                        <c:otherwise>
                          ${l.linija.cijenaEconomy }E<input type="radio" name="klase" value="ec" required>
                        </c:otherwise>
                        </c:choose>
                    </td>
                    
                    <td> <c:choose>
                       <c:when test="${l.avion.brSjedistaBusiness-l.popunjenostBusiness==0 }">
                       rasprodato
                       </c:when>
                        <c:otherwise>
                        ${l.linija.cijenaBusiness }E<input type="radio" name="klase" value="bus" required>
                        </c:otherwise>
                        </c:choose>
                        <input type="hidden" name="idLet" value="${l.idLet }">
                     </td> 
                    <td><button type="submit" class="rezer">Rezerviši</button></td>
                   </form>   
                 </tr>
                
                 
                 </c:forEach>

               
              </table>
     </ul>
   </div>
   </c:otherwise>
   </c:choose>
  
  
  
</body>
</html>
