<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<ui:combo-single hiddenName="mode" widthTextValue="200px">
	<jsp:attribute name="valuesHtml">
		<li value="<%=org.bgerp.plugin.custom.Calculator.MODE_RUB_TO_CARD%>">Оплата в рублях на карту</li>
		<li value="<%=org.bgerp.plugin.custom.Calculator.MODE_RUB_PROVIDE_DOCS%>">Оплата в рублях по счёту, выдача акта</li>
		<li value="<%=org.bgerp.plugin.custom.Calculator.MODE_EUR_PAYPAL%>">EUR to PayPal</li>
	</jsp:attribute>
</ui:combo-single>

<c:set var="PARAM_ID"><%=org.bgerp.plugin.custom.Caclulator.PARAM_SESSIONS_ID%></c:set>
<ui:combo-single hiddenName="limit" prefixText="${l.l('Сессий')}:" list="${}"/>

<%-- licences --%>
