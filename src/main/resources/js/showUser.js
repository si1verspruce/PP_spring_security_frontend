$(document).ready(() => {
    showUserData();
    $("#user-page").on("click", showUserData);
});

function showUserData() {
    $.getJSON("/authenticated", (data) => {
        let usersTr = $("<tr></tr>");
        $("#authenticated-info-body").empty().append(usersTr, data);
        usersTr.append($("<td></td>").text(data["id"]));
        usersTr.append($("<td></td>").text(data["firstName"]));
        usersTr.append($("<td></td>").text(data["lastName"]));
        usersTr.append($("<td></td>").text(data["age"]));
        usersTr.append($("<td></td>").text(data["username"]));
        let rolesTd = $("<td></td>");
        usersTr.append(rolesTd);
        $.each(data["authorities"], (key, value) => {
            let authority = value["authority"]
            $(rolesTd.append(` ${authority}`));
            if (authority.toLowerCase() === "admin") {
                $("#admin-page-nav").show();
            }
        });
    });
}