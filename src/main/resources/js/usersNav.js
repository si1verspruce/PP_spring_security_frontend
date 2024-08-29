import {switchPanel} from "./userModule.js";

$(document).ready(() => {
    $("#add-user-page").on("click", () => {
        switchPanel("#users-table", "#add-user-form", "#show-users-page", "#add-user-page");
    });
    $("#show-users-page").on("click", () => {
        showUsersPage();
    });
});

document.addEventListener("userAdded", showUsersPage);

function showUsersPage() {
    switchPanel("#add-user-form", "#users-table", "#add-user-page", "#show-users-page");
}