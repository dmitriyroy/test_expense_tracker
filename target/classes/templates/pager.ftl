<#macro pager url page type>
    <#if page.getTotalPages() gt 7>
        <#assign
            totalPages = page.getTotalPages()
            pageNumber = page.getNumber() + 1
            head = (pageNumber > 4)?then([1,-1],[1,2,3])
            tail = (pageNumber < totalPages - 3)?then([-1, totalPages],[totalPages - 2, totalPages - 1, totalPages])
            bodyBefore = (pageNumber > 4 && pageNumber < totalPages - 1)?then([pageNumber - 2, pageNumber - 1],[])
            bodyAfter = (pageNumber > 2 && pageNumber < totalPages - 3)?then([pageNumber + 1 , pageNumber + 2],[])

            body = head + bodyBefore + (pageNumber > 3 && pageNumber < totalPages - 2)?then([pageNumber],[]) + bodyAfter + tail
        >
    <#else>
        <#assign body = 1..page.getTotalPages()
        totalPages = page.getTotalPages()
        pageNumber = page.getNumber() + 1 >
    </#if>
    <#if type == 'page' || type == 'all'>
        <div>
            <nav aria-label="navigation">
                <ul class="pagination justify-content-end">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Pages</a>
                    </li>
                    <#list body as p>
                        <#if p == pageNumber >
                            <li class="page-item active">
                                <a class="page-link-darkgreen" href="${url}&page=${p-1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                            </li>
                        <#elseif p == -1>
                            <li class="page-item disabled">
                                <a class="page-link-darkgreen" href="#" tabindex="-1">...</a>
                            </li>
                        <#else>
                            <li class="page-item ">
                                <a class="page-link-darkgreen" href="${url}&page=${p-1}&size=${page.getSize()}" tabindex="-1">${p}</a>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </nav>
        </div>
    </#if>
    <#if type == 'size' || type == 'all'>
        <div>
            <nav aria-label="navigation">
                <ul class="pagination justify-content-end">
                    <li class="page-item disabled">
                        <a class="page-link" href="#" tabindex="-1">Elements on page</a>
                    </li>
                    <#if page.getTotalElements() gt 100>
                        <#assign countElementOnList = [5, 10, 20, 50, 100, 300] />
                    <#elseif page.getTotalElements() gt 50>
                        <#assign countElementOnList = [5, 10, 20, 50, 100] />
                    <#elseif page.getTotalElements() gt 20>
                        <#assign countElementOnList = [5, 10, 20, 50] />
                    <#elseif page.getTotalElements() gt 10>
                        <#assign countElementOnList = [5, 10, 20] />
                    <#elseif page.getTotalElements() gt 5>
                        <#assign countElementOnList = [5, 10] />
                    <#else>
                        <#assign countElementOnList = [5] />
                    </#if>
                    <#list countElementOnList as s>
                        <#if pageNumber * page.getSize() gt page.getTotalElements() >
                            <#assign pageNum = (page.getTotalElements() / s)?ceiling >
                        <#else>
                            <#assign pageNum = ((pageNumber * page.getSize()) / s)?ceiling >
                        </#if>
                        <li class="page-item <#if s == page.getSize()>active</#if>">
                            <a class="page-link-darkgreen" href="${url}&page=${pageNum-1}&size=${s}" tabindex="-1">${s}</a>
                        </li>
                    </#list>
                </ul>
            </nav>
        </div>
    </#if>
</#macro>