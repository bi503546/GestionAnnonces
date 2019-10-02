<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#create-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="create-annonce" class="content scaffold-create" role="main">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
            
%{--            ancien formaulaire sans les proprietes iluustration--}%
            
            <g:form resource="${this.annonce}" method="POST">
                    <f:field bean="annonce" property="title"/>
                    <f:field bean="annonce" property="description"/>
                    <f:field bean="annonce" property="validTill"/>
                    <f:field bean="annonce" property="author"/>
                   <f:field bean="annonce" property="illustrations">
                       <g:uploadForm name="myUpload" action="save" method="POST">
                           <input type="file" name="illustration" />
                           <g:submitButton name="create" value="create" />
                       </g:uploadForm>
                   </f:field>
%{--                    <f:all bean="annonce"/>--}%
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
            
            
            
%{--            <form action="save" method="post" >--}%
%{--                <fieldset class="form">--}%
%{--                    <div class='fieldcontain'>--}%
%{--                        <label for='title'>Title<span class='required-indicator'>*</span></label>--}%
%{--                        <input type="text" name="Title" value="" required="" id="title" />--}%
%{--                    </div>--}%
%{--                    <div class='fieldcontain'>--}%
%{--                        <label for='description'>Description<span class='required-indicator'>*</span></label>--}%
%{--                        <input type="text" name="Description" value="" required="" id="description" />--}%
%{--                    </div>--}%
%{--                    <div class='fieldcontain'>--}%
%{--                        <label for='author'>Author</label>--}%
%{--                        <input type="text" name="Author" value="" id="author" />--}%
%{--                    </div>--}%
%{--                </fieldset>--}%
%{--                <fieldset class="buttons">--}%
%{--                    <input type="submit" name="create" class="save" value="Create" id="create" />--}%
%{--                </fieldset>--}%
%{--            </form>--}%

        </div>
    </body>
</html>
