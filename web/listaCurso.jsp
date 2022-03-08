<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cursos</title>
    </head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Curso ID</th>
                <th>Curso</th>
                <th>Duração (Meses)</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${cursos}" var="curso">
                <tr>
                    <td><c:out value="${curso.id}" /></td>
                    <td><c:out value="${curso.curso}" /></td>
                    <td><c:out value="${curso.duracao}" /></td>
                    <td><a href="CursoController?action=edit&cursoId=<c:out value="${curso.id}"/>">Update</a></td>
                    <td><a href="CursoController?action=delete&cursoId=<c:out value="${curso.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="CursoController?action=insert">Adicionar Curso</a></p>
</body>
</html>
