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
            <select id="accDropDown" name="accDropDown">
                <#list accountsList as account>
                    <option value="${account}">${account}</option>
                </#list>
            </select>
            <br>

            <div class="actions"><input type="submit" value="Submit"></div>
        </form>
        <br>
        <div>Balance: £ ${account.balance} for account ${account.name}</div>

        <ul class="transactions">
            <#if transactionList??>
                <#list transactionList as transaction>
                    <li>${transaction.fromAcc} ${transaction.toAcc} ${transaction.amount}
                        <#else>
                    <li>There're no transactions.
                </#list>
                <#else>
                    <li>There're no transactions
            </#if>
        </ul>
    </div>
    </body>
</html>