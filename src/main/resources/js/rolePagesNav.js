import {switchPanel} from "./userModule.js";

$(document).ready(() => {
    $("#admin-page").on("click", () => {
        switchPanel("#user-main", "#admin-main", "#user-page", "#admin-page");
    });
    $("#user-page").on("click", () => {
        switchPanel("#admin-main", "#user-main", "#admin-page", "#user-page");
    });
});