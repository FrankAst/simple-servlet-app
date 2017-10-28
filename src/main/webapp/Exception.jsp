
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Oooops...</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

</head>
<style>
    .error {
        margin: 0 auto;
        text-align: center;
    }

    .error-code {
        bottom: 60%;
        color: #2d353c;
        font-size: 96px;
        line-height: 100px;
    }

    .error-desc {
        font-size: 12px;
        color: #647788;
    }

    .m-b-10 {
        margin-bottom: 10px!important;
    }

    .m-b-20 {
        margin-bottom: 20px!important;
    }

    .m-t-20 {
        margin-top: 20px!important;
    }

</style>
<body>

<div class="error">
    <div class="error-code m-b-10 m-t-20">${status}<i class="fa fa-warning"></i></div>
    <h3 class="font-bold">Error details</h3>

    <div id="info"  class="error-desc">
        <h4 id="uri"><b>Url:</b> ${uri}</h4>
        <h4 id="servlet"><b>Called servlet:</b> ${servlet}</h4>
        <h4 id="exception"><b>Exception:</b> ${exception}</h4>
        <div>
            <a class=" login-detail-panel-button btn" href="http://localhost:8080/welcome">
                <i class="fa fa-arrow-left"></i>
                Go back to Homepage
            </a>
        </div>
    </div>
</div>

</body>


</html>