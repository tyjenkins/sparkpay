<html>
    <head>
        <title>See transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <div>
        <h2>Accounts</h2>
        <br/>
        <#list accountsList as account>
            <div class="account">
                <h2>${account}</h2>
            </div>
        </#list>
        [ errors go here ]
        <b>Person:</b> [ from Account.list() ]
        <b>[ submit ]</b>
        <div>Balance: [amount]</div>
        <div>some awesome table with transaction amounts</div>
    </div>
    </body>
</html>