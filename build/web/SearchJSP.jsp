<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.action.Search"%>
<%
    Search db = new Search();

    String query = request.getParameter("q");

    List<String> records = db.getData(query);

    Iterator<String> iterator = records.iterator();
    while (iterator.hasNext()) {
        String record = (String) iterator.next();
        out.println(record);
    }
%>
