/*!
* Start Bootstrap - Shop Homepage v5.0.5 (https://startbootstrap.com/template/shop-homepage)
* Copyright 2013-2022 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-shop-homepage/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project

function deleteComment(id) {
    let url = "/comment/" + id
    $.ajax({
        url: url,
        type: 'DELETE',
        success: function (){
            //добавить функцию для загрузки комментариев
        }
    })
}