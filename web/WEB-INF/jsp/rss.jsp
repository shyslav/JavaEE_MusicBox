<?xml version="1.0"?>
<rss version="2.0">
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%response.setContentType("application/xml");%>
  <channel>
    <title>My xml</title>
    <link>http://localhost:8080/musicbox</link>
    <description>Rss</description>
    <language>en-us</language>
    <pubDate>Tue, 10 Jun 2015 04:00:00 GMT</pubDate>
    <c:forEach items="${rss}" var="item">
        <item>
      <title>${item.name}</title>
      <link>/musicbox/news/view/${item.id}.htm</link>
      <description>${item.small_text}</description>
      <pubDate>${item.date_create}</pubDate>
      <guid>/musicbox/news/view/${item.id}.xml</guid>
    </item> 
    </c:forEach>
  </channel>
</rss>