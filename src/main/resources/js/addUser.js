import {serializeUser} from "./userModule.js";

$(document).ready(() => {
    let addUserForm = $("#add-user-form");
    addUserForm.on("submit", () => {
        let user = serializeUser(addUserForm);
        $.ajax({
            type: "PUT",
            url: "admin/add",
            data: JSON.stringify(user),
            async: false,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            headers: {'X-CSRF-TOKEN': _csrf}
        }).done(() => document.dispatchEvent(userAdded));
        addUserForm[0].reset();
    });
});