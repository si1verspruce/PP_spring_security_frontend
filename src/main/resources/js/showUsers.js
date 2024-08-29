$(document).ready(() => {
    showUsersData();
    $("#admin-page").on("click", showUsersData);
});

document.addEventListener("userAdded", showUsersData);
document.addEventListener("userRemoved", showUsersData);
document.addEventListener("userEdited", showUsersData);

function showUsersData() {
    $.getJSON("/admin/users", (data) => {
        let usersBody = $("#users-body");
        usersBody.empty();
        $.each(data, (key, value) => {
            let usersTr = fillUserData(usersBody, value);
            addEditButton(usersTr, value);
            addDeleteButton(usersTr, value);
        });
    });
}

function fillUserData(tagContainer, userData) {
    let usersTr = $("<tr></tr>").attr({
        "class": "user-tr",
        "data-user": JSON.stringify(userData)
    });
    tagContainer.append(usersTr, userData);
    usersTr.append($("<td></td>").text(userData["id"]));
    usersTr.append($("<td></td>").text(userData["firstName"]));
    usersTr.append($("<td></td>").text(userData["lastName"]));
    usersTr.append($("<td></td>").text(userData["age"]));
    usersTr.append($("<td></td>").text(userData["username"]));
    let rolesTd = $("<td></td>");
    usersTr.append(rolesTd);
    $.each(userData["authorities"], (key, value) => {
        $(rolesTd.append(` ${value["authority"]}`))
    });
    return usersTr;
}

function addEditButton(tagContainer) {
    tagContainer.append($("<td></td>").append($("<button></button>").text("Edit").attr({
        "class": "edit-button btn btn-primary",
        "data-bs-toggle": "modal",
        "data-bs-target": "#edit-user-modal"
    })));
}

function addDeleteButton(tagContainer) {
    tagContainer.append($("<td></td>").append($("<button></button>").text("Delete").attr({
        "class": "remove-button btn btn-danger",
        "data-bs-toggle": "modal",
        "data-bs-target": "#remove-user-modal"
    })));
}