<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jspf/taglibs.jsp"%>

<c:set var="resultUiid" value="${u:uiid()}"/>

<form action="/open/plugin/custom/subscription.do">
	<input type="hidden" name="action" value="calc"/>

	<ui:combo-single hiddenName="modeId" widthTextValue="200px"
		list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Subscription.PARAM_MODE_ID)%>"/>

	<ui:combo-single 
		hiddenName="sessionsId" prefixText="${l.l('Сессий')}:" styleClass="ml05"
		list="<%=ru.bgcrm.cache.ParameterCache.getListParamValues(org.bgerp.plugin.custom.Subscription.PARAM_SESSIONS_ID)%>"/>

	<button class="btn-grey ml1" type="button" onclick="
		const processIds = getCheckedProcessIds();
		if (!processIds) {
			alert('${l.l('Выберите плагины!')}');
			return;
		}
		$$.ajax.load($$.ajax.formUrl(this.form) + '&processIds=' + processIds, $('#${resultUiid}'));
	">${l.l('Посчитать')}</button>
</form>
<div id="${resultUiid}" class="in-inline-block">
	<%-- calculation result will be put here --%>
</div>