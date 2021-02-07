<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<ui:combo-single hiddenName="mode">
	<jsp:attribute name="valuesHtml">
		<li value="<%=org.bgerp.plugin.custom.bitel.Calculator.MODE_RUB_TO_CARD%>">Оплата в рублях на карту</li>
		<li value="<%=org.bgerp.plugin.custom.bitel.Calculator.MODE_RUB_PROVIDE_DOCS%>">Оплата в рублях по счёту, выдача акта</li>
		<li value="<%=org.bgerp.plugin.custom.bitel.Calculator.MODE_EUR_PAYPAL%>">EUR to PayPal</li>
	</jsp:attribute>
</ui:combo-single>
