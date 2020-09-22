<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
 <head>
 	<meta charset="UTF-8">
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
            <li><a href="/Aero/zaposleni/unesiLet.jsp">Unesite novi let</a></li>
            
        </ul>
      </div>

      <form action="/Aero/radnik/sacuvajLiniju" method="post">
          <div class="unosLeta">
            <p>
              <label for="od">Od </label>
              <input type="text" name="od" id="od" required>
            </p>
            <p>
              <label for="do1">Do </label>
              <input type="text" name="do1" id="do1" required>
            </p>
            <p>
              <label for="poAer">Polazni aerodrom </label>
              <select name="poAer" id="poAer">
              <c:forEach items="${aerodromi }" var="a">
              	<option value="${a.kod }">${a.kod }</option>
              	
              </c:forEach>
                
              </select>
            </p>

            <p>
              <label for="doAer">Dolazni aerodrom </label>
               <select name="doAer" id="poAer">
               <c:forEach items="${aerodromi }" var="a">
                	<option value="${a.kod }">${a.kod }</option>
               </c:forEach>
              </select>
            </p>

            <p>
              <label for="poVrijeme">Polazak</label>
              <input type="time" name="poVrijeme" id="poVrijeme" class="vrijeme" required>
            </p>

            <p>
              <label for="doVrijeme">Dolazak</label>
              <input type="time" name="doVrijeme" id="doVrijeme" required>
            </p>

            <p>
              <label for="cijenaE">Cijena ekonomske karte</label>
              <input type="number" name="cijenaE" id="cijenaE" required>
            </p>

            <p>
              <label for="cijenaB">Cijena biznis karte</label>
              <input type="number" name="cijenaB" id="cijenaB" required>
            </p>
            
            <input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />

            
          <button type="submit" class="unesiLetDugme">Unesi liniju</button>
          

          
        </div>

      </form>


    </body>
    </html>
