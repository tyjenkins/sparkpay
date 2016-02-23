<html>
    <head>
        <title>See transactions</title>
        <meta name="layout" content="main" />
    </head>
    <body>
    <div>
        <h2>Accounts</h2>
        <form action="/transactions" method="post">
            <b>Person:</b>
            <select>
                <#list accountsList as account>
                    <option value="${account}">${account}</option>
                </#list>
            </select>
            <br>

            <div class="actions"><input type="submit" value="Submit"></div>
        </form>
        <br>
        <div>Balance: £ ${account.balance}</div>
        <div>some awesome table with transaction amounts</div>
    </div>
    </body>
</html>