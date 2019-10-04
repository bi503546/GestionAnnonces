<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#edit-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="edit-annonce" class="content scaffold-edit" role="main">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.annonce}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.annonce}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form resource="${this.annonce}" method="post" enctype="multipart/form-data">
                <f:field bean="annonce" property="title"/>
                <f:field bean="annonce" property="description"/>
                <f:field bean="annonce" property="validTill"/>
                <f:field bean="annonce" property="author">
                    <select name="author"  id="author">
                        <g:each in="${mbds_2019_2020.User.all}" var="user">
                            <option value="${user.id}">${user.username}</option>
                        </g:each>
                    </select>
                </f:field>

                <f:field property="illustrations" bean="annonce">
                    <input multiple="multiple" type="file" itemprop="prop" name="illu" />
                    <g:each in="${annonce.illustrations}" var="illustration">
                        <div class="container">
                            <asset:image style="margin-left: 20px; width:100px; height:100px" src="${illustration.filename}"/>
                            <input value="Supprimer" class="btn" type="button" onclick="callMyAjax(${annonce.id},${illustration.id})"/>
                        </div>
                    </g:each>
                </f:field>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    <g:javascript>

  function callMyAjax(param1,param2){
    $.ajax({
       url : '${g.createLink(controller: 'annonce', action: 'deletefromillustrations')}',
       data:{
            param1:param1,
            param2:param2
            },
       success :
             window.location.reload()
    });
}
    </g:javascript>
    </body>
</html>
