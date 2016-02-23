<html>
    <head>
        <title>See transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <div>
        <h2>Accounts</h2>
        <select>
            <#list accountsList as account>
                <option value="${account}">${account}</option>
            </#list>
        </select>
        [ errors go here ]
        <b>Person:</b> [ from Account.list() ]
        <b>[ submit ]</b>
        <div>Balance: [amount]</div>
        <div>some awesome table with transaction amounts</div>
    </div>
    </body>
</html>