<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<html:form action="open/plugin/custom/subscription" styleClass="pl1">
	<input type="hidden" name="action" value="order"/>
	<html:hidden property="modeId"/>
	<html:hidden property="sessionsId"/>
	<html:hidden property="processIds"/>

	${l.l('Стоимость в месяц')}:&nbsp;<b>${form.response.data.cost}</b>

	<input type="text" size="20" name="email" placeholder="E-Mail" class="ml05"/>

	<button class="btn-grey ml1" type="button" 
			onclick="$$.ajax.post(this.form)">${l.l('Заказать')}</button>
</html:form>