<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>



<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <title>RHYMES</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">        
   <!-- Favicon -->
    <link rel="icon" href="/img/main-img/core-img/favicon.ico">   
    
 <link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath() %>/css/store/store.css">
 
 
</head>
<body>
 
 
  <tiles:insertAttribute name="header"/> <!--  /WEB-INF/views/common/layout/header.jsp -->
	
   <div id="body_wrap">
   
	   
		 <div id="middle_wrap">
	    	<div id="sidebar_wrap"> 
		      <tiles:insertAttribute name="left_menu"/>
		    </div>   
			<div id="content_wrap"> 
			  	<div id="content_title_wrap">

				</div>
		      <tiles:insertAttribute name="body"/> <!-- body -->
	    	</div>  
	    </div>  
	    
	    <div id="footer_wrap">
	      <tiles:insertAttribute name="footer"/> <!-- /WEB-INF/views/common/layout/footer.jsp -->
	   </div>
	  
    </div>
    
    
  </body>
</html>