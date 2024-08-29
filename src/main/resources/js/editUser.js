import {serializeUser} from "./userModule.js";

let userData;

$("#users-table").on("click", ".edit-button", (e) => {
    userData = $(e.target).closest(".user-tr").data("user");
    $("#edit-id").val(parseInt(userData["id"]));
    $("#edit-first-name").val(userData["firstName"]);
    $("#edit-last-name").val(userData["lastName"]);
    $("#edit-age").val(userData["age"]);
    $("#edit-login").val(userData["username"]);
});

$("#edit-user-confirm").on("click", () => {
    let editUserForm = $("#edit-user-form");
    let user = serializeUser(editUserForm);
    $.ajax({
        type: "PUT",
        url: `admin/users/${userData["id"]}`,
        data: JSON.stringify(user),
        async: false,
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        headers: {'X-CSRF-TOKEN': _csrf}
    }).done(() => document.dispatchEvent(userEdited));
    editUserForm[0].reset();
});