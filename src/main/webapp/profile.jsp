<%--
  Created by IntelliJ IDEA.
  User: valerijborodaev
  Date: 12/9/17
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>Your notes, </title>

<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="/static/stylesheets/notes.css" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" />
    <link rel="stylesheet" href="/static/stylesheets/navbar.css" />
    <link rel="stylesheet" href="/static/stylesheets/style.css" />
    <link rel="stylesheet" href="/static/stylesheets/picker.css" />
    <link rel="canonical" href="https://codepen.io/huszerldani/pen/NAJREN?depth=everything&order=popularity&page=53&q=pack&show_forks=false" />
    <script src="/static/javascripts/angular.min.js"></script>
    <script src="/static/javascripts/angular-ui-bootstrap-modal.js"></script>
    <script src="/static/javascripts/angular-pagination.js"></script>
    <script src="/static/https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="/static/javascripts/modules.js"></script>
</head>

<body ng-app='myApp' ng-controller='mainCtrl' ng-init='notes=${notes}'>
<div modal="showLoginForm">
    <jsp:include page="loginForm.jsp" />
</div>
<div modal="showContactForm">
    <%--<jsp:include page="contactUsForm.jsp" />--%>
</div>

<div class="container-fluid">
    <div class="row">
        <div id="nav">
            <button ng-click="openLoginModal()">Log in</button>
            <button ng-click="logout()">Log out</button>
            <button ng-click="openContactModal()">Contact us</button>
            <button ng-click="sortAsc()">Earliest</button>
            <button ng-click="sortDesc()">Latest</button>
        </div>
        <div class="col-lg-8 col-lg-offset-2 col-md-10 col-md-offset-1">
            <div id="wrap">
                <form action="" autocomplete="on">
                    <input ng-model='search' id="search" name="search" type="text" placeholder="Заголовок, дата или часть текста" autofocus>
                    <input id="search_submit" value="Rechercher">
                </form>
            </div>
        </div>
    </div>
    <div class="row" style="margin-bottom: 25px;"></div>

    <div class="row">

        <button ng-click="showForm()" type="button" class="btn btn-add" style="outline: none;">+</button>

        <div class="col-md-4 form" ng-show="isFormShow">
            <form method="post" ng-submit='create()'>
                <textarea class="mainTextAreas" ng-model="title" placeholder="Заголовок" rows="1" cols="30"></textarea>
                <textarea class="mainTextAreas" ng-model="content" placeholder="Текст" rows="9" cols="30"></textarea>
                <input ng-model="color" name="color" type="hidden" class="hid" value="#23c6c8">
                <button ng-click="showPicker()" type="button" class="btn btn-primary btns">Цвет</button>
                <button type="submit" class="btn btn-primary btns" name="save">Сохранить</button>
            </form>
        </div>

        <div ng-show="isPickerShow" id="picker" class="color_picker_dialog" draggable="false">
            <div class="hue_bar">
                <div class="hue_picker"></div>
            </div>
            <div class="sat_rect" draggable="false">
                <div class="white"></div>
                <div class="black"></div>
                <div class="sat_picker"></div>
            </div>
            <div class="bottom">
                <div class="color_preview"></div>
            </div>
        </div>

        <ul class="notes">
            <li dir-paginate='note in notes | filter : search | orderBy : "date" : toggle | itemsPerPage : 10'>
                <div class="rotate-1" style="background-color:{{note.color}}">
                    <a ng-click='remove(note.id)' class="text-danger pull-left"><i class="fa fa-trash-o "></i></a>
                    <small>{{note.date}}</small>
                    <h4>{{note.title}}</h4>
                    <p>{{note.content}}</p>
                </div>
            </li>
        </ul>
    </div>
    <div class="row" id="footer">
        <dir-pagination-controls max-size="5" direction-links="true" boundary-links="true"></dir-pagination-controls>
    </div>
</div>
</body>
<script src="static/javascripts/picker.js"></script>
</html>