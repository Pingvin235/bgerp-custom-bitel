<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<form action="/open/plugin/custom/calculate.do">
	<input type="hidden" name="processIds"/>

	<ui:combo-single hiddenName="modeId" widthTextValue="200px"
		list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Calculator.PARAM_MODE_ID)%>"/>

	<ui:combo-single 
		hiddenName="sessionsId" prefixText="${l.l('Сессий')}:" styleClass="ml05"
		list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Calculator.PARAM_SESSIONS_ID)%>"/>

	<button class="btn-grey ml1" type="button" onclick="
		const processIds = getCheckedProcessIds();
		if (!processIds) {
			alert('${l.l('Выберите плагины!')}');
			return;
		}
		//const url = '/open/plugin/custom/calculate.do?p'
	">${l.l('Посчитать')}</button>
</form>
