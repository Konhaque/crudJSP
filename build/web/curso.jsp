<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link type="text/css"
    href="css/ui-lightness/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
<title>Novo Curso</title>
</head>
<body>
    <form method="POST" action='CursoController' name="frmAddUser">
        Curso : <input
            type="text" name="curso"
            value="<c:out value="${curso.curso}" />" /> <br /> 
        Duração : <input
            type="text" name="lastName"
            value="<c:out value="${curso.duracao}" />" /> <br /> 
       <input
            type="submit" value="Submit" />
    </form>
</body>
</html>