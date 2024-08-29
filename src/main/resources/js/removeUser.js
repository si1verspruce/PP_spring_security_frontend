let userData;

$("#users-table").on("click", ".remove-button", (e) => {
    userData = $(e.target).closest(".user-tr").data("user");
    $("#remove-id").val(parseInt(userData["id"]));
    $("#remove-first-name").val(userData["firstName"]);
    $("#remove-last-name").val(userData["lastName"]);
    $("#remove-age").val(userData["age"]);
    $("#remove-login").val(userData["username"]);
    let removeRoleTag = $("#remove-role")
    removeRoleTag.empty();
    removeRoleTag.attr("size", userData["authorities"].length);
    $.each(userData["authorities"], (roleKey, roleValue) => {
        let roleOption = $("<option></option>");
        roleOption.text(roleValue["authority"]);
        removeRoleTag.append(roleOption);
    });
});

$("#remove-user-confirm").on("click", () => {
    $.ajax({
        type: "DELETE",
        url: `admin/users/${userData["id"]}`,
        async: false,
        headers: {'X-CSRF-TOKEN': _csrf}
    }).done(() => document.dispatchEvent(userRemoved));
})