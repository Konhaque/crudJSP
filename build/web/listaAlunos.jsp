<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alunos</title>
    </head>
<body>
    <table border=1>
        <thead>
            <tr>
                <th>Aluno ID</th>
                <th>Nome</th>
                <th>Data Nascimento</th>
                <th>CPF</th>
                <th>Curso</th>
                <th colspan=2>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${alunos}" var="aluno">
                <tr>
                    <td><c:out value="${aluno.id}" /></td>
                    <td><c:out value="${aluno.nome}" /></td>
                    <td><c:out value="${aluno.dataNascimento}" /></td>
                    <td><c:out value="${aluno.cpf}" /></td>
                    <td><c:out value="${aluno.idCurso}" /></td>
                    <td><a href="AlunoController?action=edit&alunoId=<c:out value="${aluno.id}"/>">Update</a></td>
                    <td><a href="AlunoController?action=delete&alunoId=<c:out value="${aluno.id}"/>">Delete</a></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <p><a href="AlunoController?action=insert">Adicionar Curso</a></p>
</body>
</html>
