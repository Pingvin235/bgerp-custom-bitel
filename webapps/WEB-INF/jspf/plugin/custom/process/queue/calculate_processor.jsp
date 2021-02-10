<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<ui:combo-single hiddenName="mode" widthTextValue="200px"
	list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Calculator.PARAM_MODE_ID)%>"/>

<ui:combo-single 
	hiddenName="sessions" prefixText="${l.l('Сессий')}:" 
	list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Calculator.PARAM_SESSIONS_ID)%>"/>

<%-- licences --%>
