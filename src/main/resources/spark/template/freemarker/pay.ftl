<html>
<head>
    <title>Pay Some Person</title>
    <meta name="layout" content="main" />
</head>
<body>
<div>
    <h2>Accounts</h2>
    <form action="/pay" method="post">
        <b>From: </b>
        <select id="accFromDropDown" name="accFromDropDown">
            <#list accountsList as account>
                <option value="${account}">${account}</option>
            </#list>
        </select>
        <br>

        <b>To: </b>
        <select id="accToDropDown" name="accToDropDown">
            <#list accountsList as account>
                <option value="${account}">${account}</option>
            </#list>
        </select>
        <br>

        <dl>
            <dt>Amount:
            <dd><input type="amount" name="amount" size="10">
        </dl>

        <div class="actions"><input type="submit" value="Submit"></div>

        <h3><#if accountTransfers.message??>${accountTransfers.message}<#else>-</#if></h3>
    </form>
    <br>

</div>
</body>
</html>