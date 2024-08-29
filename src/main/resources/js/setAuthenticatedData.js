$(document).ready($.getJSON("/authenticated", (data) => {
    $("#authenticated-login").text(data["username"]);
    $.each(data["authorities"], (key, value) => {
        $("#authenticated-roles").append(` ${value["authority"]}`)
    });
}));