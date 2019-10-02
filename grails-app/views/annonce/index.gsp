<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'annonce.label', default: 'Annonce')}" />
        <asset:javascript src="uploadr.manifest.js"/>
        <asset:stylesheet href="uploadr.manifest.css"/>
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-annonce" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-annonce" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
%{--            ancienne table d'ffichage--}%
%{--            <f:table collection="${annonceList}" />--}%
            <table>
                <thead>
                <tr>
                    <th class="sortable"><a href="/annonce/index?sort=username&amp;max=10&amp;order=asc">Title</a> </th>
                    <th class="sortable"><a href="/annonce/index?sort=thumbnail&amp;max=10&amp;order=asc">Description</a> </th>
                    <th class="sortable"><a href="/annonce/index?sort=annonces&amp;max=10&amp;order=asc">Valid Till</a> </th>
                    <th class="sortable"><a href="/annonce/index?sort=annonces&amp;max=10&amp;order=asc">Illustrations</a> </th>
                    <th class="sortable"><a href="/annonce/index?sort=annonces&amp;max=10&amp;order=asc">State</a> </th>
                    <th class="sortable"><a href="/annonce/index?sort=annonces&amp;max=10&amp;order=asc">Author</a> </th>

                </tr>
                </thead>
                <g:each in="${annonceList}" var ="annonce">
                    <tr>
                        <td>
                            <g:link controller="annonce" action="show" id="${annonce.id}">${annonce.title}</g:link>
                        </td>
                        <td>
                            <a>${annonce.description}</a>
                        </td>
                        <td>
                            <a>${annonce.validTill}</a>
                        </td>
                        <td>
                            <ul>
                                <g:each in="${annonce.illustrations}" var="illustration">
                                    <li>
                                        <asset:image style="width: 50px;height: 50px" src="${illustration.filename}"/>
                                    </li>
                                </g:each>
                            </ul>
                        </td>
                        <td>
                                <a>${annonce.state}</a>
                        </td>
                        <td>
                                <g:link controller="annonce" action="show" id="${annonce.id}">${annonce.author.username}</g:link>
                        </td>

                    </tr>
                </g:each>
            </table>
            <div class="pagination">
                <g:paginate total="${annonceCount ?: 0}" />
            </div>
        </div>
    </body>
</html>